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
@version 6.0
*/

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.FileChooser; 
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane; 
import javafx.geometry.Insets;
import javafx.scene.input.KeyCode;

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

private Media backgroundTrack = new Media(Mathefy.class.getResource("MathefyBackgroundTrack.mp3").toString());

private MediaPlayer player = new MediaPlayer(backgroundTrack);

private boolean isMute;


// The following objects are declared globally for easy access

private Button addition;

private Button subtraction;

private Button multiplication;

private Button division;

private Button checkAnswer;

private Button save;

private Scene innerScene;

	public static void main(String[] args)
		
	{
		
		launch(args);
		
	}


/**
	This method  Overrides start method in program.
	@param stage - used for setting, and showing scene
	*/
	
	
	@Override
	
	public void start(Stage stage)
		
		{
			
// Handling music playback on separate thread

new Thread(() ->
	
	{
		
		player.setCycleCount(MediaPlayer.INDEFINITE);
		
		player.play();
		
	}).start();
		
	// This button stopps the background audio track from playing if the user wishes
	
	Button playing = new Button("Pause/Play Background Audio");
	
	playing.setOnAction(playingHandler ->
		
		{
			
toggleMute();
					
		});
		
	// Creating welcome screen
			
			Label lab = new Label("Welcome to mathefy.\n Mathefy is an app created by Tyion Lashley that helps young mathmatitions work on their math skills.\n Click the gGet Started button to begin.\nRemember to have fun.\nNote: the multiply and division buttons won't be able to be acccessed unless progress is above or equal to 80.");
			
			Button but = new Button("Get Started");
			
			but.setOnAction(event ->
				
				{

	
					// This is where the second scene with all the options, textfield, and progress and problem labels will be created
					
// and a button to save answers to a file
					
					problemLabel = new Label();
					
					progressLabel.setText("Click the options button to get levels. You can not access multiplication or division unless progress is above or equal to 80.");
					
					addition = new Button("Addition");
					addition.setAccessibleText("Addition");
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
						
					subtraction = new Button("Subtraction");
					subtraction.setAccessibleText("Subtraction");
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
						
					multiplication = new Button("Multiplication");
multiplication.setAccessibleText("Multiplication");					
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
						
					division = new Button("Division");
division.setAccessibleText("Division");					
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


// Putting the addition, subtraction, multiplication, and division buttons into a Menu

	MenuButton button = new MenuButton("Options");
	button.setAccessibleText("Options");
	
	MenuItem i1 = new MenuItem("addition");

i1.setOnAction(i1Handler ->
	
	{
	
	addition.fire();

});

	MenuItem i2 = new MenuItem("subtraction");
	
	i2.setOnAction(i2Handler ->
		
		{
			
			subtraction.fire();
			
		});
				
	MenuItem i3 = new MenuItem("multiplication");
	
	i3.setOnAction(i3Handler ->
		
		{
			
			multiplication.fire();
			
		});
		
	MenuItem i4 = new MenuItem("division");
	
	i4.setOnAction(i4Handler ->
		
		{
			
			division.fire();
			
		});
		
	button.getItems().addAll(i1, i2, i3, i4);
	
											checkAnswer = new Button("Check Answer);");
																				
					checkAnswer.setOnAction(checkAnswerHandler ->
						
						{
							
							if (isCorrect())
								
							{
				
				MathStore.addToList(problemLabel.getText() + " = " + answer);
									
playSoundUsing("MathefyDing.mp3");

text.setText("");
								
								progress += 10;
								
								progressLabel.setText("Awesome! Your answer " + answer + ", was correct\n Click the options button followed by the level you were working on to get a new problem.\nCurrent progress: " + progress);
								
							}
			
							else
								
							{

playSoundUsing("MathefyBuzz.mp3");

progress -= 10;

								progressLabel.setText("Incorrect answer.\nCurrent progress:" + progress);
									
							}

						});

save = new Button("Save Answer's to File");

save.setOnAction(saveHandler ->
	
	{
		
		FileChooser chooser = new FileChooser();
		
		chooser.setTitle("Save File");
		
		chooser.setInitialDirectory(new File(System.getProperty("user.home")));
		
		File file = chooser.showSaveDialog(stage);
			
		if (file != null)
			
		{
			
			new Thread(() ->
				
				{
					
					try 
						
					{
						
						if (!file.exists())
							
						{
							
							file.createNewFile();
												
						}
						
						FileWriter writer = new FileWriter(file);
						
						writer.write(MathStore.printContents()); 
						
						writer.close();
						
					}
					
					catch(IOException e)
						
					{
						
						e.printStackTrace();
						
					}
					
				}).start();
				
		}
		
	});
	
VBox problemBox = new VBox(10, problemLabel, text);

problemBox.setAlignment(Pos.CENTER);

problemBox.setPadding(new Insets(10));

					VBox optionBox = new VBox(10, progressLabel, button);
					
					optionBox.setAlignment(Pos.CENTER_LEFT);
					
					optionBox.setPadding(new Insets(10));
					
					HBox checkAndSaveAnswerButtons = new HBox(10, playing, checkAnswer, save);
					
					checkAndSaveAnswerButtons.setAlignment(Pos.CENTER);
					
					checkAndSaveAnswerButtons.setPadding(new Insets(10));
					
BorderPane pane = new BorderPane();

pane.setLeft(optionBox);

pane.setCenter(problemBox);

pane.setBottom(checkAndSaveAnswerButtons);

					innerScene = new Scene(pane, 500, 300);
					
					handleKeyboardCommands(innerScene);
					
					innerScene.getStylesheets().add("MathefyStylesheet.css");
					
					stage.setScene(innerScene);
					
					stage.setFullScreen(true);
				});
				
			VBox vb = new VBox(10, lab, but, playing);
			
			vb.setAlignment(Pos.CENTER);
			
			vb.setPadding(new Insets(10));
			
			Scene sc = new Scene(vb, 500, 300);
			
			sc.getStylesheets().add("MathefyStylesheet.css");
			
			stage.setScene(sc);
					
			stage.setFullScreen(true);
			
			stage.show();
						 
	}

/**
	We don't want music playing if the app isn't running,
	so I'm overriding the stop method to stop music from playing if MediaPlayer object is not  null.
	*/
	
	@Override 
	
	public void stop()
		
		{
			
			if (player != null)
				
			{
				
				player.stop();
			}
					
				}
							
							
				/**
					The handleKeyboardCommands method hanldes events for commands
					@param scene - the scene in which the commands will be handled for
					*/
					
					private void handleKeyboardCommands(Scene scene)
						
				{
					
					scene.setOnKeyReleased(event ->
						
						{
							
							if (event.isControlDown() && event.getCode() == KeyCode.C)
								
							{
								
								checkAnswer.fire();
								
							}
							
							if (event.isControlDown() && event.getCode() == KeyCode.A)
								
							{
								
								addition.fire();
								
							}
							
							if (event.isControlDown() && event.getCode() == KeyCode.S)
								
							{
								
								subtraction.fire();
								
							}
							
							if (event.isControlDown() && event.getCode() == KeyCode.D)
								
							{
								
								division.fire();
								
								
							}
							
							if (event.isControlDown() && event.getCode() == KeyCode.F)
								
							{
								
								save.fire();
								
							}
							
							if (event.isControlDown() && event.getCode() == KeyCode.M)
								
							{
								
								multiplication.fire();
								
							}
								
		if (event.isControlDown() && event.getCode() == KeyCode.P)
			
		{
			
			toggleMute();
			
		}
		
						});
						
				}
				
/**
The toggleMute method mutes/unmutes the background track
*/
			
			private void toggleMute()
				
				{
					
					if (isMute)
						
					{
						
						player.setMute(false);
						
						isMute = false;
						
					}
					
					else
						
					{
						
						player.setMute(true);
					
					isMute = true;
						
					}
					
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
it also decreases the volume of the background track when playing
then sets the volume back to 100 when the track is finished
@param fileName the actual file being passed
*/

		private void playSoundUsing(String fileName)
			
		{
			
			Media media = new Media(Mathefy.class.getResource(fileName).toString());
			
			MediaPlayer mp = new MediaPlayer(media);

mp.play();

mp.setOnPlaying(() -> 
	
	{
		
		player.setVolume(0.5);
		
	});
	
mp.setOnStopped(() ->
	
	{
		
		player.setVolume(1.0);
		
	});
					
		}
		
}