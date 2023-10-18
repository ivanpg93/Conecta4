package ivan.pacheco.conecta4

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import ivan.pacheco.conecta4.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    private lateinit var column1: List<ImageView>
    private lateinit var column2: List<ImageView>
    private lateinit var column3: List<ImageView>
    private lateinit var column4: List<ImageView>
    private lateinit var column5: List<ImageView>
    private lateinit var column6: List<ImageView>
    private lateinit var column7: List<ImageView>

    private lateinit var columns: List<List<ImageView>>

    // Inicializamos las puntuaciones y el turno. Si es true, empieza el rojo
    private var player1Score: Int = 0
    private var player2Score: Int = 0
    private var bColor: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Asignamos las columnas
        column1 = listOf(binding.f1, binding.e1, binding.d1, binding.c1, binding.b1, binding.a1)
        column2 = listOf(binding.f2, binding.e2, binding.d2, binding.c2, binding.b2, binding.a2)
        column3 = listOf(binding.f3, binding.e3, binding.d3, binding.c3, binding.b3, binding.a3)
        column4 = listOf(binding.f4, binding.e4, binding.d4, binding.c4, binding.b4, binding.a4)
        column5 = listOf(binding.f5, binding.e5, binding.d5, binding.c5, binding.b5, binding.a5)
        column6 = listOf(binding.f6, binding.e6, binding.d6, binding.c6, binding.b6, binding.a6)
        column7 = listOf(binding.f7, binding.e7, binding.d7, binding.c7, binding.b7, binding.a7)

        columns = listOf(column1, column2, column3, column4, column5, column6, column7)

        binding.btnRestart.setOnClickListener {
            restartGame()
        }

        binding.btnClearScore.setOnClickListener {
            restartScores()
        }

        // Columna 1
        column1.forEach { imageView ->
            imageView.setOnClickListener {
                if (checkGapsDown(column1, column1.indexOf(imageView))) {
                    addTokenList(column1.indexOf(imageView), column1)
                } else {
                    putToken(column1, imageView)
                }
            }
        }

        // Columna 2
        column2.forEach { imageView ->
            imageView.setOnClickListener {
                if (checkGapsDown(column2, column2.indexOf(imageView))) {
                    addTokenList(column2.indexOf(imageView), column2)
                } else {
                    putToken(column2, imageView)
                }
            }
        }

        // Columna 3
        column3.forEach { imageView ->
            imageView.setOnClickListener {
                if (checkGapsDown(column3, column3.indexOf(imageView))) {
                    addTokenList(column3.indexOf(imageView), column3)
                } else {
                    putToken(column3, imageView)
                }
            }
        }

        // Columna 4
        column4.forEach { imageView ->
            imageView.setOnClickListener {
                if (checkGapsDown(column4, column4.indexOf(imageView))) {
                    addTokenList(column4.indexOf(imageView), column4)
                } else {
                    putToken(column4, imageView)
                }
            }
        }

        // Columna 5
        column5.forEach { imageView ->
            imageView.setOnClickListener {
                if (checkGapsDown(column5, column5.indexOf(imageView))) {
                    addTokenList(column5.indexOf(imageView), column5)
                } else {
                    putToken(column5, imageView)
                }
            }
        }

        // Columna 6
        column6.forEach { imageView ->
            imageView.setOnClickListener {
                if (checkGapsDown(column6, column6.indexOf(imageView))) {
                    addTokenList(column6.indexOf(imageView), column6)
                } else {
                    putToken(column6, imageView)
                }
            }
        }

        // Columna 7
        column7.forEach { imageView ->
            imageView.setOnClickListener {
                if (checkGapsDown(column7, column7.indexOf(imageView))) {
                    addTokenList(column7.indexOf(imageView), column7)
                } else {
                    putToken(column7, imageView)
                }
            }
        }
    }

    /**
     * Colocar la ficha en la posición exacta
     */
    private fun putToken(columna: List<ImageView>, imageView: ImageView) {
        if (imageView.drawable.constantState == ContextCompat.getDrawable(
                this,
                R.drawable.empty_piece
            )?.constantState
        ) {
            if (bColor) {
                imageView.setImageResource(R.drawable.red_piece)
            } else {
                imageView.setImageResource(R.drawable.yellow_piece)
            }
            checkWin(columna.indexOf(imageView), columna)
            cambiarTurno()
        } else {
            customAlertWarning(this,"¡Ya hay una ficha colocada en esa posición!")
        }
    }

    /**
     * Colocar ficha en el tablero
     */
    private fun addTokenList(position: Int, columna: List<ImageView>) {
        if (bColor) {
            columna.subList(0, position).forEach { imageView ->
                if (imageView.drawable.constantState == ContextCompat.getDrawable(
                        this,
                        R.drawable.empty_piece
                    )?.constantState
                ) {
                    imageView.setImageResource(R.drawable.red_piece)
                    checkWin(columna.indexOf(imageView), columna)
                    cambiarTurno()
                    return
                }
            }
        } else {
            columna.subList(0, position).forEach { imageView ->
                if (imageView.drawable.constantState == ContextCompat.getDrawable(
                        this,
                        R.drawable.empty_piece
                    )?.constantState
                ) {
                    imageView.setImageResource(R.drawable.yellow_piece)
                    checkWin(columna.indexOf(imageView), columna)
                    cambiarTurno()
                    return
                }
            }
        }
    }

    /**
     * Verifica si hay fichas debajo de dónde quieres colocar la ficha actual
     */
    private fun checkGapsDown(list: List<ImageView>, position: Int): Boolean {
        var boolean = false
        list.subList(0, position).forEach { imageView ->
            boolean = imageView.drawable.constantState == ContextCompat.getDrawable(
                this,
                R.drawable.empty_piece
            )?.constantState
        }
        return boolean
    }

    /**
     * Reiniciar partida
     */
    private fun restartGame() {
        columns.forEach { list ->
            list.forEach { imageView ->
                imageView.setImageResource(R.drawable.empty_piece)
            }
        }
        bColor = true
        binding.turno.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
    }

    /**
     * Reiniciar puntuaciones
     */
    private fun restartScores() {
        player1Score = 0
        player2Score = 0
        setScore()
    }

    /**
     * Actualizar la puntuación
     */
    private fun setScore() {
        binding.contadorPlayer1.text = player1Score.toString()
        binding.contadorPlayer2.text = player2Score.toString()
    }

    /**
     * Cambiar el turno del jugador
     */
    private fun cambiarTurno() {
        if (bColor) {
            bColor = false
            binding.turno.setBackgroundColor(ContextCompat.getColor(this, R.color.yellow))
        } else {
            bColor = true
            binding.turno.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
        }
    }

    /**
     * Verificar si se ha ganado
     */
    private fun checkWin(position: Int, columna: List<ImageView>) {
        checkVertical(columna)
        checkHorizontal(position)
        checkDiagonal()
        checkReverseDiagonal()
    }

    /**
     * Verificar las columnas
     */
    private fun checkVertical(columna: List<ImageView>) {
        if (bColor) {
            val ficha = ContextCompat.getDrawable(this, R.drawable.red_piece)?.constantState
            for (i in 0 until columna.size - 3) {
                if (columna[i].drawable.constantState == ficha &&
                    columna[i + 1].drawable.constantState == ficha &&
                    columna[i + 2].drawable.constantState == ficha &&
                    columna[i + 3].drawable.constantState == ficha
                ) {
                    customAlertWin(this, "¡Has ganado Jugador rojo!")
                    player1Score++
                    setScore()
                }
            }
        } else {
            val ficha = ContextCompat.getDrawable(this, R.drawable.yellow_piece)?.constantState
            for (i in 0 until columna.size - 3) {
                if (columna[i].drawable.constantState == ficha &&
                    columna[i + 1].drawable.constantState == ficha &&
                    columna[i + 2].drawable.constantState == ficha &&
                    columna[i + 3].drawable.constantState == ficha
                ) {
                    customAlertWin(this, "¡Has ganado Jugador amarillo!")
                    player2Score++
                    setScore()
                }
            }
        }
    }

    /**
     * Verificar las filas
     */
    private fun checkHorizontal(position: Int) {
        if (bColor) {
            val ficha = ContextCompat.getDrawable(this, R.drawable.red_piece)?.constantState
            for (i in 0 until columns.size - 3) {
                if (columns[i][position].drawable.constantState == ficha &&
                    columns[i + 1][position].drawable.constantState == ficha &&
                    columns[i + 2][position].drawable.constantState == ficha &&
                    columns[i + 3][position].drawable.constantState == ficha
                ) {
                    customAlertWin(this, "¡Has ganado jugador rojo!")
                    player1Score++
                    setScore()
                }
            }
        } else {
            val ficha = ContextCompat.getDrawable(this, R.drawable.yellow_piece)?.constantState
            for (i in 0 until columns.size - 3) {
                if (columns[i][position].drawable.constantState == ficha &&
                    columns[i + 1][position].drawable.constantState == ficha &&
                    columns[i + 2][position].drawable.constantState == ficha &&
                    columns[i + 3][position].drawable.constantState == ficha
                ) {
                    customAlertWin(this, "¡Has ganado jugador amarillo!")
                    player2Score++
                    setScore()
                }
            }
        }
    }

    /**
     * Verifica la digonal arriba/derecha
     */
    private fun checkDiagonal() {
        if (bColor) {
            val ficha = ContextCompat.getDrawable(this, R.drawable.red_piece)?.constantState
            for (i in 0 until columns.size - 3) {
                for (j in 0 until columns[i].size - 3) {
                    if (columns[i][j].drawable.constantState == ficha &&
                        columns[i + 1][j + 1].drawable.constantState == ficha &&
                        columns[i + 2][j + 2].drawable.constantState == ficha &&
                        columns[i + 3][j + 3].drawable.constantState == ficha
                    ) {
                        customAlertWin(this, "¡Has ganado jugador rojo!")
                        player1Score++
                        setScore()
                    }
                }
            }
        } else {
            val ficha = ContextCompat.getDrawable(this, R.drawable.yellow_piece)?.constantState
            for (i in 0 until columns.size - 3) {
                for (j in 0 until columns[i].size - 3) {
                    if (columns[i][j].drawable.constantState == ficha &&
                        columns[i + 1][j + 1].drawable.constantState == ficha &&
                        columns[i + 2][j + 2].drawable.constantState == ficha &&
                        columns[i + 3][j + 3].drawable.constantState == ficha
                    ) {
                        customAlertWin(this, "¡Has ganado jugador amarillo!")
                        player2Score++
                        setScore()
                    }
                }
            }
        }
    }

    /**
     * Verifica la digonal inversa
     */
    private fun checkReverseDiagonal() {
        if (bColor) {
            val ficha = ContextCompat.getDrawable(this, R.drawable.red_piece)?.constantState
            for (i in 0 until columns.size - 3) {
                for (j in 3 until columns[i].size) { // Comienza desde la fila 3 (abajo) hasta la última fila
                    if (columns[i][j].drawable.constantState == ficha &&
                        columns[i + 1][j - 1].drawable.constantState == ficha &&
                        columns[i + 2][j - 2].drawable.constantState == ficha &&
                        columns[i + 3][j - 3].drawable.constantState == ficha
                    ) {
                        customAlertWin(this, "¡Has ganado jugador rojo!")
                        player1Score++
                        setScore()
                    }
                }
            }
        } else {
            val ficha = ContextCompat.getDrawable(this, R.drawable.yellow_piece)?.constantState
            for (i in 0 until columns.size - 3) {
                for (j in 3 until columns[i].size) { // Comienza desde la fila 3 (abajo) hasta la última fila
                    if (columns[i][j].drawable.constantState == ficha &&
                        columns[i + 1][j - 1].drawable.constantState == ficha &&
                        columns[i + 2][j - 2].drawable.constantState == ficha &&
                        columns[i + 3][j - 3].drawable.constantState == ficha
                    ) {
                        customAlertWin(this, "¡Has ganado jugador amarillo!")
                        player2Score++
                        setScore()
                    }
                }
            }
        }
    }

    /**
     * Alerta Warning personalizado
     */
    private fun customAlertWarning(context: Context, message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.alertWarning))
        builder.setMessage(message)
        builder.setPositiveButton("OK", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    /**
     * Alerta Victoria personalizado
     */
    private fun customAlertWin(context: Context, message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.win))
        builder.setMessage(message)
        builder.setPositiveButton("VOLVER A JUGAR") { _, _ ->
            restartGame()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}