package de.musichin.ktlint.reporter.github

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class GitHubReporterProviderTest {
    @Test
    fun testGetWithImplicit() {
        assertEquals(Level.ERROR, getReporter(emptyMap()).level)
    }

    @Test
    fun testGetWithLevelError() {
        assertEquals(Level.ERROR, getReporter(mapOf("level" to "Error")).level)
    }

    @Test
    fun testGetWithLevelWarning() {
        assertEquals(Level.WARNING, getReporter(mapOf("level" to "Warning")).level)
    }

    @Test
    fun testGetWithLevelNotice() {
        assertEquals(Level.NOTICE, getReporter(mapOf("level" to "Notice")).level)
    }

    @Test
    fun testGetWithLevelNone() {
        assertEquals(Level.NONE, getReporter(mapOf("level" to "None")).level)
    }

    @Test
    fun testGetWithInvalidLevel() {
        val exception = assertFailsWith<IllegalArgumentException> { getReporter(mapOf("level" to "warn")) }

        assertEquals(
            "Invalid level 'warn' for the github reporter. Valid values: none, notice, warning, error.",
            exception.message,
        )
    }

    @Test
    fun testId() {
        assertEquals("github", GitHubReporterProvider().id)
    }

    private fun getReporter(opt: Map<String, String>) = GitHubReporterProvider().get(PrintStream(ByteArrayOutputStream()), opt)
}
