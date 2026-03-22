package com.dyrdin.phones

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PhonesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mPhonesList: ArrayList<PhoneModel> = ArrayList()

    enum class SortOrder { ASC, DESC }

    @SuppressLint("NotifyDataSetChanged")
    fun setupPhones(phonesList: ArrayList<PhoneModel>, sortBy: String, order: SortOrder = SortOrder.DESC) {
        val sorted = when (sortBy) {
            "price" -> phonesList.sortedBy { it.price.replace("$", "").toIntOrNull() ?: 0 }
            "score" -> phonesList.sortedBy { it.score.toIntOrNull() ?: 0 }
            "date"  -> phonesList.sortedBy { it.date }
            "name"  -> phonesList.sortedBy { it.name }
            else    -> phonesList.sortedBy { it.score.toIntOrNull() ?: 0 }
        }

        mPhonesList.clear()
        mPhonesList.addAll(if (order == SortOrder.DESC) sorted.reversed() else sorted)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mPhonesList.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.recyclerview_item, parent, false)
        return PhonesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PhonesViewHolder) {
            holder.bind(mPhones = mPhonesList[position])
        }
    }

    class PhonesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(mPhones: PhoneModel) {
            itemView.findViewById<TextView>(R.id.tv_phone_name).text = mPhones.name
            itemView.findViewById<TextView>(R.id.tv_price).text = "Launch Price: " + mPhones.price
            itemView.findViewById<TextView>(R.id.tv_date).text = "Launch Date: " + mPhones.date
            itemView.findViewById<TextView>(R.id.tv_score).text = "Camera Score: " + mPhones.score
        }
    }
}
