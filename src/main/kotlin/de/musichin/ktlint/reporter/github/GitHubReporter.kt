package de.musichin.ktlint.reporter.github

import com.pinterest.ktlint.core.LintError
import com.pinterest.ktlint.core.Reporter
import java.io.PrintStream

class GitHubReporter(
    private val out: PrintStream,
    val level: Level = Level.ERROR,
) : Reporter {

    override fun onLintError(file: String, err: LintError, corrected: Boolean) {
        if (corrected) return
        if (level == Level.NONE) return

        val line = err.line
        val column = err.col
        val message = escape(err.detail)
        val command = level.name.lowercase()
        out.println("::$command file=$file,line=$line,col=$column::$message")
    }

    private fun escape(data: String): String {
        return data
            .replace("%", "%25")
            .replace("\r", "%0D")
            .replace("\n", "%0A")
    }
}
