package com.hitechtalents.assessment.presentation.ui.profile_details

import androidx.lifecycle.ViewModel
import com.hitechtalents.assessment.domain.model.Profile
import com.hitechtalents.assessment.domain.model.Result
import com.hitechtalents.assessment.domain.usecases.GetProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(getProfile: GetProfile) : ViewModel() {
    private val _profile: MutableStateFlow<Result<Profile?>> =
        MutableStateFlow(Result.Initial(getProfile()))
    val profile: StateFlow<Result<Profile?>> = _profile
}