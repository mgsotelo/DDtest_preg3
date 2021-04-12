/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tests.interviews.ddtest_preg3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author mario
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean wtf = true;
        while (wtf == true) {
            Scanner in = new Scanner(System.in);
            String theinput = in.next();
            if (theinput.equals("stop")) {
                wtf = false;
            }
            if (wtf) {
                try {

                    double thenumber = Double.parseDouble(theinput);
                    BigDecimal theNumber = BigDecimal.valueOf(thenumber);

                    // primer metodo es mas optimo y rapido pero no da todos los resultados, solo los mas optimos
                    // System.out.println(thenumber);
                    // String theresults = generateArrayInSoles(thenumber);
                    // System.out.println(theresults);
                    // segundo metodo da los resultados completos (incluso aquellos que no son taaaan optimos)
                    ArrayList<ArrayList<BigDecimal>> thearray = generateArrayInSolesReloaded(theNumber);
                    ArrayList<ArrayList<BigDecimal>> finalarr = removeDuplicates(thearray);
                    System.out.println(finalarr.toString());
                    // el segundo metodo es algo lento pero funciona

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static String generateArrayInSoles(double thenumber) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        double[] monedasSoles = {0.2, 0.5, 1.0, 2.0, 5.0, 10.0, 20.0, 50.0, 100.0, 200.0};
        // System.out.println(Arrays.toString(monedasSoles));
        ArrayList<String> theresult = new ArrayList<>();
        for (int i = monedasSoles.length; i > 0; i--) {
            if (thenumber >= monedasSoles[i - 1]) {
                // System.out.println("Tomando base " + monedasSoles[i-1]);
                // System.out.println("Array # " + i);
                double thehelper = thenumber;
                ArrayList<Double> thearray = new ArrayList<>();
                // System.out.println(thehelper);
                for (int j = i; j > 0; j--) {
                    // System.out.println("Añadiendo a este array: ");
                    while (thehelper >= monedasSoles[j - 1]) {
                        int times = (int) (thehelper / monedasSoles[j - 1]);
                        for (int k = 0; k < times; k++) {
                            thearray.add(monedasSoles[j - 1]);
                        }
                        thehelper = thehelper - times * monedasSoles[j - 1];
                        // System.out.println(thehelper);
                    }
                }
                if (thehelper == 0) {
                    theresult.add(Arrays.toString(thearray.toArray()));
                }
            }
        }
        // System.out.println(Arrays.toString(theresult.toArray()));
        return Arrays.toString(theresult.toArray());

    }

    public static ArrayList<ArrayList<BigDecimal>> generateArrayInSolesReloaded(BigDecimal thenumber) {
        ArrayList<ArrayList<BigDecimal>> thearr = new ArrayList<>();
        // BigDecimal[] monedasSoles = {0.2, 0.5, 1.0, 2.0, 5.0, 10.0, 20.0, 50.0, 100.0, 200.0};

        BigDecimal[] monedasSoles = {BigDecimal.valueOf(0.2), BigDecimal.valueOf(0.5), BigDecimal.valueOf(1.0), BigDecimal.valueOf(2.0), BigDecimal.valueOf(5.0), BigDecimal.valueOf(10.0), BigDecimal.valueOf(20.0), BigDecimal.valueOf(50.0), BigDecimal.valueOf(100.0), BigDecimal.valueOf(200.0)};
        // System.out.println(thenumber);
        for (int i = monedasSoles.length; i > 0; i--) {
            BigDecimal thehelper = thenumber;
            ArrayList<BigDecimal> helparr = new ArrayList<>();
            if (thenumber.compareTo(monedasSoles[i - 1]) == 1 || thenumber.compareTo(monedasSoles[i - 1]) == 0) {
                // double helper = thenumber;
                // System.out.println(thenumber);
                thehelper = thehelper.subtract(monedasSoles[i - 1]);
                helparr.add(monedasSoles[i - 1]);
                if (thehelper.compareTo(BigDecimal.ZERO) == 0) {
                    thearr.add(helparr);
                } else {
                    for (ArrayList<BigDecimal> xdxd : generateArrayInSolesReloaded(thehelper)) {
                        xdxd.add(0, monedasSoles[i - 1]);
                        thearr.add(xdxd);
                    }
                }
            }
        }
        return thearr;
    }

    private static ArrayList<ArrayList<BigDecimal>> removeDuplicates(ArrayList<ArrayList<BigDecimal>> thearray) {
        ArrayList<ArrayList<BigDecimal>> arrcopy = thearray;
        ArrayList<ArrayList<BigDecimal>> crrcopy = new ArrayList<>();

        for (ArrayList<BigDecimal> arr : arrcopy) {
            if (!hasArr(crrcopy, arr)) {
                crrcopy.add(arr);
            }
        }
        return crrcopy;
    }

    private static boolean hasArr(ArrayList<ArrayList<BigDecimal>> crrcopy, ArrayList<BigDecimal> arr) {

        for (ArrayList<BigDecimal> crr : crrcopy) {
            if (crr.size() == arr.size()) {
                Collections.sort(arr);
                Collections.sort(crr);
                if (crr.equals(arr)) {
                    return true;
                }
            }
        }
        return false;
    }

}
