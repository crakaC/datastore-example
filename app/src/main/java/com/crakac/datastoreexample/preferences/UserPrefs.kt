package com.crakac.datastoreexample.preferences

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Serializable
data class UserPrefs(
    val dataMap: LinkedHashMap<String, Long>
) {
    companion object {
        fun fromJsonString(string: String?): UserPrefs {
            return if (string.isNullOrBlank()) {
                UserPrefs(linkedMapOf())
            } else {
                Json.decodeFromString(string)
            }
        }
    }
}