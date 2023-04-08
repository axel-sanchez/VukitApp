package com.example.vukitapp.presentation.viewmodel

import androidx.lifecycle.*
import com.example.vukitapp.data.model.MyResponse
import com.example.vukitapp.domain.usecase.GetAuthUseCase
import com.example.vukitapp.helpers.Constants
import com.example.vukitapp.helpers.Either
import kotlinx.coroutines.launch

/**
 * @author Axel Sanchez
 */
class AuthViewModel(private val getAuthUseCase: GetAuthUseCase): ViewModel() {

    private val listData: MutableLiveData<Either<Constants.ApiError, MyResponse?>> = MutableLiveData()

    private fun setListData(result: Either<Constants.ApiError, MyResponse?>) {
        listData.postValue(result)
    }

    fun getAuth(hostname: String, serial: String) {
        viewModelScope.launch {
            setListData(getAuthUseCase.call(hostname, serial))
        }
    }

    fun getAuthLiveData(): LiveData<Either<Constants.ApiError, MyResponse?>> {
        return listData
    }

    class AuthViewModelFactory(private val getAuthUseCase: GetAuthUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(GetAuthUseCase::class.java).newInstance(getAuthUseCase)
        }
    }
}