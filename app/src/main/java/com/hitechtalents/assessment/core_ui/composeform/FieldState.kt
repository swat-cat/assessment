package com.hitechtalents.assessment.core_ui.composeform

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.focus.FocusState
import com.hitechtalents.assessment.core_ui.UiText

class FieldState<T>(
    val state: MutableState<T>,
    val validators: MutableList<Validator<T>> = mutableListOf(),
    val errorText: MutableList<UiText> = mutableListOf(),
    val isDirty: MutableState<Boolean?> = mutableStateOf(true),
    val focusState: MutableState<FocusState?> = mutableStateOf(null),
    val isValid: MutableState<Boolean?> = mutableStateOf(false),
    val hasChanges: MutableState<Boolean?> = mutableStateOf(false),
    val options: MutableList<T>? = null,
    val optionItemFormatter: ((T?) -> String)? = null,
    val showErrors: MutableState<Boolean?> = mutableStateOf(true),
) {
    fun hasError(): Boolean {
        return isDirty.value == true && isValid.value == false && hasChanges.value == true
    }

    fun showErrors(): Boolean {
        return showErrors.value == true
    }

    fun selectedOption(): T? {
        if (options == null) {
            return null
        }

        return options.firstOrNull { it == state.value }
    }

    fun selectedOptionText(): String? {
        val selectedOption = selectedOption() ?: return null
        return optionItemFormatter?.invoke(selectedOption) ?: selectedOption.toString()
    }
}