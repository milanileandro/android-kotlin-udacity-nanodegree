package com.milanileandro.shoestore.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.milanileandro.shoestore.R
import com.milanileandro.shoestore.databinding.FragmentAddShoeBinding
import kotlinx.android.synthetic.main.fragment_add_shoe.*

class AddShoeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return DataBindingUtil.inflate<FragmentAddShoeBinding>(
            inflater,
            R.layout.fragment_add_shoe, container, false
        ).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        buttonCancel.setOnClickListener {
            findNavController().navigate(AddShoeFragmentDirections.toShoesFragment())
        }
    }
}