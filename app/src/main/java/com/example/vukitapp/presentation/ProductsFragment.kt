package com.example.vukitapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vukitapp.R
import com.example.vukitapp.core.MyApplication
import com.example.vukitapp.data.model.Category
import com.example.vukitapp.data.model.MyResponse2
import com.example.vukitapp.databinding.FragmentProductsBinding
import com.example.vukitapp.domain.usecase.GetProductsUseCase
import com.example.vukitapp.helpers.Constants
import com.example.vukitapp.helpers.Either
import com.example.vukitapp.helpers.hide
import com.example.vukitapp.helpers.show
import com.example.vukitapp.presentation.adapter.CategoriesAdapter
import com.example.vukitapp.presentation.viewmodel.ProductsViewModel
import javax.inject.Inject

class ProductsFragment : Fragment() {
    @Inject
    lateinit var getProductsUseCase: GetProductsUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as MyApplication).component.inject(this)
    }

    private val viewModel: ProductsViewModel by viewModels(
        factoryProducer = { ProductsViewModel.ProductsViewModelFactory(getProductsUseCase) }
    )

    private var fragmentProductsBinding: FragmentProductsBinding? = null
    private val binding get() = fragmentProductsBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentProductsBinding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentProductsBinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getProductsLiveData().observe(viewLifecycleOwner) {
            updateView(it)
        }
    }

    private fun updateView(response: Either<Constants.ApiError, MyResponse2?>) {
        with(binding) {
            response.fold(
                left = {
                    cvEmptyState.show()
                    tvErrorText.text = getString(R.string.error_api_products)
                    rvCategories.hide()
                }, right = {
                    (response as Either.Right).r?.data?.categories?.let { categories ->
                        if (categories.isEmpty()) {
                            rvCategories.hide()
                            tvErrorText.text = getString(R.string.there_is_not_products)
                            cvEmptyState.show()
                        } else {
                            rvCategories.show()
                            setAdapterCategories(categories)
                        }
                    }?: kotlin.run {
                        rvCategories.hide()
                        tvErrorText.text = getString(R.string.there_is_not_products)
                        cvEmptyState.show()
                    }
                }
            )
            cpiLoading.hide()
        }
    }

    private fun setAdapterCategories(categories: List<Category>) {
        Log.i("fragment", categories.toString())
        val recipeAdapter = CategoriesAdapter(categories)
        with(binding.rvCategories) {
            layoutManager = LinearLayoutManager(context)
            adapter = recipeAdapter
        }
    }
}