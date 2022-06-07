package pt.ipg.app_entregas

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDCliente (db: SQLiteDatabase) : TabelaBD(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                " $Nome TEXT NOT NULL, $Mail TEXT NOT NULL, $Telefone TEXT NOT NULL, $Morada TEXT NOT NULL," +
                " $Cliente_ID TEXT NOT NULL)")
    }

    companion object {
        const val NOME = "Cliente"
        const val Nome = "Nome"
        const val Mail = "Mail"
        const val Telefone = "Telefone"
        const val Morada = "Morada"
        const val Cliente_ID = "ClienteId"

    }
    }