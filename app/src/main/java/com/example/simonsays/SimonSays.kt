package com.example.simonsays

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class SimonSays:AppCompatActivity()
{
    //Necesarios para hacer el intent, y por si luego quiero cambiar a una activity que devuelva un valor.
    object SimonColors{
        const val SIMONLENGTH = "SIMON LENGTH"
    }

    /*
      La idea detras de esto es:
      --> Se guarda aleatoreamente un valor entre 0 y 3 en el array.
      --> Se muestra en pantalla.
      --> Se deja al jugador elegir
      --> El jugador elige
      --> Se comprueba el resultado
                            --> El valor de comprobación siempre a de estar en la posición 0 del array.
                                Una vez comprobado ya sea correcto o incorrecto se elimina, dejando que
                                el siguiente valor pase a estar en la possición 0.

      --> Si es correcto se elimina el valor actual y continua con el siguiente.
                            --> En caso de haber llegado a todos los valores correctos
                                (haber seguido correctamente la secuenca) se aumenta en 1
                                la cantidad de numeros a generar y se repite otra vez el bucle.

      --> En caso de fallar se muestra un mensaje "¡Has perdido!, mejor suerte la proxima vez 😢" y se termina el juego.
    */


    /*
    El Array donde se guardarán los valores.
    */

    private var arrayOfColors = ArrayList<Int>()
    //Array de Jugadores y las rondas superadas.

    private var games = mutableListOf<UserData>()

    //Nombre del jugador

    private var namePlayer = ""
    //Contador de la longitud de simon says.

    private var simonSaysLength = 1
    /*
    Función principal, deshabilita los bottones, encuentra tantos colores aleatorios como simonSaysLength
    sea de grande (empezando por 1), mientras va encontrando cada color lo muestra por pantalla, termina volviendo
    a habilitar los bottones para que el jugador repita la secuencia.
    */
    private fun symonSaysFunc(btnYellow: Button, btnRed: Button,
                              btnBlue: Button, btnGreen: Button)
    {
        val txtTurn = findViewById<TextView>(R.id.LblSimonSays)
        txtTurn.text = "Es el turno de Simon"
        changeButtons(false,btnYellow, btnRed, btnBlue, btnGreen)
        lifecycleScope.launch {
            for (i in 0 until simonSaysLength)
            {
                arrayOfColors.add(Random.nextInt(4))
                changeColorToLuminated(arrayOfColors[i],btnYellow, btnRed, btnBlue, btnGreen)
                delay(1000)
                changeColorToNormal(arrayOfColors[i],btnYellow, btnRed, btnBlue, btnGreen)
                delay(500)
            }
            changeButtons(true,btnYellow, btnRed, btnBlue, btnGreen)
            txtTurn.text = "Es tu turno"
        }

    }

    /*
     Cambia el estado del boton (habilitado/deshabilitado) dependiendo del valor de entrada (true/false).
    */
    private fun changeButtons(booleanButton: Boolean, btnYellow: Button, btnRed: Button,
                              btnBlue: Button, btnGreen: Button){
        btnYellow.isEnabled = booleanButton
        btnRed.isEnabled = booleanButton
        btnBlue.isEnabled = booleanButton
        btnGreen.isEnabled = booleanButton
    }

    /*
    Cambia el color del botton para que parezca que se ilumina.
    */
    private fun changeColorToLuminated(color: Int, btnYellow: Button, btnRed: Button,
                                       btnBlue: Button, btnGreen: Button)
    {
        when (color){
            0 -> btnYellow.setBackgroundColor(Color.rgb(242,242,108)) //#F2F26C
            1 -> btnRed.setBackgroundColor(Color.rgb(255,127,127)) //#FF7F7F
            2 -> btnBlue.setBackgroundColor(Color.rgb(134,134,255)) //#8686FF
            3 -> btnGreen.setBackgroundColor(Color.rgb(102,202,102)) //#66CA66
        }
    }

    /*
     Cambia el color del botton para que vuelva al color normal.
    */
    private fun changeColorToNormal(color: Int, btnYellow: Button, btnRed: Button,
                                    btnBlue: Button, btnGreen: Button)
    {
           btnYellow.setBackgroundColor(Color.rgb(224,224,0))
           btnRed.setBackgroundColor(Color.RED)
           btnBlue.setBackgroundColor(Color.BLUE)
           btnGreen.setBackgroundColor(Color.rgb(0,128,0))

    }

    /*
     Ilumina el color del boton seleccionado y elimina la primera posición actual de la
     lista para que cambie el valor al siguiente generado.
    */

    private fun illuminateColor(btnYellow: Button, btnRed: Button,
                                btnBlue: Button, btnGreen: Button)
    {
        lifecycleScope.launch {
            changeColorToLuminated(arrayOfColors[0],btnYellow, btnRed, btnBlue, btnGreen)
            delay(250)
            changeColorToNormal(arrayOfColors[0],btnYellow, btnRed, btnBlue, btnGreen)
            delay(250)
            arrayOfColors.remove(arrayOfColors[0])

            if (arrayOfColors.size == 0){
                simonSaysLength ++
                symonSaysFunc(btnYellow, btnRed, btnBlue, btnGreen)
            }
        }
    }

    /*
     Termina el juego en caso de fallar o de pressionar el boton de terminar.
    */

    private fun endOfSimonSays()
    {
        val textSimonSays = findViewById<TextView>(R.id.LblSimonSays)
        textSimonSays.text = "¡Has perdido! mejor suerte la proxima vez 😢"
        games = FilesManager.getUsersStats(this) as ArrayList<UserData>
        val userGame = UserData(namePlayer,simonSaysLength)
        games.add(userGame)
        FilesManager.saveUsersStats(this,games)
        finish()
        lifecycleScope.launch {
            delay(4000)
            finish()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simon_says)

        val intent = intent
        namePlayer = intent.getSerializableExtra(SimonColors.SIMONLENGTH) as String

        if(!FilesManager.comproveFile(this))
        {
            val userData = UserData("Exemple" , 0)
            games.add(userData)
            FilesManager.saveUsersStats(this,games)
        }

        val btnEnd = findViewById<Button>(R.id.btnEndGame)
        val btnRedo = findViewById<Button>(R.id.btnReDoGame)

        //Botones del juego
        val btnYellow = findViewById<Button>(R.id.btnYellow)
        val btnRed = findViewById<Button>(R.id.btnRed)
        val btnBlue = findViewById<Button>(R.id.btnBlue)
        val btnGreen = findViewById<Button>(R.id.btnGreen)

        //Si se presiona el boton de reiniciar, setea a valores iniciales.
        btnRedo.setOnClickListener {
            simonSaysLength = 1
            arrayOfColors.clear()
            symonSaysFunc(btnYellow, btnRed, btnBlue, btnGreen)
        }

        //Si se presiona el boton de finalizar termina la partida.
        btnEnd.setOnClickListener {


            val userGame = UserData(namePlayer,simonSaysLength)
            games.add(userGame)
            games = FilesManager.getUsersStats(this) as ArrayList<UserData>
            FilesManager.saveUsersStats(this,games)
            finish()
        }

        //Si se presiona el boton amarillo comprueba que sea el correcto, si es así ilumina el color y sigue,
        //sino termina la partida.
        btnYellow.setOnClickListener{
            if (arrayOfColors[0] == 0)
            {
                illuminateColor(btnYellow, btnRed, btnBlue, btnGreen)
            }else
            {
                endOfSimonSays()
            }
        }

        //Si se presiona el boton rojo comprueba que sea el correcto, si es así ilumina el color y sigue,
        //sino termina la partida.
        btnRed.setOnClickListener{
            if (arrayOfColors[0] == 1)
            {
                illuminateColor(btnYellow, btnRed, btnBlue, btnGreen)
            }else
            {
                endOfSimonSays()
            }
        }

        //Si se presiona el boton azul comprueba que sea el correcto, si es así ilumina el color y sigue,
        //sino termina la partida.
        btnBlue.setOnClickListener{
            if (arrayOfColors[0] == 2)
            {
                illuminateColor(btnYellow, btnRed, btnBlue, btnGreen)
            }else
            {
                endOfSimonSays()
            }
        }

        //Si se presiona el boton verde comprueba que sea el correcto, si es así ilumina el color y sigue,
        //sino termina la partida.
        btnGreen.setOnClickListener{
            if (arrayOfColors[0] == 3)
            {
                illuminateColor(btnYellow, btnRed, btnBlue, btnGreen)
            }else
            {
                endOfSimonSays()
            }
        }
        //Comienza el bucle del juego, esto 100% se va a tener que cambiar porque seguro que esto aquí genera errores.
        lifecycleScope.launch {
            delay(1000)
            symonSaysFunc(btnYellow, btnRed, btnBlue, btnGreen)
        }

    }
}