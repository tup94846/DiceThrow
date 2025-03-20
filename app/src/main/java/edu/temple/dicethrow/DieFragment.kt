package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlin.random.Random

class DieFragment : Fragment() {

    private val DIESIDE = "sidenumber"
    private val CURRENT_ROLL = "current_roll"

    private lateinit var dieTextView: TextView

    private var dieSides: Int = 6
    private var currentRoll: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dieSides = it.getInt(DIESIDE, 6)
        }
        savedInstanceState?.let {
            currentRoll = it.getInt(CURRENT_ROLL)
        }
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

        if (currentRoll == null) {
            throwDie()
        } else {
            dieTextView.text = currentRoll.toString()
        }

        view.setOnClickListener {
            throwDie()
        }
    }

    fun throwDie() {
        currentRoll = Random.nextInt(dieSides) + 1
        dieTextView.text = currentRoll.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        currentRoll?.let { outState.putInt(CURRENT_ROLL, it) }
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