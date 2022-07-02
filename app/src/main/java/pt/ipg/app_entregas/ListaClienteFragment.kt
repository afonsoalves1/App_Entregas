package pt.ipg.app_entregas

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import pt.ipg.app_entregas.databinding.FragmentListaClientesBinding


class ListaClienteFragment : Fragment() ,  LoaderManager.LoaderCallbacks<Cursor> {


    var clienteSelecionado :Cliente? = null
        get() = field
        set(value) {
            field = value
            (requireActivity() as MainActivity).mostraOpcoesAlterarEliminar(field != null)
        }

    private var _binding: FragmentListaClientesBinding? = null

    private var adapterClientes : AdapterClientes? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListaClientesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoaderManager.getInstance(this).initLoader(ID_LOADER_CLIENTE, null, this)

        adapterClientes = AdapterClientes(this)
        binding .recyclerViewClientes.adapter = adapterClientes
        binding.recyclerViewClientes.layoutManager = LinearLayoutManager(requireContext())

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_lista
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> =
        CursorLoader(
            requireContext(),
            ContentProviderEntregas.ENDERECO_CLIENTE,
            TabelaBDCliente.TODAS_COLUNAS,
            null,
            null,
            "${TabelaBDCliente.CAMPO_NOME}"
        )

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterClientes!!.cursor = data
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        if (_binding == null) return
        adapterClientes!!.cursor = null
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_inserir -> {
                val acao = ListaClienteFragmentDirections.actionListaClienteFragmentToEditarClienteFragment()
                findNavController().navigate(acao)
                (activity as MainActivity).atualizaTitulo(R.string.insere_cliente)
                true
            }
            R.id.action_alterar -> {
                val acao = ListaClienteFragmentDirections.actionListaClienteFragmentToEditarClienteFragment(clienteSelecionado)
                findNavController().navigate(acao)
                (activity as MainActivity).atualizaTitulo(R.string.editar_cliente)
                true
            }
            R.id.action_guardar -> {
                val acao = ListaClienteFragmentDirections.actionListaClienteFragmentToEliminarClienteFragment(clienteSelecionado!!)
                findNavController().navigate(acao)
                (activity as MainActivity).atualizaTitulo(R.string.apagar_cliente)
                true
            }
            else -> false
        }

    companion object {
        const val ID_LOADER_CLIENTE = 0
    }
}