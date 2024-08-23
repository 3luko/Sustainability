# Sustainability Trivia Game 

## Overview

This project is a contest winning Java-based quiz application that allows users to take multiple-choice and true/false quizzes. The application is designed to be flexible and can be easily extended to include more question types or features. The project includes several Java classes that work together to manage quizzes, questions, and user interaction. 

## Features

- **MultipleChoiceQuestion**: A class to handle multiple-choice questions.
- **TrueFalseQuestion**: A class to handle true/false questions.
- **Game**: A class to manage the overall flow of the quiz game.
- **Question**: A parent class that defines the general structure of a question.
- **QuizAppMusic**: A class that provides functionality for background music during the quiz.

## Classes Overview

1. **Question.java**
   - This is the base class for all types of questions. It defines the general structure and properties of a question, such as the question text and the correct answer.

2. **MultipleChoiceQuestion.java**
   - Extends the `Question` class.
   - Adds specific functionality for multiple-choice questions, including options for answers and handling the selection of the correct one.

3. **TrueFalseQuestion.java**
   - Extends the `Question` class.
   - Represents questions with a true/false format.

4. **MultipleChoice.java**
   - A class designed to manage and create multiple-choice questions. It interacts with `MultipleChoiceQuestion` to facilitate the addition and management of these types of questions.

5. **Game.java**
   - Manages the overall flow of the quiz game, including starting the quiz, processing user input, and displaying results.

6. **QuizAppMusic.java**
   - Provides background music functionality to enhance the user experience during the quiz.

## How to Run

1. Compile the Java files using a Java compiler (e.g., `javac`).
2. Run the `Game.java` class, which serves as the entry point for the application.
3. Follow the on-screen instructions to take the quiz.

## Future Enhancements

- Adding more question types like short answer or matching.
- Enhancing the user interface to be more interactive and visually appealing.
- Adding a scoring system with leaderboards.
- Extending the music functionality to allow different tracks for different quiz sections.

## Installation

1. Clone the repository.
   ```bash
   git clone https://github.com/yourusername/your-repo-name.git
