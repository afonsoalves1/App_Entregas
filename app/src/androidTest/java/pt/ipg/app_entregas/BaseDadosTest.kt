package pt.ipg.app_entregas

import android.database.sqlite.SQLiteDatabase
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BaseDadosTest {
    private fun appContext() =
        InstrumentationRegistry.getInstrumentation().targetContext

    private fun getWritableDatabase(): SQLiteDatabase {
        val openHelper = BDEntregasOpenHelper(appContext())
        return openHelper.writableDatabase
    }

    private fun insereProduto(db: SQLiteDatabase, Produto: Produto) {
        Produto.id = TabelaBDProduto(db).insert(Produto.toContentValues())
        assertNotEquals(-1, Produto.id)
    }

    




}