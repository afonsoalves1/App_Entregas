package pt.ipg.app_entregas

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.io.Serializable

data class Entrega ( var quantidade:Int,
                     var data: String,
                     var cliente:  String,
                     var produto: String,
                     var localidade: Localidade,
                     var id: Long = -1) : Serializable {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDEntrega.CAMPO_QUANTIDADE, quantidade)
        valores.put(TabelaBDEntrega.CAMPO_DATA, data)
        valores.put(TabelaBDEntrega.CAMPO_CLIENTE_ID, cliente)
        valores.put(TabelaBDEntrega.CAMPO_PRODUTO_ID, produto)
        valores.put(TabelaBDEntrega.CAMPO_LOCALIDADE_ID, localidade.id)

        return valores
    }
    companion object {
        fun fromCursor(cursor: Cursor): Entrega {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posQuantidade = cursor.getColumnIndex(TabelaBDEntrega.CAMPO_QUANTIDADE)
            val posData= cursor.getColumnIndex(TabelaBDEntrega.CAMPO_DATA)
           // val posIdCliente = cursor.getColumnIndex(TabelaBDCliente.CAMPO_ID)
            //val posNomeCliente =  cursor.getColumnIndex(TabelaBDCliente.CAMPO_NOME)
            //val posContacto =  cursor.getColumnIndex(TabelaBDCliente.CAMPO_CONTACTO)
            //val posIdade =  cursor.getColumnIndex(TabelaBDCliente.CAMPO_IDADE)
            //val posMorada =  cursor.getColumnIndex(TabelaBDCliente.CAMPO_MORADA)

            val posCliente =cursor.getColumnIndex(TabelaBDEntrega.CAMPO_CLIENTE_ID)
            val posProduto = cursor.getColumnIndex(TabelaBDEntrega.CAMPO_PRODUTO_ID)

            val posIdProduto = cursor.getColumnIndex(TabelaBDEntrega.CAMPO_PRODUTO_ID)
            val posNomeProduto =  cursor.getColumnIndex(TabelaBDProduto.CAMPO_NOME_PRODUTO)
            val posDescricao =  cursor.getColumnIndex(TabelaBDProduto.CAMPO_DESCRICAO_PRODUTO)
            val posIdLocalidade = cursor.getColumnIndex(TabelaBDEntrega.CAMPO_LOCALIDADE_ID)
            val posNomeLocalidade =  cursor.getColumnIndex(TabelaBDLocalidade.CAMPO_LOCALIDADE)



            val id = cursor.getLong(posId)
            val quantidade = cursor.getInt(posQuantidade)
            val data = cursor.getString(posData)
            val cliente = cursor.getString(posCliente)
            val produto = cursor.getString(posProduto)
            //val idCliente = cursor.getLong(posIdCliente)
            //val nomeCliente = cursor.getString(posNomeCliente)
            //val contactoCliente = cursor.getString(posContacto)
            //val idadeCliente = cursor.getString(posIdade)
            //val moradaCliente =cursor.getString(posMorada)
            //val idProduto = cursor.getLong(posIdProduto)
            //val nomeProduto = cursor.getString(posNomeProduto)
            //val descricaoProduto = cursor.getString(posDescricao)
            val idLocalidade = cursor.getLong(posIdLocalidade)
            val nomeLocalidade = cursor.getString(posNomeLocalidade)

            //al cliente = Cliente(nomeCliente, contactoCliente, idadeCliente,moradaCliente, idCliente)
            //val produto = Produto(nomeProduto, descricaoProduto,idProduto)
            val localidade = Localidade(nomeLocalidade, idLocalidade)

            return Entrega(quantidade,data, cliente ,produto,localidade,id)
        }

    }}