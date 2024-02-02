package org.example;

import java.util.HashSet;
import java.util.Set;

public class Main {

    /*
    *   input - n cents to be divided into coins: quaters, dimes, nickels and pennies
    *   return - A Set of integer arrays containing [quaters, dimes, nickels, pennies]
    *
    *   The method below will return a Set containing arrays with quantity of each coin, like:
    *                       [quaters, dimes, nickels, pennies]
    *   The execution is according to the description below:
    *       -   Get the input value (n cents);
    *       -   Create the Set of array that will store the solution;
    *       -   Create auxiliary variables for the algorithm execution:
    *           -   Values of each coin: 1, 5, 10, 25
    *           -   Maximum number of each coin that can produce or almost produce n cents;
    *       -   Core of the algorithm:
    *           -   Iterate through all combinations possible according to the restrictions:
    *               -   coin_i should not be greater than coinMax value possible, because:
    *                                coin_i * coinValue > coinMax * coinValue
    *               -   The value of iteration number should not be greater than n cents value, because:
    *                   -   For pennies, its number of coins (p_i) cannot be greater than n cents value;
    *                   -   As we are doing combinations of set values, the instantaneous values of iterations variable
    *                       should respect the condition that does not exceed the n cents value;
    *                   -   For quaters, as it is the last for, the sum:
    *                           p_i * pValue + n_i * nValue + d_i * dValue + q_i * qValue
    *                       should not be greater than n cents values.
    *           -   If the combination [q_i, d_i, n_i, p_i] multiplied by its coin value if equal to n:
    *                   -   Add solution to the Set of solutions;
    *           -   Otherwise:
    *                   -   Continue the algorithm;
    *       -   Return the solution found.
    * */
    public static Set<int[]> makeChange(int n){
        // Create the Set of ways
        Set <int[]> ways = new HashSet<>();

        // Value of each coin
        int pValue = 1;
        int nValue = 5;
        int dValue = 10;
        int qValue = 25;

        // Maximum number of coins for each one, based on n value
        int pMax = n / pValue;
        int nMax = n / nValue;
        int dMax = n / dValue;
        int qMax = n / qValue;

        // Algorithm iterate through elements up to its maximum value for each coin type
        for (int p_i = 0; p_i <= pMax && n >= p_i * pValue; p_i++) {
            for (int n_i = 0; n_i <= nMax && (n >= p_i * pValue + n_i * nValue); n_i++) {
                for (int d_i = 0; d_i <= dMax && (n >= p_i * pValue + n_i * nValue + d_i * dValue); d_i++) {
                    for (int q_i = 0; q_i <= qMax && (n >= p_i * pValue + n_i * nValue + d_i * dValue + q_i * qValue); q_i++) {
                        int result = p_i * pValue + n_i * nValue + d_i * dValue + q_i * qValue;
                        if(result == n){
                            int arr[] = new int[4];
                            arr[0] = q_i;
                            arr[1] = d_i;
                            arr[2] = n_i;
                            arr[3] = p_i;
                            ways.add(arr);
                        }
                    }
                }
            }
        }

        return ways;
    }

    public static void main(String[] args) {
        // Initialize value
        int n = 11;
        // Call method that will execute the algorithm
        Set<int[]> ways = makeChange(n);
        // Show the solution found
        for(int[] way: ways){
            System.out.println("Solution found: [" + way[0] + ", " + way[1] + ", " + way[2] + ", " + way[3] + "]");
        }
    }
}