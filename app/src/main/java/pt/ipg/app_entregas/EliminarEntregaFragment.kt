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
import pt.ipg.app_entregas.databinding.FragmentEliminarEntregaBinding


class EliminarEntregaFragment : Fragment() {
    private var _binding: FragmentEliminarEntregaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var entrega: Entrega
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEliminarEntregaBinding.inflate(inflater, container, false)
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

        entrega = EliminarEntregaFragmentArgs.fromBundle(arguments!!).entrega

        binding.textViewQuantidadeEntrega.text = (entrega.quantidade).toString()
        binding.textViewDataEntrega.text = entrega.data
        binding.textViewEntregaLocalidade.text = entrega.localidade.nome
        binding.textViewClienteEntrega.text = entrega.cliente

    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_eliminar -> {
                eliminaEntrega()
                true
            }
            R.id.action_cancelar -> {
                voltaListaEntrega()
                true
            }
            else -> false
        }

    private fun eliminaEntrega() {
        val alertDialog = AlertDialog.Builder(requireContext())

        alertDialog.apply {
            setTitle(R.string.delete)
            setMessage(R.string.sure)
            setNegativeButton(android.R.string.cancel, DialogInterface.OnClickListener { dialogInterface, i ->  })
            setPositiveButton(R.string.delete, DialogInterface.OnClickListener { dialogInterface, i -> confirmaEliminarEntrega() })
            show()
        }
    }

    private fun confirmaEliminarEntrega() {
        val enderecoEntrega = Uri.withAppendedPath(ContentProviderEntregas.ENDERECO_ENTREGA, "${entrega.id}")
        val registosEliminados = requireActivity().contentResolver.delete(enderecoEntrega, null, null)

        if (registosEliminados != 1) {
            Snackbar.make(
                binding.textViewQuantidadeEntrega,
                R.string.erro,
                Snackbar.LENGTH_INDEFINITE
            ).show()
            return
        }

        Toast.makeText(requireContext(), R.string.done, Toast.LENGTH_LONG).show()
        voltaListaEntrega()
    }

    private fun voltaListaEntrega() {
        val acao = EliminarEntregaFragmentDirections.actionEliminarEntregaFragmentToListaEntregasFragment()
        findNavController().navigate(acao)
    }
}