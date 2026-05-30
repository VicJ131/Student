package com.example.student

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.student.ui.theme.StudentTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val mostrarAñadir = remember { mutableStateOf(false) }
                    if (mostrarAñadir.value == false) {
                        PantallaPrincipal(alPresionarAñadir = { mostrarAñadir.value = true})
                    } else {
                        PantallaAnadirSesion(alVolver = { mostrarAñadir.value = false})
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StudentTheme {
        Greeting("Android")
    }
}
@Composable
fun PantallaPrincipal(alPresionarAñadir: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Horas estudiadas",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "esta semana",
            fontSize = 20.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "5",
            fontSize = 100.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.height(64.dp))

        Button(
            onClick = alPresionarAñadir,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Añadir Sesión",
                fontSize = 20.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun PantallaAnadirSesion(alVolver: () -> Unit) {
    val pestañaSeleccionada = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        IconButton(onClick = alVolver) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Volver"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TabRow(selectedTabIndex = pestañaSeleccionada.value) {
            Tab(
                selected = pestañaSeleccionada.value == 0,
                onClick = { pestañaSeleccionada.value = 0 }
            ) {
                Text("Tiempo", modifier = Modifier.padding(16.dp))
            }
            Tab(
                selected = pestañaSeleccionada.value == 1,
                onClick = { pestañaSeleccionada.value = 1 }
            ) {
                Text("Hora", modifier = Modifier.padding(16.dp))
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        if (pestañaSeleccionada.value == 0) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "01:15", fontSize = 60.sp, fontWeight = FontWeight.Bold)
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Inicio", color = Color.Gray)
                    Text(text = "09:10", fontSize = 32.sp, fontWeight = FontWeight.Medium)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Término", color = Color.Gray)
                    Text(text = "11:15", fontSize = 32.sp, fontWeight = FontWeight.Medium)
                }
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        OutlinedButton(
            onClick = { /*WIP*/},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Seleccionar Ramo", fontSize = 18.sp, modifier = Modifier.padding(8.dp))
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { /*WIP*/ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Confirmar", fontSize = 20.sp, modifier = Modifier.padding(8.dp))
        }
    }
}