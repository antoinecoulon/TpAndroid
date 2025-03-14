package com.example.tpandroid.disney

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepository {
    private val api: DisneyApiService = DisneyApiService.RetrofitInstance.api

    suspend fun fetchCharacters(): Result<List<Character>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getCharacters()
                if (response.isSuccessful) {
                    val characterResponse = response.body()
                    Result.success(characterResponse?.data ?: emptyList())
                } else {
                    Result.failure(Exception("Error: ${response.errorBody()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}