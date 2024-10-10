package com.example.simonsays

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class FilesManager
{
    companion object
    {

        fun comproveFile(context: Context): Boolean
        {
            val jsonFilePath = context.filesDir.toString() + "/userData.json"
            val jsonFile = File(jsonFilePath)
            return jsonFile.exists() && jsonFile.length() != 0L
        }

        fun getUsersStats(context: Context): MutableList<UserData>
        {
            val jsonFilePath = context.filesDir.toString() + "/userData.json"
            val jsonFile = FileReader(jsonFilePath)
            val listUsersType = object : TypeToken<MutableList<UserData>>() {}.type
            val arrayUsersData: MutableList<UserData> = Gson().fromJson(jsonFile,listUsersType)
            return arrayUsersData
        }

        fun saveUsersStats(context: Context, arrayUsersData: List<UserData>)
        {
            val jsonFilePath = context.filesDir.toString() + "/userData.json"
            val jsonFile = FileWriter(jsonFilePath)
            var gson = Gson()
            val jsonElement = gson.toJson(arrayUsersData)
            jsonFile.write(jsonElement)
            jsonFile.close()
        }
    }

}