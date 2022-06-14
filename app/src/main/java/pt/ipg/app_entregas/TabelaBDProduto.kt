package pt.ipg.app_entregas

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

open class TabelaBDProduto (db: SQLiteDatabase) : TabelaBD(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $Descricao_Produto TEXT NOT NULL," +
                "  $Nome TEXT NOT NULL, $Quantidade TEXT NOT NULL, $Produto_ID TEXT NOT NULL)")
    }

    companion object {
        const val NOME = "Produto"
        const val Descricao_Produto = "Descricao_Produto"
        const val Nome = "Nome"
        const val Quantidade = "Quantidade"
        const val Produto_ID = "Produto_ID"
    }
}