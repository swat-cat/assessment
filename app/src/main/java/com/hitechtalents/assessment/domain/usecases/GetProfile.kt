package com.hitechtalents.assessment.domain.usecases

import com.hitechtalents.assessment.domain.model.Profile
import com.hitechtalents.assessment.domain.repository.ProfileRepository
import javax.inject.Inject

class GetProfile @Inject constructor(private val profileRepository: ProfileRepository) {
    operator fun invoke(): Profile? {
        return profileRepository.getProfile()
    }
}