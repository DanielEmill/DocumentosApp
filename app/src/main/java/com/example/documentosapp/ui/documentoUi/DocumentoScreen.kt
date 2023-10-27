import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.documentosapp.data.remote.dto.DocumentoDto
import com.example.documentosapp.ui.documentoUi.DocumentoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentoScreen(documentoViewModel: DocumentoViewModel) {

    val uiState by documentoViewModel.uiState.collectAsStateWithLifecycle()
    when {
        uiState.isLoading -> {
            CircularProgressIndicator()
        }
        uiState.error != null -> {
            Text("Error: ${uiState.error}")
        }
        uiState.documentos != null -> {
            DocumentoDetails(documentoList = uiState.documentos)
        }
    }
}

@Composable
fun DocumentoDetails(documentoList: List<DocumentoDto>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(documentoList) { documento ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("${documento.Numero} ", style = MaterialTheme.typography.bodyMedium)
                Text("${documento.Rnc} ", style = MaterialTheme.typography.bodyMedium)
                Text("${documento.NombreCliente} ", style = MaterialTheme.typography.bodyMedium)
                Text("${documento.Precio} ", style = MaterialTheme.typography.bodyMedium)
                Text("${documento.Monto}", style = MaterialTheme.typography.bodyMedium)
            }
            Divider(color = Color.Gray, thickness = 1.dp)
        }
    }
}