package com.example.student

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.runtime.key
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudentTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding(),
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
    BackHandler(onBack = alVolver)

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
            val horas = remember { mutableStateOf("") }
            val minutos = remember { mutableStateOf("") }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = horas.value,
                    onValueChange = { horas.value = it },
                    label = { Text("horas") },
                    modifier = Modifier.width(100.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Text(
                    text = ":",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                OutlinedTextField(
                    value = minutos.value,
                    onValueChange = { minutos.value = it },
                    label = { Text("Minutos") },
                    modifier = Modifier.width(100.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }


        } else {
            val contexto = LocalContext.current

            val horaInicio = remember { mutableStateOf("9:00") }
            val horaTermino = remember { mutableStateOf("11:00") }

            fun abrirReloj(alElegirHora: (String) -> Unit) {
                val calendario = java.util.Calendar.getInstance()
                android.app.TimePickerDialog(
                    contexto,
                    {_, hora, minuto ->
                        val horaFormateada = "${hora.toString().padStart(2, '0')}:${minuto.toString().padStart(2, '0')}"
                        alElegirHora(horaFormateada)
                    },
                    calendario.get(java.util.Calendar.HOUR_OF_DAY),
                    calendario.get(java.util.Calendar.MINUTE),
                    true
                ).show()
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Inicio", color = Color.Gray)
                    TextButton(onClick = { abrirReloj { nuevaHora -> horaInicio.value = nuevaHora } }) {
                        Text(text = horaInicio.value, fontSize = 32.sp, fontWeight = FontWeight.Medium)
                    }
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Término", color = Color.Gray)
                    TextButton(onClick = { abrirReloj { nuevaHora -> horaTermino.value = nuevaHora } }) {
                        Text(text = horaTermino.value, fontSize = 32.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(48.dp))



        val expandido = remember { mutableStateOf(false) }
        val ramoSeleccionado = remember { mutableStateOf("Seleccionar Ramo") }
        val listaRamos = listOf("Cálculo", "Álgebra", "Programación", "Física")

        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(
                onClick = { expandido.value = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = ramoSeleccionado.value, fontSize = 18.sp, modifier = Modifier.padding(8.dp))
            }

            DropdownMenu(
                expanded = expandido.value,
                onDismissRequest = { expandido.value = false }
            ) {
                listaRamos.forEach { ramo ->
                    DropdownMenuItem(
                        text = { Text(text = ramo) },
                        onClick = {
                            ramoSeleccionado.value = ramo
                            expandido.value = false
                        }
                    )
                }
            }
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