package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    String firstArgument = "", secondArgument = "", operation = "";
    EditText resultEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (Button button : new Button[] { findViewById(R.id.button1),
                                            findViewById(R.id.button2),
                                            findViewById(R.id.button3),
                                            findViewById(R.id.button4),
                                            findViewById(R.id.button5),
                                            findViewById(R.id.button6),
                                            findViewById(R.id.button7),
                                            findViewById(R.id.button8),
                                            findViewById(R.id.button9),
                                            findViewById(R.id.button0) }) {
            button.setOnClickListener(view -> onClickDigitButton(button));
        }
        for (Button button : new Button[] { findViewById(R.id.buttonPlus),
                                            findViewById(R.id.buttonMinus),
                                            findViewById(R.id.buttonMultiply),
                                            findViewById(R.id.buttonDivide)}) {
            button.setOnClickListener(view -> onClickOperationButton(button));
        }
        resultEditText = findViewById(R.id.resultEditText);
        Button buttonResult = findViewById(R.id.buttonResult);
        buttonResult.setOnClickListener(view -> onClickResultButton(buttonResult));
        Button buttonClear = findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(view -> onClickClearButton(buttonClear));
        Button buttonClearOne = findViewById(R.id.buttonClearOne);
        buttonClearOne.setOnClickListener(view -> onClickClearOneButton(buttonClearOne));
    }

    private void onClickDigitButton(Button button) {
        if (operation.equals("")) {
            firstArgument += button.getText();
            resultEditText.setText(firstArgument);
        } else {
            secondArgument += button.getText();
            resultEditText.setText(secondArgument);
        }
    }

    private void onClickOperationButton(Button button) {
        operation = button.getText().toString();
    }

    private void onClickResultButton(Button button) {
        double result = 0;
        switch (operation) {
            case "+":
                result = Double.parseDouble(firstArgument) + Double.parseDouble(secondArgument);
                break;
            case "-":
                result = Double.parseDouble(firstArgument) - Double.parseDouble(secondArgument);
                break;
            case "*":
                result = Double.parseDouble(firstArgument) * Double.parseDouble(secondArgument);
                break;
            case "/":
                result = Double.parseDouble(firstArgument) / Double.parseDouble(secondArgument);
                break;
            default: return;
        }
        firstArgument = String.valueOf(result);
        resultEditText.setText(firstArgument);
    }

    private void onClickClearButton(Button button) {
        firstArgument = "";
        secondArgument = "";
        operation = "";
        resultEditText.setText("");
    }

    private void onClickClearOneButton(Button button) {
        if (!firstArgument.equals("") && operation.equals("")) {
            firstArgument = firstArgument.substring(0, firstArgument.length() - 1);
            resultEditText.setText(firstArgument);
        } else if (!secondArgument.equals("")) {
            secondArgument = secondArgument.substring(0, secondArgument.length() - 1);
            resultEditText.setText(secondArgument);
        }
    }
}