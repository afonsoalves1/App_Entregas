package pt.ipg.app_entregas

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import java.text.DateFormat
import java.util.*

data class Entrega ( var quantidade: Int,
                     var data: String,
                     var idCliente: Long,
                     var idProduto:Long,
                     var idLocalidade: Long,
                     var id: Long = -1
) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDEntrega.CAMPO_QUANTIDADE, quantidade)
        valores.put(TabelaBDEntrega.CAMPO_DATA, data)
        valores.put(TabelaBDEntrega.CAMPO_CLIENTE_ID, idCliente)
        valores.put(TabelaBDEntrega.CAMPO_PRODUTO_ID, idProduto)
        valores.put(TabelaBDEntrega.CAMPO_LOCALIDADE_ID, idLocalidade)

        return valores
    }
}