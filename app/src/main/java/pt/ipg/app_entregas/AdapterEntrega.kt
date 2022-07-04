package pt.ipg.app_entregas

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class AdapterEntrega(val fragment: ListaEntregasFragment) : RecyclerView.Adapter<AdapterEntrega.ViewHolderEntrega>() {
    var cursor: Cursor? = null
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyDataSetChanged()
            }
        }

    var viewHolderSelecionado : ViewHolderEntrega? = null

    inner class ViewHolderEntrega(itemEntrega: View) : RecyclerView.ViewHolder(itemEntrega), View.OnClickListener {
        val textViewQuantidade = itemEntrega.findViewById<TextView>(R.id.textViewQuantidade)
        val textViewData = itemEntrega.findViewById<TextView>(R.id.textViewData)
        val textViewCliente = itemEntrega.findViewById<TextView>(R.id.textViewCliente)
        val textViewProduto = itemEntrega.findViewById<TextView>(R.id.textViewProduto)
        val textViewLocalidade = itemEntrega.findViewById<TextView>(R.id.textViewLocalidade)

        init {
            itemEntrega.setOnClickListener(this)
        }


        var entrega: Entrega? = null
            get() = field
            set(value: Entrega?) {
                field = value

                textViewQuantidade.text = (entrega?.quantidade ?: "").toString()
                textViewData.text = (entrega?.data?: "").toString()
                textViewCliente.text = (entrega?.cliente ?: "").toString()
                textViewProduto.text = (entrega?.produto ?: "").toString()
                textViewLocalidade.text = (entrega?.localidade?.nome ?: "").toString()
            }

        override fun onClick(v: View?) {
            viewHolderSelecionado?.desseleciona()
            seleciona()
        }
        private fun seleciona() {
            itemView.setBackgroundResource(android.R.color.holo_orange_light)
            viewHolderSelecionado = this
            fragment.entregaSelecionado = entrega
        }

        private fun desseleciona() {
            itemView.setBackgroundResource(android.R.color.white)
        }

    }
    override fun onBindViewHolder(holder: ViewHolderEntrega, position: Int) {
        cursor!!.moveToPosition(position)
        holder.entrega=Entrega.fromCursor(cursor!!)
    }

    override fun getItemCount(): Int {
        if(cursor == null) return 0

        return cursor!!.count
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderEntrega {
        val itemEntrega = fragment.layoutInflater.inflate(R.layout.item_entrega, parent, false)
        return ViewHolderEntrega(itemEntrega)
    }
}