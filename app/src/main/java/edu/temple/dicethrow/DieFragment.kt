package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import kotlin.random.Random

class DieFragment : Fragment() {

    private val DIESIDE = "sidenumber"
    private val CURRENT_ROLL = "current_roll"

    private lateinit var dieTextView: TextView

    private var dieSides: Int = 6
    private var currentRoll: Int? = null

    lateinit var dieViewModel: DieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dieSides = it.getInt(DIESIDE, 6)
        }
        savedInstanceState?.let {
            currentRoll = it.getInt(CURRENT_ROLL)
        }
        dieViewModel = ViewModelProvider(requireActivity())[DieViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dieViewModel.getCurrentRoll().observe(viewLifecycleOwner) {
            dieTextView.text = it.toString()
        }

        if (dieViewModel.getCurrentRoll().value == null)
            dieViewModel.rollDie()
    }

    companion object {
        fun newInstance(sides: Int = 6) =
            DieFragment().apply {
                arguments = Bundle().apply {
                    putInt(DIESIDE, sides)
                }
            }
    }
}