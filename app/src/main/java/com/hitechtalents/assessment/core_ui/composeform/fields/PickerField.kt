package com.hitechtalents.assessment.core_ui.composeform.fields


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import com.hitechtalents.assessment.core_ui.composeform.Field
import com.hitechtalents.assessment.core_ui.composeform.FieldState
import com.hitechtalents.assessment.core_ui.composeform.Form
import com.hitechtalents.assessment.core_ui.composeform.components.SingleSelectDialogComponent
import com.hitechtalents.assessment.core_ui.composeform.components.TextFieldComponent

abstract class PickerValue {
    abstract fun searchFilter(query: String): Boolean
}

class PickerField<T : PickerValue>(
    label: String,
    form: Form,
    modifier: Modifier? = Modifier,
    fieldState: FieldState<T?>,
    isVisible: Boolean = true,
    isEnabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    formatter: ((raw: T?) -> String)? = null,
    private val submitButtonText: String?,
    private val searchGo: String?,
    private val isSearchable: Boolean = true
) : Field<T>(
    label = label,
    form = form,
    fieldState = fieldState,
    isVisible = isVisible,
    isEnabled = isEnabled,
    modifier = modifier,
    imeAction = imeAction,
    formatter = formatter
) {

    @Composable
    override fun Field() {
        this.updateComposableValue()
        if (!isVisible) {
            return
        }

        var isDialogVisible by remember { mutableStateOf(false) }
        val focusRequester = FocusRequester()
        val focusManager = LocalFocusManager.current

        TextFieldComponent(
            modifier = modifier ?: Modifier,
            isEnabled = isEnabled,
            label = label,
            text = fieldState.selectedOptionText() ?: "",
            hasError = fieldState.hasError(),
            errorText = fieldState.errorText,
            isReadOnly = true,
            trailingIcon = {
                Icon(
                    if (isDialogVisible) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    null
                )
            },
            focusRequester = focusRequester,
            focusChanged = {
                isDialogVisible = it.isFocused
            }
        )

        if (isDialogVisible) {
            SingleSelectDialogComponent(
                title = label,
                optionsList = fieldState.options!!,
                optionItemFormatter = fieldState.optionItemFormatter,
                defaultSelected = fieldState.state.value,
                submitButtonText = submitButtonText ?: "Ok",
                searchGo = searchGo ?: "Go",
                onSubmitButtonClick = {
                    isDialogVisible = false
                    this.onChange(it, form)
                    focusManager.clearFocus()
                },
                onDismissRequest = {
                    isDialogVisible = false
                    focusManager.clearFocus()
                },
                search = if (isSearchable) {
                    { options, query ->
                        options.filter { c -> c?.searchFilter(query) == true }
                    }
                } else {
                    null
                }
            )
        }
    }

}
