package com.example.advhci.speech

object AnswerMatcher {

    /**
     * Resolves raw spoken voice input into an answer index (0 to 3).
     * Returns null if no match could be identified with high confidence.
     */
    fun resolveSpokenAnswer(spokenText: String, answers: List<String>): Int? {
        // Strip punctuation and normalize whitespace
        val clean = spokenText.trim().lowercase()
            .replace(Regex("[^a-z0-9\\s]"), "")
            .trim()

        if (clean.isBlank()) return null

        // 1. Broad phonetic dictionary for single letter choices
        val phoneticMap = mapOf(
            // --- Option A (Index 0) ---
            "a" to 0, "ay" to 0, "eh" to 0, "hey" to 0, "eight" to 0, "ate" to 0,
            "option a" to 0, "choice a" to 0, "letter a" to 0, "answer a" to 0,

            // --- Option B (Index 1) ---
            "b" to 1, "bee" to 1, "be" to 1, "beat" to 1, "me" to 1, "the" to 1,
            "option b" to 1, "choice b" to 1, "letter b" to 1, "answer b" to 1,

            // --- Option C (Index 2) ---
            "c" to 2, "see" to 2, "sea" to 2, "si" to 2, "scene" to 2,
            "option c" to 2, "choice c" to 2, "letter c" to 2, "answer c" to 2,

            // --- Option D (Index 3) ---
            "d" to 3, "dee" to 3, "de" to 3, "thee" to 3, "tea" to 3,
            "option d" to 3, "choice d" to 3, "letter d" to 3, "answer d" to 3
        )
        phoneticMap[clean]?.let { return it }

        // 2. Numeric / Ordinal fallback (e.g., "first", "second", "one", "two")
        val numericMap = mapOf(
            "1" to 0, "one" to 0, "first" to 0, "option 1" to 0, "option one" to 0,
            "2" to 1, "two" to 1, "second" to 1, "to" to 1, "too" to 1, "option 2" to 1,
            "3" to 2, "three" to 2, "third" to 2, "tree" to 2, "option 3" to 2,
            "4" to 3, "four" to 3, "fourth" to 3, "for" to 3, "fore" to 3, "option 4" to 3
        )
        numericMap[clean]?.let { return it }

        // 3. Check if the utterance starts or ends with an explicit letter command
        // e.g., "i think it's b", "select c", "go with a"
        val words = clean.split("\\s+".toRegex())
        for (word in words) {
            when (word) {
                "a", "ay" -> return 0
                "b", "bee" -> return 1
                "c", "see", "sea" -> return 2
                "d", "dee" -> return 3
            }
        }

        // 4. Semantic matching: checks if any key word in the answer choice was spoken
        answers.forEachIndexed { index, answer ->
            val cleanAnswer = answer.trim().lowercase().replace(Regex("[^a-z0-9\\s]"), "")
            if (clean.contains(cleanAnswer) || cleanAnswer.contains(clean)) {
                return index
            }
        }

        return null
    }
}