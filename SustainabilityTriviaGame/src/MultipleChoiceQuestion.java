
//**************************************************************
//File Name: MultipleChoiceQuestion.java
//
//Purpose: interface to set and get the answers/options 
// for a multiple choice question.
//
//Author: Hunter Heffernan
//**************************************************************
public interface MultipleChoiceQuestion extends Question
{
    String[][] getOptions(); 			 // array for choices available to the user
    
    void setOptions(String[][] options); // sets options available to user
}
