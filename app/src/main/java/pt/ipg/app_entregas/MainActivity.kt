package pt.ipg.app_entregas

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import pt.ipg.app_entregas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    var idMenuAtual = R.menu.menu_main
        get() = field
        set(value) {
            if (value != field) {
                field = value
                invalidateOptionsMenu()
            }
        }

    var menu: Menu? = null

    var fragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_activity_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(idMenuAtual, menu)
        this.menu = menu
        return true
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_activity_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        val opcaoProcessada: Boolean

        if (fragment is MenuPrincipalFragment) {
            opcaoProcessada = (fragment as MenuPrincipalFragment).processaOpcaoMenu(item)
        } else if (fragment is ListaClienteFragment) {
            opcaoProcessada = (fragment as ListaClienteFragment).processaOpcaoMenu(item)
        } else if (fragment is EditarClienteFragment) {
            opcaoProcessada = (fragment as EditarClienteFragment).processaOpcaoMenu(item)
        }else if (fragment is EliminarClienteFragment) {
            opcaoProcessada = (fragment as EliminarClienteFragment).processaOpcaoMenu(item)
        }else if (fragment is ListaProdutoFragment) {
            opcaoProcessada = (fragment as ListaProdutoFragment).processaOpcaoMenu(item)
        } else if (fragment is EditarProdutoFragment) {
            opcaoProcessada = (fragment as EditarProdutoFragment).processaOpcaoMenu(item)
        }else if (fragment is EliminarProdutosFragment) {
                opcaoProcessada = (fragment as EliminarProdutosFragment).processaOpcaoMenu(item)
        }else if (fragment is ListaEntregasFragment) {
            opcaoProcessada = (fragment as ListaEntregasFragment).processaOpcaoMenu(item)
        } else if (fragment is EditarEntregasFragment) {
            opcaoProcessada = (fragment as EditarEntregasFragment).processaOpcaoMenu(item)
        }else if (fragment is EliminarEntregaFragment) {
            opcaoProcessada = (fragment as EliminarEntregaFragment).processaOpcaoMenu(item)
        }else {
            opcaoProcessada = false
        }
        if (opcaoProcessada) return true

        return super.onOptionsItemSelected(item)
    }


    fun mostraOpcoesAlterarEliminar(mostra: Boolean) {
        menu!!.findItem(R.id.action_alterar).setVisible(mostra)
        menu!!.findItem(R.id.action_guardar).setVisible(mostra)
    }

    fun atualizaTitulo(id_string_titulo: Int) {
        binding.toolbar.setTitle(id_string_titulo)
    }
}