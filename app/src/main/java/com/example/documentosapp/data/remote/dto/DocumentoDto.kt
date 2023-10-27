package com.example.documentosapp.data.remote.dto

import androidx.room.PrimaryKey

data class DocumentoDto (
    @PrimaryKey
    var Numero : Int?=null,
    var Fecha: String = "",
    var Rnc: String = "",
    var NombreCliente: String? = "",
    var Precio : Double = 0.0,
    var Monto: Double = 0.0
)