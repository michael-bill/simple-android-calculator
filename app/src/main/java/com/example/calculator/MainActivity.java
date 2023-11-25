package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        Button buttonDot = findViewById(R.id.buttonDot);
        buttonDot.setOnClickListener(view -> onClickDotButton(buttonDot));
        Button buttonResult = findViewById(R.id.buttonResult);
        buttonResult.setOnClickListener(view -> onClickResultButton(buttonResult));
        Button buttonClear = findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(view -> onClickClearButton(buttonClear));
        Button buttonClearOne = findViewById(R.id.buttonClearOne);
        buttonClearOne.setOnClickListener(view -> onClickClearOneButton(buttonClearOne));
        Button buttonPlusMinus = findViewById(R.id.buttonPlusMinus);
        buttonPlusMinus.setOnClickListener(view -> onClickPlusMinusButton(buttonPlusMinus));
        Button buttonCopy = findViewById(R.id.buttonCopy);
        buttonCopy.setOnClickListener(view -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("text", resultEditText.getText());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(getApplicationContext(), "Result copied to clipboard", Toast.LENGTH_SHORT).show();
        });
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

    private void onClickDotButton(Button button) {
        if (operation.equals("") && !firstArgument.equals("") && countCharString(firstArgument, '.') < 1) {
            firstArgument += button.getText();
            resultEditText.setText(firstArgument);
        } else if (!secondArgument.equals("") && countCharString(secondArgument, '.') < 1) {
            secondArgument += button.getText();
            resultEditText.setText(secondArgument);
        }
    }

    private void onClickOperationButton(Button button) {
        if (firstArgument.length() > 0 && secondArgument.length() > 0 && operation.length() > 0) {
            double result = getResult();
            firstArgument = String.valueOf(result);
            secondArgument = "";
            resultEditText.setText(firstArgument);
        }
        operation = button.getText().toString();
    }

    private void onClickPlusMinusButton(Button button) {
        if (operation.equals("") && !firstArgument.equals("")) {
            if (firstArgument.charAt(0) == '-') {
                firstArgument = firstArgument.substring(1);
            } else {
                firstArgument = "-" + firstArgument;
            }
            resultEditText.setText(firstArgument);
        } else if (!secondArgument.equals("")) {
            if (secondArgument.charAt(0) == '-') {
                secondArgument = secondArgument.substring(1);
            } else {
                secondArgument = "-" + secondArgument;
            }
            resultEditText.setText(secondArgument);
            resultEditText.setText(secondArgument);
        }
    }

    private void onClickResultButton(Button button) {
        if (firstArgument.equals("") || secondArgument.equals("") || operation.equals("")) return;
        double result = getResult();
        firstArgument = String.valueOf(result);
        secondArgument = "";
        operation = "";
        resultEditText.setText(firstArgument);
    }

    private double getResult() {
        double result = 0;
        switch (operation) {
            case "+":
                return Double.parseDouble(firstArgument) + Double.parseDouble(secondArgument);
            case "-":
                return Double.parseDouble(firstArgument) - Double.parseDouble(secondArgument);
            case "*":
                return Double.parseDouble(firstArgument) * Double.parseDouble(secondArgument);
            case "/":
                return Double.parseDouble(firstArgument) / Double.parseDouble(secondArgument);
            default: return result;
        }
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

    private int countCharString(String str, char c) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c) count++;
        }
        return count;
    }
}