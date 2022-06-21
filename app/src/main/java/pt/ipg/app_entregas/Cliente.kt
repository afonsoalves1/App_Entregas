package pt.ipg.app_entregas

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Cliente (var nome: String,
                    var contacto: Int,
                    var id: Long = -1 )
{
    fun toContentValues() : ContentValues{
        val valores = ContentValues()

        valores.put(TabelaBDCliente.CAMPO_NOME, nome)
        valores.put(TabelaBDCliente.CAMPO_CONTACTO, contacto )



        return valores
    }
}