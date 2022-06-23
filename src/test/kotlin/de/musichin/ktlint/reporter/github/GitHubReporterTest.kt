package de.musichin.ktlint.reporter.github

import com.pinterest.ktlint.core.LintError
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals

class GitHubReporterTest {

    @Test
    fun testReporterErrorWithLevelError() {
        val stream = ByteArrayOutputStream()
        val out = PrintStream(stream)
        val reporter = GitHubReporter(out, Level.ERROR)

        val lintError = LintError(1, 2, "testRuleId", "testDetail")
        reporter.before("testFile")
        reporter.onLintError("testFile", lintError, false)
        reporter.after("testFile")

        val result = stream.toString().trim()
        assertEquals("::error file=testFile,line=1,col=2::testDetail", result)
    }

    @Test
    fun testReporterErrorWithLevelWarning() {
        val stream = ByteArrayOutputStream()
        val out = PrintStream(stream)
        val reporter = GitHubReporter(out, Level.WARNING)

        val lintError = LintError(1, 2, "testRuleId", "testDetail")
        reporter.before("testFile")
        reporter.onLintError("testFile", lintError, false)
        reporter.after("testFile")

        val result = stream.toString().trim()
        assertEquals("::warning file=testFile,line=1,col=2::testDetail", result)
    }

    @Test
    fun testReporterErrorWithLevelNotice() {
        val stream = ByteArrayOutputStream()
        val out = PrintStream(stream)
        val reporter = GitHubReporter(out, Level.NOTICE)

        val lintError = LintError(1, 2, "testRuleId", "testDetail")
        reporter.before("testFile")
        reporter.onLintError("testFile", lintError, false)
        reporter.after("testFile")

        val result = stream.toString().trim()
        assertEquals("::notice file=testFile,line=1,col=2::testDetail", result)
    }

    @Test
    fun testIgnoringCorrected() {
        val stream = ByteArrayOutputStream()
        val out = PrintStream(stream)
        val reporter = GitHubReporter(out, Level.ERROR)

        val lintError = LintError(1, 2, "testRuleId", "testDetail")
        reporter.before("testFile")
        reporter.onLintError("testFile", lintError, true)
        reporter.after("testFile")

        val result = stream.toString().trim()
        assertEquals("", result)
    }
}
