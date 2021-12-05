package com.example.bengkel.ui.main.service.usage

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bengkel.R
import com.example.bengkel.data.source.remote.response.DataUsage
import com.example.bengkel.databinding.ItemUsageBinding

class UsageAdapter: RecyclerView.Adapter<UsageAdapter.ViewHolder>() {
    private var data = ArrayList<DataUsage>()
    var onUpdateItemClick: ((DataUsage) -> Unit)? = null
    var onDeleteItemClick: ((DataUsage) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<DataUsage>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(data: DataUsage) {
            with(binding){
                tvSukucadang.text = data.namaSukuCadang
                tvJumlah.text = data.jumlahSukuCadang
                tvTotal.text = data.totalBeli
            }
        }

        private val binding = ItemUsageBinding.bind(itemView)

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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_usage, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val d = data[position]
        holder.bind(d)
    }

    override fun getItemCount() = data.size
}