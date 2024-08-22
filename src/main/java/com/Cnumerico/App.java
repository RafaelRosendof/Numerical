package com.Cnumerico;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */

public class App extends Application 
{
    public static void main( String[] args )
    {
        launch(args);
    }

    @Override
    //hello world em javaFX
    public void start(Stage primaryStage){
        primaryStage.setTitle("Hello Calculo numérico!");
        Button btn = new Button();
        btn.setText("Fala Figas blz?");
        btn.setOnAction(new EventHandler<ActionEvent>() {
                
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Fala Figas blz?");
                }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root , 400 , 500));
        primaryStage.show();
    }
}

/*
 *Para compilar basta digitar no terminal:
 mvn exec:java -Dexec.mainClass="com.Cnumerico.App"


 ou para compilar 
 mvn compile 
 e para executar
 mvn package gera o jar
java -jar target/Numerical-1.0-SNAPSHOT.jar para executar o .jar
 */
