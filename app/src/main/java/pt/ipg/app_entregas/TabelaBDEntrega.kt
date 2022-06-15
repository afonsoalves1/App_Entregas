package pt.ipg.app_entregas

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDEntrega (db: SQLiteDatabase) : TabelaBD(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$CAMPO_NOME_CLIENTE REFERENCES ${TabelaBDProduto.CAMPO_NOME} ON DELETE RESTRICT," +
                "$CAMPO_NOME_PRODUTO REFERENCES ${TabelaBDProduto.CAMPO_NOME} ON DELETE RESTRICT," +
                "$CAMPO_QUANTIDADE INTEGER NOT NULL, $CAMPO_DATA TEXT NOT NULL)")
    }

    companion object {
        const val NOME = "Entrega"
        const val CAMPO_NOME_CLIENTE = "Nome_Cliente"
        const val CAMPO_NOME_PRODUTO = "Nome_Produto"
        const val CAMPO_QUANTIDADE = "Quantidade"
        const val CAMPO_DATA = "Data"

    }

}