

//**************************************************************
//File Name: Question.java
//
//Purpose: interface with methods to return topic and
// question, as well as set the topic and question. Also 
// determine if user answer is correct.
//
//Author: Hunter Heffernan
//**************************************************************
public interface Question
{
	public String getQTopic(); 				// returns topic for question
	
    public String getQuestion();  			// returns question
    
    public void setQTopic(String topic); 	// sets topic for question
    
    void setQuestion(String questionText); 	// sets the question
   
    public String getCorrectAnswer(); 		// returns correct answer
}
