package com.hitechtalents.assessment.domain.model

data class Profile(
    val firstName: String?,
    val email: String,
    val password: String,
    val webSite: String?,
    val avatarPath: String?
)
