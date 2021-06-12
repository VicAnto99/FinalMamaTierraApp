package edu.itesm.finalmamatierra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import edu.itesm.finalmamatierra.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun infoUser(view: View){
        val intento = Intent(this, Usuario::class.java)
        startActivity(intento)
    }
    fun logout (view: View){
        Firebase.auth.signOut()
        startActivity(Intent(this, Login::class.java))
        finish()
    }

    fun pagos (view: View){
        val intento = Intent(this, ActivityPagos::class.java)
        startActivity(intento)
    }
}