package pt.ipg.app_entregas

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDEntrega (db: SQLiteDatabase) : TabelaBD(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $Cliente_ID TEXT NOT NULL, $Entrega_ID TEXT NOT NULL, " +
                "$Data TEXT NOT NULL, $Produto_ID INTEGER NOT NULL,  FOREIGN KEY($Produto_ID) REFERENCES ${TabelaBDProduto.Produto_ID}(${BaseColumns._ID}) ON DELETE RESTRICT)")

    }

    companion object {
        const val NOME = "Entrega"
        const val Data = "Data"
        const val Produto_ID = "Produto_ID"
        const val Cliente_ID = "Cliente_ID"
        const val Entrega_ID = "Entrega_ID"

    }

}