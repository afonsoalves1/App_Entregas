package pt.ipg.app_entregas

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Cliente (var nome: String,
                    var contacto: Int,
                    var idade: Int,
                    var morada: String,
                    var id: Long = -1 )
{
    fun toContentValues() : ContentValues{
        val valores = ContentValues()

        valores.put(TabelaBDCliente.CAMPO_NOME, nome)
        valores.put(TabelaBDCliente.CAMPO_CONTACTO, contacto )
        valores.put(TabelaBDCliente.CAMPO_IDADE, idade )
        valores.put(TabelaBDCliente.CAMPO_MORADA, morada )

        return valores
    }
}