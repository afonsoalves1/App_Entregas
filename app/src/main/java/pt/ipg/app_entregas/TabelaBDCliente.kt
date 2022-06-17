package pt.ipg.app_entregas

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDCliente (db: SQLiteDatabase) : TabelaBD(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                " $CAMPO_NOME TEXT NOT NULL, $CAMPO_CONTACTO INT(9) NOT NULL, " +
                "$CAMPO_LOCALIDADE_ID INTEGER NOT NULL, FOREIGN KEY ($CAMPO_LOCALIDADE_ID) REFERENCES ${TabelaBDLocalidade.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT )")
    }

    companion object {
        const val NOME = "Cliente"
        const val CAMPO_NOME = "Nome"
        const val CAMPO_CONTACTO = "Telefone"
        const val CAMPO_LOCALIDADE_ID = "Localidade"

    }
    }