package com.example.hearthstoneapp.ui.shops.places

import android.content.Context
import com.example.hearthstoneapp.R
import com.example.hearthstoneapp.data.network.model.Place
import com.example.hearthstoneapp.data.network.model.PlaceResponse
import com.example.hearthstoneapp.data.network.model.toPlace
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream
import java.io.InputStreamReader

class PlacesReader(private val context: Context) {
    // GSON object responsible for converting from JSON to a Place object
    private val gson = Gson()

    // InputStream representing places.json
    private val inputStream: InputStream
        get() = context.resources.openRawResource(R.raw.places)

    /**
     * Reads the list of place JSON objects in the file places.json
     * and returns a list of Place objects
     */
    fun read(): List<Place> {
        val itemType = object : TypeToken<List<PlaceResponse>>() {}.type
        val reader = InputStreamReader(inputStream)
        return gson.fromJson<List<PlaceResponse>>(reader, itemType).map {
            it.toPlace()
        }
    }
}