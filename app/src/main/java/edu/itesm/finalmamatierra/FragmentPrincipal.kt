package edu.itesm.finalmamatierra

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import edu.itesm.finalmamatierra.databinding.ActivityRegistrarBinding
import edu.itesm.finalmamatierra.databinding.FragmentPrincipalBinding
import kotlinx.android.synthetic.main.fragment_principal.*
import java.util.ArrayList

class FragmentPrincipal : Fragment() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var bind: FragmentPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

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
            Log.i("usuario", array.toString())
            nom = array[5].toString()
            pat = array[6].toString()
            var espacio = " "
            var nombre = getView()?.findViewById<TextView>(R.id.nombre_principal)
            nombre?.text = nom + espacio + pat
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

        return inflater.inflate(R.layout.fragment_principal, container, false)
    }


    fun infoUser(view: View){
        val intento = Intent(activity, Usuario::class.java)
        startActivity(intento)
    }
    fun logout (view: View){
        Firebase.auth.signOut()
        val intento = Intent(activity, Login::class.java)
        requireActivity().startActivity(intento)
        requireActivity().finish()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        misCursos.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_fragmentPrincipal_to_fragmentMisCursos)
        }
        otrosCursos.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_fragmentPrincipal_to_fragmentOtrosCursos)
        }
    }

}