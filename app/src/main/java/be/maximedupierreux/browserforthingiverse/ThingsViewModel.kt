package be.maximedupierreux.browserforthingiverse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.maximedupierreux.browserforthingiverse.network.model.Hits
import be.maximedupierreux.browserforthingiverse.repository.ThingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ThingsViewModel(private val repository: ThingsRepository): ViewModel() {

    private val _things = MutableStateFlow<List<Hits>>(emptyList())
    val things : StateFlow<List<Hits>> = _things

    init {
        viewModelScope.launch {
            repository.getNewestThings().collect { c ->
                _things.value = c
            }
        }
    }
}