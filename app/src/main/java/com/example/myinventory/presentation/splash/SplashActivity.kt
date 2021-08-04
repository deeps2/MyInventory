package com.example.myinventory.presentation.splash

import android.os.Bundle
import com.example.domain.repository.NewsRepository
import com.example.myinventory.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    @Inject lateinit var repo: NewsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //this is just for testing and debugging the flow of coroutine and repo implementation
        //this will actually go from the view model
        //written here to test the repository implementation
        //repository -> RemoteApi -> SafeApiRequest
        runBlocking {
            launch {
                repo.getNews()
            }
        }

        //just for testing added some expression to put breakpoints
        var x = 0
        x = x + 1
        x = x + 2
    }

}