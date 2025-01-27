package com.example.tipcalculator

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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Switch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface(modifier = Modifier.fillMaxSize()){
                AppPreview()
            }



        }
    }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 500)
@Composable
fun AppPreview(){
    App()
}

@Composable
fun App(){
    val state1 = remember{mutableStateOf("")}
    val state2 = remember{mutableStateOf("")}
    val amount1 : Double = state1.value.toDoubleOrNull() ?: 0.0
    val amount2 : Double = state2.value.toDoubleOrNull() ?: 0.0
//    val tip = remember { mutableDoubleStateOf(0.00) }
    var tip : Double = amount1 * (amount2/100)
    val roundUp = remember { mutableStateOf(false) }
    if (roundUp.value) {
        tip = kotlin.math.ceil(tip)
    }



    Column(
       verticalArrangement = Arrangement.Center,
       modifier = Modifier.padding(20.dp)
           .fillMaxWidth()
   ) {
        Text(
            text = "Calculate Tip",
            style = MaterialTheme.typography.titleMedium
        )
       Spacer(Modifier.height(10.dp))

        TextField(
            value = state1.value,
            onValueChange = {
                state1.value = it
            },
            label = { Text("Bill Amount") },
            keyboardOptions = KeyboardOptions.Default.copy(
               keyboardType = KeyboardType.Number,
               imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
       Spacer(Modifier.height(20.dp))
       TextField(
           value = state2.value,
           onValueChange = {
               state2.value = it
           },
           label = { Text("Tip Percentage") },
           keyboardOptions = KeyboardOptions.Default.copy(
               keyboardType = KeyboardType.Number,
               imeAction = ImeAction.Done
           ),
           modifier = Modifier.fillMaxWidth()
       )
       Spacer(Modifier.height(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Round up tip?",
                style = MaterialTheme.typography.bodyLarge
            )
            Switch(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End),
                checked = roundUp.value,
                onCheckedChange = {checked -> roundUp.value = checked}
            )
        }
        Spacer(Modifier.height(30.dp))


        Text(
           text = "Tip Amount: $$tip",
           style = MaterialTheme.typography.headlineMedium,
           fontWeight = FontWeight.Bold
       )
    }

}
