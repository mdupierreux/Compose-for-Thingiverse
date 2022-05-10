package be.maximedupierreux.browserforthingiverse

import android.content.Context
import android.os.Bundle
import android.view.accessibility.AccessibilityManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.text
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.maximedupierreux.browserforthingiverse.network.model.Creator
import be.maximedupierreux.browserforthingiverse.network.model.Thing
import be.maximedupierreux.browserforthingiverse.repository.ThingsRepository
import be.maximedupierreux.browserforthingiverse.ui.theme.BrowserForThingiverseTheme
import coil.compose.AsyncImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val accessibilityManager = this.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
        setContent {
            BrowserForThingiverseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ThingsList(isAccessibilityEnabled = accessibilityManager.isEnabled && accessibilityManager.isTouchExplorationEnabled,
                        viewModel = ThingsViewModel(ThingsRepository()))
                }
            }
        }
    }

    @Composable
    fun ThingsList(
        isAccessibilityEnabled: Boolean,
        viewModel: ThingsViewModel
    ) {
        val things by viewModel.things.collectAsState()
        Scaffold(
            topBar = { TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                actions = {
                    if (isAccessibilityEnabled) {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = stringResource(id = R.string.search)
                            )
                        }
                    }
                }
            ) },
            floatingActionButton = {
                if (!isAccessibilityEnabled) {
                    FloatingActionButton(
                        onClick = {},
                        backgroundColor = MaterialTheme.colors.secondary,
                        contentColor = MaterialTheme.colors.onSecondary
                    ) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    }
                }
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                itemsIndexed(things) { index, thing ->
                    ThingRow(
                        thing = thing,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    if (index < things.lastIndex) {
                        Divider(
                            color = MaterialTheme.colors.onBackground.copy(alpha = .3f),
                            thickness = 0.5.dp,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun ThingRow(
        thing: Thing,
        modifier: Modifier = Modifier
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = thing.thumbnail,
                    contentDescription = null,
                    placeholder = painterResource(id = R.drawable.placeholder),
                    modifier = Modifier.size(80.dp),
                )
                Column(
                    modifier = Modifier.padding(15.dp)
                ) {
                    thing.name?.let { Text(text = it) }
                    Row(modifier = Modifier.semantics(mergeDescendants = true) { }) {
                        thing.creator?.let { Text(
                            modifier = Modifier.padding(end = 8.dp),
                            text = it.firstName ?: "")
                        }
                        thing.creator?.let { Text(text = it.lastName ?: "") }
                    }
                    LikeCount(likeCount = thing.likeCount ?: 0)
                }
            }
        }
    }

    @Composable
    fun LikeCount(
        likeCount: Int
    ) {
        val cdLikes = stringResource(id = R.string.a11y_thing_likes, likeCount)
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.semantics {
                    text = AnnotatedString(cdLikes)
                },
                text = likeCount.toString()
            )
            Image(painter = painterResource(id = R.drawable.ic_baseline_star_24), contentDescription = null)
        }
    }

    @Preview
    @Composable
    fun LikeCountPreview() {
        LikeCount(likeCount = 15)
    }

    @Preview
    @Composable
    fun ThingRowPreview() {
        val thing = Thing(
            name = "Awesome 3D Print File",
            creator = Creator(firstName = "Maxime", lastName = "Dupierreux")
        )

        ThingRow(
            thing = thing,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}