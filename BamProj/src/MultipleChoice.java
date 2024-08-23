//**************************************************************
//File Name: MultipleChoiceQuestion.java
//
//Purpose: class to create multiple choice questions, return the
// question, topic, options available to the user, and correct
// answer. As well as set the question and options.
//
//Author: Hunter Heffernan
//**************************************************************

public class MultipleChoice implements MultipleChoiceQuestion
{
	private String mcqTopic;                   // topic of multiple choice question

	private String questionText;			   // the question itself (text of the question)
	
	private String[][] options;  			   // array of options for the user (answers A, B, C, and D)
	
	private String correctAnswer;			   // String  of correct answer 
	
	public MultipleChoice (String mcqTopic, String correctAnswer) // constructor
	{
		this.mcqTopic = mcqTopic;
		this.questionText = "";
		this.correctAnswer = correctAnswer;
		this.options = new String[4][2];
	}
	
	public String getQTopic() // getter method for topic of question
	{
		return mcqTopic;
	}
	
	public String getQuestion() // getter method for question
	{
		return questionText;
	}

	public String[][] getOptions() // getter method for options available to user
	{
		return options;
	}
	
	public String getCorrectAnswer() // getter method for question
	{
		return this.correctAnswer;
	}
	
	public void setQuestion(String questionText) // setter method for question
	{
		this.questionText = questionText;
	}

	public void setOptions(String[][] options) // setter method for options
	{
		this.options = options;
	}
	
	public void setQTopic(String topic)  // setter method for question topic
	{
		this.mcqTopic = topic;
	}
}
