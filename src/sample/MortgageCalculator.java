package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MortgageCalculator extends Application {

    //region Dynamic_Fields

    //Left Dynamic Fields
    TextField amount_Text_L;
    TextField interestRate_Text_L;
    TextField time_Text_L;
    Button calculate_L;
    Label payment_Label_L;
    Label totalCost_Label_L;

    //Right Dynamic Fields
    TextField payment_Text_R;
    TextField interestRate_Text_R;
    TextField time_Text_R;
    Button calculate_R;
    Label amount_Label_R;
    Label totalCost_Label_R;

    //endregion

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        int spaceValue = 10;

        //region Left_Side_VBox

        //Left Side - Create Labels (Row 1 - 3)
        Label amount_Label_L = new Label("Principle Amount ($) = ");
        Label interestRate_Label_L = new Label("Interest Rate (%) = ");
        Label time_Label_L = new Label("Length of Time (Years) = ");

        //Left Side - Create Text Fields
        amount_Text_L = new TextField("100000");
        interestRate_Text_L = new TextField("3.92");
        time_Text_L = new TextField("30");

        //Left Side - Create Button
        calculate_L = new Button("Calculate");
        calculate_L.setOnAction(new CalculateButtonHandler_L());

        //Left Side - Create Labels (Row 4 - 5)
        payment_Label_L = new Label("Monthly Payment = $472.81");
        totalCost_Label_L = new Label("Total Cost = $170,213.30");

        //Left Side - Create Rows
        HBox row_L_0 = new HBox(spaceValue, amount_Label_L, amount_Text_L);
        HBox row_L_1 = new HBox(spaceValue, interestRate_Label_L, interestRate_Text_L);
        HBox row_L_2 = new HBox(spaceValue, time_Label_L, time_Text_L);
        HBox row_L_3 = new HBox(spaceValue, calculate_L);
        HBox row_L_4 = new HBox(spaceValue, payment_Label_L);
        HBox row_L_5 = new HBox(spaceValue, totalCost_Label_L);

        //Left Side - Align Rows
        row_L_0.setAlignment(Pos.CENTER_RIGHT);
        row_L_1.setAlignment(Pos.CENTER_RIGHT);
        row_L_2.setAlignment(Pos.CENTER_RIGHT);
        row_L_3.setAlignment(Pos.CENTER);
        row_L_4.setAlignment(Pos.CENTER);
        row_L_5.setAlignment(Pos.CENTER);

        //Left Side - Create Left Side VBox
        VBox leftSide = new VBox(row_L_0, row_L_1, row_L_2, row_L_3, row_L_4, row_L_5);
        leftSide.setSpacing(10);
        leftSide.setPadding(new Insets(10));

        //endregion

        //region Right_Side_VBox

        //Right Side - Create Labels (Row 1 - 3)
        Label payment_Label_R = new Label("Monthly Payment ($) = ");
        Label interestRate_Label_R = new Label("Interest Rate (%) = ");
        Label time_Label_R = new Label("Length of Time (Years) = ");

        //Right Side - Create Text Fields
        payment_Text_R = new TextField("473");
        interestRate_Text_R = new TextField("3.92");
        time_Text_R = new TextField("30");

        //Right Side - Create Button
        calculate_R = new Button("Calculate");
        calculate_R.setOnAction(new CalculateButtonHandler_R());

        //Right Side - Create Labels (Row 4 - 5)
        amount_Label_R = new Label("Principle Amount = $100,039.18");
        totalCost_Label_R = new Label("Total Cost = $170,280.00");

        //Right Side - Create Rows
        HBox row_R_0 = new HBox(spaceValue, payment_Label_R, payment_Text_R);
        HBox row_R_1 = new HBox(spaceValue, interestRate_Label_R, interestRate_Text_R);
        HBox row_R_2 = new HBox(spaceValue, time_Label_R, time_Text_R);
        HBox row_R_3 = new HBox(spaceValue, calculate_R);
        HBox row_R_4 = new HBox(spaceValue, amount_Label_R);
        HBox row_R_5 = new HBox(spaceValue, totalCost_Label_R);

        //Right Side - Align Rows
        row_R_0.setAlignment(Pos.CENTER_RIGHT);
        row_R_1.setAlignment(Pos.CENTER_RIGHT);
        row_R_2.setAlignment(Pos.CENTER_RIGHT);
        row_R_3.setAlignment(Pos.CENTER);
        row_R_4.setAlignment(Pos.CENTER);
        row_R_5.setAlignment(Pos.CENTER);

        //Right Side - Create Left Side VBox
        VBox rightSide = new VBox(row_R_0, row_R_1, row_R_2, row_R_3, row_R_4, row_R_5);
        rightSide.setSpacing(10);
        rightSide.setPadding(new Insets(10));

        //endregion

        //Both Sides
        HBox entireFrame = new HBox(leftSide, rightSide);

        //Create Scene
        Scene scene = new Scene(entireFrame);

        //Add the Scene to the Stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Loan Calculator");
        primaryStage.show();

    }

    class CalculateButtonHandler_L implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            double monthlyPayment = -1;
            double totalCost = -1;

            try{
                //Get Values
                double principleAmount = Double.parseDouble(amount_Text_L.getText());
                double interestRate = Double.parseDouble(interestRate_Text_L.getText());
                double numOfPayments = Double.parseDouble(time_Text_L.getText());

                //Adjust Values
                interestRate /= 100;
                interestRate /= 12;
                numOfPayments *= 12;

                //Calculate Monthly Payment
                monthlyPayment = principleAmount * ( (interestRate * Math.pow((1+interestRate), numOfPayments)) / ((Math.pow((1+interestRate), numOfPayments))-1) );
                System.out.println("monthlyPayment = "+monthlyPayment);

                //Calculate Total Cost
                totalCost = 0;
                for (int i = 0; i < numOfPayments; i++) {
                    totalCost += monthlyPayment;
                }
                System.out.println("totalCost = "+totalCost);
            }
            catch (IllegalArgumentException e){
                System.out.println("Error = "+e.getMessage());
            }

            //Update Output if valid
            if(monthlyPayment != -1){
                NumberFormat formatter = new DecimalFormat("00.00");
                String s = formatter.format(monthlyPayment);
                payment_Label_L.setText("Monthly Payment = $"+s);
            }
            if(totalCost != -1){
                NumberFormat formatter = new DecimalFormat("000,000.00");
                String s = formatter.format(totalCost);
                totalCost_Label_L.setText("Total Cost = $"+s);
            }
        }
    }

    class CalculateButtonHandler_R implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            double principleAmount = -1;
            double totalCost = -1;

            try{
                //Get Values
                double monthlyPayment = Double.parseDouble(payment_Text_R.getText());
                double interestRate = Double.parseDouble(interestRate_Text_R.getText());
                double numOfPayments = Double.parseDouble(time_Text_R.getText());

                //Adjust Values
                interestRate /= 100;
                interestRate /= 12;
                numOfPayments *= 12;

                //Calculate Principle Amount
                principleAmount = monthlyPayment / ( (interestRate * Math.pow((1+interestRate), numOfPayments)) / ((Math.pow((1+interestRate), numOfPayments))-1) );
                System.out.println("principleAmount = "+principleAmount);

                //Calculate Total Cost
                totalCost = 0;
                for (int i = 0; i < numOfPayments; i++) {
                    totalCost += monthlyPayment;
                }
                System.out.println("totalCost = "+totalCost);
            }
            catch (IllegalArgumentException e){
                System.out.println("Error = "+e.getMessage());
            }

            //Update Output if valid
            NumberFormat formatter = new DecimalFormat("000,000.00");
            if(principleAmount != -1){
                String s = formatter.format(principleAmount);
                amount_Label_R.setText("Principle Amount = $"+s);
            }
            if(totalCost != -1){
                String s = formatter.format(totalCost);
                totalCost_Label_R.setText("Total Cost = $"+s);
            }
        }
    }
}

