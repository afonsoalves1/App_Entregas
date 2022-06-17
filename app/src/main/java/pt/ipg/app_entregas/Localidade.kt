package pt.ipg.app_entregas

import android.content.ContentValues

class Localidade (var nome: String,
                  var id: Long = -1){


    fun toContentValues() : ContentValues {
            val valores = ContentValues()
            valores.put(TabelaBDLocalidade.CAMPO_LOCALIDADE, nome)
            return valores
        }
    }