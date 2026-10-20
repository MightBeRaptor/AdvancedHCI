package com.example.advhci

data class Question(
    val text: String,
    val answers: List<String>,
    val correctAnswerIndex: Int
)

// Questions and correct answers transcribed from the supplied PDF.
val quizQuestions = listOf(
    Question(
        text = "What is an Independent Variable?",
        answers = listOf(
            "Variable that is Manipulated or Changed",
            "Variable that is Measured or Recorded",
            "Variable that is Random or Uncontrollable",
            "Variable that is Consistent or Constant"
        ),
        correctAnswerIndex = 0
    ),
    Question(
        text = "What is a Dependent Variable",
        answers = listOf(
            "Variable that is Manipulated or Changed",
            "Variable that is Measured or Recorded",
            "Variable that is Random or Uncontrollable",
            "Variable that is Consistent or Constant"
        ),
        correctAnswerIndex = 1
    ),
    Question(
        text = "What is a Random Variable",
        answers = listOf(
            "Variable that is Manipulated or Changed",
            "Variable that is Measured or Recorded",
            "Variable that is Random or Uncontrollable",
            "Variable that is Consistent or Constant"
        ),
        correctAnswerIndex = 2
    ),
    Question(
        text = "What is a Control Variable",
        answers = listOf(
            "Variable that is Manipulated or Changed",
            "Variable that is Measured or Recorded",
            "Variable that is Random or Uncontrollable",
            "Variable that is Consistent or Constant"
        ),
        correctAnswerIndex = 3
    ),
    Question(
        text = "Which of the following is NOT a definition of Research?",
        answers = listOf(
            "Careful or diligent search",
            "Collecting information about a particular subject",
            "The relationship between humans and computers",
            "Investigation or experimentation aimed at the discovery and interpretation of facts, the revision of accepted theories or laws in light of new facts."
        ),
        correctAnswerIndex = 2
    ),
    Question(
        text = "If I said there is 10 cats in my house, that is an example of a",
        answers = listOf(
            "Fact",
            "Theory",
            "Law",
            "Command"
        ),
        correctAnswerIndex = 0
    ),
    Question(
        text = "The point where rank equals the number of citations for a publication is called the",
        answers = listOf(
            "H-index",
            "Scholar Score",
            "Research Index",
            "Research Score"
        ),
        correctAnswerIndex = 0
    ),
    Question(
        text = "What is the process by which another researcher reviews your publication?",
        answers = listOf(
            "Research Scrutiny",
            "Double Checking",
            "Partnership",
            "Peer Review"
        ),
        correctAnswerIndex = 3
    ),
    Question(
        text = "Which of the following was NOT researched before the invention of the iPhone?",
        answers = listOf(
            "Tilt",
            "Multitouch",
            "Flick",
            "Easy Use App Store"
        ),
        correctAnswerIndex = 3
    ),
    Question(
        text = "“Are users’ privacy settings while social networking related to their age, gender, level of education, employment status, income, number of tattoos, etc.” is an example of",
        answers = listOf(
            "Observational Method",
            "Correlation Method",
            "Experimental Method",
            "Collection Method"
        ),
        correctAnswerIndex = 1
    ),
    Question(
        text = "Categories where you can only compare if 2 things are the same or not is an example of",
        answers = listOf(
            "Interval Data",
            "Nominal Data",
            "Ratio Data",
            "Ordinal Data"
        ),
        correctAnswerIndex = 1
    ),
    Question(
        text = "Data where there is a rank and you can compare if 2 things are greater than or less than each other is an example of",
        answers = listOf(
            "Interval Data",
            "Nominal Data",
            "Ratio Data",
            "Ordinal Data"
        ),
        correctAnswerIndex = 3
    ),
    Question(
        text = "Data where there is no absolute values but there is equal distance between adjacent values and you can look at means and differences between values is",
        answers = listOf(
            "Interval Data",
            "Nominal Data",
            "Ratio Data",
            "Ordinal Data"
        ),
        correctAnswerIndex = 0
    ),
    Question(
        text = "Data where there is an absolute value, and you can compare fractions and say things like “X is twice as much as Y” is",
        answers = listOf(
            "Interval Data",
            "Nominal Data",
            "Ratio Data",
            "Ordinal Data"
        ),
        correctAnswerIndex = 2
    ),
    Question(
        text = "Internal Validity can be best described as",
        answers = listOf(
            "The extent to which the effects observed are due to the test conditions",
            "Opening something up and verifying its internals are correct",
            "The extent to which results are generalizable to other people and other situations",
            "Keeping everything inside a building without letting the outside interfere"
        ),
        correctAnswerIndex = 0
    ),
    Question(
        text = "External Validity can be best described as",
        answers = listOf(
            "The extent to which the effects observed are due to the test conditions",
            "The extent to which results are generalizable to other people and other situations",
            "Verifying something is correct by looking it at its surface from the outside",
            "Letting a test go outside instead of keeping it indoors"
        ),
        correctAnswerIndex = 1
    ),
    Question(
        text = "Which of the following is NOT a metaphor for desktop computers?",
        answers = listOf(
            "Trash Can",
            "Physical Desk",
            "Records in a Folder",
            "A Living Mouse running on the floor"
        ),
        correctAnswerIndex = 3
    ),
    Question(
        text = "Which best explains how the mouse movement affects the display cursor?",
        answers = listOf(
            "Mouse +X, Cursor +X. Mouse +Y, Cursor +Y",
            "Mouse +X, Cursor +X. Mouse +Z, Cursor +Y",
            "Mouse +Z, Cursor +X. Mouse +Y, Cursor +Y",
            "Mouse +Z, Cursor +Y. Mouse +X, Cursor -X."
        ),
        correctAnswerIndex = 1
    ),
    Question(
        text = "The Scroll Bar when accessing a document is:",
        answers = listOf(
            "Both a Display and a Control",
            "A Display but not a Control",
            "A Control but not a Display",
            "Neither a Control nor a Display"
        ),
        correctAnswerIndex = 0
    ),
    Question(
        text = "What is the difference between natural and learned control display relationship?",
        answers = listOf(
            "Natural is when it’s nature made, learned is made by humans",
            "Natural is when they both intuitively scale together, learned is when you have to figure out how they move.",
            "Natural is easy for anyone, learned comes easy to smart people",
            "Natural is when you first start using a control, learned is after you finished learning it."
        ),
        correctAnswerIndex = 1
    ),
    Question(
        text = "The delay between an input action and the corresponding response on a delay is called",
        answers = listOf(
            "Press Time",
            "Control Buffer",
            "Misappropriation",
            "Latency"
        ),
        correctAnswerIndex = 3
    ),
    Question(
        text = "The amount of movement in a display for a given amount of movement in a control is called",
        answers = listOf(
            "Input Ratio",
            "Friction",
            "CD Gain",
            "Movement Speed"
        ),
        correctAnswerIndex = 2
    ),
    Question(
        text = "A ________ is a functioning arrangement or condition. An example is the SHIFT key when pressing it, the keyboard keys will produce key variations",
        answers = listOf(
            "Medium",
            "Option",
            "Mode",
            "Selection"
        ),
        correctAnswerIndex = 2
    ),
    Question(
        text = "As an input controller, the most common mouse device is a two ______ input device.",
        answers = listOf(
            "Options",
            "Axis",
            "Dimensional",
            "Degree of Freedom"
        ),
        correctAnswerIndex = 3
    ),
    Question(
        text = "Which of the following is a major type of haptics in HCI?",
        answers = listOf(
            "Kinesthetic",
            "Mechanical",
            "Sensitive",
            "Brightness"
        ),
        correctAnswerIndex = 0
    ),
    Question(
        text = "Which of the following is hardest to implement into HCI?",
        answers = listOf(
            "Sight",
            "Sound",
            "Smell",
            "They are all easy"
        ),
        correctAnswerIndex = 2
    ),
    Question(
        text = "7 +- 2 is important in HCI because",
        answers = listOf(
            "It’s the error rate",
            "It’s the limit for human short term memory",
            "It’s the total number of allowed Inputs",
            "It’s the recommended amount of degrees of freedom"
        ),
        correctAnswerIndex = 1
    ),
    Question(
        text = "Eye movement is sometimes tracked with HCI. This is called",
        answers = listOf(
            "Fixation",
            "Peripherals",
            "Gliding",
            "Saccades"
        ),
        correctAnswerIndex = 3
    ),
    Question(
        text = "Eye tracking in HCI sometimes sees how long the eyes look at one particular thing. This is called",
        answers = listOf(
            "Saccades",
            "Peripherals",
            "Stillness",
            "Fixation"
        ),
        correctAnswerIndex = 3
    ),
    Question(
        text = "In HCI human factors, eyes can be used as",
        answers = listOf(
            "Sensors but not Responders",
            "Both Sensors and Responders",
            "Responders but not Sensors",
            "Neither Sensors or Responders"
        ),
        correctAnswerIndex = 1
    ),
    Question(
        text = "The term we use for the threshold between perceived and unperceived difference in stimuli is",
        answers = listOf(
            "Just noticeable difference",
            "Psychophysics threshold",
            "Stimuli differentiation",
            "Threshold difference"
        ),
        correctAnswerIndex = 0
    ),
    Question(
        text = "Handedness can be measured on a",
        answers = listOf(
            "Scale",
            "Binary (Left or Right)",
            "Meter",
            "It can’t be measured"
        ),
        correctAnswerIndex = 0
    )
)
