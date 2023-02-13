/**
The Mathefy program is an app that allows young kidos learning addition, subtraction, multiplication and division to
practice their maths skills.
Upon launch, the app displays a welcome screen.
Clicking the get started button brings you to a separate screen with options, a text entry area, and a check answer button.
Sounds play if the anser is correct or not.
Note: You can not move on to multiplication or division unless progress is above or equal to 80.
If your answer is incorrect or correct the progress area will decrease/increase by 5;
More functionality will be added later.
This is just the beginning.
@author Tyion Lashley
@version 1.0
*/


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage; 
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane; 
import javafx.geometry.Insets;

public class Mathefy extends Application
	
{


// Field variables
	
	private Label problemLabel;
	
	private Label progressLabel = new Label("");
	
	private TextField text = new TextField();
	
private int num1;

private int num2;

private int answer;

private int textAnswer;

private int progress = 0;

	public static void main(String[] args)
		
	{
		
		launch(args);
		
	}


/**
	This is Overriding start method in program.
	@param stage - used for setting, and showing scene
	*/
	
	
	@Override
	
	public void start(Stage stage)
		
		{
	
	// Creating welcome screen
			
			Label lab = new Label("Welcome to mathefy.\n Mathefy is an app created by Tyion Lashley that helps young mathmatitions work on their math skills.\n Click the gGet Started button to begin.\nRemember to have fun.\nNote: the multiply and division buttons won't be able to be acccessed unless progress is above or equal to 80.");
			
			Button but = new Button("Get Started");
			
			but.setOnAction(event ->
				
				{
	
					/// This is where the second scene with all the options, textfield, and progress and problem labels will be created
					
					problemLabel = new Label();
					
					Button addition = new Button("Addition");
					
					addition.setOnAction(additionHandler ->
						
						{
							
num1 = MathStore.generateRandomNum();

num2 = MathStore.generateRandomNum();

answer = num1 + num2;

problemLabel.setText(MathStore.showAddition(num1, num2));

addition.setText("Get new addition problem");

text.setText("");

progressLabel.setText("Current progress: " + progress);

						});
						
					Button subtraction = new Button("Subtraction");
					
					subtraction.setOnAction(subtractionHandler ->
						
						{
							
							num1 = MathStore.generateRandomNum();
							
							num2 = MathStore.generateRandomNum();
							
							answer = num1 - num2;
							
							problemLabel.setText(MathStore.showSubtraction(num1, num2));
						
						subtraction.setText("Get new subtraction problem");
						
						text.setText("");

progressLabel.setText("Current progress: " + progress);
						
						});
						
					Button multiplication = new Button("Multiplication");
					
					multiplication.setOnAction(multiplicationHandler ->
						
						{
							if (checkProgress())
								
							{
								
							num1 = MathStore.generateRandomNum();
							
							num2 = MathStore.generateRandomNum();
							
							answer = num1 * num2;
							
							problemLabel.setText(MathStore.showMultiplication(num1, num2));
						
						text.setText("");

progressLabel.setText("Current progress: " + progress);
						
						}
						
						else
							
						{
							
							progressLabel.setText("You can't move on unless your progress is above or equal to 80.\nCurrent progress: " + progress);
							
						}
						
						});
						
					Button division = new Button("Division");
					
					division.setOnAction(divisionHandler ->
						
						{
							if (checkProgress())
								
							{
								
							num1 = MathStore.generateRandomNum();
							
							num2 = MathStore.generateRandomNum();
							
							answer = num1 / num2;
							
							problemLabel.setText(MathStore.showDivision(num1, num2));
			
							text.setText("");

progressLabel.setText("Current progress: " + progress);							
						}
						
						else
							
						{
							
							progressLabel.setText("You can't move on unless your progress is above or equal to 80.\nCurrent progress: " + progress);
							
						}
						
						});
												
											Button checkAnswer = new Button("Check Answer);");
											
					checkAnswer.setOnAction(checkAnswerHandler ->
						
						{
							
							if (isCorrect())
								
							{

playSoundUsing("MathefyDing.mp3");
								
								progress += 5;
								
								progressLabel.setText("Awesome! Your answer " + answer + ", was correct\n Click get new ( ) problem to generate another problem.\nCurrent progress: " + progress);
								
							}
			
							else
								
							{

playSoundUsing("MathefyBuzz.mp3");

progress -= 5;

								progressLabel.setText("Incorrect answer.\nCurrent progress:" + progress);
									
							}

						});
						
VBox problemBox = new VBox(10, problemLabel, text);

problemBox.setAlignment(Pos.CENTER);

problemBox.setPadding(new Insets(10));

					VBox optionBox = new VBox(10, progressLabel, addition, subtraction, multiplication, division);
					
					optionBox.setAlignment(Pos.CENTER_LEFT);
					
					optionBox.setPadding(new Insets(10));
					
					HBox checkAndGetAnswerButtons = new HBox(10, checkAnswer);
					
					checkAndGetAnswerButtons.setAlignment(Pos.CENTER);
					
					checkAndGetAnswerButtons.setPadding(new Insets(10));
					
BorderPane pane = new BorderPane();

pane.setLeft(optionBox);

pane.setCenter(problemBox);

pane.setBottom(checkAndGetAnswerButtons);

					Scene innerScene = new Scene(pane, 500, 300);
					
					innerScene.getStylesheets().add("MathefyStylesheet.css");
					
					stage.setScene(innerScene);
					
					stage.setFullScreen(true);
				});
				
			VBox vb = new VBox(10, lab, but);
			
			vb.setAlignment(Pos.CENTER);
			
			vb.setPadding(new Insets(10));
			
			Scene sc = new Scene(vb, 500, 300);
			
			sc.getStylesheets().add("MathefyStylesheet.css");
			
			stage.setScene(sc);
					
			stage.setFullScreen(true);
			
			stage.show();
			
			Media backgroundTrack = new Media(Mathefy.class.getResource("MathefyBackgroundTrack.mp3").toString());
			
			MediaPlayer player = new MediaPlayer(backgroundTrack);
			
			player.setCycleCount(MediaPlayer.INDEFINITE);
			
			player.play();
			 
	}
	
	
	/**
		The method isCorrect checks if the answer to a problem is correct.
		@return true/false if answer is right or wrong.
		*/
		
	private boolean isCorrect()
		
	{
		
		textAnswer = Integer.parseInt(text.getText());
		
		if (textAnswer == answer)
			
		{
	
return true;

		}
		
		else 
			
		{
			
			return false;
			
		}
		
	}
	
	/**
		The checkProgress checks if the users progress is above or equal to 80 or higher.
		@return true/false if progress is equal to 80, greater than 80, or not
		*/
		
	private boolean checkProgress()
		
	{
		
		if (progress >= 80)
			
		{
			
			return true;
			
		}
		
		else
			
		{
			
			return false;
			
		}

}

/**
The playSoundUsing method plays a given file passed to its parameters
@param fileName the actual file being passed
*/

		private void playSoundUsing(String fileName)
			
		{
			
			Media media = new Media(Mathefy.class.getResource(fileName).toString());
			
			MediaPlayer player = new MediaPlayer(media);

player.play();
			
		}
		
}