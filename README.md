# SimonSays

## Idea detras del codigo:

### Empezar una nueva partida

      --> Se guarda aleatoreamente un valor entre 0 y 3 en el array.
      --> Se muestra en pantalla.
      --> Se deja al jugador elegir.
      --> El jugador elige.
      --> Se comprueba el resultado.
                            --> El valor de comprobación siempre a de estar en la posición 0 del array.
                                Una vez comprobado ya sea correcto o incorrecto se elimina, dejando que
                                el siguiente valor pase a estar en la possición 0.

      --> Si es correcto se elimina el valor actual y continua con el siguiente.
                            --> En caso de haber llegado a todos los valores correctos
                                (haber seguido correctamente la secuenca) se aumenta en 1
                                la cantidad de numeros a generar y se repite otra vez el bucle.

      --> En caso de fallar se muestra un mensaje "¡Has perdido!, mejor suerte la proxima vez 😢" y se termina el juego.

### Mirar las reglas del juego.

      -> Se mostraran las Reglas del juego enumeradas y con imagenes.

### Posibles Mejoras:

1. Mejorar la forma visual de las Reglas.
   
2. Añadir un dibujo que represente a "Simon" y que diga el color que ha "seleccionado".

3. Mejorar la forma visual del menu de inicio.
