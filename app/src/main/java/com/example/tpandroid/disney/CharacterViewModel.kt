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

    private val _infos = MutableStateFlow<Result<Info>>(Result.success(Info(0, 0, null, null)))
    val infos: StateFlow<Result<Info>> = _infos

    private var currentPage = 1

    fun fetchCharacters(page: Int = 1) {
        currentPage = page
        viewModelScope.launch {
            val result = characterRepository.fetchCharacters(page)
            _characters.value = result
            updatePaginationInfos(page)
        }
    }

    fun fetchInfos(page: Int = 1) {
        viewModelScope.launch {
            val result = characterRepository.fetchInfos(page)
            _infos.value = result
        }
    }

    private suspend fun updatePaginationInfos(page: Int) {
        val infoResult = characterRepository.fetchInfos(page)
        _infos.value = infoResult
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