package pt.ipg.app_entregas

import android.content.ContentValues
import java.text.DateFormat
import java.util.*

data class Entrega (
               var Data: String,
               var produto_ID: Int,
               var cliente_ID: Int,
               var entrega_ID: Int,
               var id: Long = -1) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()
        valores.put(TabelaBDEntrega.Data, Data)
        valores.put(TabelaBDEntrega.Produto_ID, produto_ID)
        valores.put(TabelaBDEntrega.Cliente_ID, cliente_ID)

        return valores
    }
}