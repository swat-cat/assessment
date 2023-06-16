package com.hitechtalents.assessment.core_ui.composeform

import com.hitechtalents.assessment.core_ui.UiText

abstract class Validator<T>(
    val validate: (s: T?) -> Boolean,
    val errorText: UiText
)
