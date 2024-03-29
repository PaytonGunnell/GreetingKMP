package org.kmp.testing

import kotlin.test.Test
import kotlin.test.assertEquals

class IOSRuntimeTest {
    @Test
    fun shouldDetectOS() {
        val runtime = determineCurrentRuntime()
        assertEquals(runtime.name, "ios")
        assertEquals(runtime.version, "unknown")
    }
}