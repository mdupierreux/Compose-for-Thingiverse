package be.maximedupierreux.browserforthingiverse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.maximedupierreux.browserforthingiverse.network.model.Thing
import be.maximedupierreux.browserforthingiverse.repository.ThingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ThingsViewModel(private val repository: ThingsRepository): ViewModel() {

    private val _things = MutableStateFlow<List<Thing>>(emptyList())
    val things : StateFlow<List<Thing>> = _things

    init {
        viewModelScope.launch {
            repository.getNewestThings().collect { c ->
                _things.value = c
            }
        }
    }
}