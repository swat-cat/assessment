package com.hitechtalents.assessment.core_ui.composeform.validators

import com.hitechtalents.assessment.core_ui.UiText
import com.hitechtalents.assessment.core_ui.composeform.Validator

class NotEmptyValidator<T>(errorText: UiText? = null) : Validator<T>(
    validate = {
        it != null
    },
    errorText = errorText ?: UiText.DynamicString("This field should not be empty")
)

class NotEmptyStringValidator(errorText: UiText? = null) : Validator<String?>(
    validate = {
        it?.isNotEmpty() ?: false
    },
    errorText = errorText ?: UiText.DynamicString("This field should not be empty")
)