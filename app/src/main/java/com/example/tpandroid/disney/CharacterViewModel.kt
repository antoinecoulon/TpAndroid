package com.example.tpandroid.disney

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterViewModel(private val characterRepository: CharacterRepository) : ViewModel() {
    private val _characters = MutableStateFlow<Result<List<Character>>>(Result.success(emptyList()))
    val characters: StateFlow<Result<List<Character>>> = _characters

    fun fetchCharacters() {
        viewModelScope.launch {
            val result = characterRepository.fetchCharacters()
            _characters.value = result
        }
    }

    // ViewModel Factory
    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get Application Object from Extras...
                val application = checkNotNull(extras[APPLICATION_KEY])

                return CharacterViewModel(CharacterRepository()) as T
            }
        }
    }
}