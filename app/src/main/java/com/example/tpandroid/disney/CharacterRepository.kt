package com.example.tpandroid.disney

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepository {
    private val api: DisneyApiService = DisneyApiService.RetrofitInstance.api

    suspend fun fetchInfos(page: Int = 1): Result<Info> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getCharacters(page)
                if (response.isSuccessful) {
                    val characterResponse = response.body()
                    if (characterResponse != null) {
                        Result.success(characterResponse.info)
                    } else {
                        Result.failure(Exception("Response body is null"))
                    }
                } else {
                    Result.failure(Exception("Error: ${response.errorBody()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun fetchCharacters(page: Int = 1): Result<List<Character>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getCharacters(page)
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