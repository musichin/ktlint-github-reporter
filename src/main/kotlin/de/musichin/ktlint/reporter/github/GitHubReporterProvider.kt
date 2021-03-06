package de.musichin.ktlint.reporter.github

import com.pinterest.ktlint.core.ReporterProvider
import java.io.PrintStream

class GitHubReporterProvider : ReporterProvider<GitHubReporter> {
    override val id: String = "github"

    override fun get(out: PrintStream, opt: Map<String, String>): GitHubReporter {
        val level = opt["level"] ?: return GitHubReporter(out)
        return GitHubReporter(out, Level.valueOf(level.uppercase()))
    }
}
