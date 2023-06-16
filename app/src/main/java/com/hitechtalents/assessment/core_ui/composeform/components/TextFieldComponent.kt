package com.hitechtalents.assessment.core_ui.composeform.components


import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hitechtalents.assessment.core_ui.UiText

@Composable
fun TextFieldComponent(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    prefix: @Composable() (() -> Unit)? = null,
    leadingIcon: @Composable() (() -> Unit)? = null,
    trailingIcon: @Composable() (() -> Unit)? = null,
    onChange: (String) -> Unit = {},
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyBoardActions: KeyboardActions = KeyboardActions(),
    isEnabled: Boolean = true,
    hasError: Boolean = false,
    showErrors: Boolean = true,
    errorText: MutableList<UiText> = mutableListOf(),
    interactionSource: MutableInteractionSource? = null,
    isReadOnly: Boolean = false,
    focusChanged: ((focus: FocusState) -> Unit)? = null,
    focusRequester: FocusRequester = FocusRequester(),
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusChanged {
                    focusChanged?.invoke(it)
                },
            value = text,
            onValueChange = {
                onChange(it)
            },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
            keyboardActions = keyBoardActions,
            enabled = isEnabled,
            isError = hasError,
            label = { Text(label) },
            readOnly = isReadOnly,
            interactionSource = interactionSource ?: remember { MutableInteractionSource() },
            visualTransformation = visualTransformation,
            placeholder = null,
            prefix = prefix
        )
        if (hasError && showErrors) {
            Text(
                text = errorText.map { it.asString() }.joinToString("\n"),
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.error)
            )
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun FormTextFieldPreview() {
    Surface {
        Column {
            TextFieldComponent(
                modifier = Modifier.padding(bottom = 8.dp),
                text = "My Value",
                label = "My Label",
                onChange = {},
                keyBoardActions = KeyboardActions.Default,
                isEnabled = true,
            )
            TextFieldComponent(
                modifier = Modifier.padding(bottom = 8.dp),
                text = "",
                label = "My Label",
                onChange = {},
                keyBoardActions = KeyboardActions.Default,
                isEnabled = true,
                hasError = true,
                errorText = mutableListOf(UiText.DynamicString("Should not be empty."))
            )
            TextFieldComponent(
                modifier = Modifier.padding(bottom = 8.dp),
                text = "My Picker",
                label = "My Label",
                onChange = {},
                keyBoardActions = KeyboardActions.Default,
                isEnabled = false,
                isReadOnly = true,
                trailingIcon = { Icon(Icons.Filled.KeyboardArrowDown, null) }
            )
            TextFieldComponent(
                modifier = Modifier.padding(bottom = 8.dp),
                text = "My Picker in Confirm",
                label = "My Label",
                onChange = {},
                keyBoardActions = KeyboardActions.Default,
                isEnabled = false,
                isReadOnly = true,
                trailingIcon = { Icon(Icons.Filled.KeyboardArrowDown, null) }
            )
        }
    }
}
