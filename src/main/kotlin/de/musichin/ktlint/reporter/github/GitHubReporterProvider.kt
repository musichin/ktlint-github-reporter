package de.musichin.ktlint.reporter.github

import com.pinterest.ktlint.cli.reporter.core.api.ReporterProviderV2
import java.io.PrintStream

class GitHubReporterProvider : ReporterProviderV2<GitHubReporter> {
    override val id: String = "github"

    override fun get(
        out:PrintStream,
        opt: Map<String, String>,
    ): GitHubReporter {
        val level = opt["level"] ?: return GitHubReporter(out)
        return GitHubReporter(out, parseLevel(level))
    }

    private fun parseLevel(value: String): Level {
        val level = Level.entries.firstOrNull { it.name.equals(value, ignoreCase = true) }
        if (level != null) return level

        val validValues = Level.entries.joinToString { it.name.lowercase() }
        throw IllegalArgumentException("Invalid level '$value' for the $id reporter. Valid values: $validValues.")
    }
}