package com.hitechtalents.assessment.core_ui.composeform.validators

import com.hitechtalents.assessment.core_ui.UiText
import com.hitechtalents.assessment.core_ui.composeform.Validator

class RegexpFindValidator(errorText: UiText? = null, pattern: String) : Validator<String?>(
    validate = { value ->
        (value?.let { pattern.toRegex().findAll(it).count() } ?: 0) > 0
    },
    errorText = errorText ?: UiText.DynamicString("Field is invalid")
)

class RegexpValidator(errorText: UiText? = null, pattern: String) : Validator<String?>(
    validate = { value ->
        if (value?.isEmpty() != false) true
        else value.let { pattern.toRegex().matches(it) }
    },
    errorText = errorText ?: UiText.DynamicString("Field is invalid")
)