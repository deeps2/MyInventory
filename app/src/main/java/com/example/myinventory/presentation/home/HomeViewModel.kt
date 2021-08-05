package com.example.myinventory.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.model.NewsDomain
import com.example.domain.model.Resource
import com.example.domain.usecase.GetNewsUseCase
import com.example.myinventory.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

///@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : BaseViewModel() {

    private val _newsList = MutableLiveData<Resource<List<NewsDomain>>>()

    val newsList: LiveData<Resource<List<NewsDomain>>>
        get() = _newsList

    fun fetchData() {

        viewModelScope.launch(Dispatchers.IO) {
            _newsList.postValue(Resource.Loading())

            val resource = getNewsUseCase.fetchNews()
            _newsList.postValue(resource)
        }
    }
}