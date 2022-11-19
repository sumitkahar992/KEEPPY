package com.example.keeppy.presentation.common.model

import androidx.compose.ui.graphics.Color
import com.example.keeppy.presentation.common.theme.*


enum class PriorityView(
    val colorLight: Color,
    val colorDark: Color
) {
    HIGH(HighPriorityColorLight, HighPriorityColorDark),
    MEDIUM(MediumPriorityColorLight, MediumPriorityColorDark),
    LOW(LowPriorityColorLight, LowPriorityColorDark),
    NONE(NonePriorityColorLight, NonePriorityColorDark)
}