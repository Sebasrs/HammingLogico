package interfaz;

import java.net.URL;
import java.util.ArrayList;

import algoritmo.Hamming;
import algoritmo.NumConverter;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Run extends Application {
	int timesLooped = 0;

	Stage window;
	Scene mainScene, ham1, ham2, bitChange, ham3;
	String numToHam;
	String parity;
	int windX = 1360;
	int windY = 730;
	
	HBox tableNnums;
    Text detection;
    HBox secondT;
	
	ArrayList<ArrayList<String>> matrix;
	ArrayList<ArrayList<String>> matrix2;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		NumConverter converter = new NumConverter();
		
		window = primaryStage;
		
		//First scene
		VBox canvasMain = new VBox(100);
		HBox hammingStory = new HBox(450);
		canvasMain.setPadding(new Insets(10, 50, 20, 50));
		canvasMain.setAlignment(Pos.BASELINE_CENTER);
		
		//Second scene
		VBox canvasHam1 = new VBox(174);
		canvasHam1.setPadding(new Insets(10, 50, 20, 50));
		canvasHam1.setAlignment(Pos.BASELINE_CENTER);
		
		//Third scene
		VBox canvasHam2 = new VBox(90);
		canvasHam2.setPadding(new Insets(10, 50, 20, 50));
		canvasHam2.setAlignment(Pos.BASELINE_CENTER);
		
		//Fourth scene
		VBox canvasBit = new VBox(175);
		canvasBit.setPadding(new Insets(10, 50, 20, 50));
		canvasBit.setAlignment(Pos.BASELINE_CENTER);
				
		//Fifth scene
		VBox canvasHam3 = new VBox(70);
		canvasHam3.setPadding(new Insets(10, 50, 20, 50));
		canvasHam3.setAlignment(Pos.BASELINE_CENTER);
		
		
		//First scene layout
		Text mainHeader = new Text("Código Hamming");
		mainHeader.setFont(new Font(30));
		
		URL URL = getClass().getResource("/media/logo-tec.png");
		Image logo = new Image(URL.toString());
		ImageView TEClogo = new ImageView(logo);
		TEClogo.setFitHeight(355); 
        TEClogo.setFitWidth(400);
        TEClogo.setPreserveRatio(true);
        
        //Hamming image
        VBox hammingImage = new VBox(10);
        hammingImage.setAlignment(Pos.BASELINE_CENTER);
        Label caption = new Label("Richard Wesley Hamming");
        URL URL2 = getClass().getResource("/media/hamming.jpg");
        Image hamming = new Image(URL2.toString());
        ImageView hammingV = new ImageView(hamming);
        hammingV.setFitHeight(305); 
        hammingV.setFitWidth(170);
        hammingV.setPreserveRatio(true);
        hammingImage.getChildren().addAll(hammingV, caption);
        Text someStory = new Text("En informática, el código de Hamming es un código detector y corrector de errores\nque lleva el nombre de su inventor, Richard Hamming.\nEn los datos codificados en Hamming se pueden detectar errores\nen un bit y corregirlos, sin embargo no se distingue entre errores de dos bits\n y de un bit (para lo que se usa Hamming extendido). Esto representa\nuna mejora respecto a los códigos con bit de paridad, que pueden detectar\nerrores en sólo un bit, pero no pueden corregirlo.\n--------------------------------------------------------------------------------------------------------------------\nDiseño Lógico Tarea 2 en Grupo\nIntegrantes:\n- Sebastián Rivera Soto 2016074041\n- Víctor Bulgarelli 2016078592\n- Daniel Andrés Rojas 2016089821\n- Kevin Barquero Loria 2016038605");
        someStory.setFont(new Font(14));
        
        hammingStory.getChildren().addAll(someStory, hammingImage);
        
        canvasMain.setStyle("-fx-background-color: #bdbec1;");
        
        //Buttons
        Button go = new Button("Comenzar");
        go.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try {
					fadeTransition(canvasMain, canvasHam1, ham1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }
        });
        
        canvasMain.getChildren().addAll(mainHeader,TEClogo,hammingStory, go);
        
        mainScene = new Scene(canvasMain, windX, windY);
      
        //Second scene layout
        Text typeNChoose = new Text("Digite el número en hexadecimal al que le desea realizar el proceso\nademás seleccione el tipo de paridad deseado");
        typeNChoose.setFont(new Font(20));
        typeNChoose.setStyle("-fx-text-alignment:center");
        
        VBox form = new VBox(10);
        form.setAlignment(Pos.BASELINE_CENTER);
        Text enterN = new Text("Digite número hexadecimal de 3 dígitos");
        TextField number = new TextField();
        number.setMaxWidth(70);
        form.getChildren().addAll(enterN, number);
        
        Text parityText = new Text("Tipo de paridad");
        RadioButton par = new RadioButton("Par");
        RadioButton odd = new RadioButton("Impar");
        
        ToggleGroup toggleGroup = new ToggleGroup();

        par.setToggleGroup(toggleGroup);
        odd.setToggleGroup(toggleGroup);
        HBox radios = new HBox(2);
        radios.getChildren().addAll(par,odd);
        Group radiosG = new Group(radios);
        
        VBox parBut = new VBox(10);
        parBut.setAlignment(Pos.BASELINE_CENTER);
        Button cal = new Button("Hamming");
        parBut.getChildren().addAll(parityText, radiosG);
        canvasHam1.getChildren().addAll(typeNChoose, form,parBut,cal);
        
        ham1 = new Scene(canvasHam1, windX, windY);
        
        //Third scene layout
        Text table1N = new Text("Tabla No.1\nCálculo de los bits de paridad en el código Hamming");
        table1N.setFont(new Font(18));
        
        HBox optB = new HBox(448);
        Button continuar = new Button("Cambiar un bit");
        Button continuarSin = new Button("Ir a segunda tabla");
        Button Volver = new Button("Volver a inicio");
        optB.getChildren().addAll(Volver,continuarSin, continuar);
        
        canvasHam2.getChildren().addAll(table1N);
        
        ham2 = new Scene(canvasHam2, windX,windY);
        
        //fourth scene layout
        Text bitChanger = new Text("Digite el número que representa la posición\ndel bit a cambiar");
        bitChanger.setFont(new Font(20));
        bitChanger.setStyle("-fx-text-alignment:center");
        
        Text originalArray = new Text();
        originalArray.setFont(new Font(15));
        
        VBox formToBit = new VBox(10);
        formToBit.setAlignment(Pos.BASELINE_CENTER);
        Text instruction = new Text("Digite un número entre 1 y 17");
        instruction.setFont(new Font(15));
        TextField bitToChange = new TextField();
        bitToChange.setMaxWidth(34);
        formToBit.getChildren().addAll(instruction, bitToChange);
        
        Button changeNow = new Button("Cambiar bit y desplegar segunda tabla");
        
        canvasBit.getChildren().addAll(bitChanger,originalArray ,formToBit,changeNow);
        bitChange = new Scene(canvasBit, windX, windY);
        
        //fifth scene layout
        Text table2Text = new Text("Tabla No.2\nComprobación de los bits de paridad");
        table2Text.setFont(new Font(20));
        Text sol2 = new Text("No se encontraron errores");
        Button restart = new Button("Volver a inicio");
        
        canvasHam3.getChildren().add(table2Text);
        ham3 = new Scene(canvasHam3, windX, windY);
        
        cal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	if(converter.covertFromHexToBin(number.getText()) == "Su número no es de tres dígitos" || converter.covertFromHexToBin(number.getText()) == "Error su número no es hexadecimal") {
            		final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(primaryStage);
                    VBox dialogVbox = new VBox(20);
                    dialogVbox.getChildren().add(new Text(converter.covertFromHexToBin(number.getText()) + "\nIngrese otra entrada"));
                    dialogVbox.setAlignment(Pos.BASELINE_CENTER);
                    Scene dialogScene = new Scene(dialogVbox, 300, 70);
                    dialog.setScene(dialogScene);
                    dialog.show();
                    number.setText("");
            	}else if(toggleGroup.getSelectedToggle() == null){
            		final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(primaryStage);
                    VBox dialogVbox = new VBox(20);
                    dialogVbox.setAlignment(Pos.BASELINE_CENTER);
                    dialogVbox.getChildren().add(new Text("Seleccione alguna opción de paridad"));
                    Scene dialogScene = new Scene(dialogVbox, 300, 70);
                    dialog.setScene(dialogScene);
                    dialog.show();
            	}else {  		
            		
            		//Checks if table existed
            		if(timesLooped > 0) {
            			canvasHam2.getChildren().removeAll(tableNnums, optB);
            		}
            		
            		numToHam = number.getText();
            		RadioButton tick = (RadioButton)toggleGroup.getSelectedToggle();
            		parity = tick.getText();
            		
            		tableNnums = new HBox(80);
                    VBox conversions = new VBox(30);
                    
                    //Conversiones
                    Text title = new Text("Analizado");
                    Text hexadecimal = new Text();
                    Text decimal = new Text();
                    Text binary = new Text();
                    Text bcdT = new Text();
                    conversions.getChildren().addAll(title,hexadecimal, decimal, binary, bcdT);
                    
                    //Crea tabla
                    GridPane hamT1 = new GridPane();
                    
                    hamT1.add(new Text("p1"), 1, 0);
                    hamT1.add(new Text("p2"), 2, 0);
                    hamT1.add(new Text("d1"), 3, 0);
                    hamT1.add(new Text("p3"), 4, 0);
                    hamT1.add(new Text("d2"), 5, 0);
                    hamT1.add(new Text("d3"), 6, 0);
                    hamT1.add(new Text("d4"), 7, 0);
                    hamT1.add(new Text("p4"), 8, 0);
                    hamT1.add(new Text("d5"), 9, 0);
                    hamT1.add(new Text("d6"), 10, 0);
                    hamT1.add(new Text("d7"), 11, 0);
                    hamT1.add(new Text("d8"), 12, 0);
                    hamT1.add(new Text("d9"), 13, 0);
                    hamT1.add(new Text("d10"), 14, 0);
                    hamT1.add(new Text("d11"), 15, 0);
                    hamT1.add(new Text("p5"), 16, 0);
                    hamT1.add(new Text("d12"), 17, 0);
              
                    hamT1.add(new Text("Palabra de datos(sin paridad):"), 0, 1);
                    hamT1.add(new Text("p1"), 0, 2);
                    hamT1.add(new Text("p2"), 0, 3);
                    hamT1.add(new Text("p3"), 0, 4);
                    hamT1.add(new Text("p4"), 0, 5);
                    hamT1.add(new Text("p5"), 0, 6);
                    hamT1.add(new Text("Palabra de datos(con paridad):"), 0, 7);
                    
                    hamT1.setHgap(30);
                    hamT1.setVgap(45);
                                        
                    tableNnums.getChildren().addAll(hamT1, conversions);
            		
            		hexadecimal.setText("Hexadecimal: "+ number.getText().toUpperCase());
            		decimal.setText("Decimal: " + Integer.toString(converter.anyToDecimal(16, number.getText())));
            		binary.setText("Binario: " + converter.covertFromHexToBin(number.getText()));
            		bcdT.setText("BCD: " + converter.decimalToBCD(Integer.toString(converter.anyToDecimal(16, number.getText()))));
            		matrix = new Hamming().hammingMatrix(converter.covertFromHexToBin(number.getText()), parity);
            		
            		for(int i = 0; i < matrix.size(); i++) {
            			for(int j = 0; j < matrix.get(i).size(); j++) {
            				hamT1.add(new Text(matrix.get(i).get(j)), j+1, i+1);
            			}
            		}
            		
                    canvasHam2.getChildren().addAll(tableNnums, optB);
            		
            		number.setText("");
            		
            		try {
    					fadeTransition(canvasHam1, canvasHam2, ham2);
    				} catch (InterruptedException e) {
    					e.printStackTrace();
    				}
            	}
            }
        });
        
        restart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	timesLooped++;
            	try {
					fadeTransition(canvasHam3, canvasMain, mainScene);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }
        });
        
        //Third scene buttons
        continuar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	originalArray.setText("El codigo original es:\n" + new Hamming().getCodedChain(matrix.get(matrix.size()-1)));
            	
            	try {
					fadeTransition(canvasHam2, canvasBit, bitChange);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }
        });
        continuarSin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	if(timesLooped > 0) {
        			canvasHam3.getChildren().removeAll(secondT,detection,sol2, restart);
        		}
            	
            	Hamming solution = new Hamming();
            	matrix2 = solution.verifyCode(new Hamming().getCodedChain(matrix.get(matrix.size()-1)), parity);
            	
            	GridPane hamT2 = new GridPane();
                
                hamT2.add(new Text("p1"), 1, 0);
                hamT2.add(new Text("p2"), 2, 0);
                hamT2.add(new Text("d1"), 3, 0);
                hamT2.add(new Text("p3"), 4, 0);
                hamT2.add(new Text("d2"), 5, 0);
                hamT2.add(new Text("d3"), 6, 0);
                hamT2.add(new Text("d4"), 7, 0);
                hamT2.add(new Text("p4"), 8, 0);
                hamT2.add(new Text("d5"), 9, 0);
                hamT2.add(new Text("d6"), 10, 0);
                hamT2.add(new Text("d7"), 11, 0);
                hamT2.add(new Text("d8"), 12, 0);
                hamT2.add(new Text("d9"), 13, 0);
                hamT2.add(new Text("d10"), 14, 0);
                hamT2.add(new Text("d11"), 15, 0);
                hamT2.add(new Text("p5"), 16, 0);
                hamT2.add(new Text("d12"), 17, 0);
                hamT2.add(new Text("Prueba de paridad"), 18, 0);
                hamT2.add(new Text("Bit de paridad"), 19, 0);
          
                hamT2.add(new Text("Palabra de datos recibida:"), 0, 1);
                hamT2.add(new Text("p1"), 0, 2);
                hamT2.add(new Text("p2"), 0, 3);
                hamT2.add(new Text("p3"), 0, 4);
                hamT2.add(new Text("p4"), 0, 5);
                hamT2.add(new Text("p5"), 0, 6);
                
                hamT2.setHgap(30);
                hamT2.setVgap(45);
                               
                for(int i = 0; i < matrix2.size(); i++) {
        			for(int j = 0; j < matrix2.get(i).size(); j++) {
        				hamT2.add(new Text(matrix2.get(i).get(j)), j+1, i+1);
        			}
        		}
                secondT = new HBox(10);
                secondT.getChildren().addAll(hamT2);
                            	
                canvasHam3.getChildren().addAll(secondT, sol2,restart);
                
                try {
					fadeTransition(canvasHam2, canvasHam3, ham3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }
        });
        Volver.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	timesLooped++;
            	try {
					fadeTransition(canvasHam2, canvasMain, mainScene);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }
        });
        
        //Fourth scene button
        changeNow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try {
	            	if(Integer.parseInt(bitToChange.getText()) > 17 || Integer.parseInt(bitToChange.getText()) < 1) {
	            		final Stage dialog = new Stage();
	                    dialog.initModality(Modality.APPLICATION_MODAL);
	                    dialog.initOwner(primaryStage);
	                    VBox dialogVbox = new VBox(20);
	                    dialogVbox.getChildren().add(new Text("El numero que ingresó\nno esta en el rango especificado" + "\nIngrese otra entrada"));
	                    dialogVbox.setAlignment(Pos.BASELINE_CENTER);
	                    Scene dialogScene = new Scene(dialogVbox, 300, 100);
	                    dialog.setScene(dialogScene);
	                    dialog.show();
	                    bitToChange.setText("");
	            	}else {
	            		matrix.get(matrix.size()-1).set(Integer.parseInt(bitToChange.getText())-1, reverseBit(matrix.get(matrix.size()-1).get(Integer.parseInt(bitToChange.getText())-1)));
	            		
	            		Hamming solution = new Hamming();
	                	matrix2 = solution.verifyCode(new Hamming().getCodedChain(matrix.get(matrix.size()-1)), parity);
	                	
	                	if(timesLooped > 0) {
	            			canvasHam3.getChildren().removeAll(secondT,detection,sol2, restart);
	            		}
	                	
	                	GridPane hamT2 = new GridPane();
	                    
	                    hamT2.add(new Text("p1"), 1, 0);
	                    hamT2.add(new Text("p2"), 2, 0);
	                    hamT2.add(new Text("d1"), 3, 0);
	                    hamT2.add(new Text("p3"), 4, 0);
	                    hamT2.add(new Text("d2"), 5, 0);
	                    hamT2.add(new Text("d3"), 6, 0);
	                    hamT2.add(new Text("d4"), 7, 0);
	                    hamT2.add(new Text("p4"), 8, 0);
	                    hamT2.add(new Text("d5"), 9, 0);
	                    hamT2.add(new Text("d6"), 10, 0);
	                    hamT2.add(new Text("d7"), 11, 0);
	                    hamT2.add(new Text("d8"), 12, 0);
	                    hamT2.add(new Text("d9"), 13, 0);
	                    hamT2.add(new Text("d10"), 14, 0);
	                    hamT2.add(new Text("d11"), 15, 0);
	                    hamT2.add(new Text("p5"), 16, 0);
	                    hamT2.add(new Text("d12"), 17, 0);
	                    hamT2.add(new Text("Prueba de paridad"), 18, 0);
	                    hamT2.add(new Text("Bit de paridad"), 19, 0);
	              
	                    hamT2.add(new Text("Palabra de datos recibida:"), 0, 1);
	                    hamT2.add(new Text("p1"), 0, 2);
	                    hamT2.add(new Text("p2"), 0, 3);
	                    hamT2.add(new Text("p3"), 0, 4);
	                    hamT2.add(new Text("p4"), 0, 5);
	                    hamT2.add(new Text("p5"), 0, 6);
	                    
	                    hamT2.setHgap(30);
	                    hamT2.setVgap(45);
	                    
	                    for(int i = 0; i < matrix2.size(); i++) {
	            			for(int j = 0; j < matrix2.get(i).size(); j++) {
	            				hamT2.add(new Text(matrix2.get(i).get(j)), j+1, i+1);
	            			}
	            		}
	                    
	                    String state;
	                    if(solution.bitDetected == 0) {
	                    	state = "No se encontraron errores";
	                    }else {
	                    	state = "Se detectó un error en el bit número: " + solution.bitDetected;
	                    }
	                    detection = new Text(state);
	                    detection.setFont(new Font(15));
	                    
	                    secondT = new HBox(10);
	                    secondT.getChildren().addAll(hamT2);
	                	
	                    canvasHam3.getChildren().addAll(secondT, detection, restart);
	                    
	                    bitToChange.setText("");
	                    
	                    try {
	    					fadeTransition(canvasBit, canvasHam3, ham3);
	    				} catch (InterruptedException e) {
	    					e.printStackTrace();
	    				}
	            	}
            	}catch(Exception e) {
            		final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(primaryStage);
                    VBox dialogVbox = new VBox(20);
                    dialogVbox.getChildren().add(new Text("Su entrada no es válida" + "\nIngrese otra entrada"));
                    dialogVbox.setAlignment(Pos.BASELINE_CENTER);
                    Scene dialogScene = new Scene(dialogVbox, 300, 70);
                    dialog.setScene(dialogScene);
                    dialog.show();
                    bitToChange.setText("");
            	}
            }
        });
        
		//Default is mainScene
		window.setTitle("Código Hamming - codificador y simulador de errores - Diseño Lógico Grupo 1");
		window.setScene(mainScene);
		window.centerOnScreen();
		window.show();
	}
	
	public String reverseBit(String bit) {
		if(bit.equals("1")) {
			return "0";
		}else {
			return "1";
		}
	}
	
	public void fadeTransition(VBox from, VBox to, Scene load) throws InterruptedException {
		FadeTransition t2 = new FadeTransition();
    	t2.setDuration(Duration.millis(400));
    	t2.setNode(to);
    	t2.setFromValue(0);
    	t2.setToValue(1);
    	
		FadeTransition t = new FadeTransition();
    	t.setDuration(Duration.millis(400));
    	t.setNode(from);
    	t.setFromValue(1);
    	t.setToValue(0);
    	t.play();
    	t.setOnFinished(new EventHandler<ActionEvent>() {
    	    @Override
    	    public void handle(ActionEvent event) {
    	    	window.setScene(load);
    			t2.play();
    	    }
    	});
	}
}