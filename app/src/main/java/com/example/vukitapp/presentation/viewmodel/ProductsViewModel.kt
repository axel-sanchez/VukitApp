package com.example.vukitapp.presentation.viewmodel

import androidx.lifecycle.*
import com.example.vukitapp.data.model.MyResponse2
import com.example.vukitapp.domain.usecase.GetProductsUseCase
import com.example.vukitapp.helpers.Constants
import com.example.vukitapp.helpers.Either
import kotlinx.coroutines.launch

/**
 * @author Axel Sanchez
 */
class ProductsViewModel(private val getProductsUseCase: GetProductsUseCase): ViewModel() {
    private val listData: MutableLiveData<Either<Constants.ApiError, MyResponse2?>> by lazy {
        MutableLiveData<Either<Constants.ApiError, MyResponse2?>>().also {
            getProducts()
        }
    }

    private fun setListData(result: Either<Constants.ApiError, MyResponse2?>) {
        listData.postValue(result)
    }

    private fun getProducts() {
        viewModelScope.launch {
            setListData(getProductsUseCase.call())
        }
    }

    fun getProductsLiveData(): LiveData<Either<Constants.ApiError, MyResponse2?>> {
        return listData
    }

    class ProductsViewModelFactory(private val getProductsUseCase: GetProductsUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(GetProductsUseCase::class.java).newInstance(getProductsUseCase)
        }
    }
}