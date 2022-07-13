package pt.ipg.app_entregas

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleCursorAdapter
import android.widget.Spinner

import android.widget.Toast
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import pt.ipg.app_entregas.databinding.FragmentEditarEntregasBinding


class EditarEntregasFragment : Fragment(),LoaderManager.LoaderCallbacks<Cursor> {
    private var _binding: FragmentEditarEntregasBinding? = null

    private val binding get() = _binding!!

    private var entrega: Entrega? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditarEntregasBinding.inflate(inflater, container, false)
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
            entrega = EditarEntregasFragmentArgs.fromBundle(arguments!!).entrega

            if (entrega != null) {
                binding.editTextQuantidade.setText((entrega!!.quantidade).toString())
                binding.editTextData.setText(entrega!!.data)
                binding.editTextCliente.setText((entrega!!.cliente).toString())
                binding.editTextProduto.setText((entrega!!.produto).toString())
            }
        }

        LoaderManager.getInstance(this).initLoader(ID_LOADER_LOCALIDADE, null, this)
    }

    companion object {

        const val ID_LOADER_LOCALIDADE = 0
    }

    override fun onCreateLoader(id: Int, args: Bundle?):  Loader<Cursor>  =

        CursorLoader(
            requireContext(),
            ContentProviderEntregas.ENDERECO_LOCALIDADE,
            TabelaBDLocalidade.TODAS_COLUNAS,
            null,
            null,
            "${TabelaBDLocalidade.CAMPO_LOCALIDADE}"
        )





    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        val adapterLocalidade = SimpleCursorAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            data,
            arrayOf(TabelaBDLocalidade.CAMPO_LOCALIDADE),
            intArrayOf(android.R.id.text1),
            0
        )

        binding.spinnerLocalidade.adapter = adapterLocalidade

        atualizaLocalidadeSelecionada()

    }

    private fun atualizaLocalidadeSelecionada() {
        if (entrega == null) return
        val idLocalidade = entrega!!.localidade.id

        val ultimaLocalidade = binding.spinnerLocalidade.count - 1

        for (i in 0..ultimaLocalidade) {
            if (binding.spinnerLocalidade.getItemIdAtPosition(i) == idLocalidade) {
                binding.spinnerLocalidade.setSelection(i)
                return
            }
        }
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.action_guardar -> {
                guardar()
                true
            }
            R.id.action_cancelar -> {
                voltaListaEntrega()
                true
            }
            else -> false
        }

    private fun guardar() {
        val quantidade = binding.editTextQuantidade.text.toString()
        if (quantidade.isBlank()) {
            binding.editTextQuantidade.error = getString(R.string.campo_obrigatorio)
            binding.editTextQuantidade.requestFocus()
            return
        }

        val data = binding.editTextData.text.toString()
        if (data.isBlank()) {
            binding.editTextData.error = getString(R.string.campo_obrigatorio)
            binding.editTextData.requestFocus()
            return
        }

        val cliente = binding.editTextCliente.text.toString()
        if (cliente.isBlank()) {
            binding.editTextCliente.error = getString(R.string.campo_obrigatorio)
            binding.editTextCliente.requestFocus()
            return
        }
        val produto = binding.editTextProduto.text.toString()
        if (produto.isBlank()) {
            binding.editTextProduto.error = getString(R.string.campo_obrigatorio)
            binding.editTextProduto.requestFocus()
            return
        }

        val idLocalidade = binding.spinnerLocalidade.selectedItemId
        if (idLocalidade == Spinner.INVALID_ROW_ID) {
            binding.textViewLocalidadeEntrega.error = getString(R.string.campo_obrigatorio)
            binding.spinnerLocalidade.requestFocus()
            return
        }

        val entregaGuardado =
            if (entrega == null) {
                insereEntrega(quantidade, data, cliente, produto, idLocalidade)
            } else {
                alteraEntrega(quantidade, data, cliente, produto, idLocalidade)
            }

        if (entregaGuardado) {
            Toast.makeText(requireContext(), R.string.done, Toast.LENGTH_LONG)
                .show()
            voltaListaEntrega()
        } else {
            Snackbar.make(binding.editTextQuantidade, R.string.erro, Snackbar.LENGTH_INDEFINITE)
                .show()
            return
        }
    }

    private fun alteraEntrega(quantidade: String, data: String, cliente: String, produto: String, localidadeId: Long
    ): Boolean {
        val entrega = Entrega(
            quantidade.toInt(),
            data,
            cliente,
            produto,
            Localidade(id = localidadeId)
        )

        val enderecoEntrega = Uri.withAppendedPath(
            ContentProviderEntregas.ENDERECO_ENTREGA,
            "${this.entrega!!.id}"
        )

        val registosAlterados = requireActivity().contentResolver.update(
            enderecoEntrega,
            entrega.toContentValues(),
            null,
            null
        )

        return registosAlterados == 1
    }

    private fun insereEntrega(
        quantidade: String,
        data: String,
        cliente: String,
        produto: String,
        localidadeId: Long
    ): Boolean {
        val entrega = Entrega(
            quantidade.toInt(),
            data,
            cliente,
            produto,
            Localidade(id = localidadeId)
        )

        val enderecoEntregaInserido = requireActivity().contentResolver.insert(
            ContentProviderEntregas.ENDERECO_ENTREGA,
            entrega.toContentValues()
        )

        return enderecoEntregaInserido != null
    }


    private fun voltaListaEntrega() {
        findNavController().navigate(R.id.action_editarEntregasFragment_to_listaEntregasFragment)
    }




        override fun onLoaderReset(loader: Loader<Cursor>) {

            if (_binding == null) return
            binding.spinnerLocalidade.adapter = null
        }
    }