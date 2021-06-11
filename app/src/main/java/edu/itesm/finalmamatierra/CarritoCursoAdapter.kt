package edu.itesm.finalmamatierra

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CarritoCursoAdapter(private val data: List<CarritoCursos>?) : RecyclerView.Adapter<CarritoCursoAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(property: CarritoCursos) {

            var nombre = view.findViewById<TextView>(R.id.nombre)
            var instructor = view.findViewById<TextView>(R.id.instructor)
            var duracion = view.findViewById<TextView>(R.id.duracion)


            nombre.text = property.nombre
            instructor.text = property.instructor
            duracion.text = property.duracion
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.carrito_curso,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data!![position])
    }

    override fun getItemCount(): Int {
        return data!!.size
    }
}