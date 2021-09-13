package io.homeassistant.companion.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import io.homeassistant.companion.android.databinding.ActivityHomeBinding

class Home : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.composeView.setContent {
            Column(Modifier.fillMaxSize(), horizontalAlignment = CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Image(painter = painterResource(id = R.drawable.app_icon), null, Modifier.size(64.dp))
                Text(text = "Home Assistant Wear OS")
            }
        }
    }
}
