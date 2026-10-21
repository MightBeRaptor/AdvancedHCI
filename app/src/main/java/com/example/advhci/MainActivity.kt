
package com.example.advhci
//imports

import android.Manifest
import android.content.pm.PackageManager
import com.example.advhci.speech.AnswerMatcher
import com.example.advhci.speech.SpeechRecognizerManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.advhci.ui.theme.AdvHCITheme
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat


//initialize screen and main entry point
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AdvHCITheme {
                QuizScreen()
            }
        }
    }
}

@Composable
fun QuizScreen() {
    //audio variables
    val context = LocalContext.current
    val speechManager = remember { SpeechRecognizerManager(context) }

    val isListening by speechManager.isListening.collectAsState()
    val spokenText by speechManager.spokenText.collectAsState()
    val errorMessage by speechManager.errorMessage.collectAsState()

    //set up question number, the score, and the selected answer
    var questionIndex by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }
    var selectedAnswer by remember { mutableIntStateOf(-1) }

    //set the number of questions in the game, not the same as total questions in the bank, and then shuffle the questions and get that many of them
    var gameQuestions by remember {
        mutableStateOf(quizQuestions.shuffled().take(15))
    }
    //if the current question is greater than or equal to the number of questions in the game then end the quiz
    val quizFinished = questionIndex >= gameQuestions.size

    // Audio Permission Handling
    var hasAudioPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    // Process Voice Answer When Recognized
    LaunchedEffect(spokenText) {
        if (spokenText.isNotBlank() && selectedAnswer == -1 && !quizFinished) {
            val currentQuestion = gameQuestions[questionIndex]
            val matchedIndex = AnswerMatcher.resolveSpokenAnswer(spokenText, currentQuestion.answers)

            if (matchedIndex != null) {
                selectedAnswer = matchedIndex
                if (matchedIndex == currentQuestion.correctAnswerIndex) {
                    score++
                }
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            speechManager.destroy()
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasAudioPermission = isGranted
        if (isGranted) {
            speechManager.startListening()
        }
    }

    //column setup so it's all vertical
    Column(
        modifier = Modifier
            //column fills max size so the width of the whole column fills all screen space
            .fillMaxSize()
            //prevents the buttons and text from reaching the ends of the screen
            .padding(24.dp),
        //centered vertically
        verticalArrangement = Arrangement.Center,
        //centered horizontally
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //check if the quiz is done
        if (quizFinished) {
            Text(
                //tell the user the quiz is finished
                text = "Quiz Complete!",
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(16.dp))
            //tell the user their final score, which is correct answers/total answers
            Text("Final Score: $score / ${gameQuestions.size}")

            //spacer adds gaps between the elements
            Spacer(modifier = Modifier.height(24.dp))

            //when play again is clicked
            Button(onClick = {
                //get 15 new questions, and then reset the index and score
                gameQuestions = quizQuestions.shuffled().take(15)
                questionIndex = 0
                score = 0
                //intentionally set to an invalid number so the game doesn't think you answered a question on accident
                selectedAnswer = -1
            }) {
                Text("Play Again")
            }

        } else {
            //get the question data
            val question = gameQuestions[questionIndex]

            Text(
                text = "Question ${questionIndex + 1} of ${gameQuestions.size}",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = question.text,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(24.dp))

            question.answers.forEachIndexed { index, answer ->
                val buttonColors = listOf(
                    Color(0xFFD63B43), // A - Red
                    Color(0xFF2675C8), // B - Blue
                    Color(0xFFD49A16), // C - Yellow
                    Color(0xFF278850)  // D - Green
                )
                Button(

                    onClick = {
                        //make sure there's no answer already selected
                        if (selectedAnswer == -1) {
                            selectedAnswer = index
                            //check that the answer is correct
                            if (index == question.correctAnswerIndex) {
                                score++
                            }
                        }
                    },
                    //if there's no answer selected, the buttons become enabled, otherwise it becomes disabled
                    enabled = selectedAnswer == -1,
                    //change color of button to what the index says and the text to white
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColors[index],
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text("${'A' + index}. $answer")
                }
            }

            if (selectedAnswer == -1) {
                Button(
                    onClick = {
                        if (hasAudioPermission) {
                            if (isListening) speechManager.stopListening() else speechManager.startListening()
                        } else {
                            permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isListening) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text(if (isListening) "Listening... (Tap to Cancel)" else "🎙️ Speak Answer")
                }

                if (spokenText.isNotBlank()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Heard: \"$spokenText\"",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                errorMessage?.let { error ->
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            //if there's a selected answer
            if (selectedAnswer != -1) {
                Spacer(modifier = Modifier.height(16.dp))

                //if the answer is correct or incorrect
                if (selectedAnswer == question.correctAnswerIndex) {
                    Text("Correct!")
                } else {
                    Text("Incorrect! The correct answer is ${question.answers[question.correctAnswerIndex]}")
                }

                Spacer(modifier = Modifier.height(16.dp))

                //when the next button is clicked, it increases the question number and resets the current selection back to -1
                Button(onClick = {
                    questionIndex++
                    selectedAnswer = -1
                }) {
                    Text("Next Question")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            //display the current score, which is number of correct answers
            Text("Correct Answers: $score")
        }
    }
}
