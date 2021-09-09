package de.musichin.ktlint.reporter.github

import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals

class GitHubReporterProviderTest {

    @Test
    fun testGetError() {
        val stream = ByteArrayOutputStream()
        val out = PrintStream(stream)
        val reporter = GitHubReporterProvider().get(out, emptyMap())

        assertNotNull(reporter)
        assertFalse(reporter.warn)
    }

    @Test
    fun testGetWarning() {
        val stream = ByteArrayOutputStream()
        val out = PrintStream(stream)
        val reporter = GitHubReporterProvider().get(out, mapOf("warn" to "true"))

        assertNotNull(reporter)
        assertTrue(reporter.warn)
    }

    @Test
    fun testId() {
        val id = GitHubReporterProvider().id

        assertEquals("github", id)
    }
}
