//File Name: TrueFalse.java
//
//Purpose: class to create true/false questions, return the
// question, topic, and correct answer  of the question, set 
// the question and  topic. As well as check if the user 
// answer is correct.
//
//Author: Hunter Heffernan
//**************************************************************

public class TrueFalse implements TrueFalseQuestion {
    private String tfTopic;       // topic of the true/false question
    private String questionText;  // the question itself (text of the question)
    private String[] options;     // array of options for the user (True/False)
    private String correctAnswer; // String of correct answer (true or false)

    public TrueFalse(String tfTopic, String correctAnswer) {
        this.tfTopic = tfTopic;
        this.questionText = "";
        this.correctAnswer = correctAnswer;
        this.options = new String[2]; // Initialize with "True" and "False"
        this.options[0] = "True";
        this.options[1] = "False";
    }

    public String getQTopic() { // getter method for topic of question
        return tfTopic;
    }

    public String getQuestion() { // getter method for question 
        return questionText;
    }

    public String getCorrectAnswer() { // getter method for correct answer
        return correctAnswer;
    }

    public String[] getOptions() {
        return options;
    }

    public void setQuestion(String questionText) { // setter method for question text
        this.questionText = questionText;
    }

    public void setQTopic(String topic) {
        this.tfTopic = topic;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }
}