package com.dushanesmith.yahoocodingexercise.data.local

import androidx.room.TypeConverter
import com.dushanesmith.yahoocodingexercise.data.model.TeamStats
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converters {
    var type: Type = object : TypeToken<HashMap<String, TeamStats>?>() {}.type

    @TypeConverter
    fun fromHashmapOfTeamStats(hashmap: HashMap<String, TeamStats>): String{
        return Gson().toJson(hashmap)
    }

    @TypeConverter
    fun fromStringToHashMap(string: String): HashMap<String, TeamStats>{
        return Gson().fromJson(string, type)
    }
}