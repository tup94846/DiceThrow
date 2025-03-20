package edu.temple.dicethrow

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize two dice fragments with possibly different side counts
        val dieFragment1 = DieFragment.newInstance(6)  // 6-sided die
        val dieFragment2 = DieFragment.newInstance(20) // 20-sided die

        // Add the fragments dynamically to the container views
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView1, dieFragment1)
            .replace(R.id.fragmentContainerView2, dieFragment2)
            .commit()

        val rollButton: Button = findViewById(R.id.rollButton)
        rollButton.setOnClickListener {
            // Roll both dice
            dieFragment1.throwDie()
            dieFragment2.throwDie()
        }
    }
}
