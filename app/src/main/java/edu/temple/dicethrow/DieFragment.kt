package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlin.random.Random

class DieFragment : Fragment() {

    val DIESIDE = "sidenumber"
    val CURRENT_ROLL = "current_roll" // Key for saving state

    lateinit var dieTextView: TextView

    var dieSides: Int = 6
    var currentRoll: Int? = null // Store the rolled number

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dieSides = it.getInt(DIESIDE, 6)
        }
        savedInstanceState?.let {
            currentRoll = it.getInt(CURRENT_ROLL) // Restore previous roll if exists
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
            dieTextView.text = currentRoll.toString() // Restore previous roll
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
        currentRoll?.let { outState.putInt(CURRENT_ROLL, it) } // Save current roll
    }
}
