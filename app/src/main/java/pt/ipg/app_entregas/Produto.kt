package pt.ipg.app_entregas

import android.content.ContentValues

class Produto ( var nome_produto: String,
                var Descricao_Produto: String,
                var Quantidade_P: Int,
                var id: Long = -1) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()
        valores.put(TabelaBDProduto.Nome, nome_produto)
        valores.put(TabelaBDProduto.Descricao_Produto, Descricao_Produto)
        valores.put(TabelaBDProduto.Quantidade, Quantidade_P)

        return valores
    }
}