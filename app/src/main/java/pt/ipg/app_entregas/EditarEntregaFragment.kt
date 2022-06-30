package pt.ipg.app_entregas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pt.ipg.app_entregas.databinding.FragmentEditarEntregaBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_editar_entrega.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditarEntregaFragment  : Fragment() {
    private var _binding: FragmentEditarEntregaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditarEntregaBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editar_entrega, container, false)
    }

    companion object {

    }
}