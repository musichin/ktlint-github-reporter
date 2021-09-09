package de.musichin.ktlint.reporter.github

import com.pinterest.ktlint.core.LintError
import com.pinterest.ktlint.core.Reporter
import java.io.PrintStream

class GitHubReporter(
    private val out: PrintStream,
    val warn: Boolean = false,
) : Reporter {
    private val command = if (warn) "warning" else "error"

    override fun onLintError(file: String, err: LintError, corrected: Boolean) {
        if (corrected) return

        val line = err.line
        val column = err.col
        val message = escape(err.detail)
        out.println("::$command file=$file,line=$line,col=$column::$message")
    }

    private fun escape(data: String): String {
        return data
            .replace("%", "%25")
            .replace("\r", "%0D")
            .replace("\n", "%0A")
    }
}
