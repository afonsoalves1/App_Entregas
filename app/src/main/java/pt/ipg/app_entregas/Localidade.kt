package pt.ipg.app_entregas

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

class Localidade (var nome: String,
                  var id: Long = -1){


    fun toContentValues() : ContentValues {
            val valores = ContentValues()
            valores.put(TabelaBDLocalidade.CAMPO_LOCALIDADE, nome)
            return valores
        }

    companion object {
        fun fromCursor(cursor: Cursor): Localidade {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posLocalidade = cursor.getColumnIndex(TabelaBDLocalidade.CAMPO_LOCALIDADE)

            val id = cursor.getLong(posId)
            val localidade = cursor.getString(posLocalidade)

            return Localidade(localidade, id)
        }
    }


}