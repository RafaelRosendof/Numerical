package com.Cnumerico.calculus;

import com.Cnumerico.calculus.DerivatesClass;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.IntStream;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/*
forwardMethod(Function<BigDecimal , BigDecimal> f , BigDecimal [] x  , BigDecimal h , MathContext mc)
backwardMethod(Function<BigDecimal , BigDecimal> f , BigDecimal [] x , BigDecimal h , MathContext mc)
OnlyOneForward(Function<BigDecimal , BigDecimal> f , BigDecimal x , BigDecimal h , MathContext mc)
OnlyOneBackward(Function<BigDecimal , BigDecimal> f , BigDecimal x , BigDecimal h , MathContext mc)
centralDiff(Function<BigDecimal , BigDecimal> f , BigDecimal[] x , BigDecimal h , MathContext mc)
 */
public class DerivatesView extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage pStage){

        Button bt1 = new Button("Derivada forward");
        bt1.setOnAction(e -> StartforwardMethod(pStage));

        Button bt2 = new Button("Derivada backward");
        bt2.setOnAction(e -> StartbackwardMethod(pStage));

        Button bt3 = new Button("Derivada central");
        bt3.setOnAction(e -> StartcentralDiff(pStage));

        Button bt4 = new Button("Derivada forward one point");
        bt4.setOnAction(e -> StartOnlyOneForward(pStage));

        Button bt5 = new Button("Derivada backward one point");
        bt5.setOnAction(e -> StartOnlyOneBackward(pStage));

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(bt1, bt2, bt3, bt4, bt5);

        Scene scene = new Scene(layout, 600, 500);
        pStage.setScene(scene);
        pStage.show();
    }


    public void StartforwardMethod(Stage pStage){
        pStage.setTitle("Derivada forward");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10 , 10 ,10 , 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label labH = new Label("Digite o valor de H: ");
        GridPane.setConstraints(labH, 0 , 0);

        //Deveria ser uma lista?
        Label labX = new Label("Digite os valores de X: ");
        GridPane.setConstraints(labX, 0 , 1); 

        Label labRes = new Label("Resultado: ");
        GridPane.setConstraints(labRes, 0 , 3 , 2 , 1);

        TextField txtH = new TextField();
        GridPane.setConstraints(txtH, 1, 0);

        TextField txtX = new TextField();
        GridPane.setConstraints(txtX, 1, 1);

        Button btn = new Button("Calcular: ");
        GridPane.setConstraints(btn, 0, 2);

        ProgressBar pb = new ProgressBar();
        pb.setVisible(false);
        GridPane.setConstraints(pb, 0, 4, 2, 1);

        grid.getChildren().addAll(labH, txtH , labX , txtX , btn,labRes, pb);

        Scene scene = new Scene(grid, 600, 500);
        pStage.setScene(scene);
        pStage.show();

        btn.setOnAction(e -> {
            pb.setVisible(true);

            try{
            Function<BigDecimal, BigDecimal> f = x  -> BigDecimal.valueOf(Math.log10(Math.sqrt(x.doubleValue() * x.doubleValue() + x.doubleValue()) + 1));

            BigDecimal h = new BigDecimal(txtH.getText());

            String[] xval = txtX.getText().split(",");
            BigDecimal[] x = Arrays.stream(xval).map(String::trim).map(BigDecimal::new).toArray(BigDecimal[]::new);

            MathContext mc = new MathContext(10, RoundingMode.HALF_UP);

            DerivatesClass dc = new DerivatesClass();
            BigDecimal[] res = dc.forwardMethod(f, x, h, mc);

            StringBuilder sb = new StringBuilder();
            sb.append("Resultado: ");
            for(int i = 0 ; i < res.length ; i++){
                sb.append("f'(").append(x[i]).append(") = ").append(res[i].toPlainString()).append("\n");

            }
            labRes.setText(sb.toString());
            
            }catch(Exception ex){
                labRes.setText("Erro Verifique os valores digitados");

            }finally{
                pb.setVisible(false);
            }

        });

    }
    
    public void StartbackwardMethod(Stage pStage){

    }

    public void StartcentralDiff(Stage pStage){

    }

    public void StartOnlyOneForward(Stage pStage){

    }

    public void StartOnlyOneBackward(Stage pStage){

    }
    
}
