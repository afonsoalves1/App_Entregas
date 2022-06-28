package pt.ipg.app_entregas

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import java.text.DateFormat
import java.util.*
import android.database.Cursor
import android.provider.BaseColumns

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
    companion object {
        fun fromCursor(cursor: Cursor): Entrega {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posQuantidade = cursor.getColumnIndex(TabelaBDEntrega.CAMPO_QUANTIDADE)
            val posData= cursor.getColumnIndex(TabelaBDEntrega.CAMPO_DATA)
            val posIdCliente = cursor.getColumnIndex(TabelaBDEntrega.CAMPO_CLIENTE_ID)
            //val posNomeCliente =  cursor.getColumnIndex(TabelaBDCliente.CAMPO_NOME)
            //val posContacto =  cursor.getColumnIndex(TabelaBDCliente.CAMPO_CONTACTO)
            //val posIdade =  cursor.getColumnIndex(TabelaBDCliente.CAMPO_IDADE)
            //val posMorada =  cursor.getColumnIndex(TabelaBDCliente.CAMPO_MORADA)
            val posIdProduto = cursor.getColumnIndex(TabelaBDEntrega.CAMPO_PRODUTO_ID)
            //val posNomeProduto =  cursor.getColumnIndex(TabelaBDProduto.CAMPO_NOME_PRODUTO)
            //val posDescricao =  cursor.getColumnIndex(TabelaBDProduto.CAMPO_DESCRICAO_PRODUTO)
            val posIdLocalidade = cursor.getColumnIndex(TabelaBDEntrega.CAMPO_LOCALIDADE_ID)
            //val posNomeLocalidade =  cursor.getColumnIndex(TabelaBDLocalidade.CAMPO_LOCALIDADE)

            val id = cursor.getLong(posId)
            val quantidade = cursor.getInt(posQuantidade)
            val data = cursor.getString(posData)
            val idCliente = cursor.getLong(posIdCliente)
            //val nomeCliente = cursor.getString(posNomeCliente)
            //val contactoCliente = cursor.getInt(posContacto)
            //val idadeCliente = cursor.getInt(posIdade)
            //val moradaCliente =cursor.getString(posMorada)
            val idProduto = cursor.getLong(posIdProduto)
            //val nomeProduto = cursor.getString(posNomeProduto)
            //val descricaoProduto = cursor.getString(posDescricao)
            val idLocalidade = cursor.getLong(posIdLocalidade)
            //val nomeLocalidade = cursor.getString(posNomeLocalidade)

            //val cliente = Cliente(nomeCliente, contactoCliente, idadeCliente,moradaCliente, idCliente)
            //val produto = Produto(nomeProduto, descricaoProduto,idProduto)
            //val localidade = Localidade(nomeLocalidade, idLocalidade)

            return Entrega(quantidade,data, idCliente,idProduto,idLocalidade,id)
        }

    }}