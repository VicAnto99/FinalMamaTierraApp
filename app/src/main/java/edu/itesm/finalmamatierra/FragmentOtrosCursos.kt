package edu.itesm.finalmamatierra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_otros_cursos.*

class FragmentOtrosCursos : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_otros_cursos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        otros_cursos_lista.apply{
            layoutManager = LinearLayoutManager(activity)
            adapter = CursosAdapter(createData())
        }
    }

    fun createData(): ArrayList<Cursos>{
        val cursos = ArrayList<Cursos>()
        cursos.add(Cursos("01","Introducción a la ecología","Este curso propone a los usuarios aprender acerca de la ecología, las causas y efectos del cambio climático y las características de los principales biomas terrestres.","Mateo Juárez","2–3 horas.","3 sesiones","2 actividades",R.drawable.planeta_tierrav))
        cursos.add(Cursos("02","Estudios ecológicos: ciudadanía y comunidad ecológica","Dirigido a profesionales y estudiantes de ciencias ambientales, este curso presentará las consecuencias sociales y ambientales de crecimiento de la población, la dinámica poblacional y biología del comportamiento.","Alex Cervantes","2–3 horas.","4 sesiones","3 actividades",R.drawable.hola))
        cursos.add(Cursos("03","Estudios ecológicos: ecosistema ecológico","Se trata de un breve curso que indagará acerca de los tipos de ecosistemas, cómo se transfiere la energía entre los niveles tróficos, y cómo las actividades humanas han causado grandes trastornos a estos ciclos.","Juan Rodríguez","2–3 horas.","2 sesiones","3 actividades",R.drawable.maxresdefault))
        cursos.add(Cursos("04","Conservación de la biodiversidad","Este curso propone enseñar a los usuarios la importancia de la biología de la conservación en el mantenimiento de la biodiversidad, además de explicar las causas históricas de altas tasas de extinción animal y los efectos del cambio climático, la pérdida de hábitat y la caza.","Jose Velázquez","2–3 horas.","2 sesiones","4 actividades",R.drawable.planeta_tierrav))
        cursos.add(Cursos("05","Manejo de recursos hídricos","Se trata de una serie, compuesta por 3 cursos, cuyo cometido es presentar los fundamentos de la gestión del agua y los recursos hídricos. Los usuarios aprenderán, entre otras cosas, cómo afecta el cambio climático al ciclo del agua y la seguridad pública y las técnicas de procesamiento de las aguas residuales.","Mercedes Vázquez","2–3 horas.","3 sesiones","2 actividades",R.drawable.hola))
        cursos.add(Cursos("06","Diploma en ciencia medioambiental","Compuesto por 27 módulos temáticos, este curso tiene como cometido presentar los fundamentos de las ciencias medioambientales, por ejemplo, los sistemas de energía alternativa, el control de la contaminación y de mitigación, el manejo de recursos naturales y los efectos del cambio climático.","Jathzely Vasconcelos","7 semanas. 4–6 horas semanales","3 sesiones","4 actividades",R.drawable.maxresdefault))
        cursos.add(Cursos("07","Diploma en desarrollo sustentable","Este curso explicará qué es exactamente la sostenibilidad, y cómo se practica, y examinará la importancia del desarrollo sostenible en la lucha contra el cambio climático. Asimismo, presentará cuales son las fuentes de energía sostenibles capaces de satisfacer las demandas actuales y futuras e indagará acerca de la conservación del agua.","Maritza Sánchez","15–20 horas.","5 sesiones","3 actividades",R.drawable.planeta_tierrav))
        cursos.add(Cursos("08","Desarrollo urbano sustentable","Este curso indicará cuáles son los retos más relevantes que las regiones metropolitanas enfrentan respecto a la contaminación y cómo se puede responder a estos desafíos y construir ciudades sostenibles, además de presentar ejemplos de algunas soluciones innovadoras.","Tadeo González","10–15 horas.","4 sesiones","2 actividades",R.drawable.hola))
        cursos.add(Cursos("09","Comprender la negación del cambio climático","Dictado por docentes de la Universidad de Queenland, Australia, este curso tiene como cometido dar luz al debate sobre el cambio climático.","Yuyal Hernandez","2–3 horas.","2 sesiones","5 actividades",R.drawable.maxresdefault))
        cursos.add(Cursos("10","Ingeniería: construir junto a la naturaleza","La propuesta de este curso es explorar el uso de materiales naturales y los procesos ecológicos para diseñar y construir infraestructuras hidráulicas eficaces y sostenibles.","Omac Tebe","5 semanas. 3–5 horas semanales.","5 sesiones","2 actividades",R.drawable.planeta_tierrav))
        return cursos
    }

}