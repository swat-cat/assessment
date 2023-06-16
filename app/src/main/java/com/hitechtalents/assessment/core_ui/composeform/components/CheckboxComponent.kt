package com.hitechtalents.assessment.core_ui.composeform.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.hitechtalents.assessment.core_ui.UiText

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CheckboxComponent(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit),
    label: String,
    hasError: Boolean = false,
    errorText: MutableList<UiText>? = null
) {
    Column(modifier = Modifier.padding(top = 8.dp)) {
        Column(modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .clickable(
                indication = rememberRipple(color = MaterialTheme.colorScheme.primary),
                interactionSource = remember { MutableInteractionSource() },
                onClick = { onCheckedChange(!checked) }
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = null
                )

                Spacer(Modifier.size(6.dp))

                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        if (hasError && errorText != null) {
            Text(
                text = errorText.map { it.asString() }.joinToString(),
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                style = TextStyle.Default.copy(color = MaterialTheme.colorScheme.error)
            )
        }
    }
}