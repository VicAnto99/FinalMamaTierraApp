package edu.itesm.finalmamatierra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.util.ArrayList

class Principal : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        database = FirebaseDatabase.getInstance()
        reference = database.getReference()

        var user = Firebase.auth.currentUser
        var id = user?.uid
        var array = ArrayList<Any>()
        var nom = ""
        var pat = ""

        reference.child("Users").child(id!!).get().addOnSuccessListener {
            for (ds in it.getChildren()) {
                val data = ds.value.toString()
                array.add(data)
            }
            nom = array[4].toString()
            pat = array[5].toString()
            var espacio = " "
            var nombre = findViewById<TextView>(R.id.nombre_principal)
            nombre.text = nom + espacio + pat
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.fl_wrapper, fragment)
        commit()
    }

    fun infoUser(view: View){
        val intento = Intent(this, Usuario::class.java)
        startActivity(intento)
    }
    fun logout (view: View){
        Firebase.auth.signOut()
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }


}