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

    override fun query(
        columns: Array<String>,
        selection: String?,
        selectionArgs: Array<String>?,
        groupBy: String?,
        having: String?,
        orderBy: String?
    ): Cursor {
        val queryBuilder = SQLiteQueryBuilder()
        queryBuilder.tables = "$NOME INNER JOIN ${TabelaBDCliente.NOME} ON ${TabelaBDCliente.CAMPO_ID} = $CAMPO_CLIENTE_ID,"+
                "${TabelaBDProduto.NOME} ON ${TabelaBDProduto.CAMPO_ID} = $CAMPO_PRODUTO_ID,"+
                "${TabelaBDLocalidade.NOME} ON ${TabelaBDLocalidade.CAMPO_ID} = $CAMPO_LOCALIDADE_ID "
                return queryBuilder.query(db, columns, selection, selectionArgs, groupBy, having, orderBy)
    }


    companion object {
        const val NOME = "Entrega"
        const val CAMPO_ID = "$NOME.${BaseColumns._ID}"

        const val CAMPO_QUANTIDADE = "Quantidade"
        const val CAMPO_DATA = "Data"
        const val CAMPO_CLIENTE_ID = "idCliente"
        const val CAMPO_PRODUTO_ID = "idProduto"
        const val CAMPO_LOCALIDADE_ID = "idLocalidade"

        val TODAS_COLUNAS = arrayOf(
            CAMPO_ID, CAMPO_QUANTIDADE, CAMPO_DATA, CAMPO_CLIENTE_ID,
            CAMPO_PRODUTO_ID, CAMPO_LOCALIDADE_ID, TabelaBDCliente.CAMPO_NOME, TabelaBDCliente.CAMPO_CONTACTO, TabelaBDCliente.CAMPO_IDADE, TabelaBDCliente.CAMPO_MORADA
        , TabelaBDProduto.CAMPO_NOME_PRODUTO,TabelaBDProduto.CAMPO_DESCRICAO_PRODUTO, TabelaBDLocalidade.CAMPO_LOCALIDADE)
    }
}