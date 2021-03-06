package pt.ipg.app_entregas

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDCliente (db: SQLiteDatabase) : TabelaBD(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                " $CAMPO_NOME TEXT NOT NULL, $CAMPO_CONTACTO INTEGER NOT NULL, $CAMPO_IDADE INTEGER NOT NULL,$CAMPO_MORADA TEXT NOT NULL)") }


    companion object {
        const val NOME = "Cliente"
        const val CAMPO_ID = "$NOME.${BaseColumns._ID}"

        const val CAMPO_NOME = "Nome"
        const val CAMPO_CONTACTO = "Telefone"
        const val CAMPO_IDADE = "Idade"
        const val CAMPO_MORADA = "Morada"

        val TODAS_COLUNAS = arrayOf(CAMPO_ID, CAMPO_NOME, CAMPO_CONTACTO, CAMPO_IDADE, CAMPO_MORADA)

    }
}