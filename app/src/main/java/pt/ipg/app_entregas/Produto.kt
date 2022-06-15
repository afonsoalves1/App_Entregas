package pt.ipg.app_entregas

import android.content.ContentValues

data class Produto ( var nome: String,
                var Descricao: String,
                var id: Long = -1) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()
        valores.put(TabelaBDProduto.CAMPO_NOME,nome)
        valores.put(TabelaBDProduto.CAMPO_DESCRICAO_PRODUTO,Descricao)

        return valores
    }
}