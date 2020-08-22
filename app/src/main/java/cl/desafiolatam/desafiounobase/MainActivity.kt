package cl.desafiolatam.desafiounobase

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_main.*
import java.security.Key

class MainActivity : AppCompatActivity() {

    lateinit var advance: Button
    lateinit var container: ConstraintLayout
    lateinit var preferences :SharedPreferences
    val filename = "cl.desafiolatam.desafiounobase.MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preferences =  getSharedPreferences(filename,Context.MODE_PRIVATE)
        advance = findViewById(R.id.login_button)
        container = findViewById(R.id.container)
        setUpListeners()
    }

    private fun setUpListeners() {
        advance.setOnClickListener {
            if (name_input.text!!.isNotEmpty()) {
                val intent: Intent
                if (hasSeenWelcome()) {
                    intent = Intent(this, HomeActivity::class.java)
                } else {
                    saveUsuario(name_input.text.toString())
                    intent = Intent(this, WelcomeActivity::class.java)
                }
                startActivity(intent)
            } else {
                Snackbar.make(container, "El nombre no puede estar vacío", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun hasSeenWelcome(): Boolean {

       // val preferences =  getPreferences(Context.MODE_PRIVATE)
        val stringSet = preferences.getStringSet("usuario", setOf())
        return stringSet.contains(name_input.text.toString())

    }
    private fun saveUsuario (userName : String){
        val setString = preferences.getStringSet("usuario", mutableSetOf())

        setString.add(userName)
        preferences.edit().putStringSet("usuario",setString).apply()

    }
}
