package com.example.simonsays

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.random.Random

class SimonSays:AppCompatActivity()
{
    object SimonColors{
        //const val COLORS = "COLORS"
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

    //Contador de la longitud de simon says.
    private var simonSaysLength = 1

    //Botones del juego
    private val btnYellow = findViewById<Button>(R.id.btnYellow)
    private val btnRed = findViewById<Button>(R.id.btnRed)
    private val btnBlue = findViewById<Button>(R.id.btnBlue)
    private val btnGreen = findViewById<Button>(R.id.btnGreen)

    /*
    Función principal, deshabilita los bottones, encuentra tantos colores aleatorios como simonSaysLength
    sea de grande (empezando por 1), mientras va encontrando cada color lo muestra por pantalla, termina volviendo
    a habilitar los bottones para que el jugador repita la secuencia.
    */
    @OptIn(DelicateCoroutinesApi::class)
    private fun symonSaysFunc()
    {
        val txtTurn = findViewById<TextView>(R.id.LblSimonSays)
        txtTurn.text = "Es el turno de Simon"
        changeButtons(false)
        for (i in 0..simonSaysLength)
        {
            arrayOfColors.add(Random.nextInt(4))

            GlobalScope.launch {
                changeColorToLuminated(arrayOfColors[i])
                Thread.sleep(4000)
                changeColorToNormal(arrayOfColors[i])
            }
        }
        changeButtons(true)
        txtTurn.text = "Es tu turno"
    }

    /*
     Cambia el estado del boton (habilitado/deshabilitado) dependiendo del valor de entrada (true/false).
    */
    private fun changeButtons(booleanButton: Boolean){
        btnYellow.isEnabled = booleanButton
        btnRed.isEnabled = booleanButton
        btnBlue.isEnabled = booleanButton
        btnGreen.isEnabled = booleanButton
    }

    /*
    Cambia el color del botton para que parezca que se ilumina.
    */
    private fun changeColorToLuminated(color: Int)
    {
        when (color){
            0 -> btnYellow.setBackgroundColor(Color.rgb(242,242,108)) //#F2F26CFF
            1 -> btnRed.setBackgroundColor(Color.rgb(255,127,127)) //#FF7F7F
            2 -> btnBlue.setBackgroundColor(Color.rgb(134,134,255)) //#8686FF
            3 -> btnGreen.setBackgroundColor(Color.rgb(102,202,102)) //#66CA66
        }
    }

    /*
     Cambia el color del botton para que vuelva al color normal.
    */
    private fun changeColorToNormal(color: Int)
    {
        when (color){
            0 -> btnYellow.setBackgroundColor(Color.YELLOW)
            1 -> btnRed.setBackgroundColor(Color.RED)
            2 -> btnBlue.setBackgroundColor(Color.BLUE)
            3 -> btnGreen.setBackgroundColor(Color.GREEN)
        }
    }

    /*
     Ilumina el color del boton seleccionado y elimina la primera posición actual de la
     lista para que cambie el valor al siguiente generado.
    */
    @OptIn(DelicateCoroutinesApi::class)
    private fun illuminateColor()
    {
        GlobalScope.launch {
            changeColorToLuminated(arrayOfColors[0])
            Thread.sleep(4000)
            changeColorToNormal(arrayOfColors[0])
        }
        arrayOfColors.remove(arrayOfColors[0])

        if (arrayOfColors.size == 0){
            simonSaysLength ++
            symonSaysFunc()
        }
    }

    /*
     Termina el juego en caso de fallar o de pressionar el boton de terminar.
    */

    private fun endOfSimonSays()
    {
        val textSimonSays = findViewById<TextView>(R.id.LblSimonSays)
        textSimonSays.text = "¡Has perdido! mejor suerte la proxima vez 😢"
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simon_says)

        val btnEnd = findViewById<Button>(R.id.btnEndGame)
        val btnRedo = findViewById<Button>(R.id.btnReDoGame)

        //Si se presiona el boton de reiniciar, setea a valores iniciales.
        btnRedo.setOnClickListener {
            simonSaysLength = 1
            arrayOfColors.clear()
            symonSaysFunc()
        }

        //Si se presiona el boton de finalizar termina la partida.
        btnEnd.setOnClickListener {
            endOfSimonSays()
        }

        //Si se presiona el boton amarillo comprueba que sea el correcto, si es así ilumina el color y sigue,
        //sino termina la partida.
        btnYellow.setOnClickListener{
            if (arrayOfColors[0] == 0)
            {
                illuminateColor()
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
                illuminateColor()
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
                illuminateColor()
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
                illuminateColor()
            }else
            {
                endOfSimonSays()
            }
        }
        //Comienza el bucle del juego, esto 100% se va a tener que cambiar porque seguro que esto aquí genera errores.
        symonSaysFunc()
    }
}