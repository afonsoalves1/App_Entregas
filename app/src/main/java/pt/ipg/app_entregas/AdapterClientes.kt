package pt.ipg.app_entregas

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterClientes (val fragment: ListaClienteFragment): RecyclerView.Adapter<AdapterClientes.ViewHolderCliente>() {


    var cursor: Cursor? = null
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyDataSetChanged()
            }
        }

    var viewHolderSelecionado : ViewHolderCliente? = null

    inner class ViewHolderCliente(itemCliente: View) : RecyclerView.ViewHolder(itemCliente), View.OnClickListener {
        val textViewNome = itemCliente.findViewById<TextView>(R.id.textViewcliente)
        val textViewContacto = itemCliente.findViewById<TextView>(R.id.textViewContacto)
        val textViewMorada = itemCliente.findViewById<TextView>(R.id.textViewMorada)
        val textViewIdade = itemCliente.findViewById<TextView>(R.id.textViewIdade)

        init {
            itemCliente.setOnClickListener(this)
        }


        var cliente: Cliente? = null
            get() = field
            set(value: Cliente?) {
                field = value

                textViewNome.text = cliente?.nome ?: ""
                textViewContacto.text = (cliente?.contacto?: "") as CharSequence?
                textViewMorada.text = cliente?.morada ?: ""
                textViewIdade.text = (cliente?.idade ?: "") as CharSequence?
            }

        override fun onClick(v: View?) {
            viewHolderSelecionado?.desseleciona()
            seleciona()
        }
        private fun seleciona() {
            itemView.setBackgroundResource(android.R.color.holo_orange_light)
            viewHolderSelecionado = this
            fragment.clienteSelecionado = cliente
        }

        private fun desseleciona() {
            itemView.setBackgroundResource(android.R.color.white)
        }

    }
    override fun onBindViewHolder(holder: ViewHolderCliente, position: Int) {
        cursor!!.moveToPosition(position)
        holder.cliente=Cliente.fromCursor(cursor!!)
    }

    override fun getItemCount(): Int {
        if(cursor == null) return 0

        return cursor!!.count
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCliente {
        val itemClientes = fragment.layoutInflater.inflate(R.layout.item_cliente, parent, false)
        return ViewHolderCliente(itemClientes)
    }
}


