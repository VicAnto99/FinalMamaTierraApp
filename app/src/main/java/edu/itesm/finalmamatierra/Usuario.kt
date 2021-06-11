package edu.itesm.finalmamatierra

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import edu.itesm.finalmamatierra.databinding.ActivityUsuarioBinding
import java.io.ByteArrayOutputStream
import java.util.*

class Usuario : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var analytics: FirebaseAnalytics
    private lateinit var bundle: Bundle
    private lateinit var bind: ActivityUsuarioBinding
    private lateinit var auth: FirebaseAuth
    private val RICapture = 10007
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var foto: Bitmap
    var PICK_IMAGE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)

        analytics = FirebaseAnalytics.getInstance(this)
        bundle = Bundle()
        database = FirebaseDatabase.getInstance()
        reference = database.getReference()
        bind = ActivityUsuarioBinding.inflate(layoutInflater)
        setContentView(bind.root)
        auth = Firebase.auth

        var user = Firebase.auth.currentUser
        var id = user?.uid
        var array = ArrayList<Any>()
        var nom = ""
        var pat = ""
        var con = ""
        var mat = ""
        var cor = ""
        var fot = ""

        reference.child("Users").child(id!!).get().addOnSuccessListener {
            for (ds in it.getChildren()) {
                val data = ds.value.toString()
                array.add(data)
            }
            con = array[0].toString()
            cor = array[1].toString()
            fot = array[2].toString()
            mat = array[3].toString()
            nom = array[4].toString()
            pat = array[5].toString()
            var paterno = findViewById<TextView>(R.id.paterno_ajustes)
            var nombre = findViewById<TextView>(R.id.nombre_ajustes)
            var materno = findViewById<TextView>(R.id.materno_ajustes)
            var correo = findViewById<TextView>(R.id.correo_ajustes)
            var contraseña = findViewById<TextView>(R.id.contra_ajustes)
            var fotografia = findViewById<ImageView>(R.id.foto_user)
            contraseña.text = con
            correo.text = cor
            materno.text = mat
            nombre.text = nom
            paterno.text = pat
            Glide.with(this)
                .load(fot)
                .placeholder(R.drawable.mamatierra1)
                .error(R.drawable.mamatierra1)
                .circleCrop()
                .into(fotografia)
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

        guarda()
    }

    fun guarda(){
        bind.guardarCambio.setOnClickListener {

            database = FirebaseDatabase.getInstance()
            reference = database.getReference()

            var user = Firebase.auth.currentUser
            var id = user?.uid

            var paterno = findViewById<TextView>(R.id.paterno_ajustes).text
            var nombre = findViewById<TextView>(R.id.nombre_ajustes).text
            var materno = findViewById<TextView>(R.id.materno_ajustes).text
            var correo = findViewById<TextView>(R.id.correo_ajustes).text
            var contrasena = findViewById<TextView>(R.id.contra_ajustes).text
            if( foto != null){
                val baos = ByteArrayOutputStream()
                foto.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                val data = baos.toByteArray()
                val fileName = UUID.randomUUID().toString()
                val storage_reference = FirebaseStorage.getInstance().getReference("/UserPhotos/$fileName")
                val uploadTask = storage_reference.putBytes(data)
                uploadTask.addOnSuccessListener {
                    storage_reference.downloadUrl.addOnSuccessListener {
                        val nUser = User(
                            nombre.toString(),
                            paterno.toString(),
                            materno.toString(),
                            correo.toString(),
                            contrasena.toString(),
                            it.toString()
                        )
                        reference.child("Users").child(id!!).setValue(nUser)
                        Toast.makeText(this, "Foto tomada!", Toast.LENGTH_LONG).show()
                    }
                }.addOnFailureListener{
                    Toast.makeText(this, "Error al subir el usuario!", Toast.LENGTH_LONG).show()
                }
            }
            Toast.makeText(this, "Cambio guardado con éxito!", Toast.LENGTH_LONG).show()
            val intento = Intent(this, Principal::class.java)
            startActivity(intento)
        }
    }

    public fun foto(view: View){
        val tomarFoto = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(tomarFoto, RICapture)
    }
    public fun gal(view: View){
        //val tomarFoto = Intent(MediaStore.EXTRA_MEDIA_ALBUM)
        //startActivityForResult(tomarFoto, RICapture)
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RICapture && resultCode == RESULT_OK){
            foto = data?.extras?.get("data") as Bitmap
        }
    }
}