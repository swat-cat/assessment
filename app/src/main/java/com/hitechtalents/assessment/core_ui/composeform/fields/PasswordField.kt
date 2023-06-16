package com.hitechtalents.assessment.core_ui.composeform.fields

import android.annotation.SuppressLint
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.hitechtalents.assessment.core_ui.composeform.Field
import com.hitechtalents.assessment.core_ui.composeform.FieldState
import com.hitechtalents.assessment.core_ui.composeform.Form
import com.hitechtalents.assessment.core_ui.composeform.components.TextFieldComponent

class PasswordField(
    label: String,
    form: Form,
    modifier: Modifier? = Modifier,
    fieldState: FieldState<String?>,
    isVisible: Boolean = true,
    isEnabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
) : Field<String>(
    label = label,
    form = form,
    fieldState = fieldState,
    isVisible = isVisible,
    isEnabled = isEnabled,
    modifier = modifier,
    imeAction = imeAction,
    formatter = null
) {

    /**
     * Returns a composable representing the DateField / Picker for this field
     */
    @SuppressLint("NotConstructor")
    @Composable
    override fun Field() {
        this.updateComposableValue()
        if (!isVisible) {
            return
        }

        var passwordVisible by remember { mutableStateOf(false) }
        TextFieldComponent(
            modifier = modifier ?: Modifier,
            imeAction = imeAction ?: ImeAction.Next,
            isEnabled = isEnabled,
            keyBoardActions = KeyboardActions.Default,
            keyboardType = KeyboardType.Password,
            onChange = {
                this.onChange(it, form)
            },
            focusChanged = {
                this.onFocusChanged(it, form)
            },
            label = label,
            text = formatter?.invoke(value.value) ?: (value.value ?: ""),
            hasError = fieldState.hasError(),
            errorText = fieldState.errorText,
            showErrors = fieldState.showErrors(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                // Please provide localized description for accessibility services
                val description = if (passwordVisible) "Hide password" else "Show password"
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = image, description, tint = if (passwordVisible)
                            MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                    )
                }
            }
        )
    }
}
