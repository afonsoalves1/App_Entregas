package pt.ipg.app_entregas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import pt.ipg.app_entregas.databinding.FragmentEditarClienteBinding


class EditarClienteFragment : Fragment() {
    private var _binding: FragmentEditarClienteBinding? = null

    private val binding get() = _binding!!

    private var cliente: Cliente? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditarClienteBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity() as MainActivity
        activity.fragment  =this
        activity.idMenuAtual = R.menu.menu_edicao

        if (arguments != null) {
            cliente = EditarClienteFragmentArgs.fromBundle(arguments!!).cliente

            if (cliente != null) {
                binding.editTextNome.setText(cliente!!.nome)
                binding.editTextContacto.setText(cliente!!.contacto)
                binding.editTextIdade.setText(cliente!!.idade)
                binding.editTextMorada.setText(cliente!!.morada)
            }
        }
    }



    companion object {

    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_guardar -> {
                guardar()
                true
            }
            R.id.action_cancelar -> {
                voltaListaClientes()
                true
            }
            else -> false
        }

    private fun guardar() {
        val nome = binding.editTextNome.text.toString()
        if (nome.isBlank()) {
            binding.editTextNome.error = getString(R.string.campo_obrigatorio)
            binding.editTextNome.requestFocus()
            return
        }

        val contacto = binding.editTextContacto.text.toString()
        if (contacto.isBlank()) {
            binding.editTextContacto.error = getString(R.string.campo_obrigatorio)
            binding.editTextContacto.requestFocus()
            return
        }

        val idade = binding.editTextIdade.text.toString()
        if (idade.isBlank()) {
            binding.editTextIdade.error = getString(R.string.campo_obrigatorio)
            binding.editTextIdade.requestFocus()
            return
        }
        val morada = binding.editTextMorada.text.toString()
        if (morada.isBlank()) {
            binding.editTextMorada.error = getString(R.string.campo_obrigatorio)
            binding.editTextMorada.requestFocus()
            return
        }
    }

    private fun voltaListaClientes() {
        findNavController().navigate(R.id.action_editarClienteFragment_to_listaClienteFragment)
    }
}