package edu.itesm.finalmamatierra

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cursos(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val instructor: String,
    val duracion: String,
    val sesiones: String,
    val actividades: String,
    val foto: Int
): Parcelable
