package de.musichin.ktlint.reporter.github

import com.pinterest.ktlint.cli.reporter.core.api.KtlintCliError
import com.pinterest.ktlint.cli.reporter.core.api.KtlintCliError.Status.FORMAT_IS_AUTOCORRECTED
import com.pinterest.ktlint.cli.reporter.core.api.KtlintCliError.Status.LINT_CAN_NOT_BE_AUTOCORRECTED
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

        val lintError = KtlintCliError(1, 2, "testRuleId", "testDetail", LINT_CAN_NOT_BE_AUTOCORRECTED)
        reporter.before("testFile")
        reporter.onLintError("testFile", lintError)
        reporter.after("testFile")

        val result = stream.toString().trim()
        assertEquals("::error file=testFile,line=1,col=2::testDetail", result)
    }

    @Test
    fun testReporterErrorWithLevelWarning() {
        val stream = ByteArrayOutputStream()
        val out = PrintStream(stream)
        val reporter = GitHubReporter(out, Level.WARNING)

        val lintError = KtlintCliError(1, 2, "testRuleId", "testDetail", LINT_CAN_NOT_BE_AUTOCORRECTED)
        reporter.before("testFile")
        reporter.onLintError("testFile", lintError)
        reporter.after("testFile")

        val result = stream.toString().trim()
        assertEquals("::warning file=testFile,line=1,col=2::testDetail", result)
    }

    @Test
    fun testReporterErrorWithLevelNotice() {
        val stream = ByteArrayOutputStream()
        val out = PrintStream(stream)
        val reporter = GitHubReporter(out, Level.NOTICE)

        val lintError = KtlintCliError(1, 2, "testRuleId", "testDetail", LINT_CAN_NOT_BE_AUTOCORRECTED)
        reporter.before("testFile")
        reporter.onLintError("testFile", lintError)
        reporter.after("testFile")

        val result = stream.toString().trim()
        assertEquals("::notice file=testFile,line=1,col=2::testDetail", result)
    }

    @Test
    fun testReporterErrorWithLevelNone() {
        val stream = ByteArrayOutputStream()
        val out = PrintStream(stream)
        val reporter = GitHubReporter(out, Level.NONE)

        val lintError = KtlintCliError(1, 2, "testRuleId", "testDetail", LINT_CAN_NOT_BE_AUTOCORRECTED)
        reporter.before("testFile")
        reporter.onLintError("testFile", lintError)
        reporter.after("testFile")

        val result = stream.toString().trim()
        assertEquals("", result)
    }

    @Test
    fun testIgnoringCorrected() {
        val stream = ByteArrayOutputStream()
        val out = PrintStream(stream)
        val reporter = GitHubReporter(out, Level.ERROR)

        val lintError = KtlintCliError(1, 2, "testRuleId", "testDetail", FORMAT_IS_AUTOCORRECTED)
        reporter.before("testFile")
        reporter.onLintError("testFile", lintError)
        reporter.after("testFile")

        val result = stream.toString().trim()
        assertEquals("", result)
    }
}
