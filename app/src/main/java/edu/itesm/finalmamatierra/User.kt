package edu.itesm.finalmamatierra

data class User(val nombre:String?, val paterno:String?, val materno:String?, val correo:String?, val contrasena:String?, val foto:String?){
    constructor():this("", "", "", "", "", "")
}

