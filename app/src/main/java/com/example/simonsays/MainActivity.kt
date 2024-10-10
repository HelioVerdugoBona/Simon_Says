package com.example.simonsays

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity()
{
    //No sirve de nada, pero la necesito para hacer el intent
    private var  simonSaysLength = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Los botones
        val btnReady = findViewById<Button>(R.id.btnStartGame)
        val btnReglas = findViewById<Button>(R.id.btnGameRules)
        val txtNom = findViewById<EditText>(R.id.TxtNombre)
        val txtNomMal = findViewById<TextView>(R.id.LblNombe)


        //Al hacer click en nueva partida, crea una activity para hacer la partida
        btnReady.setOnClickListener {

            if (txtNom.text.toString() != "") {
                val intent = Intent(this, SimonSays::class.java)
                intent.putExtra(SimonSays.SimonColors.SIMONLENGTH, txtNom.text.toString())
                startActivity(intent)
                txtNom.text.clear()
            }else
            {
                txtNomMal.text = "¡Introduce un Nombre!"
            }

        }

        //Al hacer click en reglas, muestra las reglas.
        btnReglas.setOnClickListener {
            val intent = Intent(this, GameRules::class.java)
            intent.putExtra(GameRules.Gamerules.RULESLENGTH, simonSaysLength)
            startActivity(intent)
        }

    }

}