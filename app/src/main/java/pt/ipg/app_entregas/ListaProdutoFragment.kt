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

import pt.ipg.app_entregas.databinding.FragmentListaProdutoBinding


class ListaProdutoFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    var produtoSelecionado: Produto? = null
    get() = field
    set(value) {
        field = value
        (requireActivity() as MainActivity).mostraOpcoesAlterarEliminar(field != null)
    }

    private var _binding: FragmentListaProdutoBinding? = null

    private var adapterProdutos : AdapterProdutos? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListaProdutoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoaderManager.getInstance(this).initLoader(ID_LOADER_PRODUTO, null, this)

        adapterProdutos = AdapterProdutos(this)
        binding .recyclerViewProdutos.adapter = adapterProdutos
        binding.recyclerViewProdutos.layoutManager = LinearLayoutManager(requireContext())

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
            ContentProviderEntregas.ENDERECO_PRODUTO,
            TabelaBDProduto.TODAS_COLUNAS,
            null,
            null,
            "${TabelaBDProduto.CAMPO_NOME_PRODUTO}"
        )

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterProdutos!!.cursor = data
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        if (_binding == null) return
        adapterProdutos!!.cursor = null
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_inserir -> {
                val acao = ListaProdutoFragmentDirections.actionListaProdutoFragmentToEditarProdutoFragment()
                findNavController().navigate(acao)
                (activity as MainActivity).atualizaTitulo(R.string.insere_produto)
                true
            }
            R.id.action_alterar -> {
                val acao = ListaProdutoFragmentDirections.actionListaProdutoFragmentToEditarProdutoFragment(produtoSelecionado)
                findNavController().navigate(acao)
                (activity as MainActivity).atualizaTitulo(R.string.editar_produto)
                true
            }
            R.id.action_guardar -> {
                val acao = ListaProdutoFragmentDirections.actionListaProdutoFragmentToEliminarProdutosFragment(produtoSelecionado!!)
                findNavController().navigate(acao)
                (activity as MainActivity).atualizaTitulo(R.string.apagar_produto)
                true
            }

            else -> false
        }

    companion object {
    const val ID_LOADER_PRODUTO = 0
}

}