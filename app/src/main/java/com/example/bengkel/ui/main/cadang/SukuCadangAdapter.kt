package com.example.bengkel.ui.main.cadang

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bengkel.R
import com.example.bengkel.data.source.remote.response.DataSukuCadang
import com.example.bengkel.databinding.ItemCadangBinding

class SukuCadangAdapter: RecyclerView.Adapter<SukuCadangAdapter.ViewHolder>() {

    private var data = ArrayList<DataSukuCadang>()
    var onUpdateItemClick: ((DataSukuCadang) -> Unit)? = null
    var onDeleteItemClick: ((DataSukuCadang) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<DataSukuCadang>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_cadang, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val d = data[position]
        holder.bind(d)
    }

    override fun getItemCount() = data.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCadangBinding.bind(itemView)

        init {
            with(binding){

                btnDelete.setOnClickListener {
                    onDeleteItemClick?.invoke(data[adapterPosition])
                }

                btnEdit.setOnClickListener {
                    onUpdateItemClick?.invoke(data[adapterPosition])
                }
            }
        }

        fun bind(data: DataSukuCadang) {
            with(binding){
                tvName.text = data.namaSukuCadang
                tvPrice.text = "harga : ${data.harga}"
                tvStock.text = "jumlah stok : ${data.jumlahStok}"
            }
        }

    }


}