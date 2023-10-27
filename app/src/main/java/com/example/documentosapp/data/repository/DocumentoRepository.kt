package com.example.documentosapp.data.repository

import com.example.documentosapp.data.DocumentoApi
import com.example.documentosapp.data.remote.dto.DocumentoDto
import com.example.documentosapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DocumentoRepository @Inject constructor(
    private val api: DocumentoApi
) {
    fun getDocumentos(): Flow<Resource<List<DocumentoDto>>> = flow {
        try {
            emit(Resource.Loading())
            val documento = api.getDocumentos()
            emit(Resource.Success(documento))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}



