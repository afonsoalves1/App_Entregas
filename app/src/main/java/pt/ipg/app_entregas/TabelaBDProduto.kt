package pt.ipg.app_entregas

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

open class TabelaBDProduto (db: SQLiteDatabase) : TabelaBD(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT,  $CAMPO_NOME TEXT NOT NULL,"+
                "$CAMPO_DESCRICAO_PRODUTO INTEGER NOT NULL)")

    }

    companion object {
        const val NOME = "Produto"
        const val CAMPO_NOME = "Nome_Produto"
        const val CAMPO_DESCRICAO_PRODUTO = "Descricao_Produto"

    }
}