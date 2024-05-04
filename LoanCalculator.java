//M07 Programming Assignment (1)
//Ivy Tech Community College
//SDEV 200 - Java
//Professor Bumgardner
//Nativida Muhammad
// 04 May 2024

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoanCalculator extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create labels and text fields
        Label loanAmountLabel = new Label("Loan Amount:");
        TextField loanAmountField = new TextField();
        Label interestRateLabel = new Label("Interest Rate (per annum):");
        TextField interestRateField = new TextField();
        Label numberOfYearsLabel = new Label("Number of Years:");
        TextField numberOfYearsField = new TextField();

        // Create button for calculation
        Button calculateButton = new Button("Compute Loan Payment");

        // Event handler for button click
        calculateButton.setOnAction(e -> {
            try {
                double loanAmount = Double.parseDouble(loanAmountField.getText());
                double annualInterestRate = Double.parseDouble(interestRateField.getText());
                int numberOfYears = Integer.parseInt(numberOfYearsField.getText());

                // Perform loan calculation
                double monthlyPayment = calculateMonthlyPayment(loanAmount, annualInterestRate, numberOfYears);
                double totalPayment = calculateTotalPayment(monthlyPayment, numberOfYears);

                // Display result
                System.out.println("Monthly Payment: $" + monthlyPayment);
                System.out.println("Total Payment: $" + totalPayment);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter valid numbers.");
            }
        });

        // Create grid pane layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Add components to grid pane
        gridPane.add(loanAmountLabel, 0, 0);
        gridPane.add(loanAmountField, 1, 0);
        gridPane.add(interestRateLabel, 0, 1);
        gridPane.add(interestRateField, 1, 1);
        gridPane.add(numberOfYearsLabel, 0, 2);
        gridPane.add(numberOfYearsField, 1, 2);
        gridPane.add(calculateButton, 0, 3, 2, 1);

        // Set scene
        Scene scene = new Scene(gridPane, 300, 200);
        primaryStage.setTitle("Loan Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to calculate monthly payment
    private double calculateMonthlyPayment(double loanAmount, double annualInterestRate, int numberOfYears) {
        double monthlyInterestRate = annualInterestRate / 12 / 100;
        int numberOfPayments = numberOfYears * 12;
        return loanAmount * monthlyInterestRate / (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));
    }

    // Method to calculate total payment
    private double calculateTotalPayment(double monthlyPayment, int numberOfYears) {
        return monthlyPayment * numberOfYears * 12;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
