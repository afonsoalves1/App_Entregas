package pt.ipg.app_entregas

import android.content.ContentValues

data class Cliente (var nome_Cliente: String,
                    var Mail_Cliente: String,
                    var Telefone_Cliente: Int,
                    var Morada_Cliente: String,
                    var Localidade_Cliente: String,
                    var Client_ID: Int,
                    var id: Long = -1,) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()
        valores.put(TabelaBDCliente.Nome, nome_Cliente)
        valores.put(TabelaBDCliente.Mail, Mail_Cliente)
        valores.put(TabelaBDCliente.Telefone, Telefone_Cliente)
        valores.put(TabelaBDCliente.Morada, Morada_Cliente)
        valores.put(TabelaBDCliente.Localidade, Localidade_Cliente)

        return valores
    }
}