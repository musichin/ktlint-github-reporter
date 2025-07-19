# 🛠 ktlint GitHub reporter
[![Kotlin](https://img.shields.io/badge/Kotlin-2.2.0-blue.svg)](http://kotlinlang.org)
[![CI](https://github.com/musichin/ktlint-github-reporter/actions/workflows/ci.yml/badge.svg)](https://github.com/musichin/ktlint-github-reporter/actions/workflows/ci.yml)
[![codecov](https://codecov.io/gh/musichin/ktlint-github-reporter/branch/main/graph/badge.svg?token=I2LXI4OUBT)](https://codecov.io/gh/musichin/ktlint-github-reporter)
[![Release](https://img.shields.io/github/v/release/musichin/ktlint-github-reporter)](https://github.com/musichin/ktlint-github-reporter/releases)
[![Maven Central](https://img.shields.io/maven-central/v/de.musichin.ktlint.reporter/ktlint-reporter-github)](https://central.sonatype.com/artifact/de.musichin.ktlint.reporter/ktlint-reporter-github)

A custom [ktlint](https://ktlint.github.io) reporter that annotates Kotlin lint issues directly in GitHub PRs using official GitHub [workflow commands](https://docs.github.com/en/actions/reference/workflow-commands-for-github-actions#setting-an-error-message).

- ✅ Integrates seamlessly with GitHub Actions
- 🎯 Supports `error`, `warning`, `notice`, or `none` levels
- 📦 Published to Maven Central

---

## 🚀 Quick Start

### ▶️ Using JAR
Download the latest JAR from the [Releases](https://github.com/musichin/ktlint-github-reporter/releases) page:

```bash
ktlint --reporter=github,artifact=ktlint-github-reporter.jar
```
Set the annotation level (default is error):
```bash
ktlint --reporter=github?level=warning,artifact=ktlint-github-reporter.jar
```

### 🛠 Using Maven or Gradle

#### Gradle (Kotlin or Groovy DSL)
```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation "de.musichin.ktlint.reporter:ktlint-reporter-github:x.y.z"
}
```

#### Maven
```xml
<dependency>
  <groupId>de.musichin.ktlint.reporter</groupId>
  <artifactId>ktlint-reporter-github</artifactId>
  <version>x.y.z</version>
</dependency>
```

### 🧪 GitHub Actions Integration
Use in a GitHub Actions workflow:
```yaml
steps:
  - uses: actions/checkout@v4
  - name: Run ktlint with GitHub reporter
    run: ktlint --reporter=github,artifact=ktlint-github-reporter.jar
```
Alternatively, use the [ktlint-check](https://github.com/musichin/ktlint-check) GitHub Action:
```yaml
- uses: musichin/ktlint-check@v3
  with:
    ktlint-version: '1.7.0'
    level: warning
```
> This action internally uses this reporter.

## 🔧 Configuration Options
| Option  | Description                                                  | Default |
|---------|--------------------------------------------------------------|---------|
| `level` | Sets severity level: `error`, `warning`, `notice`, or `none` | `error` |

## Example
![](example.jpg)

## 📝 License
MIT License © 2025 Anton Musichin
