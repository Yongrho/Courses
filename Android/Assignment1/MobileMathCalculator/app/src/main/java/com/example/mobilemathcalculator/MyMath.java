package com.example.mobilemathcalculator;

public class MyMath {
    public void MyMath() {
    }

    public double calc(double leftNum, double rightNum, char op) {
        double ret = 0.0;
        switch (op) {
            case '+':
                ret = leftNum + rightNum;
                break;
            case '-':
                ret = leftNum - rightNum;
                break;
            case '*':
                ret = leftNum * rightNum;
                break;
            case '/':
                ret = leftNum / rightNum;
                break;
        }
        return ret;
    }
}
