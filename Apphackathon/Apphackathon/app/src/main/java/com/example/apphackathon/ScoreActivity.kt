package com.example.apphackathon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.text.style.TextAlign


class ScoreActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LeaderboardScreen()
        }
    }
}

@Composable
fun LeaderboardScreen() {
    val userScore = ScoreRepository.getAllScores().find { it.name == "Sharan" }
    val scores = remember {
        mutableStateListOf<PlayerScore>().apply {
            addAll(ScoreRepository.getAllScores().sortedBy { it.penalty })
        }
    }

    val totalPenalty = scores.sumOf { it.penalty }
    val streak = scores.count { it.penalty == 0 }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(16.dp)) {

            // 🔝 Top Bar with Streak and Score
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(Color(0xFF4CAF50), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("🔥$streak", color = Color.White, fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.width(12.dp))
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(4.dp)
                        .background(Color.Gray.copy(alpha = 0.3f))
                )

                Text(
                    text = "Penalty: $totalPenalty",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ✅ Personal Fuel Efficiency Card
            if (userScore != null) {
                Text(
                    text = "🌿 Your Fuel Efficiency",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFDCEDC8))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Name: ${userScore.name}", fontSize = 20.sp)
                        Text("Efficiency: ${"%.2f".format(userScore.efficiency)} km/L", fontSize = 18.sp)
                        Text("Fuel Used: ${userScore.fuelUsed} L", fontSize = 16.sp)
                        Text("Distance: ${userScore.distanceKm} km", fontSize = 16.sp)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }

            // 🏁 Leaderboard Title
            Text(
                text = "🏁 Leaderboard",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (scores.isEmpty()) {
                Text("No scores yet. Start a session to see results!", fontSize = 18.sp)
            } else {
                LazyColumn {
                    itemsIndexed(scores) { index, player ->
                        LeaderboardCard(rank = index + 1, player = player)
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun LeaderboardCard(rank: Int, player: PlayerScore) {
    val severityColor = when {
        player.penalty == 0 -> Color(0xFF4CAF50) // green
        player.penalty > -3 -> Color(0xFFFFC107) // amber
        player.penalty > -6 -> Color(0xFFFF5722) // orange
        else -> Color(0xFFF44336) // red
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(severityColor),
        colors = CardDefaults.cardColors(containerColor = severityColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Rank #$rank", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Text("Name: ${player.name}", fontSize = 20.sp)
            Text("Penalty: ${player.penalty}", fontSize = 18.sp)
            Text("Streak: ${player.streak}", fontSize = 16.sp, color = Color.Gray)

        }
    }
}