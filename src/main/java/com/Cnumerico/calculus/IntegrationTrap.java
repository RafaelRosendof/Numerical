package com.Cnumerico.calculus;
import com.Cnumerico.calculus.IntegrationClass;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.function.Function;



public class IntegrationTrap extends Application {
   public static void main(String[] args) {
       launch(args);

   }

   @Override
   public void start(Stage primaryStage) {
       primaryStage.setTitle("Integração numérica");

       //layout
       GridPane grid = new GridPane();
       grid.setPadding(new Insets(10,10,10,10));
       grid.setVgap(8);
       grid.setHgap(10);

       //labels e textfields
       Label labA = new Label("Digite o valor de A: ");
       GridPane.setConstraints(labA, 0, 0);

       TextField txtA = new TextField();
       GridPane.setConstraints(txtA, 1, 0);

        Label labelB = new Label("Digite o valor de b:");
        GridPane.setConstraints(labelB, 0, 1);
        TextField textB = new TextField();
        GridPane.setConstraints(textB, 1, 1);

        Label labelN = new Label("Digite o valor de n:");
        GridPane.setConstraints(labelN, 0, 2);
        TextField textN = new TextField();
        GridPane.setConstraints(textN, 1, 2);

        Label labelTol = new Label("Digite a tolerância:");
        GridPane.setConstraints(labelTol, 0, 3);
        TextField textTol = new TextField();
        GridPane.setConstraints(textTol, 1, 3);

        //butão

        Button btn = new Button("Calcular");
        GridPane.setConstraints(btn,  0 , 4);

        //Res label

        Label labelRes = new Label("Resultado:  ");
        GridPane.setConstraints(labelRes, 0, 5 , 2 , 1);

        //barra de progresso 
        ProgressBar pb = new ProgressBar();
        pb.setVisible(false);
        GridPane.setConstraints(pb , 0, 6 , 2 , 1);

        grid.getChildren().addAll(labA, txtA, labelB, textB, labelN, textN, labelTol, textTol, btn, labelRes, pb);

        btn.setOnAction(e -> {pb.setVisible(true);


            ////////
       ///////Lambda expression
       Function <BigDecimal , BigDecimal>  FF = x -> BigDecimal.valueOf(Math.log10(Math.sqrt(x.doubleValue() * x.doubleValue() + x.doubleValue()) + 1));

            try{
                BigDecimal a = new BigDecimal(txtA.getText());
                BigDecimal b = new BigDecimal(textB.getText());
                int n = Integer.parseInt(textN.getText());
                BigDecimal tol = new BigDecimal(textTol.getText());

                MathContext mc = new MathContext(30, RoundingMode.HALF_UP);
                
                BigDecimal res = new IntegrationClass().trapezoidalRule(FF, a, b, n, tol, mc);
                labelRes.setText("Jadna meu amor, o valor que você calculou é: " + res.toPlainString());

            }catch(Exception ex){
                labelRes.setText("Jadna meu amor, você digitou algo errado, tente novamente");
            }finally{
                pb.setVisible(false);
            }

        });

        Scene scene = new Scene(grid, 500 , 400);
        primaryStage.setScene(scene);
        primaryStage.show();

   }
}