package com.hitechtalents.assessment.presentation.ui.sign_up

import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hitechtalents.assessment.R
import com.hitechtalents.assessment.core_ui.UiText
import com.hitechtalents.assessment.core_ui.composeform.FieldState
import com.hitechtalents.assessment.core_ui.composeform.Form
import com.hitechtalents.assessment.core_ui.composeform.FormField
import com.hitechtalents.assessment.core_ui.composeform.validators.EmailValidator
import com.hitechtalents.assessment.core_ui.composeform.validators.MinLengthValidator
import com.hitechtalents.assessment.core_ui.composeform.validators.RegexpValidator
import com.hitechtalents.assessment.domain.model.Profile
import com.hitechtalents.assessment.domain.model.Result
import com.hitechtalents.assessment.domain.usecases.GetProfile
import com.hitechtalents.assessment.domain.usecases.SignUp
import com.huhx.picker.data.AssetInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(val getProfile: GetProfile, private val signUp: SignUp) :
    ViewModel() {
    private val _profile: MutableStateFlow<Result<Profile?>> =
        MutableStateFlow(Result.Initial(getProfile()))
    val profile: StateFlow<Result<Profile?>> = _profile
    val showPicker = MutableStateFlow(false)
    val avatarPath = MutableStateFlow<String?>(null)
    val form = SignUpForm(profile.value.data)

    init {
        fetchData()
        form.validate()
    }

    private fun fetchData() = viewModelScope.launch {
        getProfile()?.let {
            avatarPath.emit(it.avatarPath)
        }
    }

    fun onClosePicker(asset: AssetInfo?) = viewModelScope.launch {
        showPicker.emit(false)
        avatarPath.emit(asset?.uriString)
    }

    fun onPickAvatar(asset: AssetInfo?) = viewModelScope.launch {
        showPicker.emit(false)
        avatarPath.emit(asset?.uriString)
    }

    fun openPicker() = viewModelScope.launch { showPicker.emit(true) }

    fun submit() = viewModelScope.launch {
        form.email.state.value?.let {
            form.password.state.value?.let { it1 ->
                Profile(
                    firstName = form.firstName.state.value,
                    email = it,
                    password = it1,
                    avatarPath = avatarPath.value,
                    webSite = form.webSite.state.value
                )
            }
        }?.let {
            try {
                signUp(it)
                _profile.emit(Result.Success(it))
            } catch (e: Exception) {
                _profile.emit(Result.Error("Failed to save profile"))
            }
        }
    }
}

class SignUpForm(profile: Profile?) : Form() {
    override fun self(): Form {
        return this;
    }

    @FormField
    val firstName = FieldState<String?>(
        state = mutableStateOf(profile?.firstName),
    )

    @FormField
    val email = FieldState<String?>(
        state = mutableStateOf(profile?.email),
        validators = mutableListOf(
            EmailValidator()
        )
    )

    @FormField
    val password = FieldState<String?>(
        state = mutableStateOf(null),
        validators = mutableListOf(
            MinLengthValidator(8)
        )
    )

    @FormField
    val webSite = FieldState<String?>(
        state = mutableStateOf(profile?.webSite),
        validators = mutableListOf(
            RegexpValidator(
                pattern = Patterns.WEB_URL.pattern(), errorText = UiText.StringResource(
                    R.string.url_error
                )
            )
        )
    )
}