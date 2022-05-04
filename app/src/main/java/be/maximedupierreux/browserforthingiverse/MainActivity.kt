package be.maximedupierreux.browserforthingiverse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import be.maximedupierreux.browserforthingiverse.network.model.Hits
import be.maximedupierreux.browserforthingiverse.repository.ThingsRepository
import be.maximedupierreux.browserforthingiverse.ui.theme.BrowserForThingiverseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BrowserForThingiverseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ThingsList(viewModel = ThingsViewModel(ThingsRepository()))
                }
            }
        }
    }

    @Composable
    fun ThingsList(viewModel: ThingsViewModel) {
        val things by viewModel.things.collectAsState()

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(things) { thing ->
                ThingRow(thing = thing)
            }
        }
    }

    @Composable
    fun ThingRow(thing: Hits) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            elevation = 10.dp
        ) {
            Column(
                modifier = Modifier.padding(15.dp)
            ) {
                thing.name?.let { Text(text = it) }
                Row (
                    modifier = Modifier.padding(16.dp)
                ){
                    thing.creator?.let { Text(text = it.firstName ?: "") }
                    thing.creator?.let { Text(text = it.lastName ?: "") }
                }
            }
        }

    }
}