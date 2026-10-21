package com.example.advhci.speech

object AnswerMatcher {

    /**
     * Resolves raw spoken voice input into an answer index (0 to 3).
     * Returns null if no match could be identified with high confidence.
     */
    fun resolveSpokenAnswer(spokenText: String, answers: List<String>): Int? {
        val clean = spokenText.trim().lowercase()

        // 1. Direct letter matching (A, B, C, D)
        val letterMap = mapOf(
            "a" to 0, "option a" to 0, "choice a" to 0, "letter a" to 0, "hey" to 0, "ay" to 0,
            "b" to 1, "option b" to 1, "choice b" to 1, "letter b" to 1, "bee" to 1,
            "c" to 2, "option c" to 2, "choice c" to 2, "letter c" to 2, "sea" to 2, "see" to 2,
            "d" to 3, "option d" to 3, "choice d" to 3, "letter d" to 3, "dee" to 3
        )
        letterMap[clean]?.let { return it }

        // 2. Direct numeric matching (1, 2, 3, 4)
        val numberMap = mapOf(
            "1" to 0, "one" to 0, "first" to 0, "option 1" to 0, "option one" to 0,
            "2" to 1, "two" to 1, "to" to 1, "too" to 1, "second" to 1, "option 2" to 1, "option two" to 1,
            "3" to 2, "three" to 2, "third" to 2, "option 3" to 2, "option three" to 2,
            "4" to 3, "four" to 3, "for" to 3, "fourth" to 3, "option 4" to 3, "option four" to 3
        )
        numberMap[clean]?.let { return it }

        // 3. Exact or substring matching against option text (e.g. "Fact", "Manipulated", "Peer Review")
        answers.forEachIndexed { index, answer ->
            val cleanAnswer = answer.trim().lowercase()
            if (clean == cleanAnswer || clean.contains(cleanAnswer) || cleanAnswer.contains(clean)) {
                return index
            }
        }

        return null
    }
}