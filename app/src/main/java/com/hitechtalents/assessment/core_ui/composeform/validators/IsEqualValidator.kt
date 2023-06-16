package com.hitechtalents.assessment.core_ui.composeform.validators

import com.hitechtalents.assessment.core_ui.UiText
import com.hitechtalents.assessment.core_ui.composeform.Validator

class IsEqualValidator<T>(expectedValue: () -> T, errorText: UiText? = null) : Validator<T>(
    validate = {
        it == expectedValue()
    },
    errorText = errorText ?: UiText.DynamicString("This field's value is not as expected.")
)