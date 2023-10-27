import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.documentosapp.data.remote.dto.DocumentoDto
import com.example.documentosapp.ui.documentoUi.DocumentoViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentoScreen(documentoViewModel: DocumentoViewModel) {
    val uiState by documentoViewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Documentos API") }
            )
        }
    ) { padding ->
        when {
            uiState.isLoading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }
            uiState.error != null -> {
                Text("Error: ${uiState.error}")
            }
            uiState.documentos != null -> {
                Column {
                    Spacer(modifier = Modifier.height(padding.calculateTopPadding()))
                    DocumentoDetails(documentoList = uiState.documentos)
                }
            }
        }
    }
}
@Composable
fun DocumentoDetails(documentoList: List<DocumentoDto>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(documentoList) { documento ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                elevation = CardDefaults.elevatedCardElevation()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Documento #${documento.Numero}",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("RNC: ${documento.Rnc}", style = MaterialTheme.typography.bodyMedium)
                    Text("Cliente: ${documento.NombreCliente}", style = MaterialTheme.typography.bodyMedium)
                    Text("Precio: ${documento.Precio}", style = MaterialTheme.typography.bodyMedium)
                    Text("Monto: ${documento.Monto}", style = MaterialTheme.typography.bodyMedium)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}