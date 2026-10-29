package com.hisnaladhkar.app.data.local.seed

import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.RoomDatabase
import com.hisnaladhkar.app.data.local.AdhkarDatabase
import com.hisnaladhkar.app.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

/**
 * يُدخل الدفعة الأولى من الأذكار عند أول إنشاء لقاعدة البيانات فقط (onCreate، وليس onOpen).
 * الاعتماد على Provider<AdhkarDatabase> (بدل حقن AdhkarDatabase مباشرة) ضروري لكسر الحلقة
 * الدائرية: هذا الـ Callback نفسه يُمرَّر لبناء AdhkarDatabase، فلا يمكنه الاعتماد عليها مباشرة.
 */
class SeedDatabaseCallback @Inject constructor(
    private val databaseProvider: Provider<AdhkarDatabase>,
    @ApplicationScope private val scope: CoroutineScope
) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        scope.launch {
            val database = databaseProvider.get()
            database.categoryDao().insertAll(SeedData.categories)
            database.dhikrDao().insertAll(SeedData.adhkar)
            database.dhikrCategoryCrossRefDao().insertAll(SeedData.crossRefs)
        }
    }
}
