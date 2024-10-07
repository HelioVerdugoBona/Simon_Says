package com.example.simonsays

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class GameRules:AppCompatActivity()
{
    object Gamerules{
        const val RULESLENGTH = "SIMON LENGTH"
    }

    /*
        Se mostraran las Reglas del juego enumeradas y con imagenes.
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_rules)

        val btnExit = findViewById<Button>(R.id.btnVolver)
        btnExit.setOnClickListener {
            finish()
        }

    }
}