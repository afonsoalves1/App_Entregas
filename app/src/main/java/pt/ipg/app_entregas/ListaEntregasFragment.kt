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
import pt.ipg.app_entregas.databinding.FragmentListaEntregasBinding


class ListaEntregasFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {
    var entregaSelecionado :Entrega? = null
        get() = field
        set(value) {
            field = value
            (requireActivity() as MainActivity).mostraOpcoesAlterarEliminar(field != null)
        }

    private var _binding: FragmentListaEntregasBinding? = null

    private var adapterEntrega : AdapterEntrega? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListaEntregasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoaderManager.getInstance(this).initLoader(ID_LOADER_ENTREGA, null, this)

        adapterEntrega = AdapterEntrega(this)
        binding .recyclerViewEntregas.adapter = adapterEntrega
        binding.recyclerViewEntregas.layoutManager = LinearLayoutManager(requireContext())

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
            ContentProviderEntregas.ENDERECO_ENTREGA,
            TabelaBDEntrega.TODAS_COLUNAS,
            null,
            null,
            "${TabelaBDEntrega.CAMPO_QUANTIDADE}"
        )

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterEntrega!!.cursor = data
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        if (_binding == null) return
        adapterEntrega!!.cursor = null
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_inserir -> {
                val acao = ListaEntregasFragmentDirections.actionListaEntregasFragmentToEditarEntregasFragment()
                findNavController().navigate(acao)
                (activity as MainActivity).atualizaTitulo(R.string.insere_entrega)
                true
            }
            R.id.action_alterar -> {
                val acao = ListaEntregasFragmentDirections.actionListaEntregasFragmentToEditarEntregasFragment(entregaSelecionado)
                findNavController().navigate(acao)
                (activity as MainActivity).atualizaTitulo(R.string.editar_entrega)
                true
            }
            R.id.action_guardar -> {
                val acao = ListaEntregasFragmentDirections.actionListaEntregasFragmentToEliminarEntregaFragment(entregaSelecionado!!)
                findNavController().navigate(acao)
                (activity as MainActivity).atualizaTitulo(R.string.eliminar_entrega)
                true
            }

            else -> false
        }

    companion object {
        const val ID_LOADER_ENTREGA = 0
    }
}