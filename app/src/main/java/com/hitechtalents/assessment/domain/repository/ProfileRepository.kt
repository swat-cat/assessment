package com.hitechtalents.assessment.domain.repository

import com.hitechtalents.assessment.domain.model.Profile

interface ProfileRepository {
    fun saveProfile(profile: Profile)
    fun getProfile(): Profile?
}