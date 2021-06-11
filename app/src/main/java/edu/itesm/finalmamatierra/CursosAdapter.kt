package edu.itesm.finalmamatierra

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class CursosAdapter(private val cursos: List<Cursos>): RecyclerView.Adapter<CursosAdapter.CursoViewHolder>() {

    inner class CursoViewHolder(val renglon: View): RecyclerView.ViewHolder(renglon){
        //var foto = renglon.findViewById<ImageView>(R.id.foto)

        var nombre = renglon.findViewById<TextView>(R.id.nombre)
        var instructor = renglon.findViewById<TextView>(R.id.instructor)
        var duracion = renglon.findViewById<TextView>(R.id.duracion)

        fun bind(property: Cursos) {
            lateinit var database: FirebaseDatabase
            var reference: DatabaseReference
            val add_btn = renglon.findViewById<Button>(R.id.agregar)

            val usuario = Firebase.auth.currentUser

            nombre.text = property.nombre
            instructor.text = property.instructor
            duracion.text = property.duracion

            database = FirebaseDatabase.getInstance()
            //reference=database.getReference("Comics")
            add_btn.setOnClickListener {
                reference = database.getReference("Users/${usuario?.uid}/cursos")
                val id = reference.push().key
                val comic = Cursos(id.toString(),property.nombre,property.descripcion,property.instructor,property.duracion,property.sesiones,property.actividades,property.foto)
                reference.child(id!!).setValue(comic)
                Toast.makeText(renglon.context, "Comic agregado", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val renglon_vista = inflater.inflate(R.layout.renglon_curso,parent, false)
        return CursoViewHolder(renglon_vista)
    }


    override fun onBindViewHolder(holder: CursoViewHolder, position: Int) {
        holder.bind(cursos!![position])
        val curso = cursos[position]
        //holder.foto.setImageResource(curso.foto)
        holder.nombre.text = curso.nombre
        holder.instructor.text = curso.instructor
        holder.duracion.text = curso.duracion
        holder.itemView.setOnClickListener{
            val action = FragmentOtrosCursosDirections.actionFragmentOtrosCursosToFragmentCurso(curso)
            holder.itemView.findNavController().navigate(action)
        }
    }
    override fun getItemCount(): Int {
        return cursos.size
    }
}