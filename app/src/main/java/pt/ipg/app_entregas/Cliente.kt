package pt.ipg.app_entregas

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.io.Serializable

data class Cliente (var nome: String = "",
                    var contacto: Int ,
                    var data_nascimento: String = "" ,
                    var morada: String = "",
                    var id: Long = -1 ) : Serializable
{
    fun toContentValues() : ContentValues{
        val valores = ContentValues()

        valores.put(TabelaBDCliente.CAMPO_NOME, nome)
        valores.put(TabelaBDCliente.CAMPO_CONTACTO, contacto )
        valores.put(TabelaBDCliente.CAMPO_IDADE, data_nascimento )
        valores.put(TabelaBDCliente.CAMPO_MORADA, morada )

        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Cliente {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaBDCliente.CAMPO_NOME)
            val posContacto = cursor.getColumnIndex(TabelaBDCliente.CAMPO_CONTACTO)
            val posIdade = cursor.getColumnIndex(TabelaBDCliente.CAMPO_IDADE)
            val posMorada = cursor.getColumnIndex(TabelaBDCliente.CAMPO_MORADA)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)
            val contacto = cursor.getInt(posContacto)
            val idade = cursor.getString(posIdade)
            val morada = cursor.getString(posMorada)

            return Cliente(nome, contacto,idade, morada,id)
        }
    }
}