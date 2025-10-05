package com.example.bmicalculator

import android.os.AsyncTask
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showSystemUi = true)
fun tracker(){
   var weight by remember { mutableStateOf("") }
    var bmi by remember { mutableStateOf<Float?>(null) }
    var result by remember { mutableStateOf("") }
    var emoji by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var message=remember { SnackbarHostState()}
    var scope= rememberCoroutineScope()
    var statuscolour by remember { mutableStateOf(Color.Black) }
    Scaffold(topBar = {TopAppBar(title ={Text("Weight tracker",
        color = Color.White, fontWeight = FontWeight.Bold,
        fontSize = 30.sp)}, colors = TopAppBarDefaults.topAppBarColors(
        colorResource(R.color.dark_green_40)
    ))},
        snackbarHost = { SnackbarHost(hostState = message) },
        content = {paddingValues ->
            Column(modifier = Modifier.fillMaxSize().
            padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                value =weight,
                onValueChange = {weight=it},
                    modifier = Modifier.fillMaxWidth().padding(5.dp),
                label = {Text("Weight(Kg)")},
                placeholder = {Text("Enter weight in kilogram")},
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,   // Number keyboard
                        imeAction = ImeAction.Done            // Keyboard ka Done button
                    )
                )
                OutlinedTextField(
                    value =height,
                    onValueChange = {height=it},
                    modifier = Modifier.fillMaxWidth().padding(5.dp),
                    label = {Text("Height(m)")},
                    placeholder = {Text("Enter height in metre")},
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,   // Number keyboard
                        imeAction = ImeAction.Done            // Keyboard ka Done button
                    ))
           Row {    Button(onClick =  {
                 if (weight.isNotEmpty()&&height.isNotEmpty()){
                    var w=weight.trim().toFloatOrNull()
                    var h=height.trim().toFloatOrNull()
                    bmi= if (w!=null&&h!=null&&h>0){
                        w/(h*h)
                    }else {null}
                    when{
                        bmi!!<18.5 -> {result="Underweight"
                        statuscolour=Color.Blue
                        emoji= "😅"
                        }
                        bmi!!>18.5&&bmi!!<24.9 -> {result="Normal weight"
                        statuscolour=Color.Green
                        emoji="💪"
                        }
                        bmi!!>25&&bmi!!<29.9-> {result="Overweight"
                        statuscolour= Color.Magenta
                        emoji="🍔"
                        }
                        bmi!!>=30-> {result="Obese"
                        statuscolour=Color.Red
                        emoji="❤️‍🔥"
                        }
                        else -> "Enter valid weight & height"
                    }}else{scope.launch { message.showSnackbar("Please fill the fields") }}
                }, modifier = Modifier.padding(7.dp),
                    colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.dark_green_40),
                    contentColor = Color.White
                    ))
                {Row {  Text("Calculate")
                    Icon(Icons.Default.PlayArrow, contentDescription = "Calculate")}}
               Button(onClick = {if (weight.isNotEmpty()&&height.isNotEmpty()){weight=""
                                height=""
                                bmi=null
                                result=""
                                statuscolour=Color.Black
                                scope.launch { message.showSnackbar("All fields have cleared") }}else
                                {scope.launch { message.showSnackbar("Fields are already cleared") }}},
                   modifier = Modifier.padding(7.dp),
                   colors = ButtonDefaults.buttonColors(
                       containerColor = colorResource(R.color.dark_green_40),
                       contentColor = Color.White)) {Row {  Text("Reset")
                   Icon(Icons.Default.Refresh, contentDescription = "refresh") } }}
                Column {  Text("BMI=${bmi?.let{String.format("%.2f",it)}?: ""}")
                    Text("Result=$result $emoji",color=statuscolour)}
              } })}