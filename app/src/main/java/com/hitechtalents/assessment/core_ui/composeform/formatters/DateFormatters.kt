package com.hitechtalents.assessment.core_ui.composeform.formatters

import java.text.DateFormat
import java.util.Date

fun dateShort(r: Date?): String {
    return if (r != null) DateFormat.getDateInstance().format(r) else ""
}

fun dateLong(r: Date?): String {
    return if (r != null) DateFormat.getDateInstance(DateFormat.LONG).format(r) else ""
}
