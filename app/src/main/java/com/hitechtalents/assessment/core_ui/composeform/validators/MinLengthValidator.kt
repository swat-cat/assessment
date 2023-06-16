package com.hitechtalents.assessment.core_ui.composeform.validators

import com.hitechtalents.assessment.core_ui.UiText
import com.hitechtalents.assessment.core_ui.composeform.Validator

class MinLengthValidator(minLength: Int, errorText: UiText? = null) : Validator<String?>(
    validate = {
        (it?.length ?: -1) >= minLength
    },
    errorText = errorText ?: UiText.DynamicString("This field is too short")
)

class MaxLengthValidator(maxLength: Int, errorText: UiText? = null) : Validator<String?>(
    validate = {
        (it?.length ?: -1) <= maxLength
    },
    errorText = errorText ?: UiText.DynamicString("This field is too short")
)