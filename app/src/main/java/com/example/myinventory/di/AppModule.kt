package com.example.myinventory.di

import android.content.Context
import android.content.SharedPreferences
import com.example.data.api.NewsApiWebService
import com.example.data.local.sharedpref.SharedPreferenceManager
import com.example.myinventory.presentation.utils.AppConstants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(AppConstants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    @Singleton
    @Provides
    fun provideNewsWebService(retrofit: Retrofit) = retrofit.create(NewsApiWebService::class.java)

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context) = context.getSharedPreferences(AppConstants.SHARED_PREF_FILE, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideSessionManager(preferences: SharedPreferences) = SharedPreferenceManager(preferences)

}