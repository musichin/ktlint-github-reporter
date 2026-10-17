package de.musichin.ktlint.reporter.github

import com.pinterest.ktlint.cli.reporter.core.api.KtlintCliError
import com.pinterest.ktlint.cli.reporter.core.api.KtlintCliError.Status.BASELINE_IGNORED
import com.pinterest.ktlint.cli.reporter.core.api.KtlintCliError.Status.FORMAT_IS_AUTOCORRECTED
import com.pinterest.ktlint.cli.reporter.core.api.KtlintCliError.Status.LINT_CAN_NOT_BE_AUTOCORRECTED
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.Test
import kotlin.test.assertEquals

class GitHubReporterTest {
    @Test
    fun testReporterErrorWithLevelError() {
        assertEquals("::error file=testFile,line=1,col=2,title=ktlint%3AtestRuleId::testDetail", report(Level.ERROR))
    }

    @Test
    fun testReporterErrorWithLevelWarning() {
        assertEquals("::warning file=testFile,line=1,col=2,title=ktlint%3AtestRuleId::testDetail", report(Level.WARNING))
    }

    @Test
    fun testReporterErrorWithLevelNotice() {
        assertEquals("::notice file=testFile,line=1,col=2,title=ktlint%3AtestRuleId::testDetail", report(Level.NOTICE))
    }

    @Test
    fun testReporterErrorWithLevelNone() {
        assertEquals("", report(Level.NONE))
    }

    @Test
    fun testIgnoringCorrected() {
        assertEquals("", report(status = FORMAT_IS_AUTOCORRECTED))
    }

    @Test
    fun testIgnoringBaseline() {
        assertEquals("", report(detail = "", status = BASELINE_IGNORED))
    }

    @Test
    fun testEscapingFileProperty() {
        assertEquals(
            "::error file=C%3A\\dir%2C1\\100%25.kt,line=1,col=2,title=ktlint%3AtestRuleId::testDetail",
            report(file = "C:\\dir,1\\100%.kt"),
        )
    }

    @Test
    fun testEscapingMessage() {
        assertEquals(
            "::error file=testFile,line=1,col=2,title=ktlint%3AtestRuleId::100%25 sure%0D%0Anext: a, b",
            report(detail = "100% sure\r\nnext: a, b"),
        )
    }

    @Test
    fun testRuleIdInTitle() {
        assertEquals(
            "::error file=testFile,line=1,col=2,title=ktlint%3Astandard%3Ano-consecutive-blank-lines::Needless blank line(s)",
            report(ruleId = "standard:no-consecutive-blank-lines", detail = "Needless blank line(s)"),
        )
    }

    @Test
    fun testEmptyRuleId() {
        assertEquals(
            "::error file=testFile,line=1,col=2,title=ktlint::Not a valid Kotlin file",
            report(ruleId = "", detail = "Not a valid Kotlin file"),
        )
    }

    private fun report(
        level: Level = Level.ERROR,
        file: String = "testFile",
        ruleId: String = "testRuleId",
        detail: String = "testDetail",
        status: KtlintCliError.Status = LINT_CAN_NOT_BE_AUTOCORRECTED,
    ): String {
        val stream = ByteArrayOutputStream()
        val reporter = GitHubReporter(PrintStream(stream), level)
        reporter.onLintError(file, KtlintCliError(1, 2, ruleId, detail, status))
        return stream.toString().trim()
    }
}
