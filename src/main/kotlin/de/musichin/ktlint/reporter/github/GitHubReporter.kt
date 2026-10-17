package de.musichin.ktlint.reporter.github

import com.pinterest.ktlint.cli.reporter.core.api.KtlintCliError
import com.pinterest.ktlint.cli.reporter.core.api.KtlintCliError.Status.BASELINE_IGNORED
import com.pinterest.ktlint.cli.reporter.core.api.KtlintCliError.Status.FORMAT_IS_AUTOCORRECTED
import com.pinterest.ktlint.cli.reporter.core.api.ReporterV2
import java.io.PrintStream

class GitHubReporter(
    private val out: PrintStream,
    val level: Level = Level.ERROR,
) : ReporterV2 {
    override fun onLintError(
        file: String,
        ktlintCliError: KtlintCliError,
    ) {
        if (level == Level.NONE) return
        if (ktlintCliError.status == FORMAT_IS_AUTOCORRECTED) return
        if (ktlintCliError.status == BASELINE_IGNORED) return

        val line = ktlintCliError.line
        val column = ktlintCliError.col
        val title = title(ktlintCliError.ruleId)
        val message = escape(ktlintCliError.detail)
        val command = level.name.lowercase()
        out.println("::$command file=${escapeProperty(file)},line=$line,col=$column,title=${escapeProperty(title)}::$message")
    }

    private fun title(ruleId: String): String = if (ruleId.isBlank()) "ktlint" else "ktlint:$ruleId"

    private fun escape(data: String): String = data.replace("%", "%25").replace("\r", "%0D").replace("\n", "%0A")

    private fun escapeProperty(data: String): String = escape(data).replace(":", "%3A").replace(",", "%2C")
}
