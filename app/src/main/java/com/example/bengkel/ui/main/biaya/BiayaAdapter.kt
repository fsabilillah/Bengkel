package com.example.bengkel.ui.main.biaya

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bengkel.R
import com.example.bengkel.data.source.remote.response.DataBiaya
import com.example.bengkel.databinding.ItemUserBinding

class BiayaAdapter : RecyclerView.Adapter<BiayaAdapter.ViewHolder>() {
    private var data = ArrayList<DataBiaya>()
    var onReadItemClick: ((DataBiaya) -> Unit)? = null
    var onUpdateItemClick: ((DataBiaya) -> Unit)? = null
    var onDeleteItemClick: ((DataBiaya) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<DataBiaya>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {
        private val binding = ItemUserBinding.bind(itemView)

        init {
            with(binding){
                btnView.setOnClickListener {
                    onReadItemClick?.invoke(data[adapterPosition])
                }

                btnDelete.setOnClickListener {
                    onDeleteItemClick?.invoke(data[adapterPosition])
                }

                btnEdit.setOnClickListener {
                    onUpdateItemClick?.invoke(data[adapterPosition])
                }
            }
        }


        fun bind(d: DataBiaya) {
            with(binding){
                tvName.text = d.biayaTambahan
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val d = data[position]
        holder.bind(d)
    }

    override fun getItemCount() = data.size
}