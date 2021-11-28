package com.example.bengkel.ui.main.admin

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bengkel.R
import com.example.bengkel.data.source.remote.response.DataUser
import com.example.bengkel.databinding.ItemUserBinding

class AdminAdapter : RecyclerView.Adapter<AdminAdapter.ViewHolder>() {
    private var data = ArrayList<DataUser>()
    var onReadItemClick: ((DataUser) -> Unit)? = null
    var onUpdateItemClick: ((DataUser) -> Unit)? = null
    var onDeleteItemClick: ((DataUser) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<DataUser>){
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


        fun bind(d: DataUser) {
            with(binding){
                tvName.text = d.nama
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