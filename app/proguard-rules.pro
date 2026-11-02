# قواعد ProGuard/R8 الخاصة بالمشروع، جاهزة مسبقاً تحسباً لتفعيل isMinifyEnabled عند النشر.
# ملاحظة: isMinifyEnabled لا يزال false حالياً (انظر app/build.gradle.kts)، فهذا الملف لا يغيّر
# سلوك أي بناء حالي؛ يحتاج تفعيل التصغير اختباراً فعلياً على جهاز/محاكي قبل الاعتماد النهائي
# على بنية Release، لأن تضارب قواعد keep لا يظهر إلا وقت التشغيل الفعلي بعد التصغير.

# Room: الكيانات وDAOs تُستخدم عبر أكواد مولّدة وقت الترجمة (annotation processing)، لكن نُبقي
# أسماء الحقول حتى لا يختل map ORM لو أعاد R8 تسمية حقل بالخطأ خارج نطاق الكود المولّد.
-keepclassmembers class com.hisnaladhkar.app.data.local.entity.** { *; }
-keepclassmembers class com.hisnaladhkar.app.data.local.relation.** { *; }

# مكتبة Adhan: مكتبة خارجية صغيرة بلا قواعد consumer-proguard خاصة بها، نُبقيها كاملة احتياطاً.
-keep class com.batoulapps.adhan.** { *; }

# نماذج طبقة Domain تُستخدم كحالة Compose State (data class)؛ الإبقاء عليها يمنع كسر equals/toString
# المعتمد عليه في بعض فحوصات recomposition وفي اختبارات الوحدة المصدّرة لاحقاً لبنية Release.
-keep class com.hisnaladhkar.app.domain.model.** { *; }
