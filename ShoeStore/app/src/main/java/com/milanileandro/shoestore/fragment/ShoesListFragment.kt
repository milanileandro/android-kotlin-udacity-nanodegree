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
import kotlinx.android.synthetic.main.fragment_shoes_list.*

class ShoesListFragment : Fragment() {

    private lateinit var viewModel: ShoesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(ShoesViewModel::class.java)

        return DataBindingUtil.inflate<FragmentShoesListBinding>(
            inflater,
            R.layout.fragment_shoes_list, container, false
        ).root
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
        linearLayoutShoesList.addView(textViewShoe)
    }

    private fun setListeners() {
        actionButtonAddShoe.setOnClickListener {
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