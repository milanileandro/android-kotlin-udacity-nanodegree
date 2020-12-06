package com.milanileandro.shoestore.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.milanileandro.shoestore.models.Shoe

class ShoesViewModel : ViewModel() {

    private lateinit var _shoesList: MutableLiveData<List<Shoe>>
    val shoesList: MutableLiveData<List<Shoe>>
        get() = _shoesList

    init {
        loadInitialShoeList()
    }

    fun addShoe(shoe: Shoe) {
        _shoesList.value?.toMutableList()?.add(shoe)
    }

    private fun loadInitialShoeList() {
        _shoesList = MutableLiveData<List<Shoe>>().apply {
            value = mutableListOf(
                Shoe(
                    "STAN SMITH",
                    12.0,
                    "Adidas",
                    "Match them with your outfit for a dressed-up version of monochrome."
                ),
                Shoe(
                    "NMD_R1",
                    11.5,
                    "Adidas",
                    "Boost midsole and a flexible textile upper"
                ),
                Shoe(
                    "X9000L4",
                    13.0,
                    "Adidas",
                    "Floating triangles and supportive underlays create a bold design on the mesh upper"
                ),
                Shoe(
                    "ZX 2K BOOST",
                    9.5,
                    "Adidas",
                    "A progressive look and technical design, brimming with Boost"
                ),
                Shoe(
                    "AIR VAPORMAX FLYKNIT 3",
                    6.5,
                    "Nike",
                    "Revolutionary VaporMax technology keeps spring in your step with toe-to-heel cushioning."
                ),
                Shoe(
                    "AIR MAX 270",
                    10.5,
                    "Nike",
                    "Nike's first lifestyle Air Max brings you style, comfort and big attitude."
                ),
                Shoe(
                    "AIR MAX 90 SURPLUS",
                    12.0,
                    "Nike",
                    "Military-inspired colors combine with the composite toe cap ."
                )
            )
        }
    }
}