package com.dyrdin.phones

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PhonesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mPhonesList: ArrayList<PhoneModel> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setupPhones(phonesList: ArrayList<PhoneModel>) {
        mPhonesList.clear()
        mPhonesList.addAll(phonesList)
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
