package com.example.bengkel.ui.main.service

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.bengkel.R
import com.example.bengkel.data.source.remote.response.DataService
import com.example.bengkel.databinding.ItemServiceBinding

class ServiceAdapter(private val type: String): RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {

    private var data = ArrayList<DataService>()
    var onUpdateItemClick: ((DataService) -> Unit)? = null
    var onDeleteItemClick: ((DataService) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<DataService>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val binding = ItemServiceBinding.bind(itemView)

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

        fun bind(data: DataService) {
            with(binding){
                tvId.text = data.idNota
                tvNamaPel.text = data.namaPelanggan
                tvNamaBar.text = data.namaBarang
                tvNoTel.text = data.noTelepon
                tvTglSer.text = data.tanggalService
                tvTglSel.text = data.tanggalSelesai
                tvHarga.text = data.hargaJasa
                tvKet.text = data.keterangan
                tvBiaya.text = data.biayaTambahan
                tvTot.text = data.totalHarga
                tvStat.text = data.status

                btnEdit.isVisible = type != "history"
                btnDelete.isVisible = type != "history"
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_service, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val d = data[position]
        holder.bind(d)
    }

    override fun getItemCount() = data.size
}