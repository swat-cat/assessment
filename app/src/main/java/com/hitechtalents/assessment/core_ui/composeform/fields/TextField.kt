package com.hitechtalents.assessment.core_ui.composeform.fields

import android.annotation.SuppressLint
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.hitechtalents.assessment.core_ui.composeform.Field
import com.hitechtalents.assessment.core_ui.composeform.FieldState
import com.hitechtalents.assessment.core_ui.composeform.Form
import com.hitechtalents.assessment.core_ui.composeform.components.TextFieldComponent
import java.util.*

class TextField(
    label: String,
    form: Form,
    modifier: Modifier? = Modifier,
    fieldState: FieldState<String?>,
    isVisible: Boolean = true,
    isEnabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    formatter: ((raw: String?) -> String)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    private val prefix: @Composable() (() -> Unit)? = null,
) : Field<String>(
    label = label,
    form = form,
    fieldState = fieldState,
    isVisible = isVisible,
    isEnabled = isEnabled,
    modifier = modifier,
    imeAction = imeAction,
    formatter = formatter,
    keyboardType = keyboardType,
    visualTransformation = visualTransformation
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

        TextFieldComponent(
            modifier = modifier ?: Modifier,
            imeAction = imeAction ?: ImeAction.Next,
            isEnabled = isEnabled,
            keyBoardActions = KeyboardActions.Default,
            keyboardType = keyboardType,
            focusChanged = {
                this.onFocusChanged(it, form)
            },
            onChange = {
                this.onChange(it, form)
            },
            label = label,
            text = formatter?.invoke(value.value) ?: (value.value ?: ""),
            hasError = fieldState.hasError(),
            errorText = fieldState.errorText,
            visualTransformation = visualTransformation,
            prefix = prefix
        )
    }
}
