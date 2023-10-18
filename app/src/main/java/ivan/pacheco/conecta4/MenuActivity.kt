package ivan.pacheco.conecta4

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ivan.pacheco.conecta4.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPlay.setOnClickListener {
            this.finish()
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }

        binding.btnExit.setOnClickListener {
            customAlertConfirm(this,"¿DESEAS SALIR DEL JUEGO?")
        }
    }

    /**
     * Alerta Confirmación personalizada
     */
    private fun customAlertConfirm(context: Context, message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.alertWarning))
        builder.setMessage(message)
        builder.setPositiveButton("SÍ") { _, _ ->
            (context as AppCompatActivity).finish()
        }
        builder.setNegativeButton("NO") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}