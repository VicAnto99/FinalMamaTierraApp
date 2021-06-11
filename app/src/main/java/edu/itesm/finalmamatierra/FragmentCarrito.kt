package edu.itesm.finalmamatierra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_curso.*
import kotlinx.android.synthetic.main.fragment_curso.duracion
import kotlinx.android.synthetic.main.fragment_curso.instructor
import kotlinx.android.synthetic.main.fragment_curso.nombre
import kotlinx.android.synthetic.main.renglon_curso.*

class FragmentCarrito : Fragment() {

    private val args by navArgs<FragmentCarritoArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carrito, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //photo.setImageResource(args.CarritoCurso.foto)
        nombre.text = args.CarritoCurso.nombre
        descripcion.text = args.CarritoCurso.descripcion
        instructor.text = args.CarritoCurso.instructor
        duracion.text = args.CarritoCurso.duracion
        sesiones.text = args.CarritoCurso.sesiones
        actividades.text = args.CarritoCurso.actividades
        Toast.makeText(context, args.CarritoCurso.nombre, 4000).show()
    }
}