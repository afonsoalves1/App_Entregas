package pt.ipg.app_entregas

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import java.text.DateFormat
import java.util.*

data class Entrega ( var quantidade: Int,
                     var data: String,
                     var id: Long = -1,
) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDEntrega.CAMPO_QUANTIDADE, quantidade)
        valores.put(TabelaBDEntrega.CAMPO_DATA, data)

        return valores
    }
}