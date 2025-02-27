package com.cadastro.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cadastro.Estados
import com.cadastro.Formulario
import com.cadastro.Sexo

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FormularioUI()
        }
    }
}

@Composable
fun FormularioUI() {
    val context = LocalContext.current
    var nome by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var sexo by remember { mutableStateOf(Sexo.MASCULINO) }
    var cidade by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf(Estados.AC) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Box(
            modifier = Modifier.fillMaxWidth()
                .background(Color(0xFF800080))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Cadastro", color = Color.White, fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))
        CampoTexto("Nome completo", nome) { nome = it }
        CampoTexto("Telefone", telefone) { telefone = it }
        CampoTexto("E-mail", email) { email = it }

        Row {
            RadioButton(selected = sexo == Sexo.MASCULINO, onClick = { sexo = Sexo.MASCULINO })
            Text("Masculino")
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(selected = sexo == Sexo.FEMININO, onClick = { sexo = Sexo.FEMININO })
            Text("Feminino")
        }

        CampoTexto("Cidade", cidade) { cidade = it }
        DropdownEstados(estado) { estado = it }

        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            BotaoFormulario("Limpar", Color.Magenta) {
                nome = ""; telefone = ""; email = ""; sexo = Sexo.MASCULINO; cidade = ""; estado = Estados.AC
            }
            BotaoFormulario("Salvar", Color.Blue) {
                val formulario = Formulario(nome, telefone, email, sexo, cidade, estado)
                Toast.makeText(context, formulario.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampoTexto(label: String, valor: String, aoMudar: (String) -> Unit) {
    OutlinedTextField(
        value = valor,
        onValueChange = aoMudar,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors()
    )
}

@Composable
fun DropdownEstados(estadoSelecionado: Estados, aoSelecionar: (Estados) -> Unit) {
    var expandido by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxWidth()) {
        Button(onClick = { expandido = true }) {
            Text(estadoSelecionado.name)
        }
        DropdownMenu(expanded = expandido, onDismissRequest = { expandido = false }) {
            Estados.entries.forEach { estado ->
                DropdownMenuItem(text = { Text(estado.name) }, onClick = {
                    aoSelecionar(estado)
                    expandido = false
                })
            }
        }
    }
}

@Composable
fun BotaoFormulario(texto: String, cor: Color, aoClicar: () -> Unit) {
    Button(
        onClick = aoClicar,
        colors = ButtonDefaults.buttonColors(containerColor = cor),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.width(120.dp)
    ) {
        Text(texto, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFormularioUI() {
    FormularioUI()
}
