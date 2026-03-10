package com.dyrdin.phones

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    private val myAdapter = PhonesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        loadData()

        root.findViewById<RecyclerView>(R.id.recyclerView).layoutManager =
            LinearLayoutManager(requireContext())

        root.findViewById<RecyclerView>(R.id.recyclerView).adapter = myAdapter

        return root
    }

    private fun loadData() {
        myAdapter.setupPhones(ArrayList(PhonesData.phonesArr.toList()))
    }
}
