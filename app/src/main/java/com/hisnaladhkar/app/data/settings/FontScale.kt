package com.hisnaladhkar.app.data.settings

enum class FontScale(val multiplier: Float, val arabicLabel: String) {
    SMALL(0.85f, "صغير"),
    MEDIUM(1.0f, "متوسط"),
    LARGE(1.2f, "كبير"),
    EXTRA_LARGE(1.4f, "كبير جداً")
}
