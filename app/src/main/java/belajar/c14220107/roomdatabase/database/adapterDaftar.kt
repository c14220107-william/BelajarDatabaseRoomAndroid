package belajar.c14220107.roomdatabase.database

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import belajar.c14220107.roomdatabase.R
import belajar.c14220107.roomdatabase.TambahDaftar

class adapterDaftar (private val daftarBelanja : MutableList<daftarBelanja>) :
    RecyclerView.Adapter<adapterDaftar.ListViewHolder>()
{
        private lateinit var onItemClickCallback : OnItemClickCallback

        interface OnItemClickCallback {
            fun delData(dtBelanja: daftarBelanja)
        }
        fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
            this.onItemClickCallback = onItemClickCallback
        }

        fun isiData(daftar : List<daftarBelanja>){
            daftarBelanja.clear()
            daftarBelanja.addAll(daftar)
            notifyDataSetChanged()
        }

        inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var _tvTanggal = itemView.findViewById<TextView>(R.id.tanggal)
            var _tvItem = itemView.findViewById<TextView>(R.id.item)
            var _tvJumlah = itemView.findViewById<TextView>(R.id.jumlah)

            var _btnEdit = itemView.findViewById<ImageButton>(R.id.btnEdit)
            var _btnDelete = itemView.findViewById<ImageButton>(R.id.btnDelete)


        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list, parent, false
        )
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return daftarBelanja.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var daftar = daftarBelanja[position]

        holder._tvTanggal.setText(daftar.tanggal)
        holder._tvItem.setText(daftar.item)
        holder._tvJumlah.setText(daftar.jumlah)

        holder._btnEdit.setOnClickListener{
            val intent = Intent(it.context, TambahDaftar::class.java)
            intent.putExtra("id",daftar.id)
            intent.putExtra("addEdit",1)
            it.context.startActivity(intent)
        }

        holder._btnDelete.setOnClickListener{
            onItemClickCallback.delData(daftar)
        }
    }


}