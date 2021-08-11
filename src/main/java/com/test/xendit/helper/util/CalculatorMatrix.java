package com.test.xendit.helper.util;

import org.openqa.selenium.Point;

public class CalculatorMatrix {

    public static final String[][] CALC_MATRIX = {
            {"Result", "Result", "Result", "Result", "Result"},                       // row 1
            {"MC", "MR", "M+", "M-", "CE"},   //row 2
            {"7", "8", "9", "/", "sqrt"}, //row 3
            {"4", "5", "6", "*", "%"},  //row 4
            {"1", "2", "3", "-", "1/x"},  //row 5
            {"0", ".", "+/-", "+", "="}   //row 6
    };

    public static Point getKeyPosition(String key) {

        for (int i = 1; i < CALC_MATRIX.length; i++) {
            for (int j = 0; j < CALC_MATRIX[i].length; j++) {
                if (CALC_MATRIX[i][j].equals(key))
                    return new Point(j, i + 1);
            }
        }
        return new Point(0, 0);
    }


}
