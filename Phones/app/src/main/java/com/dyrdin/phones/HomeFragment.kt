package com.dyrdin.phones

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    private lateinit var onClickListener: () -> Unit
    private val myAdapter = PhonesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        loadData()

        root.findViewById<Button>(R.id.btnAsc).setOnClickListener {
            myAdapter.setupPhones(ArrayList(PhonesData.phonesArr.toList()), "price", PhonesAdapter.SortOrder.ASC)
        }

        root.findViewById<Button>(R.id.btnDesc).setOnClickListener {
            myAdapter.setupPhones(ArrayList(PhonesData.phonesArr.toList()), "price", PhonesAdapter.SortOrder.DESC)
        }

        root.findViewById<RecyclerView>(R.id.recyclerView).layoutManager =
            LinearLayoutManager(requireContext())

        root.findViewById<RecyclerView>(R.id.recyclerView).adapter = myAdapter

        return root
    }

    private fun loadData() {
        myAdapter.setupPhones(ArrayList(PhonesData.phonesArr.toList()), "price", PhonesAdapter.SortOrder.DESC)
    }
}
