package com.hisnaladhkar.app.data.local.seed

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * اختبارات سلامة بيانات التأسيس (Seed) عبر الدفعات الثلاث. نظراً لأن كل المحتوى أُدخل يدوياً
 * من ملفات PDF على مدى عدة آلاف من الأسطر، هذا الاختبار شبكة أمان ضد أخطاء نسخ لاصقة بحتة:
 * تضارب معرّفات، تصنيفات مفقودة، أو نص فارغ — وليس تحققاً من صحة المحتوى الديني نفسه.
 */
class SeedDataIntegrityTest {

    private val allCategoryLists = listOf(
        SeedData.categories,
        SeedDataMafatihAlDua.categories,
        SeedDataHisnAlMuslim.categories
    )
    private val allDhikrLists = listOf(
        SeedData.adhkar,
        SeedDataMafatihAlDua.adhkar,
        SeedDataHisnAlMuslim.adhkar
    )
    private val allCrossRefLists = listOf(
        SeedData.crossRefs,
        SeedDataMafatihAlDua.crossRefs,
        SeedDataHisnAlMuslim.crossRefs
    )

    @Test
    fun `no duplicate category ids across all seed batches`() {
        val ids = allCategoryLists.flatten().map { it.id }
        assertEquals("يوجد معرّف تصنيف مكرر بين الدفعات الثلاث", ids.size, ids.toSet().size)
    }

    @Test
    fun `no duplicate dhikr ids across all seed batches`() {
        val ids = allDhikrLists.flatten().map { it.id }
        assertEquals("يوجد معرّف ذكر مكرر بين الدفعات الثلاث", ids.size, ids.toSet().size)
    }

    @Test
    fun `every cross-ref points at a dhikr id and a category id that actually exist`() {
        val dhikrIds = allDhikrLists.flatten().map { it.id }.toSet()
        val categoryIds = allCategoryLists.flatten().map { it.id }.toSet()

        allCrossRefLists.flatten().forEach { crossRef ->
            assertTrue(
                "crossRef يشير إلى dhikrId ${crossRef.dhikrId} غير موجود في أي دفعة أذكار",
                crossRef.dhikrId in dhikrIds
            )
            assertTrue(
                "crossRef يشير إلى categoryId ${crossRef.categoryId} غير موجود في أي دفعة تصنيفات",
                crossRef.categoryId in categoryIds
            )
        }
    }

    @Test
    fun `no dhikr has blank Arabic text`() {
        allDhikrLists.flatten().forEach { dhikr ->
            assertTrue("الذكر رقم ${dhikr.id} نصه فارغ", dhikr.arabicText.isNotBlank())
        }
    }

    @Test
    fun `no category has a blank Arabic name`() {
        allCategoryLists.flatten().forEach { category ->
            assertTrue("التصنيف رقم ${category.id} اسمه فارغ", category.nameArabic.isNotBlank())
        }
    }

    @Test
    fun `Hisn al-Muslim batch contains exactly the number of items it was transcribed with`() {
        // رقم ثابت موثّق في تعليق SeedDataHisnAlMuslim نفسه (270)؛ هذا الاختبار يَكشف أي
        // تعديل مستقبلي يُسقط أو يضيف عناصر عن غير قصد في تلك الدفعة تحديداً.
        assertEquals(270, SeedDataHisnAlMuslim.adhkar.size)
    }

    @Test
    fun `Mafatih al-Dua batch contains exactly the number of items it was transcribed with`() {
        // 130 دعاءً في الكتاب، لكن اثنين منها (رقم 10 و59) طابقا نصّياً عنصرين موجودين أصلاً
        // في SeedData فلم يُكرَّر صفّاهما (انظر تعليق extraCrossRefsForExistingDhikr هناك).
        assertEquals(128, SeedDataMafatihAlDua.adhkar.size)
    }
}
