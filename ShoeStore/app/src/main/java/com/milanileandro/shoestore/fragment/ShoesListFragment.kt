package com.milanileandro.shoestore.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.milanileandro.shoestore.R
import com.milanileandro.shoestore.databinding.FragmentShoesListBinding
import com.milanileandro.shoestore.model.Shoe
import com.milanileandro.shoestore.viewmodel.ShoesViewModel

class ShoesListFragment : Fragment() {

    private lateinit var viewModel: ShoesViewModel
    private lateinit var binding: FragmentShoesListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(requireActivity()).get(ShoesViewModel::class.java)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoes_list, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()
        setListeners()
    }

    private fun addNewItemOnLayout(shoe: Shoe) {
        val textViewShoe = TextView(requireContext())
        textViewShoe.text =
            StringBuilder().append(
                "${shoe.name}  " +
                        "\nCompany: ${shoe.company} " +
                        "\nSize: ${shoe.size} " +
                        "\nDescription: ${shoe.description}"
            )
        textViewShoe.setTextColor(ContextCompat.getColor(requireContext(), R.color.purple_500))
        textViewShoe.setPadding(64)
        textViewShoe.typeface = ResourcesCompat.getFont(requireContext(), R.font.roboto_black)
        binding.linearLayoutShoesList.addView(textViewShoe)
    }

    private fun setListeners() {
        binding.actionButtonAddShoe.setOnClickListener {
            findNavController().navigate(ShoesListFragmentDirections.toAddShoeFragment())
        }
    }

    private fun setObservers() {
        viewModel.shoesList.observe(viewLifecycleOwner, { shoeList ->
            shoeList?.iterator()?.forEach { shoe ->
                addNewItemOnLayout(shoe)
            }
        })
    }
}