package edu.itesm.finalmamatierra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.itesm.finalmamatierra.databinding.ActivityLoginBinding
import edu.itesm.finalmamatierra.databinding.ActivityMainBinding

class Login : AppCompatActivity() {

    private lateinit var bind: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bind = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bind.root)

        auth = Firebase.auth


        login()
    }

    private fun login(){
        bind.entra.setOnClickListener {
            if(bind.contra.text.isNotEmpty() && bind.contra.text.isNotBlank() && bind.usuario.text.isNotEmpty() && bind.usuario.text.isNotBlank()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    bind.usuario.text.toString(),
                    bind.contra.text.toString()
                ).addOnCompleteListener{
                    if(it.isSuccessful){
                        Toast.makeText(this,"Bienvenido a Mamá Tierra!", Toast.LENGTH_LONG).show()
                        val correo = bind.usuario.text.toString()
                        val intento = Intent(this, MainActivity::class.java)
                        startActivity(intento)
                        bind.usuario.text.clear()
                        bind.contra.text.clear()
                    }else{
                        Toast.makeText(this,"Error en los datos!", Toast.LENGTH_LONG).show()
                        bind.usuario.text.clear()
                        bind.contra.text.clear()
                    }
                }
            }
            else{
                Toast.makeText(this,"Error en los datos o llena los datos!", Toast.LENGTH_LONG).show()
            }
        }
    }
    fun regis(view: View){
        val intento = Intent(this, Registrar::class.java)
        startActivity(intento)
    }

    override fun onStart(){
        super.onStart()
        val usuarioActivo = Firebase.auth.currentUser
        if (usuarioActivo != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

}