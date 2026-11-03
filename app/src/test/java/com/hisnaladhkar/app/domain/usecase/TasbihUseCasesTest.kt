package com.hisnaladhkar.app.domain.usecase

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TasbihUseCasesTest {

    private lateinit var repository: FakeTasbihRepository
    private lateinit var increment: IncrementTasbihUseCase
    private lateinit var reset: ResetTasbihUseCase
    private lateinit var getCounts: GetTasbihCountsUseCase

    @Before
    fun setUp() {
        repository = FakeTasbihRepository()
        increment = IncrementTasbihUseCase(repository)
        reset = ResetTasbihUseCase(repository)
        getCounts = GetTasbihCountsUseCase(repository)
    }

    @Test
    fun `incrementing the same phrase three times counts to three`() = runBlocking {
        increment("سبحان الله")
        increment("سبحان الله")
        increment("سبحان الله")

        val counts = getCounts().first()

        assertEquals(3, counts.first { it.phrase == "سبحان الله" }.count)
    }

    @Test
    fun `different phrases are counted independently`() = runBlocking {
        increment("سبحان الله")
        increment("الحمد لله")
        increment("الحمد لله")

        val counts = getCounts().first().associate { it.phrase to it.count }

        assertEquals(1, counts["سبحان الله"])
        assertEquals(2, counts["الحمد لله"])
    }

    @Test
    fun `reset zeroes out only the given phrase`() = runBlocking {
        increment("سبحان الله")
        increment("سبحان الله")
        increment("الحمد لله")

        reset("سبحان الله")

        val counts = getCounts().first().associate { it.phrase to it.count }
        assertEquals(0, counts["سبحان الله"])
        assertEquals(1, counts["الحمد لله"])
    }
}
