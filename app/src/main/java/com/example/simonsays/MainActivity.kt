package com.example.simonsays

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity()
{
    //No sirve de nada, pero la necesito para hacer el intent
    private var  simonSaysLength = 1

    /*private val getResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult())
    {

        if (it.resultCode == RESULT_OK){
            arrayOfColors = it.data?.getIntegerArrayListExtra(SimonSays.SimonColors.COLORS) as ArrayList<Int>

        }else if (it.resultCode == RESULT_CANCELED){
            val simonSaysLbl = findViewById<TextView>(R.id.LblSimonSays)
            simonSaysLbl.text = "An Error has occurred"
        }
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Los botones
        val btnReady = findViewById<Button>(R.id.btnStartGame)
        val btnReglas = findViewById<Button>(R.id.btnGameRules)

        //Al hacer click en nueva partida, crea una activity para hacer la partida
        btnReady.setOnClickListener {
             val intent = Intent(this, SimonSays::class.java)
             intent.putExtra(SimonSays.SimonColors.SIMONLENGTH, simonSaysLength)

            // getResult.launch(intent)
        }

        //Al hacer click en reglas, muestra las reglas. No estoy seguro de que vaya a hacer esta parte, solo si sobra tiempo.
        btnReglas.setOnClickListener {






        }

    }

}