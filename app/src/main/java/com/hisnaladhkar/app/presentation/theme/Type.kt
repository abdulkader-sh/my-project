package com.hisnaladhkar.app.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

// TODO(المرحلة 7 - وضع القراءة): استبدال FontFamily.Default بخط "Noto Naskh Arabic"
// بعد إضافة ملفات الخط إلى res/font، ليكون القراءة الطويلة أوضح وأريح.
val AppTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 20.sp,
        lineHeight = 32.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 24.sp,
        lineHeight = 32.sp
    )
)
