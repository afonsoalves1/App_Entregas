package pt.ipg.app_entregas

import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import pt.ipg.app_entregas.databinding.FragmentEditarProdutosBinding
import pt.ipg.app_entregas.databinding.FragmentEliminarClienteBinding
import pt.ipg.app_entregas.databinding.FragmentEliminarProdutosBinding


class EliminarProdutosFragment : Fragment() {

    private var _binding: FragmentEliminarProdutosBinding? = null

    private val binding get() = _binding!!

    private lateinit var produto: Produto

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEliminarProdutosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity() as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_eliminar

        produto = EliminarProdutosFragmentArgs.fromBundle(arguments!!).produto

        binding.textViewNomeProduto.text = produto.nome
        binding.textViewDescricaoProduto.text = produto.Descricao

    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_eliminar -> {
                eliminaProduto()
                true
            }
            R.id.action_cancelar -> {
                voltaListaProduto()
                true
            }
            else -> false
        }

    private fun eliminaProduto() {
        val alertDialog = AlertDialog.Builder(requireContext())

        alertDialog.apply {
            setTitle(R.string.delete_produto)
            setMessage(R.string.sure)
            setNegativeButton(android.R.string.cancel, DialogInterface.OnClickListener { dialogInterface, i ->  })
            setPositiveButton(R.string.delete, DialogInterface.OnClickListener { dialogInterface, i -> confirmaEliminarProduto() })
            show()
        }
    }

    private fun confirmaEliminarProduto() {
        val enderecoCliente = Uri.withAppendedPath(ContentProviderEntregas.ENDERECO_PRODUTO, "${produto.id}")
        val registosEliminados = requireActivity().contentResolver.delete(enderecoCliente, null, null)

        if (registosEliminados != 1) {
            Snackbar.make(
                binding.textViewNomeProduto,
                R.string.erro,
                Snackbar.LENGTH_INDEFINITE
            ).show()
            return
        }

        Toast.makeText(requireContext(), R.string.done, Toast.LENGTH_LONG).show()
        voltaListaProduto()
    }

    private fun voltaListaProduto() {
        val acao = EliminarProdutosFragmentDirections.actionEliminarProdutosFragmentToListaProdutoFragment2()
        findNavController().navigate(acao)
    }
}