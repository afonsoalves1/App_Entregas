package pt.ipg.app_entregas

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.provider.BaseColumns

class TabelaBDEntrega (db: SQLiteDatabase) : TabelaBD(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_QUANTIDADE INTEGER NOT NULL, " +
                "$CAMPO_DATA TEXT NOT NULL,"+
                "$CAMPO_CLIENTE_ID INTEGER NOT NULL," +
                "$CAMPO_PRODUTO_ID INTEGER NOT NULL," +
                "$CAMPO_LOCALIDADE_ID INTEGER NOT NULL, " +
                "FOREIGN KEY ($CAMPO_CLIENTE_ID) REFERENCES ${TabelaBDCliente.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT,"+
                "FOREIGN KEY ($CAMPO_PRODUTO_ID) REFERENCES ${TabelaBDProduto.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT," +
                "FOREIGN KEY ($CAMPO_LOCALIDADE_ID) REFERENCES ${TabelaBDLocalidade.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT)")
    }


    companion object {
        const val NOME = "Entrega"
        const val CAMPO_QUANTIDADE = "Quantidade"
        const val CAMPO_DATA = "Data"
        const val CAMPO_CLIENTE_ID = "idCliente"
        const val CAMPO_PRODUTO_ID = "idProduto"
        const val CAMPO_LOCALIDADE_ID = "idLocalidade"


    }
}