package com.example.textinputlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    var lastValidName = ""

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        // Obtiene el componente por su id
        val nameInput = findViewById<TextInputLayout>(R.id.nameInput)
        val errorText = findViewById<TextView>(R.id.my_text)
        val containsNumbers = Regex("""\d+""")

        // Evento
        nameInput.editText?.doAfterTextChanged {
            // Si el nombre contiene numeros es incorrecto
            if (containsNumbers.containsMatchIn(nameInput.editText!!.text)) {
                // Muestra mensaje de que es incorrecto
                Snackbar.make(nameInput, "Nombre incorrecto", Snackbar.LENGTH_SHORT)
                    .setAction("Deshacer") {
                        // Hace el input el ultimo nombre correcto escrito
                        nameInput.editText!!.setText(this.lastValidName)

                        // Ancla la posicion del Snackbar
                        Snackbar.make(nameInput, "Ultimo Nombre Correcto!", Snackbar.LENGTH_LONG)
//                            .setAnchorView(nameInput)
                            .show()
                    }.show()
            } else {
                // Si es correcto lo guardamos como el ultimo nombre correcto ingresado
                this.lastValidName = nameInput.editText!!.text.toString()
            }
            // Log.d(TAG,"El valor ingresado por el usuario fue ${nameInput.editText!!.text}")
        }
    }
}