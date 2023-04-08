package com.example.vukitapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.vukitapp.R
import com.example.vukitapp.core.MyApplication
import com.example.vukitapp.databinding.FragmentMainBinding
import com.example.vukitapp.domain.usecase.GetAuthUseCase
import com.example.vukitapp.presentation.viewmodel.AuthViewModel
import javax.inject.Inject

/**
 * @author Axel Sanchez
 */
class MainFragment : Fragment() {
    @Inject
    lateinit var getAuthUseCase: GetAuthUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as MyApplication).component.inject(this)
    }

    private val viewModel: AuthViewModel by viewModels(
        factoryProducer = { AuthViewModel.AuthViewModelFactory(getAuthUseCase) }
    )

    private var fragmentMainBinding: FragmentMainBinding? = null
    private val binding get() = fragmentMainBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentMainBinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            btnGetProducts.setOnClickListener {
                viewModel.getAuth(etHostName.text.toString(), etLicense.text.toString())
            }
            etLicense.doOnTextChanged { text, _, _, _ ->
                btnGetProducts.isEnabled = text?.toString().equals("") != true
            }
        }

        viewModel.getAuthLiveData().observe(viewLifecycleOwner) {
            navigateToProductList()
        }
    }

    private fun navigateToProductList() {
        findNavController().navigate(R.id.action_menuFragment_to_productsFragment, null)
    }
}