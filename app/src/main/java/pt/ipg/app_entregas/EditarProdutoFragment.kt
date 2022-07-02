package pt.ipg.app_entregas

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import pt.ipg.app_entregas.databinding.FragmentEditarProdutosBinding


class EditarProdutoFragment : Fragment() {
    private var _binding: FragmentEditarProdutosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var produto: Produto? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditarProdutosBinding.inflate(inflater, container, false)
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
        activity.idMenuAtual = R.menu.menu_edicao

        if (arguments != null) {
            produto = EditarProdutoFragmentArgs.fromBundle(arguments!!).produto
                if (produto != null) {
                binding.editTextProduto.setText(produto!!.nome)
                binding.editTextDescricao.setText(produto!!.Descricao)

            }
        }

    }

    /**
     * Instantiate and return a new Loader for the given ID.
     *
     *
     * This will always be called from the process's main thread.
     *
     * @param id The ID whose loader is to be created.
     * @param args Any arguments supplied by the caller.
     * @return Return a new Loader instance that is ready to start loading.
     */

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_guardar -> {
                guardar()
                true
            }
            R.id.action_cancelar -> {
                voltaListaProduto()
                true
            }
            else -> false
        }

    private fun guardar() {
        val nome = binding.editTextProduto.text.toString()
        if (nome.isBlank()) {
            binding.editTextProduto.error = getString(R.string.campo_obrigatorio)
            binding.editTextProduto.requestFocus()
            return
        }

        val descricao= binding.editTextDescricao.text.toString()
        if (descricao.isBlank()) {
            binding.editTextDescricao.error = getString(R.string.campo_obrigatorio)
            binding.editTextDescricao.requestFocus()
            return
        }


        val produtoGuardado =
            if (produto== null) {
                insereProduto(nome, descricao)
            } else {
                alteraProduto(nome, descricao)
            }

        if (produtoGuardado) {
            Toast.makeText(requireContext(), R.string.done, Toast.LENGTH_LONG)
                .show()
            voltaListaProduto()
        } else {
            Snackbar.make(binding.editTextProduto, R.string.erro, Snackbar.LENGTH_INDEFINITE).show()
            return
        }
    }

    private fun alteraProduto(nome : String, descricao : String ) : Boolean {
        val produto = Produto(nome, descricao)

        val enderecoProduto = Uri.withAppendedPath(ContentProviderEntregas.ENDERECO_PRODUTO, "${this.produto!!.id}")

        val registosAlterados = requireActivity().contentResolver.update(enderecoProduto, produto.toContentValues(), null, null)

        return registosAlterados == 1
    }

    private fun insereProduto(nome : String, descricao : String ): Boolean {
        val produto = Produto(nome, descricao)

        val enderecoPrpdutoInserido = requireActivity().contentResolver.insert(ContentProviderEntregas.ENDERECO_PRODUTO, produto.toContentValues())

        return enderecoPrpdutoInserido != null
    }

    private fun voltaListaProduto() {
        findNavController().navigate(R.id.action_editarProdutoFragment_to_listaProdutoFragment)
    }

}