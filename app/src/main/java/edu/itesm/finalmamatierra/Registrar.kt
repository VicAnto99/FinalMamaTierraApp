package edu.itesm.finalmamatierra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import edu.itesm.finalmamatierra.databinding.ActivityRegistrarBinding

class Registrar : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var analytics: FirebaseAnalytics
    private lateinit var bundle: Bundle
    private lateinit var bind: ActivityRegistrarBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)

        analytics = FirebaseAnalytics.getInstance(this)
        bundle = Bundle()
        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Users")
        bind = ActivityRegistrarBinding.inflate(layoutInflater)
        setContentView(bind.root)
        auth = Firebase.auth
        registraEntra()
    }

    fun registraEntra(){
        bind.registrarYEntrar.setOnClickListener {
            if (bind.nameUser.text.isNotEmpty() && bind.contrasenaUsuario.text.isNotEmpty() && bind.apellidoMaterno.text.isNotEmpty() && bind.apellidoPaterno.text.isNotEmpty() && bind.correoUser.text.isNotEmpty()){
                val nombre = findViewById<EditText>(R.id.name_user).text
                val paterno = findViewById<EditText>(R.id.apellido_paterno).text
                val materno = findViewById<EditText>(R.id.apellido_materno).text
                val correo = findViewById<EditText>(R.id.correo_user).text
                val contrasena = findViewById<EditText>(R.id.contrasena_usuario).text
                val foto = ""
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    bind.correoUser.text.toString(), //usuario y password
                    bind.contrasenaUsuario.text.toString()
                ).addOnCompleteListener{
                    if(it.isSuccessful){
                        usuarioCreado()
                        val nUser = User(nombre.toString(), paterno.toString(), materno.toString(), correo.toString(), contrasena.toString(), foto)
                        val user = Firebase.auth.currentUser
                        val id = user?.uid
                        reference.child(id!!).setValue(nUser)
                        bundle.putString("edu_itesm_Mamatierra_main", "added_User")
                        analytics.logEvent("registrar", bundle)
                        val intento = Intent(this, MainActivity::class.java)
                        startActivity(intento)
                        Toast.makeText(this, "Usuario creado con éxito!", Toast.LENGTH_LONG).show()
                        bind.nameUser.text.clear()
                        bind.apellidoPaterno.text.clear()
                        bind.apellidoMaterno.text.clear()
                        bind.correoUser.text.clear()
                        bind.contrasenaUsuario.text.clear()
                    }
                }.addOnFailureListener{
                    Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun usuarioCreado(){
        val builder = AlertDialog.Builder(this)
        with(builder){
            setTitle("Usuario Mamá Tierra")
            setMessage("Usuario creado con éxito!")
            setPositiveButton("Ok", null)
            show()
        }
    }
}