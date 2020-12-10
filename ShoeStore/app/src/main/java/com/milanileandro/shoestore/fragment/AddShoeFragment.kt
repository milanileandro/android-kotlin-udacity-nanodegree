package com.milanileandro.shoestore.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.milanileandro.shoestore.R
import com.milanileandro.shoestore.databinding.FragmentAddShoeBinding
import com.milanileandro.shoestore.model.Shoe
import com.milanileandro.shoestore.viewmodel.ShoesViewModel

class AddShoeFragment : Fragment() {

    private lateinit var binding: FragmentAddShoeBinding
    private lateinit var shoesViewModel: ShoesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        shoesViewModel = ViewModelProvider(requireActivity()).get(ShoesViewModel::class.java)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_shoe, container,
            false
        )

        binding.shoeViewModel = shoesViewModel
        binding.shoeModel = Shoe("", null, "", "")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        binding.buttonCancel.setOnClickListener {
            findNavController().navigate(AddShoeFragmentDirections.toShoesFragment())
            shoesViewModel.onAddShoeFinished()
        }

        shoesViewModel.eventAddShoe.observe(viewLifecycleOwner, { shoeAdded ->
            if (shoeAdded) {
                findNavController().navigate(AddShoeFragmentDirections.toShoesFragment())
                shoesViewModel.onAddShoeFinished()
            }
        })


        /*
           The logic below was necessary because to adding app:errorEnabled into TextInputLayout
           (fragment_add_shoe.xml file) it didn't work
         */
        shoesViewModel.errorShoeName.observe(viewLifecycleOwner, { hasError ->
            if (hasError) binding.editTextShoeName.error = "Enter the shoe name"
        })

        shoesViewModel.errorDescription.observe(viewLifecycleOwner, { hasError ->
            if (hasError) binding.editTextDescription.error = "Enter the shoe description"
        })

        shoesViewModel.errorShoeSize.observe(viewLifecycleOwner, { hasError ->
            if (hasError) binding.editTextShoeSize.error = "Enter the shoe size"
        })

        shoesViewModel.errorCompany.observe(viewLifecycleOwner, { hasError ->
            if (hasError) binding.editTextCompany.error = "Enter the company name\""
        })

    }
}