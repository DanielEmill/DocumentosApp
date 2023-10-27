package com.example.documentosapp.ui.documentoUi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.documentosapp.data.remote.dto.DocumentoDto
import com.example.documentosapp.data.repository.DocumentoRepository
import com.example.documentosapp.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DocumentoViewModel(
    private val documentoRepository: DocumentoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DocumentoListState())
    val uiState: StateFlow<DocumentoListState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            documentoRepository.getDocumentos().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _uiState.update { it.copy(documentos = result.data ?: emptyList()) }
                    }

                    is Resource.Error -> {
                        _uiState.update { it.copy(error = result.message ?: "Error desconocido") }
                    }
                }
            }
        }
    }
}
data class DocumentoListState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val documentos: List<DocumentoDto> = emptyList()
)