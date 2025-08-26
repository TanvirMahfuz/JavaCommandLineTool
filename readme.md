# Java Shell Project

This is a custom Java shell project that allows you to run file system commands like `ls`, `cd`, `count`, and more. It is built using **Java**, follows a modular structure with **commands, input handling, and process execution**, and supports multithreading for recursive operations.

---

## Features

- `cd <folder>` – Change current directory.
- `ls` – List all files and directories in the current directory (color-coded output).
- `ls -l` – Long listing format, shows file type, size, last modified date, and color-coded names.
- `count *` – Count files and directories in the current directory (non-recursive).
- `count -a` – Count all files and directories recursively.
- `count -m` – Count all files and directories recursively using multithreading for faster execution.
- `exit` – Exit the shell.

---

## Getting Started

### Prerequisites

- Java 8 or higher
- Optional: Maven or Gradle (if using build tools)

### Running from IDE

1. Open the project in your IDE (IntelliJ, Eclipse, etc.).
2. Run `Main.java`.
3. You will see the shell prompt:
