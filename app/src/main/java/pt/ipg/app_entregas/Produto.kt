package pt.ipg.app_entregas

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.io.Serializable

data class Produto ( var nome: String,
                var Descricao: String,
                var id: Long = -1): Serializable {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()
        valores.put(TabelaBDProduto.CAMPO_NOME_PRODUTO,nome)
        valores.put(TabelaBDProduto.CAMPO_DESCRICAO_PRODUTO,Descricao)

        return valores
    }
    companion object {
        fun fromCursor(cursor: Cursor): Produto {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNomeProduto = cursor.getColumnIndex(TabelaBDProduto.CAMPO_NOME_PRODUTO)
            val posDescricao = cursor.getColumnIndex(TabelaBDProduto.CAMPO_DESCRICAO_PRODUTO)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNomeProduto)
            val descricao = cursor.getString(posDescricao)

            return Produto(nome, descricao,id)
        }
    }

}