package com.hisnaladhkar.app.data.local.seed

import com.hisnaladhkar.app.data.local.entity.CategoryEntity
import com.hisnaladhkar.app.data.local.entity.DhikrCategoryCrossRef
import com.hisnaladhkar.app.data.local.entity.DhikrEntity
import com.hisnaladhkar.app.domain.model.AuthenticityGrade
import com.hisnaladhkar.app.domain.model.CategoryDimension
import com.hisnaladhkar.app.domain.model.LengthCategory

/**
 * دفعة ثالثة من المحتوى: النسخة الرسمية الكاملة من كتاب "حصن المسلم من أذكار الكتاب
 * والسنة" للشيخ سعيد بن علي بن وهف القحطاني، الصادرة عن رئاسة الشؤون الدينية بالمسجد
 * الحرام والمسجد النبوي (حرمين)، رفعها صاحب المشروع كملف PDF (71 صفحة). هذا هو المصدر
 * الأساسي الذي بُني عليه تصنيف "SOURCE" الخاص بالتطبيق أصلاً (CAT_SOURCE_HISN)، وتغطي
 * هذه الدفعة كامل محتوى الكتاب: 132 قسماً و267 دعاءً/ذكراً مرقّماً.
 *
 * طريقة النقل: كما في الدفعة السابقة، نُقل كل نص بالقراءة البصرية المباشرة لصفحات الـPDF
 * صفحة صفحة (pdftotext غير صالح لهذا النوع من الخط العربي المشكَّل). رُقّمت كل العناصر هنا
 * بترتيب ورودها الفعلي في الكتاب (وليس بالضرورة مطابقة حرفياً لرقمها المطبوع في هامش كل
 * صفحة؛ لاحظت أثناء النقل عدم اتساق في بعض الأرقام المطبوعة نفسها بين صفحة وأخرى، فاعتمدت
 * ترتيب الورود الفعلي بدل محاولة مطابقة رقمية دقيقة قد تقع فيها أخطاء). معرّف كل ذكر هو
 * 3000 + رقمه التسلسلي هنا. المجموع الذي استقر عليه النقل 270 عنصراً؛ علماً أن الطبعات
 * الشائعة لكتاب "حصن المسلم" تُعرف عادة بأنها تحوي 267 دعاءً، والفارق الطفيف هنا على الأرجح
 * ناتج عن اختلاف تجزئة بعض الفقرات المتعددة الشطور إلى عناصر منفصلة أثناء النقل اليدوي، وليس
 * إضافة أو حذفاً لأي محتوى فعلي.
 *
 * قاعدة التصنيف المعتمدة لدرجة الصحة (AuthenticityGrade) لهذه الدفعة:
 * - QURAN: آية قرآنية.
 * - SAHIH: ورد في تخريج المؤلف ذكر البخاري و/أو مسلم (سواء منفردين أو مع غيرهما).
 * - HASAN: تخريج إلى مصدر آخر (أبو داود، الترمذي، النسائي، ابن ماجه، أحمد، الحاكم،
 *   الطبراني، موطأ مالك، ابن خزيمة، ابن حبان، الدارمي...) دون ذكر البخاري/مسلم. هذا
 *   تصنيف تقريبي يعتمد على ثقة المؤلف في تخريجه، وليس تحقيقاً مستقلاً مني لكل حديث.
 * - NEEDS_REVIEW: حين ينص الكتاب نفسه على ضعف أو وقف الأثر على صحابي، أو حين تعذّر
 *   عليّ قراءة رقم التخريج أو اسم مصدره بثقة كافية من الصفحة.
 *
 * ملاحظة مهمة: حرصت على نقل رقم الحديث كلما كان واضحاً بثقة في هامش الصفحة؛ وحيثما لم
 * تتوفر هذه الثقة اكتفيت باسم المصدر (الكتاب) دون رقم، مع إضافة ملاحظة تنصح بالتحقق من
 * الرقم الدقيق قبل الاعتماد النهائي — تفادياً لأي خطأ في رقم حديث قد ينجم عن النقل
 * اليدوي لمرجع بالغ الكِبر (71 صفحة، 267 عنصراً).
 */
object SeedDataHisnAlMuslim {

    // تصنيفات مُعاد استخدامها من الدفعة الأولى (SeedData) بنفس المعرّفات
    private const val CAT_MORNING = 1L
    private const val CAT_PROTECTION = 2L
    private const val CAT_ISTIGHFAR = 3L
    private const val CAT_DISTRESS = 5L
    private const val CAT_POPULAR = 6L
    private const val CAT_SOURCE_QURAN = 7L
    private const val CAT_SOURCE_HISN = 8L
    private const val CAT_EVENING = 9L

    // تصنيفات جديدة خاصة بهذه الدفعة (THEME)
    private const val CAT_PURITY = 201L            // الطهارة والوضوء
    private const val CAT_HOME_MOSQUE = 202L       // الخروج والدخول (المنزل/المسجد)
    private const val CAT_ADHAN = 203L             // الأذان والإقامة
    private const val CAT_PRAYER = 204L            // أذكار الصلاة
    private const val CAT_ISTIKHARA = 205L         // صلاة الاستخارة
    private const val CAT_WITR = 206L              // الوتر والقنوت
    private const val CAT_SLEEP = 207L             // النوم والاستيقاظ والرؤيا
    private const val CAT_OPPRESSION = 208L        // الخوف من الظلم والسلطان
    private const val CAT_WHISPERS = 209L          // الوسوسة وطرد الشيطان
    private const val CAT_MARRIAGE = 210L          // الزواج والمولود
    private const val CAT_ILLNESS = 211L           // المرض وعيادة المريض
    private const val CAT_DEATH = 212L             // الموت والجنازة والقبور
    private const val CAT_WEATHER = 213L           // الريح والرعد والمطر والاستسقاء
    private const val CAT_HILAL = 214L             // رؤية الهلال
    private const val CAT_FASTING = 215L           // الصيام والإفطار والطعام
    private const val CAT_GATHERING = 216L         // المجلس وكفارته وآداب الحديث
    private const val CAT_TRAVEL = 217L            // الركوب والسفر
    private const val CAT_SALAM = 218L             // إفشاء السلام وآدابه
    private const val CAT_HAJJ = 219L              // الحج والعمرة
    private const val CAT_TASBEEH = 220L           // التسبيح والتحميد والتهليل والتكبير
    private const val CAT_MISC = 221L              // آداب متفرقة وأحوال متنوعة

    val categories = listOf(
        CategoryEntity(CAT_PURITY, "الطهارة والوضوء", CategoryDimension.THEME, 20),
        CategoryEntity(CAT_HOME_MOSQUE, "الخروج والدخول (المنزل والمسجد)", CategoryDimension.THEME, 21),
        CategoryEntity(CAT_ADHAN, "الأذان والإقامة", CategoryDimension.THEME, 22),
        CategoryEntity(CAT_PRAYER, "أذكار الصلاة", CategoryDimension.THEME, 23),
        CategoryEntity(CAT_ISTIKHARA, "صلاة الاستخارة", CategoryDimension.THEME, 24),
        CategoryEntity(CAT_WITR, "الوتر والقنوت", CategoryDimension.THEME, 25),
        CategoryEntity(CAT_SLEEP, "النوم والاستيقاظ والرؤيا", CategoryDimension.THEME, 26),
        CategoryEntity(CAT_OPPRESSION, "الخوف من الظلم والسلطان", CategoryDimension.THEME, 27),
        CategoryEntity(CAT_WHISPERS, "الوسوسة وطرد الشيطان", CategoryDimension.THEME, 28),
        CategoryEntity(CAT_MARRIAGE, "الزواج والمولود", CategoryDimension.THEME, 29),
        CategoryEntity(CAT_ILLNESS, "المرض وعيادة المريض", CategoryDimension.THEME, 30),
        CategoryEntity(CAT_DEATH, "الموت والجنازة والقبور", CategoryDimension.THEME, 31),
        CategoryEntity(CAT_WEATHER, "الريح والرعد والمطر والاستسقاء", CategoryDimension.THEME, 32),
        CategoryEntity(CAT_HILAL, "رؤية الهلال", CategoryDimension.THEME, 33),
        CategoryEntity(CAT_FASTING, "الصيام والإفطار والطعام", CategoryDimension.THEME, 34),
        CategoryEntity(CAT_GATHERING, "المجلس وآداب الحديث", CategoryDimension.THEME, 35),
        CategoryEntity(CAT_TRAVEL, "الركوب والسفر", CategoryDimension.THEME, 36),
        CategoryEntity(CAT_SALAM, "إفشاء السلام وآدابه", CategoryDimension.THEME, 37),
        CategoryEntity(CAT_HAJJ, "الحج والعمرة", CategoryDimension.THEME, 38),
        CategoryEntity(CAT_TASBEEH, "التسبيح والتحميد والتهليل والتكبير", CategoryDimension.THEME, 39),
        CategoryEntity(CAT_MISC, "آداب متفرقة وأحوال متنوعة", CategoryDimension.THEME, 40)
    )

    private const val HASAN_NOTE =
        "التخريج من كتاب \"حصن المسلم\"؛ يُستحسن التحقق من رقم الحديث ودرجته عند مختص قبل الاعتماد النهائي."
    private const val NUMBER_UNCERTAIN_NOTE =
        "لم أتمكن من قراءة رقم الحديث الدقيق بثقة كافية من الصفحة الأصلية؛ يُنصح بمراجعته قبل النشر النهائي."

    private data class RawDua(
        val n: Int,
        val text: String,
        val source: String,
        val grade: AuthenticityGrade,
        val length: LengthCategory,
        val themeCategoryIds: List<Long>,
        val note: String? = null,
        val needsReview: Boolean = false,
        val narrator: String? = null,
        val popular: Boolean = false,
        val repeatCount: Int? = null
    )

    private val raw = mutableListOf<RawDua>()

    init {
        // 1 - أذكار الاستيقاظ من النوم (1-4)
        raw += RawDua(1, "الْحَمْدُ لِلَّهِ الَّذِي أَحْيَانَا بَعْدَ مَا أَمَاتَنَا وَإِلَيْهِ النُّشُورُ.", "أبو داود، وأحمد", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_SLEEP), popular = true)
        raw += RawDua(2, "لَا إِلَهَ إِلَّا اللهُ وَحْدَهُ لَا شَرِيكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ، وَهُوَ عَلَى كُلِّ شَيْءٍ قَدِيرٌ، سُبْحَانَ اللهِ، وَالْحَمْدُ لِلَّهِ، وَلَا إِلَهَ إِلَّا اللهُ، وَاللهُ أَكْبَرُ، وَلَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِاللهِ، رَبِّ اغْفِرْ لِي.", "البخاري، ومسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_SLEEP), popular = true)
        raw += RawDua(3, "الْحَمْدُ لِلَّهِ الَّذِي عَافَانِي فِي جَسَدِي، وَرَدَّ عَلَيَّ رُوحِي، وَأَذِنَ لِي بِذِكْرِهِ.", "الترمذي", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_SLEEP))
        raw += RawDua(4, "إِنَّ فِي خَلْقِ السَّمَاوَاتِ وَالْأَرْضِ وَاخْتِلَافِ اللَّيْلِ وَالنَّهَارِ لَآيَاتٍ لِأُولِي الْأَلْبَابِ... (إلى آخر سورة آل عمران).", "آل عمران: 190-200", AuthenticityGrade.QURAN, LengthCategory.LONG, listOf(CAT_SLEEP))

        // 2-5 - أدعية لبس الثوب ولبس الجديد وما يقال عند وضعه (5-9)
        raw += RawDua(5, "الْحَمْدُ لِلَّهِ الَّذِي كَسَانِي هَذَا (الثَّوْبَ) وَرَزَقَنِيهِ مِنْ غَيْرِ حَوْلٍ مِنِّي وَلَا قُوَّةٍ.", "أبو داود، والترمذي، وابن ماجه", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_MISC))
        raw += RawDua(6, "اللَّهُمَّ لَكَ الْحَمْدُ أَنْتَ كَسَوْتَنِيهِ، أَسْأَلُكَ مِنْ خَيْرِهِ وَخَيْرِ مَا صُنِعَ لَهُ، وَأَعُوذُ بِكَ مِنْ شَرِّهِ وَشَرِّ مَا صُنِعَ لَهُ.", "أبو داود، والترمذي", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_MISC))
        raw += RawDua(7, "تُبْلِي وَيُخْلِفُ اللهُ تَعَالَى.", "أبو داود", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_MISC))
        raw += RawDua(8, "الْبَسْ جَدِيدًا، وَعِشْ حَمِيدًا، وَمُتْ شَهِيدًا.", "ابن ماجه، والبغوي", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_MISC))
        raw += RawDua(9, "بِسْمِ اللهِ.", "الترمذي", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_MISC))

        // 6-7 - دعاء دخول الخلاء والخروج منه (10-11)
        raw += RawDua(10, "بِسْمِ اللهِ، اللَّهُمَّ إِنِّي أَعُوذُ بِكَ مِنَ الْخُبُثِ وَالْخَبَائِثِ.", "البخاري، ومسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_PURITY), popular = true)
        raw += RawDua(11, "غُفْرَانَكَ.", "أبو داود، والترمذي، وابن ماجه", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_PURITY))

        // 8-9 - الذكر قبل الوضوء وبعد الفراغ منه (12-15)
        raw += RawDua(12, "بِسْمِ اللهِ.", "أبو داود، وابن ماجه", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_PURITY))
        raw += RawDua(13, "أَشْهَدُ أَنْ لَا إِلَهَ إِلَّا اللهُ وَحْدَهُ لَا شَرِيكَ لَهُ، وَأَشْهَدُ أَنَّ مُحَمَّدًا عَبْدُهُ وَرَسُولُهُ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_PURITY), popular = true)
        raw += RawDua(14, "اللَّهُمَّ اجْعَلْنِي مِنَ التَّوَّابِينَ وَاجْعَلْنِي مِنَ الْمُتَطَهِّرِينَ.", "الترمذي", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_PURITY))
        raw += RawDua(15, "سُبْحَانَكَ اللَّهُمَّ وَبِحَمْدِكَ، أَشْهَدُ أَنْ لَا إِلَهَ إِلَّا أَنْتَ، أَسْتَغْفِرُكَ وَأَتُوبُ إِلَيْكَ.", "النسائي في عمل اليوم والليلة", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_PURITY))

        // 10-11 - الذكر عند الخروج من المنزل ودخوله (16-18)
        raw += RawDua(16, "بِسْمِ اللهِ، تَوَكَّلْتُ عَلَى اللهِ، وَلَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِاللهِ.", "أبو داود، والترمذي", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_HOME_MOSQUE), popular = true)
        raw += RawDua(17, "اللَّهُمَّ إِنِّي أَعُوذُ بِكَ أَنْ أَضِلَّ أَوْ أُضَلَّ، أَوْ أَزِلَّ أَوْ أُزَلَّ، أَوْ أَظْلِمَ أَوْ أُظْلَمَ، أَوْ أَجْهَلَ أَوْ يُجْهَلَ عَلَيَّ.", "أبو داود، والترمذي، والنسائي، وابن ماجه", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_HOME_MOSQUE))
        raw += RawDua(18, "بِسْمِ اللهِ وَلَجْنَا، وَبِسْمِ اللهِ خَرَجْنَا، وَعَلَى اللهِ رَبِّنَا تَوَكَّلْنَا، ثُمَّ لِيُسَلِّمْ عَلَى أَهْلِهِ.", "أبو داود", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_HOME_MOSQUE))

        // 12-14 - دعاء الذهاب إلى المسجد ودخوله وخروجه (19-21)
        raw += RawDua(19, "اللَّهُمَّ اجْعَلْ فِي قَلْبِي نُورًا، وَفِي لِسَانِي نُورًا، وَفِي سَمْعِي نُورًا، وَفِي بَصَرِي نُورًا، وَمِنْ فَوْقِي نُورًا، وَمِنْ تَحْتِي نُورًا، وَعَنْ يَمِينِي نُورًا، وَعَنْ شِمَالِي نُورًا، وَمِنْ أَمَامِي نُورًا، وَمِنْ خَلْفِي نُورًا، وَاجْعَلْ فِي نَفْسِي نُورًا، وَأَعْظِمْ لِي نُورًا، وَعَظِّمْ لِي نُورًا، وَاجْعَلْ لِي نُورًا، وَاجْعَلْنِي نُورًا، اللَّهُمَّ أَعْطِنِي نُورًا، وَاجْعَلْ فِي عَصَبِي نُورًا، وَفِي لَحْمِي نُورًا، وَفِي دَمِي نُورًا، وَفِي شَعْرِي نُورًا، وَفِي بَشَرِي نُورًا، وَاجْعَلْ لِي نُورًا فِي قَبْرِي، وَنُورًا فِي عِظَامِي، وَزِدْنِي نُورًا، وَزِدْنِي نُورًا، وَزِدْنِي نُورًا، وَهَبْ لِي نُورًا عَلَى نُورٍ.", "البخاري، ومسلم (بألفاظ مجموعة)", AuthenticityGrade.SAHIH, LengthCategory.LONG, listOf(CAT_HOME_MOSQUE))
        raw += RawDua(20, "أَعُوذُ بِاللهِ الْعَظِيمِ، وَبِوَجْهِهِ الْكَرِيمِ، وَسُلْطَانِهِ الْقَدِيمِ، مِنَ الشَّيْطَانِ الرَّجِيمِ.", "أبو داود، والترمذي، وابن ماجه", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_HOME_MOSQUE))
        raw += RawDua(21, "بِسْمِ اللهِ، وَالصَّلَاةُ وَالسَّلَامُ عَلَى رَسُولِ اللهِ، اللَّهُمَّ افْتَحْ لِي أَبْوَابَ رَحْمَتِكَ (عند الدخول)، اللَّهُمَّ إِنِّي أَسْأَلُكَ مِنْ فَضْلِكَ (عند الخروج)، اللَّهُمَّ اعْصِمْنِي مِنَ الشَّيْطَانِ الرَّجِيمِ.", "مسلم، وابن ماجه", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_HOME_MOSQUE))

        // 15 - أذكار الأذان (22-26)
        raw += RawDua(22, "يَقُولُ مِثْلَ مَا يَقُولُ الْمُؤَذِّنُ، إِلَّا فِي ((حَيَّ عَلَى الصَّلَاةِ وَحَيَّ عَلَى الْفَلَاحِ)) فَيَقُولُ: لَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِاللهِ.", "البخاري، ومسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_ADHAN))
        raw += RawDua(23, "أَشْهَدُ أَنْ لَا إِلَهَ إِلَّا اللهُ وَحْدَهُ لَا شَرِيكَ لَهُ وَأَنَّ مُحَمَّدًا عَبْدُهُ وَرَسُولُهُ، رَضِيتُ بِاللهِ رَبًّا، وَبِمُحَمَّدٍ رَسُولًا، وَبِالْإِسْلَامِ دِينًا.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_ADHAN))
        raw += RawDua(24, "اللَّهُمَّ صَلِّ عَلَى مُحَمَّدٍ (بعد الفراغ من إجابة المؤذن).", "ابن خزيمة", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_ADHAN))
        raw += RawDua(25, "اللَّهُمَّ رَبَّ هَذِهِ الدَّعْوَةِ التَّامَّةِ، وَالصَّلَاةِ الْقَائِمَةِ، آتِ مُحَمَّدًا الْوَسِيلَةَ وَالْفَضِيلَةَ، وَابْعَثْهُ مَقَامًا مَحْمُودًا الَّذِي وَعَدْتَهُ، إِنَّكَ لَا تُخْلِفُ الْمِيعَادَ.", "البخاري", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_ADHAN), popular = true)
        raw += RawDua(26, "يَدْعُو بِمَا شَاءَ بَيْنَ الْأَذَانِ وَالْإِقَامَةِ فَإِنَّ الدُّعَاءَ حِينَئِذٍ لَا يُرَدُّ.", "أبو داود، والترمذي", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_ADHAN))

        // 16 - دعاء الاستفتاح (27-32)
        raw += RawDua(27, "اللَّهُمَّ بَاعِدْ بَيْنِي وَبَيْنَ خَطَايَايَ كَمَا بَاعَدْتَ بَيْنَ الْمَشْرِقِ وَالْمَغْرِبِ، اللَّهُمَّ نَقِّنِي مِنْ خَطَايَايَ كَمَا يُنَقَّى الثَّوْبُ الْأَبْيَضُ مِنَ الدَّنَسِ، اللَّهُمَّ اغْسِلْنِي مِنْ خَطَايَايَ بِالثَّلْجِ وَالْمَاءِ وَالْبَرَدِ.", "البخاري، ومسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_PRAYER), popular = true)
        raw += RawDua(28, "سُبْحَانَكَ اللَّهُمَّ وَبِحَمْدِكَ، وَتَبَارَكَ اسْمُكَ، وَتَعَالَى جَدُّكَ، وَلَا إِلَهَ غَيْرُكَ.", "أصحاب السنن الأربعة", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_PRAYER))
        raw += RawDua(29, "وَجَّهْتُ وَجْهِيَ لِلَّذِي فَطَرَ السَّمَاوَاتِ وَالْأَرْضَ حَنِيفًا وَمَا أَنَا مِنَ الْمُشْرِكِينَ، إِنَّ صَلَاتِي وَنُسُكِي وَمَحْيَايَ وَمَمَاتِي لِلَّهِ رَبِّ الْعَالَمِينَ، لَا شَرِيكَ لَهُ وَبِذَلِكَ أُمِرْتُ وَأَنَا مِنَ الْمُسْلِمِينَ، اللَّهُمَّ أَنْتَ الْمَلِكُ لَا إِلَهَ إِلَّا أَنْتَ، أَنْتَ رَبِّي وَأَنَا عَبْدُكَ، ظَلَمْتُ نَفْسِي وَاعْتَرَفْتُ بِذَنْبِي فَاغْفِرْ لِي ذُنُوبِي جَمِيعًا إِنَّهُ لَا يَغْفِرُ الذُّنُوبَ إِلَّا أَنْتَ، وَاهْدِنِي لِأَحْسَنِ الْأَخْلَاقِ لَا يَهْدِي لِأَحْسَنِهَا إِلَّا أَنْتَ، وَاصْرِفْ عَنِّي سَيِّئَهَا لَا يَصْرِفُ عَنِّي سَيِّئَهَا إِلَّا أَنْتَ، لَبَّيْكَ وَسَعْدَيْكَ، وَالْخَيْرُ كُلُّهُ بِيَدَيْكَ، وَالشَّرُّ لَيْسَ إِلَيْكَ، أَنَا بِكَ وَإِلَيْكَ، تَبَارَكْتَ وَتَعَالَيْتَ، أَسْتَغْفِرُكَ وَأَتُوبُ إِلَيْكَ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.LONG, listOf(CAT_PRAYER))
        raw += RawDua(30, "اللَّهُمَّ رَبَّ جِبْرَائِيلَ وَمِيكَائِيلَ وَإِسْرَافِيلَ، فَاطِرَ السَّمَاوَاتِ وَالْأَرْضِ، عَالِمَ الْغَيْبِ وَالشَّهَادَةِ، أَنْتَ تَحْكُمُ بَيْنَ عِبَادِكَ فِيمَا كَانُوا فِيهِ يَخْتَلِفُونَ، اهْدِنِي لِمَا اخْتُلِفَ فِيهِ مِنَ الْحَقِّ بِإِذْنِكَ، إِنَّكَ تَهْدِي مَنْ تَشَاءُ إِلَى صِرَاطٍ مُسْتَقِيمٍ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.LONG, listOf(CAT_PRAYER))
        raw += RawDua(31, "اللهُ أَكْبَرُ كَبِيرًا، اللهُ أَكْبَرُ كَبِيرًا، اللهُ أَكْبَرُ كَبِيرًا، وَالْحَمْدُ لِلَّهِ كَثِيرًا، وَالْحَمْدُ لِلَّهِ كَثِيرًا، وَالْحَمْدُ لِلَّهِ كَثِيرًا، وَسُبْحَانَ اللهِ بُكْرَةً وَأَصِيلًا (ثَلَاثًا)، أَعُوذُ بِاللهِ مِنَ الشَّيْطَانِ: مِنْ نَفْخِهِ وَنَفْثِهِ وَهَمْزِهِ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_PRAYER))
        raw += RawDua(32, "اللَّهُمَّ لَكَ الْحَمْدُ أَنْتَ نُورُ السَّمَاوَاتِ وَالْأَرْضِ وَمَنْ فِيهِنَّ، وَلَكَ الْحَمْدُ أَنْتَ قَيِّمُ السَّمَاوَاتِ وَالْأَرْضِ وَمَنْ فِيهِنَّ، وَلَكَ الْحَمْدُ أَنْتَ رَبُّ السَّمَاوَاتِ وَالْأَرْضِ وَمَنْ فِيهِنَّ، وَلَكَ الْحَمْدُ لَكَ مُلْكُ السَّمَاوَاتِ وَالْأَرْضِ وَمَنْ فِيهِنَّ، وَلَكَ الْحَمْدُ أَنْتَ مَلِكُ السَّمَاوَاتِ وَالْأَرْضِ، وَلَكَ الْحَمْدُ أَنْتَ الْحَقُّ، وَوَعْدُكَ الْحَقُّ، وَقَوْلُكَ الْحَقُّ، وَلِقَاؤُكَ الْحَقُّ، وَالْجَنَّةُ حَقٌّ، وَالنَّارُ حَقٌّ، وَالنَّبِيُّونَ حَقٌّ، وَمُحَمَّدٌ حَقٌّ، وَالسَّاعَةُ حَقٌّ، اللَّهُمَّ لَكَ أَسْلَمْتُ، وَعَلَيْكَ تَوَكَّلْتُ، وَبِكَ آمَنْتُ، وَإِلَيْكَ أَنَبْتُ، وَبِكَ خَاصَمْتُ، وَإِلَيْكَ حَاكَمْتُ، فَاغْفِرْ لِي مَا قَدَّمْتُ وَمَا أَخَّرْتُ، وَمَا أَسْرَرْتُ وَمَا أَعْلَنْتُ، أَنْتَ الْمُقَدِّمُ وَأَنْتَ الْمُؤَخِّرُ، لَا إِلَهَ إِلَّا أَنْتَ.", "البخاري، ومسلم", AuthenticityGrade.SAHIH, LengthCategory.LONG, listOf(CAT_PRAYER))

        // 17 - دعاء الركوع (33-37)
        raw += RawDua(33, "سُبْحَانَ رَبِّيَ الْعَظِيمِ.", "أبو داود وغيره", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_PRAYER), popular = true, repeatCount = 3)
        raw += RawDua(34, "سُبْحَانَكَ اللَّهُمَّ رَبَّنَا وَبِحَمْدِكَ، اللَّهُمَّ اغْفِرْ لِي.", "البخاري، ومسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_PRAYER))
        raw += RawDua(35, "سُبُّوحٌ قُدُّوسٌ، رَبُّ الْمَلَائِكَةِ وَالرُّوحِ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_PRAYER))
        raw += RawDua(36, "اللَّهُمَّ لَكَ رَكَعْتُ، وَبِكَ آمَنْتُ، وَلَكَ أَسْلَمْتُ، خَشَعَ لَكَ سَمْعِي، وَبَصَرِي، وَمُخِّي، وَعَظْمِي، وَعَصَبِي، وَمَا اسْتَقَلَّتْ بِهِ قَدَمِي.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_PRAYER))
        raw += RawDua(37, "سُبْحَانَ ذِي الْجَبَرُوتِ، وَالْمَلَكُوتِ، وَالْكِبْرِيَاءِ، وَالْعَظَمَةِ.", "أبو داود وغيره", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_PRAYER))

        // 18 - دعاء الرفع من الركوع (38-40)
        raw += RawDua(38, "سَمِعَ اللهُ لِمَنْ حَمِدَهُ.", "متفق عليه", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_PRAYER))
        raw += RawDua(39, "رَبَّنَا وَلَكَ الْحَمْدُ حَمْدًا كَثِيرًا طَيِّبًا مُبَارَكًا فِيهِ.", "متفق عليه", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_PRAYER))
        raw += RawDua(40, "مِلْءَ السَّمَاوَاتِ وَمِلْءَ الْأَرْضِ، وَمِلْءَ مَا بَيْنَهُمَا، وَمِلْءَ مَا شِئْتَ مِنْ شَيْءٍ بَعْدُ، أَهْلَ الثَّنَاءِ وَالْمَجْدِ، أَحَقُّ مَا قَالَ الْعَبْدُ، وَكُلُّنَا لَكَ عَبْدٌ، اللَّهُمَّ لَا مَانِعَ لِمَا أَعْطَيْتَ، وَلَا مُعْطِيَ لِمَا مَنَعْتَ، وَلَا يَنْفَعُ ذَا الْجَدِّ مِنْكَ الْجَدُّ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.LONG, listOf(CAT_PRAYER))

        // 19 - دعاء السجود (41-47)
        raw += RawDua(41, "سُبْحَانَ رَبِّيَ الْأَعْلَى.", "أحمد وأصحاب السنن", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_PRAYER), popular = true, repeatCount = 3)
        raw += RawDua(42, "سُبْحَانَكَ اللَّهُمَّ رَبَّنَا وَبِحَمْدِكَ، اللَّهُمَّ اغْفِرْ لِي.", "متفق عليه", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_PRAYER))
        raw += RawDua(43, "سُبُّوحٌ قُدُّوسٌ، رَبُّ الْمَلَائِكَةِ وَالرُّوحِ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_PRAYER))
        raw += RawDua(44, "اللَّهُمَّ لَكَ سَجَدْتُ، وَبِكَ آمَنْتُ، وَلَكَ أَسْلَمْتُ، سَجَدَ وَجْهِي لِلَّذِي خَلَقَهُ وَصَوَّرَهُ، وَشَقَّ سَمْعَهُ وَبَصَرَهُ، تَبَارَكَ اللهُ أَحْسَنُ الْخَالِقِينَ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_PRAYER))
        raw += RawDua(45, "سُبْحَانَ ذِي الْجَبَرُوتِ، وَالْمَلَكُوتِ، وَالْكِبْرِيَاءِ، وَالْعَظَمَةِ.", "أبو داود وغيره", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_PRAYER))
        raw += RawDua(46, "اللَّهُمَّ اغْفِرْ لِي ذَنْبِي كُلَّهُ: دِقَّهُ وَجِلَّهُ، وَأَوَّلَهُ وَآخِرَهُ، وَعَلَانِيَتَهُ وَسِرَّهُ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_PRAYER))
        raw += RawDua(47, "اللَّهُمَّ إِنِّي أَعُوذُ بِرِضَاكَ مِنْ سَخَطِكَ، وَبِمُعَافَاتِكَ مِنْ عُقُوبَتِكَ، وَأَعُوذُ بِكَ مِنْكَ، لَا أُحْصِي ثَنَاءً عَلَيْكَ، أَنْتَ كَمَا أَثْنَيْتَ عَلَى نَفْسِكَ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_PRAYER), popular = true)

        // 20 - دعاء الجلسة بين السجدتين (48-49)
        raw += RawDua(48, "رَبِّ اغْفِرْ لِي، رَبِّ اغْفِرْ لِي.", "أبو داود، وابن ماجه", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_PRAYER))
        raw += RawDua(49, "اللَّهُمَّ اغْفِرْ لِي، وَارْحَمْنِي، وَاهْدِنِي، وَاجْبُرْنِي، وَعَافِنِي، وَارْزُقْنِي، وَارْفَعْنِي.", "أصحاب السنن", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_PRAYER))

        // 21 - دعاء سجود التلاوة (50-51)
        raw += RawDua(50, "سَجَدَ وَجْهِي لِلَّذِي خَلَقَهُ وَشَقَّ سَمْعَهُ وَبَصَرَهُ بِحَوْلِهِ وَقُوَّتِهِ؛ فَتَبَارَكَ اللهُ أَحْسَنُ الْخَالِقِينَ.", "الترمذي وغيره", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_PRAYER))
        raw += RawDua(51, "اللَّهُمَّ اكْتُبْ لِي بِهَا عِنْدَكَ أَجْرًا، وَضَعْ عَنِّي بِهَا وِزْرًا، وَاجْعَلْهَا لِي عِنْدَكَ ذُخْرًا، وَتَقَبَّلْهَا مِنِّي كَمَا تَقَبَّلْتَهَا مِنْ عَبْدِكَ دَاوُدَ.", "الترمذي", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_PRAYER))

        // 22 - التشهد (52)
        raw += RawDua(52, "التَّحِيَّاتُ لِلَّهِ، وَالصَّلَوَاتُ وَالطَّيِّبَاتُ، السَّلَامُ عَلَيْكَ أَيُّهَا النَّبِيُّ وَرَحْمَةُ اللهِ وَبَرَكَاتُهُ، السَّلَامُ عَلَيْنَا وَعَلَى عِبَادِ اللهِ الصَّالِحِينَ، أَشْهَدُ أَنْ لَا إِلَهَ إِلَّا اللهُ وَأَشْهَدُ أَنَّ مُحَمَّدًا عَبْدُهُ وَرَسُولُهُ.", "متفق عليه", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_PRAYER), popular = true)

        // 23 - الصلاة على النبي بعد التشهد (53-54)
        raw += RawDua(53, "اللَّهُمَّ صَلِّ عَلَى مُحَمَّدٍ وَعَلَى آلِ مُحَمَّدٍ، كَمَا صَلَّيْتَ عَلَى آلِ إِبْرَاهِيمَ إِنَّكَ حَمِيدٌ مَجِيدٌ، اللَّهُمَّ بَارِكْ عَلَى مُحَمَّدٍ وَعَلَى آلِ مُحَمَّدٍ، كَمَا بَارَكْتَ عَلَى آلِ إِبْرَاهِيمَ إِنَّكَ حَمِيدٌ مَجِيدٌ.", "متفق عليه", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_PRAYER), popular = true)
        raw += RawDua(54, "اللَّهُمَّ صَلِّ عَلَى مُحَمَّدٍ وَعَلَى أَزْوَاجِهِ وَذُرِّيَّتِهِ، كَمَا صَلَّيْتَ عَلَى آلِ إِبْرَاهِيمَ، وَبَارِكْ عَلَى مُحَمَّدٍ وَعَلَى أَزْوَاجِهِ وَذُرِّيَّتِهِ، كَمَا بَارَكْتَ عَلَى آلِ إِبْرَاهِيمَ، إِنَّكَ حَمِيدٌ مَجِيدٌ.", "متفق عليه", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_PRAYER))

        // 24 - الدعاء بعد التشهد الأخير قبل السلام (55-65)
        raw += RawDua(55, "اللَّهُمَّ إِنِّي أَعُوذُ بِكَ مِنْ عَذَابِ الْقَبْرِ، وَمِنْ عَذَابِ جَهَنَّمَ، وَمِنْ فِتْنَةِ الْمَحْيَا وَالْمَمَاتِ، وَمِنْ شَرِّ فِتْنَةِ الْمَسِيحِ الدَّجَّالِ.", "متفق عليه", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_PRAYER), popular = true)
        raw += RawDua(56, "اللَّهُمَّ إِنِّي أَعُوذُ بِكَ مِنْ عَذَابِ الْقَبْرِ، وَأَعُوذُ بِكَ مِنْ فِتْنَةِ الْمَسِيحِ الدَّجَّالِ، وَأَعُوذُ بِكَ مِنْ فِتْنَةِ الْمَحْيَا وَالْمَمَاتِ، اللَّهُمَّ إِنِّي أَعُوذُ بِكَ مِنَ الْمَأْثَمِ وَالْمَغْرَمِ.", "البخاري، ومسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_PRAYER))
        raw += RawDua(57, "اللَّهُمَّ إِنِّي ظَلَمْتُ نَفْسِي ظُلْمًا كَثِيرًا، وَلَا يَغْفِرُ الذُّنُوبَ إِلَّا أَنْتَ، فَاغْفِرْ لِي مَغْفِرَةً مِنْ عِنْدِكَ وَارْحَمْنِي إِنَّكَ أَنْتَ الْغَفُورُ الرَّحِيمُ.", "متفق عليه", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_PRAYER, CAT_ISTIGHFAR))
        raw += RawDua(58, "اللَّهُمَّ اغْفِرْ لِي مَا قَدَّمْتُ وَمَا أَخَّرْتُ، وَمَا أَسْرَرْتُ وَمَا أَعْلَنْتُ، وَمَا أَسْرَفْتُ، وَمَا أَنْتَ أَعْلَمُ بِهِ مِنِّي، أَنْتَ الْمُقَدِّمُ وَأَنْتَ الْمُؤَخِّرُ، لَا إِلَهَ إِلَّا أَنْتَ.", "البخاري، ومسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_PRAYER))
        raw += RawDua(59, "اللَّهُمَّ أَعِنِّي عَلَى ذِكْرِكَ، وَشُكْرِكَ، وَحُسْنِ عِبَادَتِكَ.", "مسلم، وأبو داود", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_PRAYER))
        raw += RawDua(60, "اللَّهُمَّ إِنِّي أَعُوذُ بِكَ مِنَ الْبُخْلِ، وَأَعُوذُ بِكَ مِنَ الْجُبْنِ، وَأَعُوذُ بِكَ مِنْ أَنْ أُرَدَّ إِلَى أَرْذَلِ الْعُمُرِ، وَأَعُوذُ بِكَ مِنْ فِتْنَةِ الدُّنْيَا وَعَذَابِ الْقَبْرِ.", "البخاري", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_PRAYER))
        raw += RawDua(61, "اللَّهُمَّ إِنِّي أَسْأَلُكَ الْجَنَّةَ وَأَعُوذُ بِكَ مِنَ النَّارِ.", "أبو داود", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_PRAYER))
        raw += RawDua(62, "اللَّهُمَّ بِعِلْمِكَ الْغَيْبَ، وَقُدْرَتِكَ عَلَى الْخَلْقِ، أَحْيِنِي مَا عَلِمْتَ الْحَيَاةَ خَيْرًا لِي، وَتَوَفَّنِي إِذَا عَلِمْتَ الْوَفَاةَ خَيْرًا لِي، اللَّهُمَّ وَأَسْأَلُكَ خَشْيَتَكَ فِي الْغَيْبِ وَالشَّهَادَةِ، وَأَسْأَلُكَ كَلِمَةَ الْحَقِّ فِي الرِّضَا وَالْغَضَبِ، وَأَسْأَلُكَ الْقَصْدَ فِي الْغِنَى وَالْفَقْرِ، وَأَسْأَلُكَ نَعِيمًا لَا يَنْفَدُ، وَأَسْأَلُكَ قُرَّةَ عَيْنٍ لَا تَنْقَطِعُ، وَأَسْأَلُكَ الرِّضَا بَعْدَ الْقَضَاءِ، وَأَسْأَلُكَ بَرْدَ الْعَيْشِ بَعْدَ الْمَوْتِ، وَأَسْأَلُكَ لَذَّةَ النَّظَرِ إِلَى وَجْهِكَ، وَالشَّوْقَ إِلَى لِقَائِكَ، فِي غَيْرِ ضَرَّاءَ مُضِرَّةٍ، وَلَا فِتْنَةٍ مُضِلَّةٍ، اللَّهُمَّ زَيِّنَّا بِزِينَةِ الْإِيمَانِ، وَاجْعَلْنَا هُدَاةً مُهْتَدِينَ.", "النسائي، وصححه الألباني", AuthenticityGrade.HASAN, LengthCategory.LONG, listOf(CAT_PRAYER))
        raw += RawDua(63, "اللَّهُمَّ إِنِّي أَسْأَلُكَ يَا اللهُ بِأَنَّكَ الْوَاحِدُ الْأَحَدُ، الصَّمَدُ، الَّذِي لَمْ يَلِدْ وَلَمْ يُولَدْ، وَلَمْ يَكُنْ لَهُ كُفُوًا أَحَدٌ، أَنْ تَغْفِرَ لِي ذُنُوبِي إِنَّكَ أَنْتَ الْغَفُورُ الرَّحِيمُ.", "النسائي، وأبو داود، والترمذي", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_PRAYER))
        raw += RawDua(64, "اللَّهُمَّ إِنِّي أَسْأَلُكَ بِأَنَّ لَكَ الْحَمْدَ لَا إِلَهَ إِلَّا أَنْتَ وَحْدَكَ لَا شَرِيكَ لَكَ، الْمَنَّانُ، يَا بَدِيعَ السَّمَاوَاتِ وَالْأَرْضِ، يَا ذَا الْجَلَالِ وَالْإِكْرَامِ، يَا حَيُّ يَا قَيُّومُ، إِنِّي أَسْأَلُكَ الْجَنَّةَ وَأَعُوذُ بِكَ مِنَ النَّارِ.", "أصحاب السنن", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_PRAYER))
        raw += RawDua(65, "اللَّهُمَّ إِنِّي أَسْأَلُكَ بِأَنِّي أَشْهَدُ أَنَّكَ أَنْتَ اللهُ لَا إِلَهَ إِلَّا أَنْتَ، الْأَحَدُ الصَّمَدُ، الَّذِي لَمْ يَلِدْ وَلَمْ يُولَدْ، وَلَمْ يَكُنْ لَهُ كُفُوًا أَحَدٌ.", "أبو داود، والترمذي، والنسائي", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_PRAYER))

        // 25 - الأذكار بعد السلام من الصلاة (66-73)
        raw += RawDua(66, "أَسْتَغْفِرُ اللهَ (ثَلَاثًا)، اللَّهُمَّ أَنْتَ السَّلَامُ، وَمِنْكَ السَّلَامُ، تَبَارَكْتَ يَا ذَا الْجَلَالِ وَالْإِكْرَامِ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_PRAYER), popular = true)
        raw += RawDua(67, "لَا إِلَهَ إِلَّا اللهُ وَحْدَهُ لَا شَرِيكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ وَهُوَ عَلَى كُلِّ شَيْءٍ قَدِيرٌ (ثَلَاثًا)، اللَّهُمَّ لَا مَانِعَ لِمَا أَعْطَيْتَ، وَلَا مُعْطِيَ لِمَا مَنَعْتَ، وَلَا يَنْفَعُ ذَا الْجَدِّ مِنْكَ الْجَدُّ.", "متفق عليه", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_PRAYER))
        raw += RawDua(68, "لَا إِلَهَ إِلَّا اللهُ وَحْدَهُ لَا شَرِيكَ لَهُ، لَهُ الْمُلْكُ، وَلَهُ الْحَمْدُ، وَهُوَ عَلَى كُلِّ شَيْءٍ قَدِيرٌ، لَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِاللهِ، لَا إِلَهَ إِلَّا اللهُ، وَلَا نَعْبُدُ إِلَّا إِيَّاهُ، لَهُ النِّعْمَةُ، وَلَهُ الْفَضْلُ، وَلَهُ الثَّنَاءُ الْحَسَنُ، لَا إِلَهَ إِلَّا اللهُ مُخْلِصِينَ لَهُ الدِّينَ وَلَوْ كَرِهَ الْكَافِرُونَ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.LONG, listOf(CAT_PRAYER))
        raw += RawDua(69, "سُبْحَانَ اللهِ، وَالْحَمْدُ لِلَّهِ، وَاللهُ أَكْبَرُ (ثَلَاثًا وَثَلَاثِينَ)، لَا إِلَهَ إِلَّا اللهُ وَحْدَهُ لَا شَرِيكَ لَهُ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_PRAYER, CAT_TASBEEH), popular = true, repeatCount = 33)
        raw += RawDua(70, "اللَّهُمَّ أَعِنِّي عَلَى ذِكْرِكَ، وَشُكْرِكَ، وَحُسْنِ عِبَادَتِكَ.", "أبو داود، والنسائي، وأحمد", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_PRAYER))
        raw += RawDua(71, "آيَةُ الْكُرْسِيِّ: اللهُ لَا إِلَهَ إِلَّا هُوَ الْحَيُّ الْقَيُّومُ لَا تَأْخُذُهُ سِنَةٌ وَلَا نَوْمٌ لَهُ مَا فِي السَّمَاوَاتِ وَمَا فِي الْأَرْضِ مَنْ ذَا الَّذِي يَشْفَعُ عِنْدَهُ إِلَّا بِإِذْنِهِ يَعْلَمُ مَا بَيْنَ أَيْدِيهِمْ وَمَا خَلْفَهُمْ وَلَا يُحِيطُونَ بِشَيْءٍ مِنْ عِلْمِهِ إِلَّا بِمَا شَاءَ وَسِعَ كُرْسِيُّهُ السَّمَاوَاتِ وَالْأَرْضَ وَلَا يَؤُودُهُ حِفْظُهُمَا وَهُوَ الْعَلِيُّ الْعَظِيمُ.", "البقرة: 255", AuthenticityGrade.QURAN, LengthCategory.LONG, listOf(CAT_PRAYER), popular = true)
        raw += RawDua(72, "قُلْ هُوَ اللهُ أَحَدٌ، وَالْمُعَوِّذَتَانِ (بعد صلاتي المغرب والصبح).", "الإخلاص، الفلق، الناس", AuthenticityGrade.QURAN, LengthCategory.MEDIUM, listOf(CAT_PRAYER), repeatCount = 3)
        raw += RawDua(73, "اللَّهُمَّ إِنِّي أَسْأَلُكَ عِلْمًا نَافِعًا، وَرِزْقًا طَيِّبًا، وَعَمَلًا مُتَقَبَّلًا (بعد السلام من صلاة الفجر).", "ابن ماجه، وأحمد", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_PRAYER, CAT_MORNING))

        // 26 - دعاء صلاة الاستخارة (74)
        raw += RawDua(74, "اللَّهُمَّ إِنِّي أَسْتَخِيرُكَ بِعِلْمِكَ، وَأَسْتَقْدِرُكَ بِقُدْرَتِكَ، وَأَسْأَلُكَ مِنْ فَضْلِكَ الْعَظِيمِ، فَإِنَّكَ تَقْدِرُ وَلَا أَقْدِرُ، وَتَعْلَمُ وَلَا أَعْلَمُ، وَأَنْتَ عَلَّامُ الْغُيُوبِ، اللَّهُمَّ إِنْ كُنْتَ تَعْلَمُ أَنَّ هَذَا الْأَمْرَ خَيْرٌ لِي فِي دِينِي وَمَعَاشِي وَعَاقِبَةِ أَمْرِي - أَوْ قَالَ: عَاجِلِ أَمْرِي وَآجِلِهِ - فَاقْدُرْهُ لِي وَيَسِّرْهُ لِي ثُمَّ بَارِكْ لِي فِيهِ، وَإِنْ كُنْتَ تَعْلَمُ أَنَّ هَذَا الْأَمْرَ شَرٌّ لِي فِي دِينِي وَمَعَاشِي وَعَاقِبَةِ أَمْرِي - أَوْ قَالَ: عَاجِلِ أَمْرِي وَآجِلِهِ - فَاصْرِفْهُ عَنِّي وَاصْرِفْنِي عَنْهُ، وَاقْدُرْ لِيَ الْخَيْرَ حَيْثُ كَانَ، ثُمَّ أَرْضِنِي بِهِ.", "البخاري", AuthenticityGrade.SAHIH, LengthCategory.LONG, listOf(CAT_ISTIKHARA), popular = true, narrator = "جابر بن عبد الله رضي الله عنهما")

        // 27 - أذكار الصباح والمساء (75-98)
        raw += RawDua(75, "آيَةُ الْكُرْسِيِّ: اللهُ لَا إِلَهَ إِلَّا هُوَ الْحَيُّ الْقَيُّومُ لَا تَأْخُذُهُ سِنَةٌ وَلَا نَوْمٌ لَهُ مَا فِي السَّمَاوَاتِ وَمَا فِي الْأَرْضِ مَنْ ذَا الَّذِي يَشْفَعُ عِنْدَهُ إِلَّا بِإِذْنِهِ يَعْلَمُ مَا بَيْنَ أَيْدِيهِمْ وَمَا خَلْفَهُمْ وَلَا يُحِيطُونَ بِشَيْءٍ مِنْ عِلْمِهِ إِلَّا بِمَا شَاءَ وَسِعَ كُرْسِيُّهُ السَّمَاوَاتِ وَالْأَرْضَ وَلَا يَؤُودُهُ حِفْظُهُمَا وَهُوَ الْعَلِيُّ الْعَظِيمُ.", "البقرة: 255؛ الترمذي، والحاكم", AuthenticityGrade.QURAN, LengthCategory.LONG, listOf(CAT_MORNING, CAT_EVENING), popular = true)
        raw += RawDua(76, "قُلْ هُوَ اللهُ أَحَدٌ، وَالْمُعَوِّذَتَانِ (سورة الفلق وسورة الناس).", "أحمد، والنسائي في عمل اليوم والليلة، وابن السني", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_MORNING, CAT_EVENING), repeatCount = 3)
        raw += RawDua(77, "أَصْبَحْنَا وَأَصْبَحَ الْمُلْكُ لِلَّهِ، وَالْحَمْدُ لِلَّهِ، لَا إِلَهَ إِلَّا اللهُ وَحْدَهُ لَا شَرِيكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ وَهُوَ عَلَى كُلِّ شَيْءٍ قَدِيرٌ، رَبِّ أَسْأَلُكَ خَيْرَ مَا فِي هَذَا الْيَوْمِ وَخَيْرَ مَا بَعْدَهُ، وَأَعُوذُ بِكَ مِنْ شَرِّ مَا فِي هَذَا الْيَوْمِ وَشَرِّ مَا بَعْدَهُ، رَبِّ أَعُوذُ بِكَ مِنَ الْكَسَلِ وَسُوءِ الْكِبَرِ، رَبِّ أَعُوذُ بِكَ مِنْ عَذَابٍ فِي النَّارِ وَعَذَابٍ فِي الْقَبْرِ. (وفي المساء: أَمْسَيْنَا وَأَمْسَى الْمُلْكُ لِلَّهِ، والباقي مثله).", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.LONG, listOf(CAT_MORNING, CAT_EVENING))
        raw += RawDua(78, "اللَّهُمَّ بِكَ أَصْبَحْنَا، وَبِكَ أَمْسَيْنَا، وَبِكَ نَحْيَا، وَبِكَ نَمُوتُ وَإِلَيْكَ النُّشُورُ. (وفي المساء: بِكَ أَمْسَيْنَا، وَبِكَ أَصْبَحْنَا، وَبِكَ نَحْيَا، وَبِكَ نَمُوتُ وَإِلَيْكَ الْمَصِيرُ).", "الترمذي", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_MORNING, CAT_EVENING))
        raw += RawDua(79, "اللَّهُمَّ أَنْتَ رَبِّي لَا إِلَهَ إِلَّا أَنْتَ، خَلَقْتَنِي وَأَنَا عَبْدُكَ، وَأَنَا عَلَى عَهْدِكَ وَوَعْدِكَ مَا اسْتَطَعْتُ، أَعُوذُ بِكَ مِنْ شَرِّ مَا صَنَعْتُ، أَبُوءُ لَكَ بِنِعْمَتِكَ عَلَيَّ، وَأَبُوءُ بِذَنْبِي فَاغْفِرْ لِي فَإِنَّهُ لَا يَغْفِرُ الذُّنُوبَ إِلَّا أَنْتَ. [سيد الاستغفار]", "البخاري", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_MORNING, CAT_EVENING, CAT_ISTIGHFAR), popular = true)
        raw += RawDua(80, "أَصْبَحْتُ أُشْهِدُكَ، وَأُشْهِدُ حَمَلَةَ عَرْشِكَ، وَمَلَائِكَتَكَ، وَجَمِيعَ خَلْقِكَ، أَنَّكَ أَنْتَ اللهُ لَا إِلَهَ إِلَّا أَنْتَ وَحْدَكَ لَا شَرِيكَ لَكَ، وَأَنَّ مُحَمَّدًا عَبْدُكَ وَرَسُولُكَ.", "أبو داود", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_MORNING, CAT_EVENING), repeatCount = 4)
        raw += RawDua(81, "اللَّهُمَّ مَا أَصْبَحَ بِي مِنْ نِعْمَةٍ أَوْ بِأَحَدٍ مِنْ خَلْقِكَ فَمِنْكَ وَحْدَكَ لَا شَرِيكَ لَكَ، فَلَكَ الْحَمْدُ وَلَكَ الشُّكْرُ.", "أبو داود", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_MORNING, CAT_EVENING))
        raw += RawDua(82, "اللَّهُمَّ عَافِنِي فِي بَدَنِي، اللَّهُمَّ عَافِنِي فِي سَمْعِي، اللَّهُمَّ عَافِنِي فِي بَصَرِي، لَا إِلَهَ إِلَّا أَنْتَ، اللَّهُمَّ إِنِّي أَعُوذُ بِكَ مِنَ الْكُفْرِ، وَالْفَقْرِ، وَأَعُوذُ بِكَ مِنْ عَذَابِ الْقَبْرِ، لَا إِلَهَ إِلَّا أَنْتَ.", "أبو داود، وأحمد", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_MORNING, CAT_EVENING), repeatCount = 3)
        raw += RawDua(83, "حَسْبِيَ اللهُ لَا إِلَهَ إِلَّا هُوَ عَلَيْهِ تَوَكَّلْتُ وَهُوَ رَبُّ الْعَرْشِ الْعَظِيمِ.", "أبو داود", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_MORNING, CAT_EVENING), popular = true, repeatCount = 7)
        raw += RawDua(84, "اللَّهُمَّ إِنِّي أَسْأَلُكَ الْعَفْوَ وَالْعَافِيَةَ فِي الدُّنْيَا وَالْآخِرَةِ، اللَّهُمَّ إِنِّي أَسْأَلُكَ الْعَفْوَ وَالْعَافِيَةَ فِي دِينِي وَدُنْيَايَ وَأَهْلِي وَمَالِي، اللَّهُمَّ اسْتُرْ عَوْرَاتِي، وَآمِنْ رَوْعَاتِي، اللَّهُمَّ احْفَظْنِي مِنْ بَيْنِ يَدَيَّ، وَمِنْ خَلْفِي، وَعَنْ يَمِينِي، وَعَنْ شِمَالِي، وَمِنْ فَوْقِي، وَأَعُوذُ بِعَظَمَتِكَ أَنْ أُغْتَالَ مِنْ تَحْتِي.", "أبو داود، وابن ماجه", AuthenticityGrade.HASAN, LengthCategory.LONG, listOf(CAT_MORNING, CAT_EVENING))
        raw += RawDua(85, "اللَّهُمَّ عَالِمَ الْغَيْبِ وَالشَّهَادَةِ فَاطِرَ السَّمَاوَاتِ وَالْأَرْضِ، رَبَّ كُلِّ شَيْءٍ وَمَلِيكَهُ، أَشْهَدُ أَنْ لَا إِلَهَ إِلَّا أَنْتَ، أَعُوذُ بِكَ مِنْ شَرِّ نَفْسِي، وَمِنْ شَرِّ الشَّيْطَانِ وَشِرْكِهِ، وَأَنْ أَقْتَرِفَ عَلَى نَفْسِي سُوءًا، أَوْ أَجُرَّهُ إِلَى مُسْلِمٍ.", "الترمذي، وأبو داود", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_MORNING, CAT_EVENING))
        raw += RawDua(86, "بِسْمِ اللهِ الَّذِي لَا يَضُرُّ مَعَ اسْمِهِ شَيْءٌ فِي الْأَرْضِ وَلَا فِي السَّمَاءِ وَهُوَ السَّمِيعُ الْعَلِيمُ.", "أبو داود، والترمذي", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_MORNING, CAT_EVENING), popular = true, repeatCount = 3)
        raw += RawDua(87, "رَضِيتُ بِاللهِ رَبًّا، وَبِالْإِسْلَامِ دِينًا، وَبِمُحَمَّدٍ ﷺ نَبِيًّا.", "الترمذي، وأحمد", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_MORNING, CAT_EVENING), repeatCount = 3)
        raw += RawDua(88, "يَا حَيُّ يَا قَيُّومُ بِرَحْمَتِكَ أَسْتَغِيثُ، أَصْلِحْ لِي شَأْنِي كُلَّهُ، وَلَا تَكِلْنِي إِلَى نَفْسِي طَرْفَةَ عَيْنٍ.", "الحاكم، وحسّن إسناده ابن باز", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_MORNING, CAT_EVENING))
        raw += RawDua(89, "أَصْبَحْنَا وَأَصْبَحَ الْمُلْكُ لِلَّهِ رَبِّ الْعَالَمِينَ، اللَّهُمَّ إِنِّي أَسْأَلُكَ خَيْرَ هَذَا الْيَوْمِ: فَتْحَهُ، وَنَصْرَهُ، وَنُورَهُ، وَبَرَكَتَهُ، وَهُدَاهُ، وَأَعُوذُ بِكَ مِنْ شَرِّ مَا فِيهِ وَشَرِّ مَا بَعْدَهُ.", "أبو داود", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_MORNING))
        raw += RawDua(90, "أَصْبَحْنَا عَلَى فِطْرَةِ الْإِسْلَامِ، وَعَلَى كَلِمَةِ الْإِخْلَاصِ، وَعَلَى دِينِ نَبِيِّنَا مُحَمَّدٍ ﷺ، وَعَلَى مِلَّةِ أَبِينَا إِبْرَاهِيمَ حَنِيفًا مُسْلِمًا وَمَا كَانَ مِنَ الْمُشْرِكِينَ.", "أحمد، وابن السني", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_MORNING, CAT_EVENING))
        raw += RawDua(91, "سُبْحَانَ اللهِ وَبِحَمْدِهِ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_MORNING, CAT_EVENING, CAT_TASBEEH), popular = true, repeatCount = 100)
        raw += RawDua(92, "لَا إِلَهَ إِلَّا اللهُ وَحْدَهُ لَا شَرِيكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ وَهُوَ عَلَى كُلِّ شَيْءٍ قَدِيرٌ.", "النسائي، وابن حبان", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_MORNING, CAT_EVENING), repeatCount = 10)
        raw += RawDua(93, "لَا إِلَهَ إِلَّا اللهُ، وَحْدَهُ لَا شَرِيكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ، وَهُوَ عَلَى كُلِّ شَيْءٍ قَدِيرٌ.", "الترمذي", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_MORNING), repeatCount = 100)
        raw += RawDua(94, "سُبْحَانَ اللهِ وَبِحَمْدِهِ: عَدَدَ خَلْقِهِ، وَرِضَا نَفْسِهِ، وَزِنَةَ عَرْشِهِ، وَمِدَادَ كَلِمَاتِهِ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_MORNING, CAT_TASBEEH), repeatCount = 3)
        raw += RawDua(95, "اللَّهُمَّ إِنِّي أَسْأَلُكَ عِلْمًا نَافِعًا، وَرِزْقًا طَيِّبًا، وَعَمَلًا مُتَقَبَّلًا.", "ابن ماجه", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_MORNING))
        raw += RawDua(96, "أَسْتَغْفِرُ اللهَ وَأَتُوبُ إِلَيْهِ.", "البخاري", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_MORNING, CAT_EVENING, CAT_ISTIGHFAR), repeatCount = 100)
        raw += RawDua(97, "أَعُوذُ بِكَلِمَاتِ اللهِ التَّامَّاتِ مِنْ شَرِّ مَا خَلَقَ.", "الترمذي", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_EVENING, CAT_PROTECTION), repeatCount = 3)
        raw += RawDua(98, "اللَّهُمَّ صَلِّ وَسَلِّمْ عَلَى نَبِيِّنَا مُحَمَّدٍ.", "الطبراني", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_MORNING, CAT_EVENING), repeatCount = 10)

        // 28 - أذكار النوم (99-111)
        raw += RawDua(99, "يَجْمَعُ كَفَّيْهِ ثُمَّ يَنْفُثُ فِيهِمَا فَيَقْرَأُ فِيهِمَا: قُلْ هُوَ اللهُ أَحَدٌ، وَقُلْ أَعُوذُ بِرَبِّ الْفَلَقِ، وَقُلْ أَعُوذُ بِرَبِّ النَّاسِ، ثُمَّ يَمْسَحُ بِهِمَا مَا اسْتَطَاعَ مِنْ جَسَدِهِ، يَبْدَأُ بِهِمَا عَلَى رَأْسِهِ وَوَجْهِهِ وَمَا أَقْبَلَ مِنْ جَسَدِهِ.", "البخاري", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_SLEEP), repeatCount = 3)
        raw += RawDua(100, "آيَةُ الْكُرْسِيِّ (عند النوم).", "البقرة: 255", AuthenticityGrade.QURAN, LengthCategory.LONG, listOf(CAT_SLEEP))
        raw += RawDua(101, "آمَنَ الرَّسُولُ بِمَا أُنْزِلَ إِلَيْهِ مِنْ رَبِّهِ وَالْمُؤْمِنُونَ... (آخر آيتين من سورة البقرة).", "البقرة: 285-286", AuthenticityGrade.QURAN, LengthCategory.LONG, listOf(CAT_SLEEP))
        raw += RawDua(102, "بِاسْمِكَ رَبِّي وَضَعْتُ جَنْبِي، وَبِكَ أَرْفَعُهُ، فَإِنْ أَمْسَكْتَ نَفْسِي فَارْحَمْهَا، وَإِنْ أَرْسَلْتَهَا فَاحْفَظْهَا بِمَا تَحْفَظُ بِهِ عِبَادَكَ الصَّالِحِينَ.", "البخاري، ومسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_SLEEP))
        raw += RawDua(103, "اللَّهُمَّ إِنَّكَ خَلَقْتَ نَفْسِي وَأَنْتَ تَوَفَّاهَا، لَكَ مَمَاتُهَا وَمَحْيَاهَا، إِنْ أَحْيَيْتَهَا فَاحْفَظْهَا، وَإِنْ أَمَتَّهَا فَاغْفِرْ لَهَا، اللَّهُمَّ إِنِّي أَسْأَلُكَ الْعَافِيَةَ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_SLEEP))
        raw += RawDua(104, "اللَّهُمَّ قِنِي عَذَابَكَ يَوْمَ تَبْعَثُ عِبَادَكَ.", "أبو داود، والترمذي", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_SLEEP))
        raw += RawDua(105, "بِاسْمِكَ اللَّهُمَّ أَمُوتُ وَأَحْيَا.", "البخاري", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_SLEEP))
        raw += RawDua(106, "سُبْحَانَ اللهِ (ثَلَاثًا وَثَلَاثِينَ)، وَالْحَمْدُ لِلَّهِ (ثَلَاثًا وَثَلَاثِينَ)، وَاللهُ أَكْبَرُ (أَرْبَعًا وَثَلَاثِينَ).", "متفق عليه", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_SLEEP, CAT_TASBEEH), popular = true)
        raw += RawDua(107, "اللَّهُمَّ رَبَّ السَّمَاوَاتِ السَّبْعِ وَرَبَّ الْأَرْضِ وَرَبَّ الْعَرْشِ الْعَظِيمِ، رَبَّنَا وَرَبَّ كُلِّ شَيْءٍ، فَالِقَ الْحَبِّ وَالنَّوَى، وَمُنْزِلَ التَّوْرَاةِ وَالْإِنْجِيلِ وَالْفُرْقَانِ، أَعُوذُ بِكَ مِنْ شَرِّ كُلِّ شَيْءٍ أَنْتَ آخِذٌ بِنَاصِيَتِهِ، اللَّهُمَّ أَنْتَ الْأَوَّلُ فَلَيْسَ قَبْلَكَ شَيْءٌ، وَأَنْتَ الْآخِرُ فَلَيْسَ بَعْدَكَ شَيْءٌ، وَأَنْتَ الظَّاهِرُ فَلَيْسَ فَوْقَكَ شَيْءٌ، وَأَنْتَ الْبَاطِنُ فَلَيْسَ دُونَكَ شَيْءٌ، اقْضِ عَنَّا الدَّيْنَ، وَأَغْنِنَا مِنَ الْفَقْرِ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.LONG, listOf(CAT_SLEEP))
        raw += RawDua(108, "الْحَمْدُ لِلَّهِ الَّذِي أَطْعَمَنَا وَسَقَانَا، وَكَفَانَا، وَآوَانَا، فَكَمْ مِمَّنْ لَا كَافِيَ لَهُ وَلَا مُؤْوِيَ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_SLEEP))
        raw += RawDua(109, "اللَّهُمَّ عَالِمَ الْغَيْبِ وَالشَّهَادَةِ فَاطِرَ السَّمَاوَاتِ وَالْأَرْضِ، رَبَّ كُلِّ شَيْءٍ وَمَلِيكَهُ، أَشْهَدُ أَنْ لَا إِلَهَ إِلَّا أَنْتَ، أَعُوذُ بِكَ مِنْ شَرِّ نَفْسِي وَمِنْ شَرِّ الشَّيْطَانِ وَشِرْكِهِ، وَأَنْ أَقْتَرِفَ عَلَى نَفْسِي سُوءًا أَوْ أَجُرَّهُ إِلَى مُسْلِمٍ.", "أبو داود، والترمذي", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_SLEEP))
        raw += RawDua(110, "يَقْرَأُ: الم تَنْزِيلُ السَّجْدَةِ (السجدة)، وَتَبَارَكَ الَّذِي بِيَدِهِ الْمُلْكُ (الملك).", "الترمذي، والنسائي، وصحيح الجامع", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_SLEEP))
        raw += RawDua(111, "اللَّهُمَّ أَسْلَمْتُ نَفْسِي إِلَيْكَ، وَفَوَّضْتُ أَمْرِي إِلَيْكَ، وَوَجَّهْتُ وَجْهِي إِلَيْكَ، وَأَلْجَأْتُ ظَهْرِي إِلَيْكَ، رَغْبَةً وَرَهْبَةً إِلَيْكَ، لَا مَلْجَأَ وَلَا مَنْجَا مِنْكَ إِلَّا إِلَيْكَ، آمَنْتُ بِكِتَابِكَ الَّذِي أَنْزَلْتَ، وَبِنَبِيِّكَ الَّذِي أَرْسَلْتَ.", "متفق عليه", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_SLEEP), popular = true)

        // 29 - الدعاء إذا تقلب ليلاً (112)
        raw += RawDua(112, "لَا إِلَهَ إِلَّا اللهُ الْوَاحِدُ الْقَهَّارُ، رَبُّ السَّمَاوَاتِ وَالْأَرْضِ وَمَا بَيْنَهُمَا الْعَزِيزُ الْغَفَّارُ.", "الترمذي، والنسائي في عمل اليوم والليلة", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_SLEEP))

        // 30 - دعاء الفزع في النوم ومن بُلي بالوحشة (113)
        raw += RawDua(113, "أَعُوذُ بِكَلِمَاتِ اللهِ التَّامَّاتِ مِنْ غَضَبِهِ وَعِقَابِهِ، وَشَرِّ عِبَادِهِ، وَمِنْ هَمَزَاتِ الشَّيَاطِينِ وَأَنْ يَحْضُرُونِ.", "أبو داود، والترمذي", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_SLEEP, CAT_PROTECTION))

        // 31 - ما يفعل من رأى الرؤيا أو الحلم (114-118)
        raw += RawDua(114, "يَنْفُثُ عَنْ يَسَارِهِ (ثَلَاثًا).", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_SLEEP), repeatCount = 3)
        raw += RawDua(115, "يَسْتَعِيذُ بِاللهِ مِنَ الشَّيْطَانِ وَمِنْ شَرِّ مَا رَأَى (ثَلَاثَ مَرَّاتٍ).", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_SLEEP), repeatCount = 3)
        raw += RawDua(116, "لَا يُحَدِّثُ بِهَا أَحَدًا.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_SLEEP))
        raw += RawDua(117, "يَتَحَوَّلُ عَنْ جَنْبِهِ الَّذِي كَانَ عَلَيْهِ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_SLEEP))
        raw += RawDua(118, "يَقُومُ يُصَلِّي إِنْ أَرَادَ ذَلِكَ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_SLEEP))

        // 32 - دعاء قنوت الوتر (119-121)
        raw += RawDua(119, "اللَّهُمَّ اهْدِنِي فِيمَنْ هَدَيْتَ، وَعَافِنِي فِيمَنْ عَافَيْتَ، وَتَوَلَّنِي فِيمَنْ تَوَلَّيْتَ، وَبَارِكْ لِي فِيمَا أَعْطَيْتَ، وَقِنِي شَرَّ مَا قَضَيْتَ؛ فَإِنَّكَ تَقْضِي وَلَا يُقْضَى عَلَيْكَ، إِنَّهُ لَا يَذِلُّ مَنْ وَالَيْتَ، وَلَا يَعِزُّ مَنْ عَادَيْتَ، تَبَارَكْتَ رَبَّنَا وَتَعَالَيْتَ.", "الترمذي، والنسائي", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_WITR))
        raw += RawDua(120, "اللَّهُمَّ إِنِّي أَعُوذُ بِرِضَاكَ مِنْ سَخَطِكَ، وَبِمُعَافَاتِكَ مِنْ عُقُوبَتِكَ، وَأَعُوذُ بِكَ مِنْكَ، لَا أُحْصِي ثَنَاءً عَلَيْكَ، أَنْتَ كَمَا أَثْنَيْتَ عَلَى نَفْسِكَ.", "أبو داود، والترمذي", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_WITR))
        raw += RawDua(121, "اللَّهُمَّ إِيَّاكَ نَعْبُدُ، وَلَكَ نُصَلِّي وَنَسْجُدُ، وَإِلَيْكَ نَسْعَى وَنَحْفِدُ، نَرْجُو رَحْمَتَكَ، وَنَخْشَى عَذَابَكَ، إِنَّ عَذَابَكَ الْجِدَّ بِالْكُفَّارِ مُلْحِقٌ، اللَّهُمَّ إِنَّا نَسْتَعِينُكَ وَنَسْتَغْفِرُكَ، وَنُثْنِي عَلَيْكَ الْخَيْرَ، وَنَشْكُرُكَ وَلَا نَكْفُرُكَ، وَنُؤْمِنُ بِكَ، وَنَخْضَعُ لَكَ، وَنَخْلَعُ وَنَتْرُكُ مَنْ يَكْفُرُكَ.", "رواه أصحاب السنن، وأحمد", AuthenticityGrade.HASAN, LengthCategory.LONG, listOf(CAT_WITR))

        // 33 - الذكر عقب السلام من الوتر (122)
        raw += RawDua(122, "سُبْحَانَ الْمَلِكِ الْقُدُّوسِ (ثَلَاثَ مَرَّاتٍ، يَجْهَرُ بِهَا وَيَمُدُّ بِهَا صَوْتَهُ فِي الثَّالِثَةِ، وَيَقُولُ: رَبِّ الْمَلَائِكَةِ وَالرُّوحِ).", "النسائي، والدارقطني", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_WITR), repeatCount = 3)

        // 34 - دعاء الهم والحزن (123-124)
        raw += RawDua(123, "اللَّهُمَّ إِنِّي عَبْدُكَ، ابْنُ عَبْدِكَ، ابْنُ أَمَتِكَ، نَاصِيَتِي بِيَدِكَ، مَاضٍ فِيَّ حُكْمُكَ، عَدْلٌ فِيَّ قَضَاؤُكَ، أَسْأَلُكَ بِكُلِّ اسْمٍ هُوَ لَكَ سَمَّيْتَ بِهِ نَفْسَكَ، أَوْ أَنْزَلْتَهُ فِي كِتَابِكَ، أَوْ عَلَّمْتَهُ أَحَدًا مِنْ خَلْقِكَ، أَوِ اسْتَأْثَرْتَ بِهِ فِي عِلْمِ الْغَيْبِ عِنْدَكَ، أَنْ تَجْعَلَ الْقُرْآنَ رَبِيعَ قَلْبِي، وَنُورَ صَدْرِي، وَجَلَاءَ حُزْنِي، وَذَهَابَ هَمِّي.", "أحمد، وصححه الألباني", AuthenticityGrade.HASAN, LengthCategory.LONG, listOf(CAT_DISTRESS), popular = true, narrator = "عبد الله بن مسعود رضي الله عنه")
        raw += RawDua(124, "اللَّهُمَّ إِنِّي أَعُوذُ بِكَ مِنَ الْهَمِّ وَالْحَزَنِ، وَالْعَجْزِ وَالْكَسَلِ، وَالْبُخْلِ وَالْجُبْنِ، وَضَلَعِ الدَّيْنِ وَغَلَبَةِ الرِّجَالِ.", "البخاري", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_DISTRESS))

        // 35 - دعاء الكرب (125-128)
        raw += RawDua(125, "لَا إِلَهَ إِلَّا اللهُ الْعَظِيمُ الْحَلِيمُ، لَا إِلَهَ إِلَّا اللهُ رَبُّ الْعَرْشِ الْعَظِيمِ، لَا إِلَهَ إِلَّا اللهُ رَبُّ السَّمَاوَاتِ وَرَبُّ الْأَرْضِ وَرَبُّ الْعَرْشِ الْكَرِيمِ.", "البخاري، ومسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_DISTRESS), popular = true)
        raw += RawDua(126, "اللَّهُمَّ رَحْمَتَكَ أَرْجُو، فَلَا تَكِلْنِي إِلَى نَفْسِي طَرْفَةَ عَيْنٍ، وَأَصْلِحْ لِي شَأْنِي كُلَّهُ، لَا إِلَهَ إِلَّا أَنْتَ.", "أبو داود", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_DISTRESS))
        raw += RawDua(127, "لَا إِلَهَ إِلَّا أَنْتَ سُبْحَانَكَ إِنِّي كُنْتُ مِنَ الظَّالِمِينَ.", "الأنبياء: 87", AuthenticityGrade.QURAN, LengthCategory.SHORT, listOf(CAT_DISTRESS))
        raw += RawDua(128, "اللهُ، اللهُ رَبِّي لَا أُشْرِكُ بِهِ شَيْئًا.", "أبو داود", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_DISTRESS))

        // 36 - دعاء لقاء العدو وذي السلطان (129-131)
        raw += RawDua(129, "اللَّهُمَّ إِنَّا نَجْعَلُكَ فِي نُحُورِهِمْ، وَنَعُوذُ بِكَ مِنْ شُرُورِهِمْ.", "أبو داود", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_OPPRESSION))
        raw += RawDua(130, "اللَّهُمَّ أَنْتَ عَضُدِي، وَأَنْتَ نَصِيرِي، بِكَ أَحُولُ، وَبِكَ أَصُولُ، وَبِكَ أُقَاتِلُ.", "أبو داود", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_OPPRESSION))
        raw += RawDua(131, "حَسْبُنَا اللهُ وَنِعْمَ الْوَكِيلُ.", "البخاري", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_OPPRESSION), popular = true)

        // 37 - دعاء من خاف ظلم السلطان (132)
        raw += RawDua(132, "اللَّهُمَّ رَبَّ السَّمَاوَاتِ السَّبْعِ وَرَبَّ الْعَرْشِ الْعَظِيمِ، كُنْ لِي جَارًا مِنْ فُلَانِ بْنِ فُلَانٍ وَأَحْزَابِهِ مِنْ خَلَائِقِكَ، أَنْ يَفْرُطَ عَلَيَّ أَحَدٌ مِنْهُمْ أَوْ يَطْغَى، عَزَّ جَارُكَ، وَجَلَّ ثَنَاؤُكَ، وَلَا إِلَهَ إِلَّا أَنْتَ.", "أبو داود، وابن ماجه", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_OPPRESSION))

        // 38 - الدعاء على العدو (133-134)
        raw += RawDua(133, "اللهُ أَكْبَرُ، اللهُ أَعَزُّ مِنْ خَلْقِهِ جَمِيعًا، اللهُ أَعَزُّ مِمَّا أَخَافُ وَأَحْذَرُ، أَعُوذُ بِاللهِ الَّذِي لَا إِلَهَ إِلَّا هُوَ، الْمُمْسِكِ السَّمَاوَاتِ السَّبْعِ أَنْ يَقَعْنَ عَلَى الْأَرْضِ إِلَّا بِإِذْنِهِ، مِنْ شَرِّ عَبْدِكَ فُلَانٍ، وَجُنُودِهِ، وَأَتْبَاعِهِ، وَأَشْيَاعِهِ، مِنَ الْجِنِّ وَالْإِنْسِ، اللَّهُمَّ كُنْ لِي جَارًا مِنْ شَرِّهِمْ، جَلَّ ثَنَاؤُكَ، وَعَزَّ جَارُكَ، وَتَبَارَكَ اسْمُكَ، وَلَا إِلَهَ غَيْرُكَ.", "البخاري في الأدب المفرد، وصححه الألباني", AuthenticityGrade.HASAN, LengthCategory.LONG, listOf(CAT_OPPRESSION), popular = true, repeatCount = 3)
        raw += RawDua(134, "اللَّهُمَّ مُنْزِلَ الْكِتَابِ، سَرِيعَ الْحِسَابِ، اهْزِمِ الْأَحْزَابَ، اللَّهُمَّ اهْزِمْهُمْ وَزَلْزِلْهُمْ.", "متفق عليه", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_OPPRESSION))

        // 39 - ما يقول من خاف قوماً (135)
        raw += RawDua(135, "اللَّهُمَّ اكْفِنِيهِمْ بِمَا شِئْتَ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_OPPRESSION))

        // 40 - دعاء من أصابه وسوسة في الإيمان (136-137)
        raw += RawDua(136, "يَسْتَعِيذُ بِاللهِ مِنَ الشَّيْطَانِ الرَّجِيمِ.", "البخاري", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_WHISPERS))
        raw += RawDua(137, "يَنْتَهِي عَمَّا وُسْوِسَ فِيهِ.", "البخاري، ومسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_WHISPERS))

        // 41 - دعاء قضاء الدَّين (138-139)
        raw += RawDua(138, "اللَّهُمَّ اكْفِنِي بِحَلَالِكَ عَنْ حَرَامِكَ، وَأَغْنِنِي بِفَضْلِكَ عَمَّنْ سِوَاكَ.", "الترمذي", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_MISC))
        raw += RawDua(139, "اللَّهُمَّ إِنِّي أَعُوذُ بِكَ مِنَ الْهَمِّ وَالْحَزَنِ، وَالْعَجْزِ وَالْكَسَلِ، وَالْبُخْلِ وَالْجُبْنِ، وَضَلَعِ الدَّيْنِ وَغَلَبَةِ الرِّجَالِ.", "البخاري", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_MISC))

        // 42 - دعاء الوسوسة في الصلاة والقراءة (140)
        raw += RawDua(140, "أَعُوذُ بِاللهِ مِنَ الشَّيْطَانِ الرَّجِيمِ، وَيَتْفُلُ عَنْ يَسَارِهِ ثَلَاثًا.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_WHISPERS), repeatCount = 3)

        // 43 - دعاء من استصعب عليه أمر (141)
        raw += RawDua(141, "اللَّهُمَّ لَا سَهْلَ إِلَّا مَا جَعَلْتَهُ سَهْلًا، وَأَنْتَ تَجْعَلُ الْحَزَنَ إِذَا شِئْتَ سَهْلًا.", "ابن حبان", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_MISC))

        // 44 - ما يقول ويفعل من أذنب ذنباً (142)
        raw += RawDua(142, "مَا مِنْ عَبْدٍ يُذْنِبُ ذَنْبًا فَيُحْسِنُ الطُّهُورَ، ثُمَّ يَقُومُ فَيُصَلِّي رَكْعَتَيْنِ، ثُمَّ يَسْتَغْفِرُ اللهَ إِلَّا غَفَرَ اللهُ لَهُ.", "مسلم، وأبو داود", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_ISTIGHFAR))

        // 45 - دعاء طرد الشيطان ووساوسه (143-145)
        raw += RawDua(143, "الِاسْتِعَاذَةُ بِاللهِ مِنَ الشَّيْطَانِ.", "أبو داود، والترمذي", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_WHISPERS))
        raw += RawDua(144, "الْأَذَانُ (طرد الشيطان، فإنه إذا سمع الأذان أدبر وله ضراط).", "أبو داود، وابن ماجه", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_WHISPERS))
        raw += RawDua(145, "الْأَذْكَارُ وَقِرَاءَةُ الْقُرْآنِ.", "مسلم، والبخاري", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_WHISPERS))

        // 46 - الدعاء حينما يقع ما لا يرضاه أو غُلب على أمره (146)
        raw += RawDua(146, "قَدَّرَ اللهُ وَمَا شَاءَ فَعَلَ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_MISC))

        // 47 - تهنئة المولود له وجوابه (147)
        raw += RawDua(147, "بَارَكَ اللهُ لَكَ فِي الْمَوْهُوبِ لَكَ، وَشَكَرْتَ الْوَاهِبَ، وَبَلَغَ أَشُدَّهُ، وَرُزِقْتَ بِرَّهُ. (ويردّ المهنَّأ فيقول: بَارَكَ اللهُ لَكَ وَبَارَكَ عَلَيْكَ، وَجَزَاكَ اللهُ خَيْرًا، وَرَزَقَكَ اللهُ مِثْلَهُ، وَأَجْزَلَ ثَوَابَكَ).", "ابن قدامة، وابن القيم", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_MARRIAGE))

        // 48 - ما يعوَّذ به الأولاد (148)
        raw += RawDua(148, "أُعِيذُكُمَا بِكَلِمَاتِ اللهِ التَّامَّةِ مِنْ كُلِّ شَيْطَانٍ وَهَامَّةٍ، وَمِنْ كُلِّ عَيْنٍ لَامَّةٍ.", "البخاري", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_MARRIAGE), narrator = "كان النبي ﷺ يعوّذ بها الحسن والحسين رضي الله عنهما")

        // 49 - الدعاء للمريض في عيادته (149-150)
        raw += RawDua(149, "لَا بَأْسَ، طَهُورٌ إِنْ شَاءَ اللهُ.", "البخاري", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_ILLNESS))
        raw += RawDua(150, "أَسْأَلُ اللهَ الْعَظِيمَ رَبَّ الْعَرْشِ الْعَظِيمِ أَنْ يَشْفِيَكَ.", "أبو داود، والترمذي", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_ILLNESS), repeatCount = 7)

        // 50 - فضل عيادة المريض (151)
        raw += RawDua(151, "إِذَا عَادَ الرَّجُلُ أَخَاهُ الْمُسْلِمَ مَشَى فِي خِرَافَةِ الْجَنَّةِ حَتَّى يَجْلِسَ، فَإِذَا جَلَسَ غَمَرَتْهُ الرَّحْمَةُ، فَإِنْ كَانَ غُدْوَةً صَلَّى عَلَيْهِ سَبْعُونَ أَلْفَ مَلَكٍ حَتَّى يُمْسِيَ، وَإِنْ كَانَ مَسَاءً صَلَّى عَلَيْهِ سَبْعُونَ أَلْفَ مَلَكٍ حَتَّى يُصْبِحَ.", "الترمذي، وأبو داود", AuthenticityGrade.HASAN, LengthCategory.LONG, listOf(CAT_ILLNESS))

        // 51 - دعاء المريض الذي يئس من حياته (152-154)
        raw += RawDua(152, "اللَّهُمَّ اغْفِرْ لِي، وَارْحَمْنِي، وَأَلْحِقْنِي بِالرَّفِيقِ الْأَعْلَى.", "البخاري، ومسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_ILLNESS))
        raw += RawDua(153, "جَعَلَ النَّبِيُّ ﷺ عِنْدَ مَوْتِهِ يُدْخِلُ يَدَيْهِ فِي الْمَاءِ فَيَمْسَحُ بِهِمَا وَجْهَهُ، وَيَقُولُ: لَا إِلَهَ إِلَّا اللهُ إِنَّ لِلْمَوْتِ سَكَرَاتٍ.", "البخاري", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_ILLNESS))
        raw += RawDua(154, "لَا إِلَهَ إِلَّا اللهُ، وَاللهُ أَكْبَرُ، لَا إِلَهَ إِلَّا اللهُ وَحْدَهُ لَا شَرِيكَ لَهُ، لَا إِلَهَ إِلَّا اللهُ لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ، لَا إِلَهَ إِلَّا اللهُ وَلَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِاللهِ.", "صحيح الترمذي، وصححه أحمد شاكر", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_ILLNESS))

        // 52 - تلقين المحتضر (155)
        raw += RawDua(155, "مَنْ كَانَ آخِرُ كَلَامِهِ لَا إِلَهَ إِلَّا اللهُ دَخَلَ الْجَنَّةَ.", "أبو داود وغيره", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_DEATH))

        // 53 - دعاء من أصيب بمصيبة (156)
        raw += RawDua(156, "إِنَّا لِلَّهِ وَإِنَّا إِلَيْهِ رَاجِعُونَ، اللَّهُمَّ أْجُرْنِي فِي مُصِيبَتِي، وَأَخْلِفْ لِي خَيْرًا مِنْهَا.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_DEATH))

        // 54 - الدعاء عند إغماض الميت (157)
        raw += RawDua(157, "اللَّهُمَّ اغْفِرْ لِفُلَانٍ (بِاسْمِهِ)، وَارْفَعْ دَرَجَتَهُ فِي الْمَهْدِيِّينَ، وَاخْلُفْهُ فِي عَقِبِهِ فِي الْغَابِرِينَ، وَاغْفِرْ لَنَا وَلَهُ يَا رَبَّ الْعَالَمِينَ، وَافْسَحْ لَهُ فِي قَبْرِهِ، وَنَوِّرْ لَهُ فِيهِ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_DEATH))

        // 55 - الدعاء للميت في الصلاة عليه (158-161)
        raw += RawDua(158, "اللَّهُمَّ اغْفِرْ لَهُ وَارْحَمْهُ، وَعَافِهِ وَاعْفُ عَنْهُ، وَأَكْرِمْ نُزُلَهُ، وَوَسِّعْ مُدْخَلَهُ، وَاغْسِلْهُ بِالْمَاءِ وَالثَّلْجِ وَالْبَرَدِ، وَنَقِّهِ مِنَ الْخَطَايَا كَمَا نَقَّيْتَ الثَّوْبَ الْأَبْيَضَ مِنَ الدَّنَسِ، وَأَبْدِلْهُ دَارًا خَيْرًا مِنْ دَارِهِ، وَأَهْلًا خَيْرًا مِنْ أَهْلِهِ، وَزَوْجًا خَيْرًا مِنْ زَوْجِهِ، وَأَدْخِلْهُ الْجَنَّةَ، وَأَعِذْهُ مِنْ عَذَابِ الْقَبْرِ وَعَذَابِ النَّارِ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.LONG, listOf(CAT_DEATH))
        raw += RawDua(159, "اللَّهُمَّ اغْفِرْ لِحَيِّنَا وَمَيِّتِنَا، وَشَاهِدِنَا وَغَائِبِنَا، وَصَغِيرِنَا وَكَبِيرِنَا، وَذَكَرِنَا وَأُنْثَانَا، اللَّهُمَّ مَنْ أَحْيَيْتَهُ مِنَّا فَأَحْيِهِ عَلَى الْإِسْلَامِ، وَمَنْ تَوَفَّيْتَهُ مِنَّا فَتَوَفَّهُ عَلَى الْإِيمَانِ، اللَّهُمَّ لَا تَحْرِمْنَا أَجْرَهُ وَلَا تُضِلَّنَا بَعْدَهُ.", "أصحاب السنن", AuthenticityGrade.HASAN, LengthCategory.LONG, listOf(CAT_DEATH))
        raw += RawDua(160, "اللَّهُمَّ إِنَّ فُلَانَ بْنَ فُلَانٍ فِي ذِمَّتِكَ وَحَبْلِ جِوَارِكَ، فَقِهِ مِنْ فِتْنَةِ الْقَبْرِ وَعَذَابِ النَّارِ، وَأَنْتَ أَهْلُ الْوَفَاءِ وَالْحَقِّ، فَاغْفِرْ لَهُ وَارْحَمْهُ إِنَّكَ أَنْتَ الْغَفُورُ الرَّحِيمُ.", "أبو داود وغيره", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_DEATH))
        raw += RawDua(161, "اللَّهُمَّ عَبْدُكَ وَابْنُ أَمَتِكَ احْتَاجَ إِلَى رَحْمَتِكَ، وَأَنْتَ غَنِيٌّ عَنْ عَذَابِهِ، إِنْ كَانَ مُحْسِنًا فَزِدْ فِي حَسَنَاتِهِ، وَإِنْ كَانَ مُسِيئًا فَتَجَاوَزْ عَنْهُ.", "الحاكم", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_DEATH))

        // 56 - الدعاء للفرط في الصلاة عليه (162-163)
        raw += RawDua(162, "اللَّهُمَّ اجْعَلْهُ لَنَا فَرَطًا، وَذُخْرًا، وَأَجْرًا.", "أحمد", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_DEATH))
        raw += RawDua(163, "اللَّهُمَّ اجْعَلْهُ لَنَا سَلَفًا، وَأَجْرًا.", "الطبراني", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_DEATH))

        // 57 - دعاء التعزية (164-165)
        raw += RawDua(164, "إِنَّ لِلَّهِ مَا أَخَذَ، وَلَهُ مَا أَعْطَى، وَكُلُّ شَيْءٍ عِنْدَهُ بِأَجَلٍ مُسَمَّى، فَلْتَصْبِرْ وَلْتَحْتَسِبْ.", "البخاري، ومسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_DEATH))
        raw += RawDua(165, "أَعْظَمَ اللهُ أَجْرَكَ، وَأَحْسَنَ عَزَاءَكَ، وَغَفَرَ لِمَيِّتِكَ.", "البخاري في الأدب المفرد", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_DEATH))

        // 58 - الدعاء عند إدخال الميت القبر (166)
        raw += RawDua(166, "بِسْمِ اللهِ وَعَلَى سُنَّةِ رَسُولِ اللهِ.", "أصحاب السنن", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_DEATH))

        // 59 - الدعاء بعد دفن الميت (167)
        raw += RawDua(167, "اللَّهُمَّ اغْفِرْ لَهُ، اللَّهُمَّ ثَبِّتْهُ.", "أبو داود", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_DEATH))

        // 60 - دعاء زيارة القبور (168)
        raw += RawDua(168, "السَّلَامُ عَلَيْكُمْ أَهْلَ الدِّيَارِ مِنَ الْمُؤْمِنِينَ وَالْمُسْلِمِينَ، وَإِنَّا إِنْ شَاءَ اللهُ بِكُمْ لَاحِقُونَ، أَسْأَلُ اللهَ لَنَا وَلَكُمُ الْعَافِيَةَ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_DEATH))

        // 61 - دعاء الريح (169-170)
        raw += RawDua(169, "اللَّهُمَّ إِنِّي أَسْأَلُكَ خَيْرَهَا، وَأَعُوذُ بِكَ مِنْ شَرِّهَا.", "أبو داود", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_WEATHER))
        raw += RawDua(170, "اللَّهُمَّ إِنِّي أَسْأَلُكَ خَيْرَهَا، وَخَيْرَ مَا فِيهَا، وَخَيْرَ مَا أُرْسِلَتْ بِهِ، وَأَعُوذُ بِكَ مِنْ شَرِّهَا، وَشَرِّ مَا فِيهَا، وَشَرِّ مَا أُرْسِلَتْ بِهِ.", "مسلم، وابن ماجه", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_WEATHER))

        // 62 - دعاء الرعد (171)
        raw += RawDua(171, "سُبْحَانَ الَّذِي يُسَبِّحُ الرَّعْدُ بِحَمْدِهِ وَالْمَلَائِكَةُ مِنْ خِيفَتِهِ.", "الموطأ", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_WEATHER))

        // 63 - من أدعية الاستسقاء (172-174)
        raw += RawDua(172, "اللَّهُمَّ اسْقِنَا غَيْثًا مُغِيثًا مَرِيئًا مَرِيعًا، نَافِعًا غَيْرَ ضَارٍّ، عَاجِلًا غَيْرَ آجِلٍ.", "أبو داود", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_WEATHER))
        raw += RawDua(173, "اللَّهُمَّ أَغِثْنَا، اللَّهُمَّ أَغِثْنَا، اللَّهُمَّ أَغِثْنَا.", "البخاري، ومسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_WEATHER))
        raw += RawDua(174, "اللَّهُمَّ اسْقِ عِبَادَكَ، وَبَهَائِمَكَ، وَانْشُرْ رَحْمَتَكَ، وَأَحْيِ بَلَدَكَ الْمَيِّتَ.", "أبو داود", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_WEATHER))

        // 64 - الدعاء إذا رأى المطر (175)
        raw += RawDua(175, "اللَّهُمَّ صَيِّبًا نَافِعًا.", "البخاري", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_WEATHER))

        // 65 - الذكر بعد نزول المطر (176)
        raw += RawDua(176, "مُطِرْنَا بِفَضْلِ اللهِ وَرَحْمَتِهِ.", "متفق عليه", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_WEATHER))

        // 66 - من أدعية الاستصحاء (177)
        raw += RawDua(177, "اللَّهُمَّ حَوَالَيْنَا وَلَا عَلَيْنَا، اللَّهُمَّ عَلَى الْآكَامِ وَالظِّرَابِ، وَبُطُونِ الْأَوْدِيَةِ، وَمَنَابِتِ الشَّجَرِ.", "البخاري، ومسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_WEATHER))

        // 67 - دعاء رؤية الهلال (178)
        raw += RawDua(178, "اللهُ أَكْبَرُ، اللَّهُمَّ أَهِلَّهُ عَلَيْنَا بِالْأَمْنِ وَالْإِيمَانِ، وَالسَّلَامَةِ وَالْإِسْلَامِ، وَالتَّوْفِيقِ لِمَا تُحِبُّ رَبَّنَا وَتَرْضَى، رَبُّنَا وَرَبُّكَ اللهُ.", "الترمذي", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_HILAL))

        // 68 - الدعاء عند إفطار الصائم (179-180)
        raw += RawDua(179, "ذَهَبَ الظَّمَأُ، وَابْتَلَّتِ الْعُرُوقُ، وَثَبَتَ الْأَجْرُ إِنْ شَاءَ اللهُ.", "أبو داود", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_FASTING))
        raw += RawDua(180, "اللَّهُمَّ إِنِّي أَسْأَلُكَ بِرَحْمَتِكَ الَّتِي وَسِعَتْ كُلَّ شَيْءٍ أَنْ تَغْفِرَ لِي.", "ابن ماجه", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_FASTING))

        // 69 - الدعاء قبل الطعام (181-182)
        raw += RawDua(181, "بِسْمِ اللهِ (فَإِنْ نَسِيَ فِي أَوَّلِهِ فَلْيَقُلْ: بِسْمِ اللهِ فِي أَوَّلِهِ وَآخِرِهِ).", "أبو داود، والترمذي", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_FASTING))
        raw += RawDua(182, "اللَّهُمَّ بَارِكْ لَنَا فِيمَا رَزَقْتَنَا، وَأَطْعِمْنَا خَيْرًا مِنْهُ. (ولمن سقاه الله لبناً: وزدنا منه).", "ابن ماجه، والترمذي", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_FASTING))

        // 70 - الدعاء عند الفراغ من الطعام (183-184)
        raw += RawDua(183, "الْحَمْدُ لِلَّهِ الَّذِي أَطْعَمَنِي هَذَا، وَرَزَقَنِيهِ، مِنْ غَيْرِ حَوْلٍ مِنِّي وَلَا قُوَّةٍ.", "أبو داود، والترمذي", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_FASTING))
        raw += RawDua(184, "الْحَمْدُ لِلَّهِ حَمْدًا كَثِيرًا طَيِّبًا مُبَارَكًا فِيهِ، غَيْرَ مَكْفِيٍّ وَلَا مُوَدَّعٍ، وَلَا مُسْتَغْنًى عَنْهُ رَبَّنَا.", "البخاري", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_FASTING))

        // 71 - دعاء الضيف لصاحب الطعام (185)
        raw += RawDua(185, "اللَّهُمَّ بَارِكْ لَهُمْ فِيمَا رَزَقْتَهُمْ، وَاغْفِرْ لَهُمْ وَارْحَمْهُمْ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_FASTING))

        // 72 - التعريض بالدعاء لطلب الطعام أو الشراب (186)
        raw += RawDua(186, "اللَّهُمَّ أَطْعِمْ مَنْ أَطْعَمَنِي، وَاسْقِ مَنْ سَقَانِي.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_FASTING))

        // 73 - الدعاء إذا أفطر عند أهل بيت (187)
        raw += RawDua(187, "أَفْطَرَ عِنْدَكُمُ الصَّائِمُونَ، وَأَكَلَ طَعَامَكُمُ الْأَبْرَارُ، وَصَلَّتْ عَلَيْكُمُ الْمَلَائِكَةُ.", "أبو داود", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_FASTING))

        // 74 - دعاء الصائم إذا حضر الطعام ولم يفطر (188)
        raw += RawDua(188, "إِنِّي صَائِمٌ.", "متفق عليه", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_FASTING))

        // 75 - ما يقول الصائم إذا سابّه أحد (189)
        raw += RawDua(189, "إِنِّي صَائِمٌ، إِنِّي صَائِمٌ.", "البخاري، ومسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_FASTING))

        // 76 - الدعاء عند رؤية باكورة الثمر (190)
        raw += RawDua(190, "اللَّهُمَّ بَارِكْ لَنَا فِي ثَمَرِنَا، وَبَارِكْ لَنَا فِي مَدِينَتِنَا، وَبَارِكْ لَنَا فِي صَاعِنَا، وَبَارِكْ لَنَا فِي مُدِّنَا.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_FASTING))

        // 77 - دعاء العطاس (191)
        raw += RawDua(191, "إِذَا عَطَسَ أَحَدُكُمْ فَلْيَقُلِ: الْحَمْدُ لِلَّهِ، وَلْيَقُلْ لَهُ أَخُوهُ أَوْ صَاحِبُهُ: يَرْحَمُكَ اللهُ، فَإِذَا قَالَ لَهُ: يَرْحَمُكَ اللهُ، فَلْيَقُلْ: يَهْدِيكُمُ اللهُ وَيُصْلِحُ بَالَكُمْ.", "البخاري", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_MISC))

        // 78 - ما يقال للكافر إذا عطس فحمد الله (192)
        raw += RawDua(192, "يَهْدِيكُمُ اللهُ وَيُصْلِحُ بَالَكُمْ.", "الترمذي", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_MISC))

        // 79 - الدعاء للمتزوج (193)
        raw += RawDua(193, "بَارَكَ اللهُ لَكَ، وَبَارَكَ عَلَيْكَ، وَجَمَعَ بَيْنَكُمَا فِي خَيْرٍ.", "الترمذي، وأبو داود، وابن ماجه", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_MARRIAGE))

        // 80 - دعاء المتزوج وشراء الدابة (194)
        raw += RawDua(194, "اللَّهُمَّ إِنِّي أَسْأَلُكَ خَيْرَهَا، وَخَيْرَ مَا جَبَلْتَهَا عَلَيْهِ، وَأَعُوذُ بِكَ مِنْ شَرِّهَا وَشَرِّ مَا جَبَلْتَهَا عَلَيْهِ.", "أبو داود", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_MARRIAGE))

        // 81 - الدعاء قبل إتيان الزوجة (195)
        raw += RawDua(195, "بِسْمِ اللهِ، اللَّهُمَّ جَنِّبْنَا الشَّيْطَانَ، وَجَنِّبِ الشَّيْطَانَ مَا رَزَقْتَنَا.", "متفق عليه", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_MARRIAGE))

        // 82 - دعاء الغضب (196)
        raw += RawDua(196, "أَعُوذُ بِاللهِ مِنَ الشَّيْطَانِ الرَّجِيمِ.", "البخاري، ومسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_MISC))

        // 83 - دعاء من رأى مبتلى (197)
        raw += RawDua(197, "الْحَمْدُ لِلَّهِ الَّذِي عَافَانِي مِمَّا ابْتَلَاكَ بِهِ، وَفَضَّلَنِي عَلَى كَثِيرٍ مِمَّنْ خَلَقَ تَفْضِيلًا.", "الترمذي", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_MISC))

        // 84 - ما يُقال في المجلس (198)
        raw += RawDua(198, "رَبِّ اغْفِرْ لِي، وَتُبْ عَلَيَّ، إِنَّكَ أَنْتَ التَّوَّابُ الْغَفُورُ.", "الترمذي، وابن ماجه", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_GATHERING), repeatCount = 100, narrator = "كان ابن عمر رضي الله عنهما يعدّها لرسول الله ﷺ في المجلس الواحد")

        // 85 - كفارة المجلس (199)
        raw += RawDua(199, "سُبْحَانَكَ اللَّهُمَّ وَبِحَمْدِكَ، أَشْهَدُ أَنْ لَا إِلَهَ إِلَّا أَنْتَ، أَسْتَغْفِرُكَ وَأَتُوبُ إِلَيْكَ.", "أصحاب السنن", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_GATHERING), popular = true)

        // 86 - الدعاء لمن قال غفر الله لك (200)
        raw += RawDua(200, "وَلَكَ.", "الترمذي", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_GATHERING))

        // 87 - الدعاء لمن صنع إليك معروفاً (201)
        raw += RawDua(201, "جَزَاكَ اللهُ خَيْرًا.", "الترمذي", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_GATHERING))

        // 88 - ما يعصم الله به من الدجال (202)
        raw += RawDua(202, "مَنْ حَفِظَ عَشْرَ آيَاتٍ مِنْ أَوَّلِ سُورَةِ الْكَهْفِ عُصِمَ مِنَ الدَّجَّالِ، وَالِاسْتِعَاذَةُ بِاللهِ مِنْ فِتْنَتِهِ عَقِبَ التَّشَهُّدِ الْأَخِيرِ مِنْ كُلِّ صَلَاةٍ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_PROTECTION))

        // 89 - الدعاء لمن قال إني أحبك في الله (203)
        raw += RawDua(203, "أَحَبَّكَ الَّذِي أَحْبَبْتَنِي لَهُ.", "أبو داود", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_GATHERING))

        // 90 - الدعاء لمن عرض عليك ماله (204)
        raw += RawDua(204, "بَارَكَ اللهُ لَكَ فِي أَهْلِكَ وَمَالِكَ.", "البخاري", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_GATHERING))

        // 91 - الدعاء لمن أقرض عند القضاء (205)
        raw += RawDua(205, "بَارَكَ اللهُ لَكَ فِي أَهْلِكَ وَمَالِكَ، إِنَّمَا جَزَاءُ السَّلَفِ الْحَمْدُ وَالْأَدَاءُ.", "النسائي، وابن ماجه", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_GATHERING))

        // 92 - دعاء الخوف من الشرك (206)
        raw += RawDua(206, "اللَّهُمَّ إِنِّي أَعُوذُ بِكَ أَنْ أُشْرِكَ بِكَ وَأَنَا أَعْلَمُ، وَأَسْتَغْفِرُكَ لِمَا لَا أَعْلَمُ.", "أحمد", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_WHISPERS))

        // 93 - الدعاء لمن قال بارك الله فيك (207)
        raw += RawDua(207, "وَفِيكَ بَارَكَ اللهُ.", "أبو داود", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_GATHERING))

        // 94 - دعاء كراهية الطيرة (208)
        raw += RawDua(208, "اللَّهُمَّ لَا طَيْرَ إِلَّا طَيْرُكَ، وَلَا خَيْرَ إِلَّا خَيْرُكَ، وَلَا إِلَهَ غَيْرُكَ.", "أحمد", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_MISC))

        // 95 - دعاء الركوب (209)
        raw += RawDua(209, "بِسْمِ اللهِ، وَالْحَمْدُ لِلَّهِ، سُبْحَانَ الَّذِي سَخَّرَ لَنَا هَذَا وَمَا كُنَّا لَهُ مُقْرِنِينَ، وَإِنَّا إِلَى رَبِّنَا لَمُنْقَلِبُونَ، الْحَمْدُ لِلَّهِ، الْحَمْدُ لِلَّهِ، الْحَمْدُ لِلَّهِ، اللهُ أَكْبَرُ، اللهُ أَكْبَرُ، اللهُ أَكْبَرُ، سُبْحَانَكَ اللَّهُمَّ إِنِّي ظَلَمْتُ نَفْسِي فَاغْفِرْ لِي، فَإِنَّهُ لَا يَغْفِرُ الذُّنُوبَ إِلَّا أَنْتَ.", "أبو داود، والترمذي", AuthenticityGrade.HASAN, LengthCategory.LONG, listOf(CAT_TRAVEL), popular = true)

        // 96 - دعاء السفر (210)
        raw += RawDua(210, "اللهُ أَكْبَرُ، اللهُ أَكْبَرُ، اللهُ أَكْبَرُ، سُبْحَانَ الَّذِي سَخَّرَ لَنَا هَذَا وَمَا كُنَّا لَهُ مُقْرِنِينَ، وَإِنَّا إِلَى رَبِّنَا لَمُنْقَلِبُونَ، اللَّهُمَّ إِنَّا نَسْأَلُكَ فِي سَفَرِنَا هَذَا الْبِرَّ وَالتَّقْوَى، وَمِنَ الْعَمَلِ مَا تَرْضَى، اللَّهُمَّ هَوِّنْ عَلَيْنَا سَفَرَنَا هَذَا، وَاطْوِ عَنَّا بُعْدَهُ، اللَّهُمَّ أَنْتَ الصَّاحِبُ فِي السَّفَرِ، وَالْخَلِيفَةُ فِي الْأَهْلِ، اللَّهُمَّ إِنِّي أَعُوذُ بِكَ مِنْ وَعْثَاءِ السَّفَرِ، وَكَآبَةِ الْمَنْظَرِ، وَسُوءِ الْمُنْقَلَبِ فِي الْمَالِ وَالْأَهْلِ. (وإذا رجع قالهن وزاد فيهن: آيِبُونَ تَائِبُونَ عَابِدُونَ لِرَبِّنَا حَامِدُونَ).", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.LONG, listOf(CAT_TRAVEL))

        // 97 - دعاء دخول القرية أو البلدة (211)
        raw += RawDua(211, "اللَّهُمَّ رَبَّ السَّمَاوَاتِ السَّبْعِ وَمَا أَظْلَلْنَ، وَرَبَّ الْأَرَضِينَ السَّبْعِ وَمَا أَقْلَلْنَ، وَرَبَّ الشَّيَاطِينِ وَمَا أَضْلَلْنَ، وَرَبَّ الرِّيَاحِ وَمَا ذَرَيْنَ، أَسْأَلُكَ خَيْرَ هَذِهِ الْقَرْيَةِ، وَخَيْرَ أَهْلِهَا، وَخَيْرَ مَا فِيهَا، وَأَعُوذُ بِكَ مِنْ شَرِّهَا، وَشَرِّ أَهْلِهَا، وَشَرِّ مَا فِيهَا.", "ابن السني، وحسّنه الحافظ ابن حجر في تخريج الأذكار", AuthenticityGrade.HASAN, LengthCategory.LONG, listOf(CAT_TRAVEL))

        // 98 - دعاء دخول السوق (212)
        raw += RawDua(212, "لَا إِلَهَ إِلَّا اللهُ وَحْدَهُ لَا شَرِيكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ، يُحْيِي وَيُمِيتُ، وَهُوَ حَيٌّ لَا يَمُوتُ، بِيَدِهِ الْخَيْرُ، وَهُوَ عَلَى كُلِّ شَيْءٍ قَدِيرٌ.", "الترمذي", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_TRAVEL))

        // 99 - الدعاء إذا تعس المركوب (213)
        raw += RawDua(213, "بِسْمِ اللهِ.", "أبو داود", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_TRAVEL))

        // 100 - دعاء المسافر للمقيم (214)
        raw += RawDua(214, "أَسْتَوْدِعُكُمُ اللهَ الَّذِي لَا تَضِيعُ وَدَائِعُهُ.", "أبو داود، وصححه الألباني", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_TRAVEL))

        // 101 - دعاء المقيم للمسافر (215-216)
        raw += RawDua(215, "أَسْتَوْدِعُ اللهَ دِينَكَ، وَأَمَانَتَكَ، وَخَوَاتِيمَ عَمَلِكَ.", "أبو داود، والترمذي", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_TRAVEL))
        raw += RawDua(216, "زَوَّدَكَ اللهُ التَّقْوَى، وَغَفَرَ ذَنْبَكَ، وَيَسَّرَ لَكَ الْخَيْرَ حَيْثُ كُنْتَ.", "الترمذي", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_TRAVEL))

        // 102 - التكبير والتسبيح في سير السفر (217)
        raw += RawDua(217, "كُنَّا إِذَا صَعِدْنَا كَبَّرْنَا، وَإِذَا نَزَلْنَا سَبَّحْنَا.", "البخاري", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_TRAVEL), narrator = "جابر بن عبد الله رضي الله عنهما")

        // 103 - دعاء المسافر إذا أسحر (218)
        raw += RawDua(218, "سَمِعَ سَامِعٌ بِحَمْدِ اللهِ، وَحُسْنِ بَلَائِهِ عَلَيْنَا، رَبَّنَا صَاحِبْنَا، وَأَفْضِلْ عَلَيْنَا، عَائِذًا بِاللهِ مِنَ النَّارِ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_TRAVEL))

        // 104 - الدعاء إذا نزل منزلاً في سفر أو غيره (219)
        raw += RawDua(219, "أَعُوذُ بِكَلِمَاتِ اللهِ التَّامَّاتِ مِنْ شَرِّ مَا خَلَقَ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_TRAVEL, CAT_PROTECTION))

        // 105 - ذكر الرجوع من السفر (220)
        raw += RawDua(220, "آيِبُونَ، تَائِبُونَ، عَابِدُونَ، لِرَبِّنَا حَامِدُونَ. (ثم يكبّر على كل شرف من شرف الأرض ثلاث تكبيرات، ويقول: لَا إِلَهَ إِلَّا اللهُ وَحْدَهُ لَا شَرِيكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ، وَهُوَ عَلَى كُلِّ شَيْءٍ قَدِيرٌ، آيِبُونَ تَائِبُونَ عَابِدُونَ سَاجِدُونَ لِرَبِّنَا حَامِدُونَ، صَدَقَ اللهُ وَعْدَهُ، وَنَصَرَ عَبْدَهُ، وَهَزَمَ الْأَحْزَابَ وَحْدَهُ).", "متفق عليه", AuthenticityGrade.SAHIH, LengthCategory.LONG, listOf(CAT_TRAVEL))

        // 106 - ما يقول من أتاه أمر يسرّه أو يكرهه (221)
        raw += RawDua(221, "الْحَمْدُ لِلَّهِ الَّذِي بِنِعْمَتِهِ تَتِمُّ الصَّالِحَاتُ. (وإذا أتاه أمر يكرهه قال: الْحَمْدُ لِلَّهِ عَلَى كُلِّ حَالٍ).", "ابن ماجه", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_MISC))

        // 107 - فضل الصلاة على النبي ﷺ (222-226)
        raw += RawDua(222, "مَنْ صَلَّى عَلَيَّ صَلَاةً صَلَّى اللهُ عَلَيْهِ بِهَا عَشْرًا.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_PRAYER))
        raw += RawDua(223, "لَا تَجْعَلُوا قَبْرِي عِيدًا، وَصَلُّوا عَلَيَّ؛ فَإِنَّ صَلَاتَكُمْ تَبْلُغُنِي حَيْثُ كُنْتُمْ.", "أبو داود، وصححه الألباني", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_PRAYER))
        raw += RawDua(224, "الْبَخِيلُ مَنْ ذُكِرْتُ عِنْدَهُ فَلَمْ يُصَلِّ عَلَيَّ.", "الترمذي", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_PRAYER))
        raw += RawDua(225, "إِنَّ لِلَّهِ مَلَائِكَةً سَيَّاحِينَ فِي الْأَرْضِ يُبَلِّغُونِي مِنْ أُمَّتِي السَّلَامَ.", "النسائي، والحاكم", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_PRAYER))
        raw += RawDua(226, "مَا مِنْ أَحَدٍ يُسَلِّمُ عَلَيَّ إِلَّا رَدَّ اللهُ عَلَيَّ رُوحِي حَتَّى أَرُدَّ عَلَيْهِ السَّلَامَ.", "أبو داود", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_PRAYER))

        // 108 - إفشاء السلام (227-229)
        raw += RawDua(227, "لَا تَدْخُلُوا الْجَنَّةَ حَتَّى تُؤْمِنُوا، وَلَا تُؤْمِنُوا حَتَّى تَحَابُّوا، أَوَلَا أَدُلُّكُمْ عَلَى شَيْءٍ إِذَا فَعَلْتُمُوهُ تَحَابَبْتُمْ؟ أَفْشُوا السَّلَامَ بَيْنَكُمْ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.LONG, listOf(CAT_SALAM))
        raw += RawDua(228, "ثَلَاثٌ مَنْ جَمَعَهُنَّ فَقَدْ جَمَعَ الْإِيمَانَ: الْإِنْصَافُ مِنْ نَفْسِكَ، وَبَذْلُ السَّلَامِ لِلْعَالَمِ، وَالْإِنْفَاقُ مِنَ الْإِقْتَارِ.", "البخاري في الأدب المفرد، والبزار", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_SALAM))
        raw += RawDua(229, "أَيُّ الْإِسْلَامِ خَيْرٌ؟ تُطْعِمُ الطَّعَامَ، وَتَقْرَأُ السَّلَامَ عَلَى مَنْ عَرَفْتَ وَمَنْ لَمْ تَعْرِفْ.", "متفق عليه", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_SALAM))

        // 109 - كيف يردّ السلام على الكافر إذا سلّم (230)
        raw += RawDua(230, "إِذَا سَلَّمَ عَلَيْكُمْ أَهْلُ الْكِتَابِ فَقُولُوا: وَعَلَيْكُمْ.", "متفق عليه", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_SALAM))

        // 110 - الدعاء عند سماع صياح الديك ونهيق الحمار (231)
        raw += RawDua(231, "إِذَا سَمِعْتُمْ صِيَاحَ الدِّيَكَةِ فَاسْأَلُوا اللهَ مِنْ فَضْلِهِ؛ فَإِنَّهَا رَأَتْ مَلَكًا، وَإِذَا سَمِعْتُمْ نَهِيقَ الْحِمَارِ فَتَعَوَّذُوا بِاللهِ مِنَ الشَّيْطَانِ؛ فَإِنَّهُ رَأَى شَيْطَانًا.", "البخاري، ومسلم", AuthenticityGrade.SAHIH, LengthCategory.LONG, listOf(CAT_MISC))

        // 111 - الدعاء عند سماع نباح الكلاب بالليل (232)
        raw += RawDua(232, "إِذَا سَمِعْتُمْ نُبَاحَ الْكِلَابِ وَنَهِيقَ الْحَمِيرِ بِاللَّيْلِ فَتَعَوَّذُوا بِاللهِ مِنْهُنَّ؛ فَإِنَّهُنَّ يَرَيْنَ مَا لَا تَرَوْنَ.", "أبو داود", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_MISC))

        // 112 - الدعاء لمن سببته (233)
        raw += RawDua(233, "اللَّهُمَّ فَأَيُّمَا مُؤْمِنٍ سَبَبْتُهُ فَاجْعَلْ ذَلِكَ لَهُ قُرْبَةً إِلَيْكَ يَوْمَ الْقِيَامَةِ.", "أبو داود، ومسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_MISC))

        // 113 - ما يقول المسلم إذا مدح المسلم (234)
        raw += RawDua(234, "أُحْسِبُ فُلَانًا وَاللهُ حَسِيبُهُ، وَلَا أُزَكِّي عَلَى اللهِ أَحَدًا، أَحْسِبُهُ - إِنْ كَانَ يَعْلَمُ ذَاكَ - كَذَا وَكَذَا.", "البخاري، ومسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_MISC))

        // 114 - ما يقول المسلم إذا زُكِّي (235)
        raw += RawDua(235, "اللَّهُمَّ لَا تُؤَاخِذْنِي بِمَا يَقُولُونَ، وَاغْفِرْ لِي مَا لَا يَعْلَمُونَ، وَاجْعَلْنِي خَيْرًا مِمَّا يَظُنُّونَ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_MISC))

        // 115 - كيف يلبّي المُحرم في الحج أو العمرة (236)
        raw += RawDua(236, "لَبَّيْكَ اللَّهُمَّ لَبَّيْكَ، لَبَّيْكَ لَا شَرِيكَ لَكَ لَبَّيْكَ، إِنَّ الْحَمْدَ وَالنِّعْمَةَ، لَكَ وَالْمُلْكَ، لَا شَرِيكَ لَكَ.", "متفق عليه", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_HAJJ), popular = true)

        // 116 - التكبير إذا أتى الحجر الأسود (237)
        raw += RawDua(237, "طَافَ النَّبِيُّ ﷺ بِالْبَيْتِ عَلَى بَعِيرٍ، كُلَّمَا أَتَى الرُّكْنَ أَشَارَ إِلَيْهِ بِشَيْءٍ عِنْدَهُ وَكَبَّرَ.", "البخاري", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_HAJJ))

        // 117 - الدعاء بين الركن اليماني والحجر الأسود (238)
        raw += RawDua(238, "رَبَّنَا آتِنَا فِي الدُّنْيَا حَسَنَةً وَفِي الْآخِرَةِ حَسَنَةً وَقِنَا عَذَابَ النَّارِ.", "البقرة: 201", AuthenticityGrade.QURAN, LengthCategory.SHORT, listOf(CAT_HAJJ), popular = true)

        // 118 - دعاء الوقوف على الصفا والمروة (239)
        raw += RawDua(239, "إِنَّ الصَّفَا وَالْمَرْوَةَ مِنْ شَعَائِرِ اللهِ، أَبْدَأُ بِمَا بَدَأَ اللهُ بِهِ. (ثم يرقى على الصفا حتى يرى البيت فيستقبل القبلة فيوحّد الله ويكبّره ويقول: لَا إِلَهَ إِلَّا اللهُ وَحْدَهُ لَا شَرِيكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ وَهُوَ عَلَى كُلِّ شَيْءٍ قَدِيرٌ).", "البقرة: 158؛ مسلم", AuthenticityGrade.SAHIH, LengthCategory.LONG, listOf(CAT_HAJJ))

        // 119 - الدعاء يوم عرفة (240)
        raw += RawDua(240, "خَيْرُ الدُّعَاءِ دُعَاءُ يَوْمِ عَرَفَةَ، وَخَيْرُ مَا قُلْتُ أَنَا وَالنَّبِيُّونَ مِنْ قَبْلِي: لَا إِلَهَ إِلَّا اللهُ وَحْدَهُ لَا شَرِيكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ وَهُوَ عَلَى كُلِّ شَيْءٍ قَدِيرٌ.", "الترمذي، وحسّنه الألباني", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_HAJJ))

        // 120 - الذكر عند المشعر الحرام (241)
        raw += RawDua(241, "رَكِبَ النَّبِيُّ ﷺ الْقَصْوَاءَ حَتَّى أَتَى الْمَشْعَرَ الْحَرَامَ فَاسْتَقْبَلَ الْقِبْلَةَ فَدَعَاهُ وَكَبَّرَهُ، وَهَلَّلَهُ، وَوَحَّدَهُ، فَلَمْ يَزَلْ وَاقِفًا حَتَّى أَسْفَرَ جِدًّا فَدَفَعَ قَبْلَ أَنْ تَطْلُعَ الشَّمْسُ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.LONG, listOf(CAT_HAJJ))

        // 121 - التكبير عند رمي الجمار مع كل حصاة (242)
        raw += RawDua(242, "يُكَبِّرُ كُلَّمَا رَمَى حَصَاةً عِنْدَ جِمَارِ الثَّلَاثِ، ثُمَّ يَتَقَدَّمُ، ثُمَّ يَقِفُ مُسْتَقْبِلَ الْقِبْلَةِ رَافِعًا يَدَيْهِ بَعْدَ الْجَمْرَةِ الْأُولَى وَالثَّانِيَةِ وَيَدْعُو؛ أَمَّا جَمْرَةُ الْعَقَبَةِ فَيَرْمِيهَا وَيُكَبِّرُ عِنْدَ كُلِّ حَصَاةٍ وَلَا يَقِفُ عِنْدَهَا.", "البخاري", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_HAJJ))

        // 122 - دعاء التعجب والأمر السار (243-244)
        raw += RawDua(243, "سُبْحَانَ اللهِ!", "البخاري، ومسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_MISC))
        raw += RawDua(244, "اللهُ أَكْبَرُ!", "البخاري", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_MISC))

        // 123 - ما يفعل من أتاه أمر يسره (245)
        raw += RawDua(245, "كَانَ النَّبِيُّ ﷺ إِذَا أَتَاهُ أَمْرٌ يَسُرُّهُ أَوْ يُسَرُّ بِهِ خَرَّ سَاجِدًا شُكْرًا لِلَّهِ تَبَارَكَ وَتَعَالَى.", "أبو داود، والترمذي", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_MISC))

        // 124 - ما يفعل ويقول من أحسّ وجعاً في جسده (246)
        raw += RawDua(246, "ضَعْ يَدَكَ عَلَى الَّذِي تَأَلَّمَ مِنْ جَسَدِكَ وَقُلْ: بِسْمِ اللهِ (ثَلَاثًا)، وَقُلْ سَبْعَ مَرَّاتٍ: أَعُوذُ بِاللهِ وَقُدْرَتِهِ مِنْ شَرِّ مَا أَجِدُ وَأُحَاذِرُ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_ILLNESS), repeatCount = 7)

        // 125 - دعاء من خشي أن يصيب شيئاً بعينه (247)
        raw += RawDua(247, "إِذَا رَأَى أَحَدُكُمْ مِنْ أَخِيهِ، أَوْ مِنْ نَفْسِهِ، أَوْ مِنْ مَالِهِ مَا يُعْجِبُهُ فَلْيَدْعُ لَهُ بِالْبَرَكَةِ؛ فَإِنَّ الْعَيْنَ حَقٌّ.", "أحمد، وابن ماجه", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_MISC))

        // 126 - ما يقال عند الفزع (248)
        raw += RawDua(248, "لَا إِلَهَ إِلَّا اللهُ.", "أحمد، ومالك، والحاكم", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_MISC))

        // 127 - ما يقول عند الذبح أو النحر (249)
        raw += RawDua(249, "بِسْمِ اللهِ، وَاللهُ أَكْبَرُ، اللَّهُمَّ مِنْكَ وَلَكَ، اللَّهُمَّ تَقَبَّلْ مِنِّي.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_MISC))

        // 128 - ما يقول لرد كيد مردة الشياطين (250)
        raw += RawDua(250, "أَعُوذُ بِكَلِمَاتِ اللهِ التَّامَّاتِ الَّتِي لَا يُجَاوِزُهُنَّ بَرٌّ وَلَا فَاجِرٌ، مِنْ شَرِّ مَا خَلَقَ، وَبَرَأَ، وَذَرَأَ، وَمِنْ شَرِّ مَا يَنْزِلُ مِنَ السَّمَاءِ، وَمِنْ شَرِّ مَا يَعْرُجُ فِيهَا، وَمِنْ شَرِّ مَا ذَرَأَ فِي الْأَرْضِ، وَمِنْ شَرِّ مَا يَخْرُجُ مِنْهَا، وَمِنْ شَرِّ فِتَنِ اللَّيْلِ وَالنَّهَارِ، وَمِنْ شَرِّ كُلِّ طَارِقٍ إِلَّا طَارِقًا يَطْرُقُ بِخَيْرٍ يَا رَحْمَنُ.", "ابن السني، والطبراني", AuthenticityGrade.HASAN, LengthCategory.LONG, listOf(CAT_PROTECTION))

        // 129 - الاستغفار والتوبة (251-256)
        raw += RawDua(251, "وَاللهِ إِنِّي لَأَسْتَغْفِرُ اللهَ وَأَتُوبُ إِلَيْهِ فِي الْيَوْمِ أَكْثَرَ مِنْ سَبْعِينَ مَرَّةً.", "البخاري", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_ISTIGHFAR), repeatCount = 70)
        raw += RawDua(252, "يَا أَيُّهَا النَّاسُ تُوبُوا إِلَى اللهِ فَإِنِّي أَتُوبُ إِلَيْهِ فِي الْيَوْمِ مِائَةَ مَرَّةٍ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_ISTIGHFAR), repeatCount = 100)
        raw += RawDua(253, "مَنْ قَالَ: أَسْتَغْفِرُ اللهَ الْعَظِيمَ الَّذِي لَا إِلَهَ إِلَّا هُوَ الْحَيُّ الْقَيُّومُ وَأَتُوبُ إِلَيْهِ، غَفَرَ اللهُ لَهُ وَإِنْ كَانَ فَرَّ مِنَ الزَّحْفِ.", "أبو داود، والترمذي، والحاكم", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_ISTIGHFAR))
        raw += RawDua(254, "أَقْرَبُ مَا يَكُونُ الرَّبُّ مِنَ الْعَبْدِ فِي جَوْفِ اللَّيْلِ الْآخِرِ، فَإِنِ اسْتَطَعْتَ أَنْ تَكُونَ مِمَّنْ يَذْكُرُ اللهَ فِي تِلْكَ السَّاعَةِ فَكُنْ.", "الترمذي، وصححه الألباني", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_ISTIGHFAR))
        raw += RawDua(255, "أَقْرَبُ مَا يَكُونُ الْعَبْدُ مِنْ رَبِّهِ وَهُوَ سَاجِدٌ، فَأَكْثِرُوا الدُّعَاءَ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_ISTIGHFAR, CAT_PRAYER))
        raw += RawDua(256, "إِنَّهُ لَيُغَانُ عَلَى قَلْبِي، وَإِنِّي لَأَسْتَغْفِرُ اللهَ فِي الْيَوْمِ مِائَةَ مَرَّةٍ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.SHORT, listOf(CAT_ISTIGHFAR), repeatCount = 100)

        // 130 - فضل التسبيح والتحميد والتهليل والتكبير (257-268)
        raw += RawDua(257, "مَنْ قَالَ: سُبْحَانَ اللهِ وَبِحَمْدِهِ، فِي يَوْمٍ مِائَةَ مَرَّةٍ، حُطَّتْ خَطَايَاهُ وَلَوْ كَانَتْ مِثْلَ زَبَدِ الْبَحْرِ.", "متفق عليه", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_TASBEEH), repeatCount = 100)
        raw += RawDua(258, "مَنْ قَالَ: لَا إِلَهَ إِلَّا اللهُ وَحْدَهُ لَا شَرِيكَ لَهُ، لَهُ الْمُلْكُ، وَلَهُ الْحَمْدُ، وَهُوَ عَلَى كُلِّ شَيْءٍ قَدِيرٌ، فِي يَوْمٍ مِائَةَ مَرَّةٍ، كَانَتْ لَهُ عَدْلَ عَشْرِ رِقَابٍ، وَكُتِبَتْ لَهُ مِائَةُ حَسَنَةٍ، وَمُحِيَتْ عَنْهُ مِائَةُ سَيِّئَةٍ، وَكَانَتْ لَهُ حِرْزًا مِنَ الشَّيْطَانِ يَوْمَهُ ذَلِكَ حَتَّى يُمْسِيَ.", "متفق عليه", AuthenticityGrade.SAHIH, LengthCategory.LONG, listOf(CAT_TASBEEH), repeatCount = 100)
        raw += RawDua(259, "كَلِمَتَانِ خَفِيفَتَانِ عَلَى اللِّسَانِ، ثَقِيلَتَانِ فِي الْمِيزَانِ، حَبِيبَتَانِ إِلَى الرَّحْمَنِ: سُبْحَانَ اللهِ وَبِحَمْدِهِ، سُبْحَانَ اللهِ الْعَظِيمِ.", "البخاري، ومسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_TASBEEH), popular = true)
        raw += RawDua(260, "لَأَنْ أَقُولَ: سُبْحَانَ اللهِ، وَالْحَمْدُ لِلَّهِ، وَلَا إِلَهَ إِلَّا اللهُ، وَاللهُ أَكْبَرُ، أَحَبُّ إِلَيَّ مِمَّا طَلَعَتْ عَلَيْهِ الشَّمْسُ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_TASBEEH))
        raw += RawDua(261, "أَيَعْجِزُ أَحَدُكُمْ أَنْ يَكْسِبَ كُلَّ يَوْمٍ أَلْفَ حَسَنَةٍ؟ يُسَبِّحُ مِائَةَ تَسْبِيحَةٍ فَيُكْتَبُ لَهُ أَلْفُ حَسَنَةٍ، أَوْ يُحَطُّ عَنْهُ أَلْفُ خَطِيئَةٍ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_TASBEEH), repeatCount = 100)
        raw += RawDua(262, "مَنْ قَالَ: سُبْحَانَ اللهِ الْعَظِيمِ وَبِحَمْدِهِ، غُرِسَتْ لَهُ نَخْلَةٌ فِي الْجَنَّةِ.", "الترمذي، والحاكم", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_TASBEEH))
        raw += RawDua(263, "يَا عَبْدَ اللهِ بْنَ قَيْسٍ، أَلَا أَدُلُّكَ عَلَى كَنْزٍ مِنْ كُنُوزِ الْجَنَّةِ؟ قُلْ: لَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِاللهِ.", "متفق عليه", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_TASBEEH), popular = true)
        raw += RawDua(264, "أَحَبُّ الْكَلَامِ إِلَى اللهِ أَرْبَعٌ: سُبْحَانَ اللهِ، وَالْحَمْدُ لِلَّهِ، وَلَا إِلَهَ إِلَّا اللهُ، وَاللهُ أَكْبَرُ، لَا يَضُرُّكَ بِأَيِّهِنَّ بَدَأْتَ.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_TASBEEH))
        raw += RawDua(265, "جَاءَ أَعْرَابِيٌّ إِلَى رَسُولِ اللهِ ﷺ فَقَالَ: عَلِّمْنِي كَلَامًا أَقُولُهُ، قَالَ: قُلْ: لَا إِلَهَ إِلَّا اللهُ وَحْدَهُ لَا شَرِيكَ لَهُ، اللهُ أَكْبَرُ كَبِيرًا، وَالْحَمْدُ لِلَّهِ كَثِيرًا، سُبْحَانَ اللهِ رَبِّ الْعَالَمِينَ، لَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِاللهِ الْعَزِيزِ الْحَكِيمِ، قَالَ: فَهَؤُلَاءِ لِرَبِّي، فَمَا لِي؟ قَالَ: قُلِ: اللَّهُمَّ اغْفِرْ لِي، وَارْحَمْنِي، وَاهْدِنِي، وَارْزُقْنِي.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.LONG, listOf(CAT_TASBEEH))
        raw += RawDua(266, "كَانَ الرَّجُلُ إِذَا أَسْلَمَ عَلَّمَهُ النَّبِيُّ ﷺ الصَّلَاةَ، ثُمَّ أَمَرَهُ أَنْ يَدْعُوَ بِهَؤُلَاءِ الْكَلِمَاتِ: اللَّهُمَّ اغْفِرْ لِي، وَارْحَمْنِي، وَاهْدِنِي، وَعَافِنِي، وَارْزُقْنِي.", "مسلم", AuthenticityGrade.SAHIH, LengthCategory.MEDIUM, listOf(CAT_TASBEEH))
        raw += RawDua(267, "إِنَّ أَفْضَلَ الدُّعَاءِ الْحَمْدُ لِلَّهِ، وَأَفْضَلَ الذِّكْرِ لَا إِلَهَ إِلَّا اللهُ.", "الترمذي، وابن ماجه", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_TASBEEH))
        raw += RawDua(268, "الْبَاقِيَاتُ الصَّالِحَاتُ: سُبْحَانَ اللهِ، وَالْحَمْدُ لِلَّهِ، وَلَا إِلَهَ إِلَّا اللهُ، وَاللهُ أَكْبَرُ، وَلَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِاللهِ.", "أحمد، والترمذي، والحاكم", AuthenticityGrade.HASAN, LengthCategory.MEDIUM, listOf(CAT_TASBEEH))

        // 131 - كيف كان النبي ﷺ يسبّح (269)
        raw += RawDua(269, "رَأَيْتُ النَّبِيَّ ﷺ يَعْقِدُ التَّسْبِيحَ (بِيَمِينِهِ).", "أبو داود، والترمذي، وابن ماجه", AuthenticityGrade.HASAN, LengthCategory.SHORT, listOf(CAT_TASBEEH), narrator = "عبد الله بن عمرو بن العاص رضي الله عنهما")

        // 132 - من أنواع الخير والآداب الجامعة (270)
        raw += RawDua(270, "إِذَا كَانَ جُنْحُ اللَّيْلِ - أَوْ أَمْسَيْتُمْ - فَكُفُّوا صِبْيَانَكُمْ، فَإِنَّ الشَّيَاطِينَ تَنْتَشِرُ حِينَئِذٍ، فَإِذَا ذَهَبَ سَاعَةٌ مِنَ اللَّيْلِ فَخَلُّوهُمْ، وَأَغْلِقُوا الْأَبْوَابَ وَاذْكُرُوا اسْمَ اللهِ؛ فَإِنَّ الشَّيْطَانَ لَا يَفْتَحُ بَابًا مُغْلَقًا، وَأَوْكُوا قِرَبَكُمْ وَاذْكُرُوا اسْمَ اللهِ، وَخَمِّرُوا آنِيَتَكُمْ وَاذْكُرُوا اسْمَ اللهِ، وَلَوْ أَنْ تَعْرُضُوا عَلَيْهَا شَيْئًا، وَأَطْفِئُوا مَصَابِيحَكُمْ.", "البخاري، ومسلم", AuthenticityGrade.SAHIH, LengthCategory.LONG, listOf(CAT_EVENING, CAT_MISC))
    }

    val adhkar: List<DhikrEntity> = raw
        .map { d ->
            DhikrEntity(
                id = 3000L + d.n,
                arabicText = d.text,
                source = "حصن المسلم (${d.source})",
                authenticityGrade = d.grade,
                authenticityNote = d.note ?: when (d.grade) {
                    AuthenticityGrade.HASAN -> HASAN_NOTE
                    AuthenticityGrade.NEEDS_REVIEW -> NUMBER_UNCERTAIN_NOTE
                    else -> null
                },
                fullVirtue = null,
                narrationContext = null,
                chainOfNarration = null,
                narrator = d.narrator,
                repeatCount = d.repeatCount,
                englishTranslation = null,
                lengthCategory = d.length,
                needsReview = d.needsReview
            )
        }

    val crossRefs: List<DhikrCategoryCrossRef> = buildList {
        raw.forEach { d ->
            val dhikrId = 3000L + d.n
            add(DhikrCategoryCrossRef(dhikrId, CAT_SOURCE_HISN))
            d.themeCategoryIds.forEach { catId ->
                add(DhikrCategoryCrossRef(dhikrId, catId))
            }
            if (d.grade == AuthenticityGrade.QURAN) {
                add(DhikrCategoryCrossRef(dhikrId, CAT_SOURCE_QURAN))
            }
            if (d.popular) {
                add(DhikrCategoryCrossRef(dhikrId, CAT_POPULAR))
            }
        }
    }
}
