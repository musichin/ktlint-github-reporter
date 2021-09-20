# ktlint GitHub reporter
[![Kotlin](https://img.shields.io/badge/Kotlin-1.5.31-blue.svg)](http://kotlinlang.org)
[![CI](https://github.com/musichin/ktlint-github-reporter/actions/workflows/ci.yml/badge.svg)](https://github.com/musichin/ktlint-github-reporter/actions/workflows/ci.yml)
[![codecov](https://codecov.io/gh/musichin/ktlint-github-reporter/branch/main/graph/badge.svg?token=I2LXI4OUBT)](https://codecov.io/gh/musichin/ktlint-github-reporter)

Uses GitHub [workflow commands](https://docs.github.com/en/actions/reference/workflow-commands-for-github-actions#setting-an-error-message) to set error messages for [ktlint](https://ktlint.github.io/) issues.

This library is used by [ktlint-check](https://github.com/musichin/ktlint-check) action.

## Usage
Download the jar and run:
```
ktlint --reporter=github,artifact=ktlint-github-reporter.jar
```
To report as warnings:
```
ktlint --reporter=github?warn,artifact=ktlint-github-reporter.jar
```

## Download
Either download the JAR directly from [Releases](https://github.com/musichin/ktlint-github-reporter/releases) section or use maven:
```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation 'de.musichin.ktlint.reporter:ktlint-reporter-github:x.y.z'
}
```

## Example
![](example.jpg)

## License

    MIT License

    Copyright (c) 2021 Anton Musichin

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.

