package pt.ipg.app_entregas

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


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

    private fun insereProduto(db: SQLiteDatabase, produto: Produto) {
        produto.id = TabelaBDProduto(db).insert(produto.toContentValues())
        assertNotEquals(-1, produto.id)
    }

    private fun insereEntrega(db: SQLiteDatabase, Entrega: Entrega) {
        Entrega.id = TabelaBDEntrega(db).insert(Entrega.toContentValues())
        assertNotEquals(-1, Entrega.id)
    }

    private fun insereLocalidade(db: SQLiteDatabase, localidade: Localidade) {
        localidade.id = TabelaBDLocalidade(db).insert(localidade.toContentValues())
        assertNotEquals(-1, localidade.id)
    }

    private fun insereCliente(db: SQLiteDatabase, cliente: Cliente) {
        cliente.id = TabelaBDEntrega(db).insert(cliente.toContentValues())
        assertNotEquals(-1, cliente.id)
    }

    @Before
    fun apagaBaseDados() {
        appContext().deleteDatabase(BDEntregasOpenHelper.NOME)
    }

    @Test
    fun consegueAbrirBaseDados() {
        val openHelper = BDEntregasOpenHelper(appContext())
        val db = openHelper.readableDatabase

        assertTrue(db.isOpen)

        db.close()
    }

    @Test
    fun consegueInserirProduto() {
        val db = getWritableDatabase()
        insereProduto(db, Produto("Caixas", "Fragil"))
        db.close()
    }

    @Test
    fun consegueInserirCliente() {
        val db = getWritableDatabase()
        insereCliente(db, Cliente("Afonso", "966666666", "Guarda"))
        db.close()
    }

    @Test
    fun consegueInserirLocalidade() {
        val db = getWritableDatabase()
        insereLocalidade(db, Localidade("Guarda"))
        db.close()
    }

    @Test
    fun consegueInserirEntrega(){
        val db = getWritableDatabase()
        insereEntrega(db, Entrega("Ze","Paletes",5,"15/06/2022","Guarda",3 ))
        db.close()
    }

    @Test
    fun consegueAlterarProduto() {

        val db = getWritableDatabase()

        val produto = Produto("Caixas", "Fragil")
        insereProduto(db, produto)

        produto.nome = "Armarios"
        produto.Descricao = "Não é fragil"

        val registosAlterados = TabelaBDProduto(db).update(
            produto.toContentValues(),
            "${BaseColumns._ID}= ?",
            arrayOf("${produto.id}"))

        assertEquals(1, registosAlterados)

        db.close()
    }
    @Test
    fun consegueAlterarCliente() {

        val db = getWritableDatabase()

        val cliente = Cliente("Afonso","966666666","Guarda")
        insereCliente(db, cliente)

        cliente.nome = "Manel"
        cliente.contacto = "988888888"
        cliente.localidade = "Covilha"


        val registosAlterados = TabelaBDCliente(db).update(
            cliente.toContentValues(),
            "${BaseColumns._ID}= ?",
            arrayOf("${cliente.id}"))

        assertEquals(1, registosAlterados)

        db.close()
    }

}