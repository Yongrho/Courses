/* reference: https://abhiandroid.com/ui/gridview */

package com.example.mobilemathcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final int MAX_LINE_LENGTH = 14;

    private enum State {
        LEFT_NUMBER,
        OPERATOR,
        RIGHT_NUMBER,
        CALCULATOR
    }

    GridView gridView;
    GridAdapter gridAdapter;
    TextView tvExpression;
    TextView tvResult;
    State mState = State.LEFT_NUMBER;
    String result = "";
    String sOp = "";
    String numLeft, numRight;
    MyMath myMath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init MyMath
        myMath = new MyMath();

        // init TextView
        tvExpression = findViewById(R.id.tvExpression);
        tvResult = findViewById(R.id.tvResult);;

        ArrayList<String> btnList = new ArrayList<>();
        // init GridView
        gridView = (GridView) findViewById(R.id.gridView);
        // 1st row
        btnList.add("");
        btnList.add("C");
        btnList.add("<-");
        btnList.add("/");

        // 2nd row
        btnList.add("7");
        btnList.add("8");
        btnList.add("9");
        btnList.add("*");

        // 3rd row
        btnList.add("4");
        btnList.add("5");
        btnList.add("6");
        btnList.add("-");

        // 4th row
        btnList.add("1");
        btnList.add("2");
        btnList.add("3");
        btnList.add("+");

        // 5th row
        btnList.add("+/-");
        btnList.add("0");
        btnList.add(".");
        btnList.add("=");

        // Create an object of CustomAdapter and set Adapter to GirdView
        gridAdapter = new GridAdapter(getApplicationContext(), btnList);
        gridView.setAdapter(gridAdapter);

        // implement setOnItemClickListener event on GridView
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                rollingCalculate(position);
            }
        });
    }

    private boolean checkValidation(String expr) {
        // See if expr has a character that includes a-z or A-Z
        Pattern pattern = Pattern.compile("[a-zA-Z]");
        Matcher matcher = pattern.matcher(expr);
        if (matcher.find()) {
            return false;
        }

        // See if expr is float or double
        pattern = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+");
        matcher = pattern.matcher(expr);
        return matcher.find();
    }

    private String handleDecimal(String s) {
        if (s.indexOf('.') < 0) {
            return s;
        }

        int len = s.length();
        if (len > MAX_LINE_LENGTH) {
            s = s.substring(0, 13);
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;

        len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '0') {
                count++;
            } else {
                if (count > 0) {
                    for (int j = 0; j < count; j++) {
                        sb.append('0');
                    }
                    count = 0;
                }
                sb.append(s.charAt(i));
            }
        }

        len = sb.length();
        if (len > 0) {
            if (sb.charAt(len - 1) == '.') {
                sb.deleteCharAt(len - 1);
            }
            s = sb.toString();
        }
        return s;
    }

    @SuppressLint("SetTextI18n")
    private void handleNumberDecimalKey(int pos, String s) {
        if (mState == State.OPERATOR) {
            tvExpression.setText(result + sOp);
            result = "";
            mState = State.RIGHT_NUMBER;
        } else if (mState == State.CALCULATOR) {
            mState = State.LEFT_NUMBER;
            result = "";
            tvExpression.setText("");
        }

        int len = result.length();
        if (len > 14) {
            return;
        }

        if (pos == 18) { // Decimal
            if (result.isEmpty()) {
                result = "0.";
            } else if (result.contains(".")) {
                return;
            } else {
                result += s;
            }
        } else {
            if (result.equals("0")) {
                result = "";
            }
            result += s;
        }
        tvResult.setText(result);
    }

    private void handleRemoveKey() {
        if (mState == State.OPERATOR) {
            return;
        }

        if (mState == State.CALCULATOR) {
            tvExpression.setText("");
            return;
        }

        if (result.length() <= 1) {
            tvResult.setText("0");
            result = "";
        } else {
            result = result.substring(0, result.length() - 1);
            tvResult.setText(result);
        }
    }
    @SuppressLint("SetTextI18n")
    private void handleOperatorKey(String s) {
        if (mState == State.CALCULATOR) {
            mState = State.LEFT_NUMBER;
        }
        if (result.isEmpty()) {
            return;
        }

        if (sOp.equals("/")) {
            if (result.equals(getString(R.string.divided_zero)) || result.equals("0")) {
                result = getString(R.string.divided_zero);
                tvResult.setText(result);
                mState = State.CALCULATOR;
                return;
            }
        } else {
            result = handleDecimal(result);
        }

        // See if the value is valid
        if (!checkValidation(result)) {
            Toast.makeText(getApplicationContext(),getString(R.string.wrong_value),Toast.LENGTH_SHORT).show();
        }

        if (mState == State.LEFT_NUMBER) {
            sOp = s;
            numLeft = result;
            tvResult.setText(result);
        } else if (mState == State.RIGHT_NUMBER) {
            // calculate the first expression
            double outcome = myMath.calc(Double.parseDouble(numLeft),
                    Double.parseDouble(result),
                    sOp.charAt(0));
            result = handleDecimal(String.valueOf(outcome));

            sOp = s;
            numLeft = result;
            tvResult.setText(result);
        } else {
            sOp = s;
        }
        tvExpression.setText(result + sOp);
        mState = State.OPERATOR;
    }

    private void handleCalculatorKey() {
        if (mState != State.RIGHT_NUMBER) {
            return;
        }

        if (sOp.equals("/") && result.equals("0")) {
            result = getString(R.string.divided_zero);
        } else {
            if (sOp.equals("-")) {
                if (result.charAt(0) == '-') {
                    sOp = "+";
                    result = result.substring(1, result.length());
                }

            } else if (sOp.equals("+")) {
                if (result.charAt(0) == '-') {
                    sOp = "-";
                    result = result.substring(1, result.length());
                }
            }

            result = handleDecimal(result);

            // See if the value is valid
            if (checkValidation(result)) {
                numRight = result;
            } else {
                Toast.makeText(getApplicationContext(),getString(R.string.wrong_value),Toast.LENGTH_SHORT).show();
            }

            String expr = numLeft + sOp + result + "=";
            tvExpression.setText(expr);

            double outcome = myMath.calc(Double.parseDouble(numLeft),
                                        Double.parseDouble(numRight),
                                        sOp.charAt(0));
            result = handleDecimal(String.valueOf(outcome));
        }
        tvResult.setText(result);
        mState = State.CALCULATOR;
    }

    @SuppressLint("SetTextI18n")
    private void rollingCalculate(int position) {
        String s= gridAdapter.getItem(position);

        switch (position) {
            // 0 ~ 9
            case 4: // 7
            case 5: // 8
            case 6: // 9
            case 8: // 4
            case 9: // 5
            case 10: // 6
            case 12: // 1
            case 13: // 2
            case 14: // 3
            case 17: // 0
            case 18: // Decimal
                handleNumberDecimalKey(position, s);
                break;

            case 1: // C
                result = "";
                sOp = "";
                numLeft = "";
                numRight = "";
                tvExpression.setText("");
                tvResult.setText("0");
                break;
            case 2: // Remove Digit
                handleRemoveKey();
                break;

            case 3: // Division
            case 7: // Multiplication
            case 11: // Subtraction
            case 15: // Addition
                handleOperatorKey(s);
                break;

            case 16: // Neg/Pos
                if (result.charAt(0) == '-') {
                    result = result.substring(1, result.length());
                } else {
                    if (result.charAt(0) == '0') {
                        if (result.charAt(1) == '.') {
                            result = "-" + result;
                        }
                    } else {
                        result = "-" + result;
                    }
                }
                tvResult.setText(result);
                break;

            case 19: // Calculate
                handleCalculatorKey();
                break;
        }
    }
}