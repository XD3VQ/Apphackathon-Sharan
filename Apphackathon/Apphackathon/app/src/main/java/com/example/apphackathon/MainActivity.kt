package com.example.apphackathon

import android.content.Intent


import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apphackathon.ui.theme.ApphackathonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApphackathonTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainMenuScreen()
                }
            }
        }
    }
}

@Preview
@Composable
fun MainMenuScreen() {
    val context = LocalContext.current

    // subtle gradient background
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF2D7DCC),
                        Color(0xFFB79051)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(horizontal = 32.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.driveforge_logo),
                contentDescription = "DriveForge Logo",
                modifier = Modifier.size(250.dp)
            )
//            Text(
//                text = "DriveForge",
//                fontSize = 32.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color(0xFF000000),
//                textAlign = TextAlign.Left
//            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Gamify your speed and challenge your friends 🚗💨",
                color = Color(0xFF231B1B),
                fontSize = 19.sp,
                textAlign = TextAlign.Left
            )

            Spacer(modifier = Modifier.height(300.dp))

            Button(
                onClick = { context.startActivity(Intent(context, MapsActivity::class.java)) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E88E5)),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Open Maps", fontSize = 18.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { context.startActivity(Intent(context, ScoreActivity::class.java)) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF43A047)),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("View Scoreboard", fontSize = 18.sp, color = Color.White)
            }
        }
    }
}
