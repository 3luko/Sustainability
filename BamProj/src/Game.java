//**************************************************************
//File Name: Game.java
//
//Purpose: Trivia game.
//
//Author: Hunter, Ethan, Ibrahima, Alejandro
//**************************************************************
import javafx.scene.control.Alert.AlertType;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends Application 
{
    private String[] qTopics, mcqAnswers, tfAnswers, mcqQuestions, tfQuestions, tfOptions, userAnswers; //instance data for the class
    private String[][] mcqOptions;
    private boolean mcqGame, tfGame;
    private int currentQuestionIndex, score = 10;
    private MultipleChoice[] mcqs;
    private TrueFalse[] tfs;
    private final int MINQ = 0, MAXQ = 10;
    private Text questionNum, topic, question, userScore, userScoreNum, thanks;
    private Button submitButton, next, prev, startMcqButton, startTfButton, closeGame;
    private RadioButton mcqA1, mcqA2, mcqA3, mcqA4, tfA1, tfA2;
    private ToggleGroup group;
    private Stage primaryStage;
    private Image image;
    private ImageView imageView;
    private Timer timer;


    public static void main(String[] args) 
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) //start method
    {   	
        this.primaryStage = primaryStage;
        initializeStartScreen();
        primaryStage.setTitle("Sustainability Trivia Game");
        primaryStage.show();
    }
    
    private void initializeStartScreen() //method for the start screen
    {
        mcqGame = false; //setting all the values to their default values, in case user chose to restart
        tfGame = false;
        currentQuestionIndex = 0;
        score = 0;
        
        VBox startLayout = new VBox(); //creating VBox object
        startLayout.setAlignment(Pos.CENTER);
        startLayout.setSpacing(20);

        Text startText = new Text("Choose a Question Type:"); //prompting user to choose either 
        startText.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20));

        startMcqButton = new Button("Multiple Choice"); //initializing button for multiple choice questions.
        startMcqButton.setOnAction(this::startGameMultipleChoice); //if button is clicked it will call the startGameMultipleChoice 
        startMcqButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;");
        
        startTfButton = new Button("True/False"); //initializing a button for true or false questions
        startTfButton.setOnAction(this::startGameTrueFalse); //if button is clicked it will call the startTrueFalse method
        startTfButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;");
        
        startLayout.getChildren().addAll(startText, startMcqButton, startTfButton); //adding the text, start button for mcq, and start button for true or false
        startLayout.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, null)));

        Scene scene = new Scene(startLayout, 600, 400);
        primaryStage.setTitle("Sustainability Trivia Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void startGameMultipleChoice(ActionEvent e) //method for starting the mcq questions
    {
        mcqGame = true; // sets the boolean to true
        initializeMcqGame(); // calls the initializeMcqGame method
        startGame(); // calls the startGame method
    }

    public void startGameTrueFalse(ActionEvent e)  //method for if you select startGameTrueFalse
    {
        tfGame = true; // sets the boolean to true
        initializeTfGame(); ;// calls the initializeTfGame method
        startGame(); // calls the startGame();
    }
    
    private void startGame() //method for starting the game based on whether the user selected mcq or tf
    {
    	topic = new Text("Topic: " + qTopics[currentQuestionIndex]); //Topic at index
    	question = new Text();
    	questionNum = new Text("Question " + (currentQuestionIndex + 1) + " of " + (MAXQ + 1)); //question number at specified index
    	userAnswers = new String[qTopics.length];
    	
        initializeRadioButtons(); //calls the initializeRadioButtons() method to show the radio buttons
        
        VBox gameLayout = new VBox(); //creating VBox object
        gameLayout.setSpacing(80);

        HBox hbox = new HBox(); //creating a HBox object
        hbox.getChildren().addAll(topic, questionNum);
        hbox.setSpacing(150);

        VBox optionsRadio;
        
        if (mcqGame)  //if mcqGame is true the radio buttons will for the mcq
        {
            optionsRadio = new VBox(mcqA1, mcqA2, mcqA3, mcqA4);
        } 
        else //if (tfGame)
        {
            optionsRadio = new VBox(tfA1, tfA2);
        }

        optionsRadio.setAlignment(Pos.CENTER_LEFT);

        HBox subButtons = new HBox();//creating a HBox object

        prev = new Button("Previous Question"); //Button for previous question
        prev.setOnAction(this::processPrevQuestion); //on action it will call the processPrevQuestion method

        submitButton = new Button("Submit"); //button for the submit button
        submitButton.setOnAction(this::processSubmit);//on action it will call the processSubmit method

        next = new Button("Next Question");//button for the next question 
        next.setOnAction(this::processNextQuestion);//on action it will call the processNextQuestion method

        subButtons.getChildren().addAll(prev, submitButton, next); // adds the buttons for prev, submit, and next into a HBox object
        subButtons.setSpacing(170);

        gameLayout.getChildren().addAll(hbox, question, optionsRadio, subButtons); //places all the objects into a large VBox
        gameLayout.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, null)));
        
        Group root = new Group(gameLayout);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Sustainability Trivia Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeRadioButtons() 
    {
        group = new ToggleGroup();

        if (mcqGame)
        {
            mcqA1 = new RadioButton("A: " + mcqOptions[currentQuestionIndex][0]);
            mcqA2 = new RadioButton("B: " + mcqOptions[currentQuestionIndex][1]);
            mcqA3 = new RadioButton("C: " + mcqOptions[currentQuestionIndex][2]);
            mcqA4 = new RadioButton("D: " + mcqOptions[currentQuestionIndex][3]);
            
            mcqA1.setToggleGroup(group);
            mcqA2.setToggleGroup(group);
            mcqA3.setToggleGroup(group);
            mcqA4.setToggleGroup(group);
            
            question.setText(mcqQuestions[0]);
        } 
        else if(tfGame) 
        {
            tfA1 = new RadioButton(tfOptions[0]);
            tfA2 = new RadioButton(tfOptions[1]);

            tfA1.setToggleGroup(group);
            tfA2.setToggleGroup(group);
            
            question.setText(tfQuestions[0]);
        }
    }
    
    public void processSubmit(ActionEvent actionEvent) //method for submit button
    {
        processRadioButton(); // calls the processRadioButton
        compareAnswers(); // calls the compareAnswers method
        displayResults(); // calls displayResults method

        submitButton.setDisable(true); //button will be set to disabled
    }
    
    private void processRadioButton() //method handling the selection of a radio button
    {
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();//get the currently selected radio button from the toggle group

        if (selectedRadioButton != null)//if radio button is selected
        {
            String selectedAnswer = selectedRadioButton.getText();//get the text (label) of the selected radio button
            
            if (mcqGame) //check if the current game is multiple choice or not
            {
                userAnswers[currentQuestionIndex] = selectedAnswer.substring(0, 1);//store first character of the selected answer in the user's answers array 
            } 
            else 
            {
                userAnswers[currentQuestionIndex] = selectedAnswer;
            }
        }
    }
    

    private void compareAnswers() 
    {
        String userAnswer = userAnswers[currentQuestionIndex];//get the user's answer for the current quesiton
        String correctAnswer = null;//initialize the correct answer to null

        if (mcqGame) //check if the game is either multiple choice or tf
        {
            correctAnswer = mcqs[currentQuestionIndex].getCorrectAnswer();//get the correct answer from the true/false questions array
        } 
        else if (tfGame) 
        {
        	correctAnswer = tfs[currentQuestionIndex].getCorrectAnswer();
        }  
                

        Alert alert = new Alert(AlertType.INFORMATION);//create an alert dialog for displaying the result
        alert.setTitle("Result");
        alert.setHeaderText(null);

        if (userAnswer != null && userAnswer.equals(correctAnswer))//if the user's answer is not null and equals the correct answer
        {
            score++; //increments
            alert.setContentText("Correct! Your current score is: " + score);//alerts that it's correct and shows current score
        } 
        else 
        {
            alert.setContentText("Incorrect. The correct answer is: " + correctAnswer + "\nYour current score is: " + score);//alerts that it was incorrect and shows current score
        }

        alert.showAndWait(); // display alert dialog and wait for user interaction
    }

    public void processNextQuestion(ActionEvent actionEvent) //method for moving to the next question in the game
    {
        if (currentQuestionIndex < MAXQ) //if the index is less than the MAXQ which is 11
        {
            currentQuestionIndex++; //increments index
            
            updateRadioButtons(); //calls the updateRadioButtons() method
            
            topic.setText("Topic: " + qTopics[currentQuestionIndex]); //sets the text of the topic label to display current questions topic
            questionNum.setText("Question " + (currentQuestionIndex + 1) + " of 11");

            submitButton.setDisable(false);//enable the submit button for new question
        } 
        else 
        {
            next.setDisable(true);//disable the next button
            scoreBoard(); //call the method for score board only if the index is higher than 11
             
        }
    }

    public void processPrevQuestion(ActionEvent actionEvent) //method for processing the previous question
    {
        if (currentQuestionIndex > MINQ) //if index is less greater than minimum which is 0 it will decrement
        {
            currentQuestionIndex--;//decrement
            
            updateRadioButtons(); //update radio buttons with new questions

            topic.setText("Topic: " + qTopics[currentQuestionIndex]);//set text of the topic to display current question
            questionNum.setText("Question " + (currentQuestionIndex + 1) + " of 11");
        }
        
        submitButton.setDisable(true);//disable the submit button for the previous question
    }
    
    
    private void updateRadioButtons() //method for updating the radio buttons
    {
    	if (mcqGame) //if mcq is true its will update multiple choice options
        {
            mcqA1.setText("A: " + mcqOptions[currentQuestionIndex][0]);
            mcqA2.setText("B: " + mcqOptions[currentQuestionIndex][1]);
            mcqA3.setText("C: " + mcqOptions[currentQuestionIndex][2]);
            mcqA4.setText("D: " + mcqOptions[currentQuestionIndex][3]);
            
            question.setText(mcqQuestions[currentQuestionIndex]); //set the text of the question label to display the current mcq question
        } 
        else if (tfGame) //if tf is true it will update multiple choice options
        {
            question.setText(tfQuestions[currentQuestionIndex]);
        }
        
        group.selectToggle(null);//clear the selection of the radio buttons (ToggleGroup)
    }    
    
    private void displayResults() //method for displaying the game over a message
    {
    	if (currentQuestionIndex == MAXQ)
    	{
    		Alert alert = new Alert(AlertType.INFORMATION); //creating a Alert object
            alert.setTitle("Game Over");
            alert.setHeaderText(null);
            alert.setContentText("Game Over! Your final score is: " + score); //showing the score
            alert.showAndWait();
    	}
    }
    
    private void initializeMcqGame()//method for initializing the mcq game 
    {
    	 
    	currentQuestionIndex = 0; //setting index to  0
    	score = 0; //setting score to 0
    	
		qTopics = new String[] //initializing a String array for topics
    			{"Sustainability Leadership Program", "Energy", "Pollinator Path",
				 "Bike", "Food", "Water", "Academics/Research", "Reuse", "Recycling",
				 "Organics Recycling", "Carbon Neutrality"};
    	
    	mcqAnswers = new String[] //initializing a String array for answers
    			{"D", "D", "B", "D", "D", "C", "D", "D", "B", "C", "B"};
    	
    	mcqQuestions = new String[]//initialing a String array for mcq questions
    			{"Which of the following is true about the Student Sustainability Leadership Program (SSLP)?",
				 "Which of the following is NOT a way to conserve energy?",
				 "What is the name of the series of gardens around campus which attract pollinators and activity on campus?",
				 "Where are the bike repair stations located on campus?",
				 "What are some ways to reduce food waste?",
				 "Which of the following is NOT a way to conserve water?",
				 "The Sustainable Communities Partnership (SCP) collaborates with local and regional government, non-profit, and campus "
				 + "partners to integrate sustainability projects important to communities into St. Thomas courses across disciplines, "
				 + "engaging students in real-world, applied learning and creative problem-solving. Which of the following majors have had "
				 + "courses taht have included an SCP project with community partners?",
				 "Dining Services reduces waste by...",
				 "Recycling bins on campus are...",
				 "All of the following itemss are accepted for organics recycling. EXCEPT",
				 "The University of St. Thomas has a goal to reach carbon neutrality by which year?"};

    	mcqOptions = new String [][] //initializing two dimensional array for mcq options
    			{
    			{"It is a student employement opportunity designed to prepare the next generation of environment leaders.",
    			"It provides hands-on experience with sustainability on campus.",
    			"It increases student sustainability knowledge and action through peer-education.",
    			"All of the above."}, 
		
    			{"Unplugging any devices when not in use.",
    			"Turning off lights when leaving the room.",
    			"Keeping windows closed to retain head indoors in the winter.",
    			"Washing laundry in small loads instead of full loads."},
		
    			{"Bee Garden",
    		   	 "Pollinator Path",
    			 "Butterfly Garden",
    			 "Bee Path"},
		
    			{"Near the bike racks on the south side of O'Shaughnessy Stadium.",
    			 "Inside the secure bike storaage in Frey Residence Hall.", 
    			 "Inside the secure bike storage in Tommie North Residence Hall.", 
    			 "All of the above"},
		
    			{"Store produce properly.", 
    			 "Prepare meals ahead of time.",
    			 "Purchase only what you need.",
    			 "All of these are ways to reduce food waste."},
		
    			{"Eating more plant-based meals.",
    		     "Taking shorter showers.", 
    		     "Leaving the sink on while brushing your teeth",
    			 "Only washing full loads of laundry"},
		
    			{"Marketing",
    			 "Engineering",
    			 "Theology",
    			 "All of the above and more"},
		
    			{"Safely recovering leftover food to be donated.",
    			 "Offering reusable to-go containers for purchase in T's.",
    			 "Offering a discount for bringing your own reusable cup to campus cofee shops.",
    			 "All of the above."},
		
    			{"Grey",
    			 "Blue",
    			 "Green",
    			 "Purple"},
		
    			{"All food scraps",
    			 "Napkins & paper towels",
    			 "Any paper cups",
    			 "Plant & flower trimmings"},
		
    			{"2025",
    			 "2035",
    			 "2045",
    			 "2055"}
    			 };
	    
	     mcqs = new MultipleChoice[qTopics.length]; //initializing an array from the MultipleChoice Class

	     for (int i = 0; i < qTopics.length; i++) //adding data to the array using a for loop
	     {
	    	 mcqs[i] = new MultipleChoice(qTopics[i], mcqAnswers[i]);
	    	 mcqs[i].setQuestion(mcqQuestions[i]);
	    	 mcqs[i].setOptions(mcqOptions);
	     }
    }
    
    
    private void initializeTfGame() //method for initializing the tf game
    {
    	currentQuestionIndex = 0; //setting index to zero
    	score = 0;//setting score to zero
    	
		qTopics = new String[] //initializing a String array for topics
    			{"Sustainability Leadership Program", "Energy", "Pollinator Path",
				 "Bike", "Food", "Water", "Academics/Research", "Reuse", "Recycling",
				 "Organics Recycling", "Carbon Neutrality"};
		
    	tfAnswers = new String[]//initializing String array for tf answers
    			{"False", "True", "True", "True", "True", "True", "True", "True", "False", "True", "True"};
    	
    	tfQuestions = new String[]//initializing String array for tf questions
    			{"The Student Sustainability Leadership Program (SSLP) is only for students with environmental majors.",
    		     "Keeping blinds closed during the hottest parts of the day limit excess heat in the summer is a way to conserve energy. ", 
    		     "The Pollinator Path isa series of gardens around campus which attract pollinators and support the study of pollinator "
    		     + "activity on campus.", 
    		     "A bike repair station is located between ASC and O'Shaughnessy Stadium.", 
    		     "You get 25 cents off for bringing a reusable cup at campus coffee shops.", 
    		     "The strom drains located on the streets on and around campus drain to the Mississippi River.",
    		     "St. Thomas offers a sustainability minor.", 
    		     "Tommie's Closet allows students to shop donated clothes for free on campus.", 
    		     "At St. Thomas, we can recycle all types of plastic, metal, glass, and paper.", 
    		     "At St. Thomas, items that are accepted for organics recycling are collected and brought to a commercial facility that "
    		     + "turns the material into compost that can be used in gardens and lawns.", 
    		     "The University of St. Thomas has a goal to reach carbon neutrality by 2035.",
    		     };
    	
    	tfOptions = new String[] {"True", "False"}; //initializing options to be True or false
    	
    	tfs = new TrueFalse[qTopics.length]; //initializing an array from the TrueFalse class
    	
    	for (int i = 0; i < qTopics.length; i++) // for loop for string data into tfs array
    	{
    	    tfs[i] = new TrueFalse(qTopics[i], tfAnswers[i]);
    	    tfs[i].setQuestion(tfQuestions[i]);
    	    tfs[i].setOptions(tfOptions);  // Ensure tfOptions is a String array defined earlier
    	}
    }
    

    public void scoreBoard() //method to show the score board at the end of the game
    { 
    	image = new Image("StTomImage.jpg");
    	imageView = new ImageView(image);
    	imageView.setFitHeight(50);
    	imageView.setFitWidth(250);
    	
    	String scoreNum = Integer.toString(score); //changes the int to a string and stores it into a string value
    	userScore = new Text("Your Score!");
    	userScoreNum = new Text(scoreNum + "/11");
    	thanks = new Text("Thanks for playing would you like to play again?");
    	
    	Button restart = new Button("Restart"); //creating a button for restarting the quiz
    	restart.setOnAction(this::restartGame); //calling the setOnAction method for when the button is clicked it will do the restart method
    	closeGame = new Button("Done"); //initializing the closeGame button.
    	closeGame.setOnAction(this::closeButton); //calling the setOnAction method which will call the closeButton method for closing the window
    	
    	if(score < 8.8) //if the score of the user is above 80% it will be green otherwise the score will display as red
    	{
    		userScoreNum.setFill(Color.RED);
    	}
    	else 
    	{
    		userScoreNum.setFill(Color.GREEN);
    	}
    	
    	HBox buttonPlace = new HBox(); //creating an HBox object
    	buttonPlace.setSpacing(45);
    	buttonPlace.setAlignment(Pos.CENTER);
    	buttonPlace.getChildren().addAll(restart, closeGame);//storing both buttons in the HBox object
    	VBox testSCORE = new VBox(); //creating a VBox object
    	testSCORE.setAlignment(Pos.TOP_CENTER);
    	testSCORE.setSpacing(40);
    	userScore.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20)); //setting the font for user score and thank you message
    	thanks.setFont(Font.font("Arial", 20));
    	
    	testSCORE.getChildren().addAll(imageView, userScore, userScoreNum, thanks, buttonPlace); //putting the message, user score, thank you message and buttons for restarting and closing in the VBox
    	
    	Scene scene = new Scene(testSCORE, 600, 400);
    	primaryStage.setTitle("Sustainability Trivia Game");
    	primaryStage.setScene(scene);
    	primaryStage.show(); 	 	
    }
    
    public void closeButton(ActionEvent event) // method for closing program
    {
    	Stage stage = (Stage) closeGame.getScene().getWindow();
    	stage.close();
    }
    
    public void restartGame(ActionEvent event) // method for restarting the game
    {
    	initializeStartScreen();//calling the initializeStartScreen method
    }
    
    private void timer() {
    	timer = new Timer();
    	
    	
    }
}