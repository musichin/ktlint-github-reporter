package de.musichin.ktlint.reporter.github

import org.junit.Assert.assertNotNull
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals

class GitHubReporterProviderTest {

    @Test
    fun testGetWithImplicit() {
        val stream = ByteArrayOutputStream()
        val out = PrintStream(stream)
        val reporter = GitHubReporterProvider().get(out, emptyMap())

        assertNotNull(reporter)
        assertEquals(Level.ERROR, reporter.level)
    }

    @Test
    fun testGetWithLevelError() {
        val stream = ByteArrayOutputStream()
        val out = PrintStream(stream)
        val reporter = GitHubReporterProvider().get(out, mapOf("level" to "Error"))

        assertNotNull(reporter)
        assertEquals(Level.ERROR, reporter.level)
    }

    @Test
    fun testGetWithLevelWarning() {
        val stream = ByteArrayOutputStream()
        val out = PrintStream(stream)
        val reporter = GitHubReporterProvider().get(out, mapOf("level" to "Warning"))

        assertNotNull(reporter)
        assertEquals(Level.WARNING, reporter.level)
    }

    @Test
    fun testGetWithLevelNotice() {
        val stream = ByteArrayOutputStream()
        val out = PrintStream(stream)
        val reporter = GitHubReporterProvider().get(out, mapOf("level" to "Notice"))

        assertNotNull(reporter)
        assertEquals(Level.NOTICE, reporter.level)
    }

    @Test
    fun testGetWithLevelNone() {
        val stream = ByteArrayOutputStream()
        val out = PrintStream(stream)
        val reporter = GitHubReporterProvider().get(out, mapOf("level" to "None"))

        assertNotNull(reporter)
        assertEquals(Level.NONE, reporter.level)
    }

    @Test
    fun testId() {
        val id = GitHubReporterProvider().id

        assertEquals("github", id)
    }
}
