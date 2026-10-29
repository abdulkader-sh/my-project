package com.hisnaladhkar.app.domain.model

/** درجة صحة الذكر كما وردت في المصدر المعتمد. */
enum class AuthenticityGrade {
    SAHIH,          // صحيح
    HASAN,          // حسن
    QURAN,          // آية قرآنية (لا تُصنَّف بدرجة حديثية)
    NEEDS_REVIEW    // النص أو مصدره غير مؤكد تماماً، يحتاج مراجعة شرعية قبل النشر
}
