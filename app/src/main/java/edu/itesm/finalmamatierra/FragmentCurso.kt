package edu.itesm.finalmamatierra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_curso.*
import kotlinx.android.synthetic.main.renglon_curso.*
import kotlinx.android.synthetic.main.renglon_curso.duracion
import kotlinx.android.synthetic.main.renglon_curso.instructor
import kotlinx.android.synthetic.main.renglon_curso.nombre

class FragmentCurso : Fragment() {

    private val args by navArgs<FragmentCursoArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_curso, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        photo.setImageResource(args.curso.foto)
        nombre.text = args.curso.nombre
        descripcion.text = args.curso.descripcion
        instructor.text = args.curso.instructor
        duracion.text = args.curso.duracion
        sesiones.text = args.curso.sesiones
        actividades.text = args.curso.actividades
        Toast.makeText(context, args.curso.nombre, 4000).show()
    }

}