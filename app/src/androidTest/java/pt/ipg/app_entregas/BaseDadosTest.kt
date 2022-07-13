package pt.ipg.app_entregas

import android.database.sqlite.SQLiteDatabase
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
        cliente.id = TabelaBDCliente(db).insert(cliente.toContentValues())
        assertNotEquals(-1, cliente.id)
    }

   // @Before
    //fun apagaBaseDados() {

      //  appContext().deleteDatabase(BDEntregasOpenHelper.NOME)
    //}

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
        insereCliente(db, Cliente("Afonso", 966666666, 24,"Guarda"))
        db.close()
    }

    @Test
    fun consegueInserirLocalidade() {
        val db = getWritableDatabase()
        insereLocalidade(db, Localidade("Guarda"))
        insereLocalidade(db, Localidade("Coviha"))
        insereLocalidade(db, Localidade("Lisboa"))
        insereLocalidade(db, Localidade("Porto"))
        insereLocalidade(db, Localidade("Viseu"))
        insereLocalidade(db, Localidade("Castelo Branco"))

    }

    @Test
    fun consegueInserirEntrega() {
        val db = getWritableDatabase()

        val cliente = Cliente("Vasco", 988888888 ,30,"Lisboa")
        insereCliente(db, cliente)

        val produto = Produto("Caixas", "Fragil" )
        insereProduto(db, produto)

        val localidade = Localidade("Guarda" )
        insereLocalidade(db, localidade)

        val cliente2 = Cliente("Vasco", 988888888 ,30,"Lisboa")
        insereCliente(db, cliente)

        val produto2 = Produto("Caixas", "Fragil" )
        insereProduto(db, produto)

        val localidade2 = Localidade("Guarda" )
        insereLocalidade(db, localidade)

        val entrega = Entrega("5", "13-02-22", "Afonso" ,"Caixas",localidade)
        insereEntrega(db, entrega)
        val entrega2 =Entrega("5", "13-02-22", "Antonio" ,"Paletes",localidade2)
        insereEntrega(db, entrega2)
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
            "${TabelaBDProduto.CAMPO_ID}= ?",
            arrayOf("${produto.id}"))

        assertEquals(1, registosAlterados)

        db.close()
    }
    @Test
    fun consegueAlterarCliente() {

        val db = getWritableDatabase()

        val cliente = Cliente("Afonso",966666666,24,"Guarda")
        insereCliente(db, cliente)

        cliente.nome = "Manel"
        cliente.contacto = 988888888
        cliente.idade = 25
        cliente.morada = "Covilhã"

        val registosAlterados = TabelaBDCliente(db).update(
            cliente.toContentValues(),
            "${TabelaBDCliente.CAMPO_ID}= ?",
            arrayOf("${cliente.id}"))

        assertEquals(1, registosAlterados)

        db.close()
    }

    @Test
    fun consegueAlterarLocalidade() {

        val db = getWritableDatabase()

        val localidade = Localidade("Guarda")
        insereLocalidade(db, localidade)

        localidade.nome = "Covilhã"

        val registosAlterados = TabelaBDLocalidade(db).update(
            localidade.toContentValues(),
            "${TabelaBDLocalidade.CAMPO_ID}= ?",
            arrayOf("${localidade.id}"))

        assertEquals(1, registosAlterados)

        db.close()
    }

    @Test
    fun consegueAlterarEntrega() {
        val db = getWritableDatabase()

        val clienteVasco = Cliente("Vasco", 988888888 ,30,"Lisboa")
        insereCliente(db, clienteVasco)

        val clienteAfonso = Cliente("Afonso", 988888888 ,30,"Lisboa")
        insereCliente(db, clienteAfonso)

        val produtoCaixas = Produto("Caixas", "Fragil" )
        insereProduto(db, produtoCaixas)

        val produtoArmarios = Produto("Armarios", "Não_Fragil" )
        insereProduto(db, produtoArmarios)

        val localidadeGuarda = Localidade("Guarda" )
        insereLocalidade(db, localidadeGuarda)

        val localidadeCovilha = Localidade("Covilha" )
        insereLocalidade(db, localidadeCovilha)

        val entrega = Entrega("6","13-02-22","clienteVasco" , "produtoCaixas", localidadeGuarda  )
        insereEntrega(db, entrega)

        entrega.quantidade = "10"
        entrega.data = "14/06/2022"
        entrega.cliente = "clienteAfonso"
        entrega.produto = "produtoArmarios"
        entrega.localidade = localidadeCovilha

        val registosAlterados = TabelaBDEntrega(db).update(
            entrega.toContentValues(),
            "${TabelaBDEntrega.CAMPO_ID}= ?",
            arrayOf("${entrega.id}"))

        assertEquals(1, registosAlterados)

        db.close()
    }

    @Test
    fun consegueEliminarCliente() {
        val db = getWritableDatabase()

        val cliente = Cliente ("Afonso",923234234,30,"Porto")
        insereCliente(db, cliente)

        val registosEliminados = TabelaBDCliente(db).delete(
            "${TabelaBDCliente.CAMPO_ID}=?",
            arrayOf("${cliente.id}"))

        assertEquals(1, registosEliminados)

        db.close()
    }
    @Test
    fun consegueEliminarEntrega() {
        val db = getWritableDatabase()

        val cliente = Cliente ("Afonso",923234234,30,"Porto")
        insereCliente(db, cliente)

        val localidade = Localidade("Guarda" )
        insereLocalidade(db, localidade)

        val produto = Produto("Caixas", "Fragil" )
        insereProduto(db, produto)

        val entrega = Entrega ("2","23-06-22", "cliente", "produto", localidade)
        insereEntrega(db, entrega)

        val registosEliminados = TabelaBDEntrega(db).delete(
            "${TabelaBDEntrega.CAMPO_ID}=?",
            arrayOf("${entrega.id}"))

        assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueEliminarLocalidade() {
        val db = getWritableDatabase()

        val localidade = Localidade("Guarda")
        insereLocalidade(db, localidade)

        val registosEliminados = TabelaBDLocalidade(db).delete(
            "${TabelaBDLocalidade.CAMPO_ID}=?",
            arrayOf("${localidade.id}"))

        assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueEliminarProduto() {
        val db = getWritableDatabase()

        val produto = Produto("Caixas","Fragil")
        insereProduto(db, produto)

        val registosEliminados = TabelaBDProduto(db).delete(
            "${TabelaBDProduto.CAMPO_ID}=?",
            arrayOf("${produto.id}"))

        assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueLerClientes() {
        val db = getWritableDatabase()

        val cliente = Cliente("Afonso",966666666,25,"Guarda")
        insereCliente(db, cliente)

        val cursor = TabelaBDCliente(db).query(
            TabelaBDCliente.TODAS_COLUNAS,
            "${TabelaBDCliente.CAMPO_ID}=?",
            arrayOf("${cliente.id}"),
            null,
            null,
            null
        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val clientBD = Cliente.fromCursor(cursor)

        assertEquals(cliente, clientBD)
        db.close()
    }

    @Test
    fun consegueLerLocalidade() {
        val db = getWritableDatabase()

        val localidade = Localidade("Guarda")
        insereLocalidade(db, localidade)

        val cursor = TabelaBDLocalidade(db).query(
            TabelaBDLocalidade.TODAS_COLUNAS,
            "${TabelaBDLocalidade.CAMPO_ID}=?",
            arrayOf("${localidade.id}"),
            null,
            null,
            null
        )
        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val localBD = Localidade.fromCursor(cursor)

        assertEquals(localidade,localBD)
        db.close()
    }

    @Test
    fun consegueLerProduto() {
        val db = getWritableDatabase()

        val produto = Produto("Caixas","Fragil")
        insereProduto(db, produto)

        val cursor = TabelaBDProduto(db).query(
            TabelaBDProduto.TODAS_COLUNAS,
            "${TabelaBDProduto.CAMPO_ID}=?",
            arrayOf("${produto.id}"),
            null,
            null,
            null
        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val produtoBD = Produto.fromCursor(cursor)

        assertEquals(produto, produtoBD)
        db.close()
    }


    @Test
    fun consegueLerEntregas() {
        val db = getWritableDatabase()

        val cliente = Cliente("Afonso",966666666,25,"Guarda")
        insereCliente(db, cliente)

        val produto = Produto("Caixas","Fragil")
        insereProduto(db, produto)

        val localidade = Localidade("Lisboa")
        insereLocalidade(db, localidade)

        val entrega = Entrega("2","24/06/2022", "cliente ","produto", localidade)
        insereEntrega(db, entrega)

        val cursor = TabelaBDEntrega(db).query(
            TabelaBDEntrega.TODAS_COLUNAS,
            "${TabelaBDEntrega.CAMPO_ID}=?",
            arrayOf("${entrega.id}"),
            null,
            null,
            null
        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val entregaBD = Entrega.fromCursor(cursor)

        assertEquals(entrega, entregaBD)

    }
}

