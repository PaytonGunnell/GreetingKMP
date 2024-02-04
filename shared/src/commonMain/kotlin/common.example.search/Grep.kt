package common.example.search

// Filters the strings based on the provided regex, then uses them in the 'action' function provided
fun grep(lines: List<String>, pattern: String, action: (String) -> Unit) {
    val regex = pattern.toRegex()
    lines.filter(regex::containsMatchIn)
        .forEach(action)
}