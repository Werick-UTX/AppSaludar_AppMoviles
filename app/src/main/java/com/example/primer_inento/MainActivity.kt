package com.example.primer_inento

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {

    private lateinit var tvSaludo: TextView
    private lateinit var etxtNombre: EditText
    private lateinit var btnSaludar: Button
    private lateinit var pbCarga: ProgressBar

    private lateinit var etxtApellido: EditText

    private lateinit var btnLimpiar: Button

    private var contador = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)){v,insets->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left,systemBars.top,systemBars.right,systemBars.bottom)
            insets
        }

        tvSaludo = findViewById(R.id.tvSaludo)
        etxtNombre = findViewById(R.id.etxtNombre)
        btnSaludar = findViewById(R.id.btnSaludar)
        pbCarga = findViewById(R.id.pbCarga)
        etxtApellido = findViewById(R.id.etxtApellido)
        btnLimpiar = findViewById(R.id.btnLimpiar)

                btnSaludar.setOnClickListener{ saludar()
                contador++}
                btnLimpiar.setOnClickListener { limpiar()}
        }


    private fun saludar(){
        val nombre =  etxtNombre.text.toString().trim()
        val apellido = etxtApellido.text.toString().trim()
        if(nombre.isEmpty()){
            Toast.makeText(this,"Escribe tu nombre primero",Toast.LENGTH_LONG).show()
            return
        }else if (nombre.length < 3){
            Toast.makeText(this, "El nombre es muy corto", Toast.LENGTH_SHORT).show()
        }
        else if (apellido.length < 3){
            Toast.makeText(this, "El apellido es muy corto", Toast.LENGTH_SHORT).show()
        }

        val nombreCompleto = if (apellido.isEmpty()) nombre else "$nombre $apellido"
        pbCarga.visibility = View.VISIBLE
        tvSaludo.text = ""
        btnSaludar.isEnabled = false

        Handler(Looper.getMainLooper()).postDelayed({

            pbCarga.visibility = View.GONE
            tvSaludo.text = "Hola, $nombreCompleto, es la vez $contador que nos saludamos"
            btnSaludar.isEnabled = true
        }, 1500)

    }
    private fun limpiar(){
        etxtNombre.setText("")
        etxtApellido.setText("")
        pbCarga.visibility = View.GONE
        tvSaludo.text = "Escribe tu nombre para empezar"
        btnSaludar.text = "Saludar"
        btnSaludar.isEnabled = true
        etxtNombre.requestFocus()
        contador = 0;
    }
}