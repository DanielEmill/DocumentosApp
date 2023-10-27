    package com.example.documentosapp.data

    import com.example.documentosapp.data.remote.dto.DocumentoDto
    import retrofit2.http.*

    interface DocumentoApi {
        @GET("documentos")
        suspend fun getDocumentos(): List<DocumentoDto>
    }