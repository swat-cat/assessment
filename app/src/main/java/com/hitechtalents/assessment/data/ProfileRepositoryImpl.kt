package com.hitechtalents.assessment.data

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hitechtalents.assessment.domain.model.Profile
import com.hitechtalents.assessment.domain.repository.ProfileRepository

class ProfileRepositoryImpl(private val sharedPreferences: SharedPreferences) : ProfileRepository {
    var gson: Gson =
        GsonBuilder().create()

    override fun saveProfile(profile: Profile) {
        sharedPreferences.edit()
            .putString("profile", gson.toJson(profile))
            .apply()
    }

    override fun getProfile(): Profile? {
        val json = sharedPreferences.getString("profile", null) ?: return null
        return gson.fromJson(json, Profile::class.java)
    }
}