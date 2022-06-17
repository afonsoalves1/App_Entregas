package pt.ipg.app_entregas

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDLocalidade(db: SQLiteDatabase): TabelaBD(db,NOME){
    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_LOCALIDADE TEXT NOT NULL)")
    }
        companion object {
        const val NOME = "Localidade"
        const val CAMPO_LOCALIDADE = "Nome_Localidade"
    }
}

