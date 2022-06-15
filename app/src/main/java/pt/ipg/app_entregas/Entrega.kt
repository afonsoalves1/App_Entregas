package pt.ipg.app_entregas

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import java.text.DateFormat
import java.util.*

data class Entrega ( var nome_cliente: String,
                     var nome_produto: String,
                     var quantidade: Int,
                     var data: String,
                     var localidade: String,
                     var id: Long = -1,
) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDEntrega.CAMPO_NOME_CLIENTE, nome_cliente)
        valores.put(TabelaBDEntrega.CAMPO_NOME_PRODUTO, nome_produto )
        valores.put(TabelaBDEntrega.CAMPO_QUANTIDADE, quantidade)
        valores.put(TabelaBDEntrega.CAMPO_DATA, data)
        valores.put(TabelaBDEntrega.CAMPO_LOCALIDADE, localidade)


        return valores
    }
}