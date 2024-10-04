package com.example.simonsays

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity()
{
    private var  simonSaysLength = 0

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

        val btnReady = findViewById<Button>(R.id.btnStartGame)


       btnReady.setOnClickListener {

             val intent = Intent(this, SimonSays::class.java)
             intent.putExtra(SimonSays.SimonColors.SIMONLENGTH, simonSaysLength)


            // getResult.launch(intent)

        }

    }

}