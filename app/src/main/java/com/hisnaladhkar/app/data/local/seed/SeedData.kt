package com.hisnaladhkar.app.data.local.seed

import com.hisnaladhkar.app.data.local.entity.CategoryEntity
import com.hisnaladhkar.app.data.local.entity.DhikrCategoryCrossRef
import com.hisnaladhkar.app.data.local.entity.DhikrEntity
import com.hisnaladhkar.app.domain.model.AuthenticityGrade
import com.hisnaladhkar.app.domain.model.CategoryDimension
import com.hisnaladhkar.app.domain.model.LengthCategory

/**
 * دفعة أولى محدودة ومتحفَّظ فيها عمداً: 8 أذكار من "أذكار الصباح" فقط، اختيرت لأنها
 * من أثبت وأشهر النصوص (آيات قرآنية، وأحاديث متفق على صحتها ومتطابقة الصياغة في
 * جميع طبعات حصن المسلم تقريباً)، تجنباً لأي خطر في الاستظهار الحرفي لنصوص دينية.
 *
 * تنبيه مهم لصاحب المشروع: حقل `source` هنا يذكر الكتاب الحديثي الأصلي (كالبخاري
 * أو أبي داود) ولا يذكر رقم عنصر محدد من طبعة "حصن المسلم"، لأن ترقيم العناصر يختلف
 * بين الطبعات ولا أستطيع تأكيده من الذاكرة بثقة كافية. يُرجى مطابقة هذه النصوص مع
 * نسختك الخاصة من حصن المسلم (أو مع مختص شرعي) قبل اعتماد الترقيم النهائي أو النشر،
 * كما طلبت في قيود المشروع.
 */
object SeedData {

    // IDs التصنيفات ثابتة يدوياً لتسهيل ربط جدول العلاقة Many-to-Many أدناه.
    private const val CAT_MORNING = 1L               // أذكار الصباح (TIME_OCCASION)
    private const val CAT_PROTECTION = 2L             // الحماية والرقية الشرعية (THEME)
    private const val CAT_ISTIGHFAR = 3L               // الاستغفار والتوبة (THEME)
    private const val CAT_PRAISE = 4L                  // الثناء على الله وأسمائه الحسنى (THEME)
    private const val CAT_DISTRESS = 5L                // الكرب والهم والحزن (THEME)
    private const val CAT_POPULAR = 6L                 // الأكثر شهرة وتداولاً (POPULAR)
    private const val CAT_SOURCE_QURAN = 7L            // القرآن الكريم (SOURCE)
    private const val CAT_SOURCE_HISN = 8L             // حصن المسلم (SOURCE)

    val categories = listOf(
        CategoryEntity(CAT_MORNING, "أذكار الصباح", CategoryDimension.TIME_OCCASION, 1),
        CategoryEntity(CAT_PROTECTION, "الحماية والرقية الشرعية", CategoryDimension.THEME, 1),
        CategoryEntity(CAT_ISTIGHFAR, "الاستغفار والتوبة", CategoryDimension.THEME, 2),
        CategoryEntity(CAT_PRAISE, "الثناء على الله وأسمائه الحسنى", CategoryDimension.THEME, 3),
        CategoryEntity(CAT_DISTRESS, "الكرب والهم والحزن", CategoryDimension.THEME, 4),
        CategoryEntity(CAT_POPULAR, "الأكثر شهرة وتداولاً", CategoryDimension.POPULAR, 1),
        CategoryEntity(CAT_SOURCE_QURAN, "القرآن الكريم", CategoryDimension.SOURCE, 1),
        CategoryEntity(CAT_SOURCE_HISN, "حصن المسلم", CategoryDimension.SOURCE, 2)
    )

    private const val AYAT_AL_KURSI = 1L
    private const val AL_IKHLAS = 2L
    private const val AL_FALAQ = 3L
    private const val AN_NAS = 4L
    private const val SAYYID_AL_ISTIGHFAR = 5L
    private const val HASBUNA_ALLAH = 6L
    private const val RADHEETU_BILLAH = 7L
    private const val BISMILLAH_LA_YADHURRU = 8L

    private const val THREE_SURAHS_VIRTUE =
        "من قالها حين يصبح وحين يمسي: \"قل هو الله أحد\" مع المعوذتين (الفلق والناس) ثلاث مرات، كفته من كل شيء."

    val adhkar = listOf(
        DhikrEntity(
            id = AYAT_AL_KURSI,
            arabicText = "اللَّهُ لَا إِلَٰهَ إِلَّا هُوَ الْحَيُّ الْقَيُّومُ لَا تَأْخُذُهُ سِنَةٌ وَلَا نَوْمٌ لَهُ مَا فِي السَّمَاوَاتِ وَمَا فِي الْأَرْضِ مَنْ ذَا الَّذِي يَشْفَعُ عِنْدَهُ إِلَّا بِإِذْنِهِ يَعْلَمُ مَا بَيْنَ أَيْدِيهِمْ وَمَا خَلْفَهُمْ وَلَا يُحِيطُونَ بِشَيْءٍ مِّنْ عِلْمِهِ إِلَّا بِمَا شَاءَ وَسِعَ كُرْسِيُّهُ السَّمَاوَاتِ وَالْأَرْضَ وَلَا يَئُودُهُ حِفْظُهُمَا وَهُوَ الْعَلِيُّ الْعَظِيمُ",
            source = "القرآن الكريم - سورة البقرة، الآية 255",
            authenticityGrade = AuthenticityGrade.QURAN,
            authenticityNote = null,
            fullVirtue = null,
            narrationContext = "لم يرد سبب ورود موثق",
            chainOfNarration = null,
            narrator = null,
            repeatCount = 1,
            englishTranslation = null,
            lengthCategory = LengthCategory.LONG,
            needsReview = false
        ),
        DhikrEntity(
            id = AL_IKHLAS,
            arabicText = "قُلْ هُوَ اللَّهُ أَحَدٌ، اللَّهُ الصَّمَدُ، لَمْ يَلِدْ وَلَمْ يُولَدْ، وَلَمْ يَكُن لَّهُ كُفُوًا أَحَدٌ",
            source = "القرآن الكريم - سورة الإخلاص",
            authenticityGrade = AuthenticityGrade.QURAN,
            authenticityNote = "فضلها المذكور مرتبط بحديث يجمعها مع المعوذتين (انظر الحقل)، رواه أبو داود والترمذي؛ يُرجى مطابقة رقم الحديث في طبعتك.",
            fullVirtue = THREE_SURAHS_VIRTUE,
            narrationContext = "لم يرد سبب ورود موثق",
            chainOfNarration = null,
            narrator = null,
            repeatCount = 3,
            englishTranslation = null,
            lengthCategory = LengthCategory.SHORT,
            needsReview = false
        ),
        DhikrEntity(
            id = AL_FALAQ,
            arabicText = "قُلْ أَعُوذُ بِرَبِّ الْفَلَقِ، مِن شَرِّ مَا خَلَقَ، وَمِن شَرِّ غَاسِقٍ إِذَا وَقَبَ، وَمِن شَرِّ النَّفَّاثَاتِ فِي الْعُقَدِ، وَمِن شَرِّ حَاسِدٍ إِذَا حَسَدَ",
            source = "القرآن الكريم - سورة الفلق",
            authenticityGrade = AuthenticityGrade.QURAN,
            authenticityNote = "فضلها المذكور مرتبط بحديث يجمعها مع الإخلاص والناس (انظر الحقل)، رواه أبو داود والترمذي؛ يُرجى مطابقة رقم الحديث في طبعتك.",
            fullVirtue = THREE_SURAHS_VIRTUE,
            narrationContext = "لم يرد سبب ورود موثق",
            chainOfNarration = null,
            narrator = null,
            repeatCount = 3,
            englishTranslation = null,
            lengthCategory = LengthCategory.SHORT,
            needsReview = false
        ),
        DhikrEntity(
            id = AN_NAS,
            arabicText = "قُلْ أَعُوذُ بِرَبِّ النَّاسِ، مَلِكِ النَّاسِ، إِلَٰهِ النَّاسِ، مِن شَرِّ الْوَسْوَاسِ الْخَنَّاسِ، الَّذِي يُوَسْوِسُ فِي صُدُورِ النَّاسِ، مِنَ الْجِنَّةِ وَالنَّاسِ",
            source = "القرآن الكريم - سورة الناس",
            authenticityGrade = AuthenticityGrade.QURAN,
            authenticityNote = "فضلها المذكور مرتبط بحديث يجمعها مع الإخلاص والفلق (انظر الحقل)، رواه أبو داود والترمذي؛ يُرجى مطابقة رقم الحديث في طبعتك.",
            fullVirtue = THREE_SURAHS_VIRTUE,
            narrationContext = "لم يرد سبب ورود موثق",
            chainOfNarration = null,
            narrator = null,
            repeatCount = 3,
            englishTranslation = null,
            lengthCategory = LengthCategory.SHORT,
            needsReview = false
        ),
        DhikrEntity(
            id = SAYYID_AL_ISTIGHFAR,
            arabicText = "اللَّهُمَّ أَنْتَ رَبِّي لَا إِلَٰهَ إِلَّا أَنْتَ، خَلَقْتَنِي وَأَنَا عَبْدُكَ، وَأَنَا عَلَى عَهْدِكَ وَوَعْدِكَ مَا اسْتَطَعْتُ، أَعُوذُ بِكَ مِنْ شَرِّ مَا صَنَعْتُ، أَبُوءُ لَكَ بِنِعْمَتِكَ عَلَيَّ، وَأَبُوءُ بِذَنْبِي، فَاغْفِرْ لِي، فَإِنَّهُ لَا يَغْفِرُ الذُّنُوبَ إِلَّا أَنْتَ",
            source = "صحيح البخاري",
            authenticityGrade = AuthenticityGrade.SAHIH,
            authenticityNote = "يُعرف بـ«سيد الاستغفار»؛ يُرجى مطابقة رقم الحديث في طبعتك من حصن المسلم.",
            fullVirtue = "من قالها حين يصبح موقناً بها فمات من يومه قبل أن يمسي فهو من أهل الجنة، ومن قالها حين يمسي موقناً بها فمات قبل أن يصبح فهو من أهل الجنة.",
            narrationContext = "لم يرد سبب ورود موثق",
            chainOfNarration = null,
            narrator = "شداد بن أوس رضي الله عنه",
            repeatCount = 1,
            englishTranslation = null,
            lengthCategory = LengthCategory.MEDIUM,
            needsReview = false
        ),
        DhikrEntity(
            id = HASBUNA_ALLAH,
            arabicText = "حَسْبِيَ اللَّهُ لَا إِلٰهَ إِلَّا هُوَ عَلَيْهِ تَوَكَّلْتُ وَهُوَ رَبُّ الْعَرْشِ الْعَظِيمِ",
            source = "حصن المسلم (أصله في سنن أبي داود)",
            authenticityGrade = AuthenticityGrade.SAHIH,
            authenticityNote = "يُرجى مطابقة رقم الحديث الدقيق في طبعتك من حصن المسلم.",
            fullVirtue = "من قالها سبع مرات حين يصبح وحين يمسي كفاه الله ما أهمّه.",
            narrationContext = "لم يرد سبب ورود موثق",
            chainOfNarration = null,
            narrator = null,
            repeatCount = 7,
            englishTranslation = null,
            lengthCategory = LengthCategory.SHORT,
            needsReview = false
        ),
        DhikrEntity(
            id = RADHEETU_BILLAH,
            arabicText = "رَضِيتُ بِاللَّهِ رَبًّا، وَبِالْإِسْلَامِ دِينًا، وَبِمُحَمَّدٍ صَلَّى اللَّهُ عَلَيْهِ وَسَلَّمَ نَبِيًّا",
            source = "حصن المسلم (أصله في سنن أبي داود والترمذي)",
            authenticityGrade = AuthenticityGrade.SAHIH,
            authenticityNote = "يُرجى مطابقة رقم الحديث الدقيق في طبعتك من حصن المسلم.",
            fullVirtue = "من قالها ثلاث مرات حين يصبح وحين يمسي كان حقاً على الله أن يُرضيه يوم القيامة.",
            narrationContext = "لم يرد سبب ورود موثق",
            chainOfNarration = null,
            narrator = null,
            repeatCount = 3,
            englishTranslation = null,
            lengthCategory = LengthCategory.SHORT,
            needsReview = false
        ),
        DhikrEntity(
            id = BISMILLAH_LA_YADHURRU,
            arabicText = "بِسْمِ اللَّهِ الَّذِي لَا يَضُرُّ مَعَ اسْمِهِ شَيْءٌ فِي الْأَرْضِ وَلَا فِي السَّمَاءِ وَهُوَ السَّمِيعُ الْعَلِيمُ",
            source = "حصن المسلم (أصله في سنن أبي داود والترمذي)",
            authenticityGrade = AuthenticityGrade.SAHIH,
            authenticityNote = "يُرجى مطابقة رقم الحديث الدقيق في طبعتك من حصن المسلم.",
            fullVirtue = "من قالها ثلاث مرات حين يصبح وحين يمسي لم تصبه فجأة بلاء حتى يمسي أو حتى يصبح.",
            narrationContext = "لم يرد سبب ورود موثق",
            chainOfNarration = null,
            narrator = null,
            repeatCount = 3,
            englishTranslation = null,
            lengthCategory = LengthCategory.SHORT,
            needsReview = false
        )
    )

    val crossRefs = listOf(
        // آية الكرسي: صباح + حماية/رقية + الأكثر شهرة + القرآن
        DhikrCategoryCrossRef(AYAT_AL_KURSI, CAT_MORNING),
        DhikrCategoryCrossRef(AYAT_AL_KURSI, CAT_PROTECTION),
        DhikrCategoryCrossRef(AYAT_AL_KURSI, CAT_POPULAR),
        DhikrCategoryCrossRef(AYAT_AL_KURSI, CAT_SOURCE_QURAN),

        // الإخلاص: صباح + ثناء + القرآن
        DhikrCategoryCrossRef(AL_IKHLAS, CAT_MORNING),
        DhikrCategoryCrossRef(AL_IKHLAS, CAT_PRAISE),
        DhikrCategoryCrossRef(AL_IKHLAS, CAT_SOURCE_QURAN),

        // الفلق: صباح + حماية/رقية + الأكثر شهرة (المعوذتين) + القرآن
        DhikrCategoryCrossRef(AL_FALAQ, CAT_MORNING),
        DhikrCategoryCrossRef(AL_FALAQ, CAT_PROTECTION),
        DhikrCategoryCrossRef(AL_FALAQ, CAT_POPULAR),
        DhikrCategoryCrossRef(AL_FALAQ, CAT_SOURCE_QURAN),

        // الناس: صباح + حماية/رقية + الأكثر شهرة (المعوذتين) + القرآن
        DhikrCategoryCrossRef(AN_NAS, CAT_MORNING),
        DhikrCategoryCrossRef(AN_NAS, CAT_PROTECTION),
        DhikrCategoryCrossRef(AN_NAS, CAT_POPULAR),
        DhikrCategoryCrossRef(AN_NAS, CAT_SOURCE_QURAN),

        // سيد الاستغفار: صباح + استغفار + الأكثر شهرة
        DhikrCategoryCrossRef(SAYYID_AL_ISTIGHFAR, CAT_MORNING),
        DhikrCategoryCrossRef(SAYYID_AL_ISTIGHFAR, CAT_ISTIGHFAR),
        DhikrCategoryCrossRef(SAYYID_AL_ISTIGHFAR, CAT_POPULAR),
        DhikrCategoryCrossRef(SAYYID_AL_ISTIGHFAR, CAT_SOURCE_HISN),

        // حسبي الله: صباح + كرب وهم
        DhikrCategoryCrossRef(HASBUNA_ALLAH, CAT_MORNING),
        DhikrCategoryCrossRef(HASBUNA_ALLAH, CAT_DISTRESS),
        DhikrCategoryCrossRef(HASBUNA_ALLAH, CAT_SOURCE_HISN),

        // رضيت بالله: صباح + ثناء
        DhikrCategoryCrossRef(RADHEETU_BILLAH, CAT_MORNING),
        DhikrCategoryCrossRef(RADHEETU_BILLAH, CAT_PRAISE),
        DhikrCategoryCrossRef(RADHEETU_BILLAH, CAT_SOURCE_HISN),

        // بسم الله الذي لا يضر: صباح + حماية/رقية
        DhikrCategoryCrossRef(BISMILLAH_LA_YADHURRU, CAT_MORNING),
        DhikrCategoryCrossRef(BISMILLAH_LA_YADHURRU, CAT_PROTECTION),
        DhikrCategoryCrossRef(BISMILLAH_LA_YADHURRU, CAT_SOURCE_HISN)
    )
}
