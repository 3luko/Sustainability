

//**************************************************************
//File Name: TrueFalseQuestions.java
//
//Purpose: interface to set and get the answers/options 
//for a true false question.
//
//Author: Hunter Heffernan
//**************************************************************
public interface TrueFalseQuestion extends Question
{
	 public String[] getOptions(); 				// array for choices available to the user
	    
	 public void setOptions(String[] options);  // sets options available to user
}
