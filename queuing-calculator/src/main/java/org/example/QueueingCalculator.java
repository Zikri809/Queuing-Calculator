package org.example;

import java.util.Scanner;

public class QueueingCalculator {
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter arrival rate (lambda): ");
    double lambda = scanner.nextDouble();
    System.out.println("Enter service rate (mu): ");
    double mu = scanner.nextDouble();
    System.out.println("Enter number of servers (c): ");
    int c = scanner.nextInt();

         // Input validation
    if (lambda <= 0 || mu <= 0 || c < 1 || lambda / (c * mu) >= 1) {
        System.out.println("Invalid input: Ensure lambda > 0, mu > 0, c >= 1, and lambda/(c*mu) < 1.");
        return;
    }

    if (c == 1) {
        calculateMM1(lambda, mu);
        } else {
        calculateMMc(lambda, mu, c);
        }
    scanner.close();
    }

    public static void calculateMM1(double lambda, double mu) {
        double rho = lambda / mu;
        double L = lambda / (mu - lambda);
        double Lq = lambda * lambda / (mu * (mu - lambda));
        double W = 1 / (mu - lambda);
        double Wq = lambda / (mu * (mu - lambda));
        System.out.printf("M/M/1 Results:%n");
        System.out.printf("Traffic Intensity (rho): %.4f%n”, rho");
        System.out.printf("Expected Number in System (L): %.4f%n", L);
        System.out.printf("Expected Queue Length (Lq): %.4f%n", Lq);
        System.out.printf("Expected Time in System (W): %.4f hours%n", W);
        System.out.printf("Expected Waiting Time in Queue (Wq): %.4f hours%n", Wq);
        for (int n = 0; n <= 5; n++) {
            double Pn = (1 - rho) * Math.pow(rho, n);
            System.out.printf("P(%d customers): %.4f%n", n, Pn);
        }
    }

    public static void calculateMMc(double lambda, double mu, int c) {
        double rho = lambda / (c * mu);
        double sum = 0;
        for (int n = 0; n < c; n++) {
            sum += Math.pow(c * rho, n) / factorial(n);
        }
        double P0 = 1 / (sum + Math.pow(c * rho, c) / (factorial(c)
                * (1 - rho)));
        double Lq = P0 * Math.pow(c * rho, c) * rho / (factorial(c)
                * Math.pow(1 - rho, 2));
        double L = Lq + c * rho;
        double Wq = Lq / lambda;
        double W = Wq + 1 / mu;
        System.out.printf("M/M/%d Results:%n", c);
        System.out.printf("Traffic Intensity (rho): %.4f%n", rho);
        System.out.printf("Expected Number in System (L): %.4f%n", L);
        System.out.printf("Expected Queue Length (Lq): %.4f%n", Lq);
        System.out.printf("Expected Time in System (W): %.4f hours%n ", W);
        System.out.printf("Expected Waiting Time in Queue (Wq): %.4f hours%n", Wq);
        for (int n = 0; n <= 5; n++) {
            double Pn = (n < c) ? P0 * Math.pow(c * rho, n) / factorial(n) : P0 * Math.pow(c * rho, n) / (Math.pow(c, n - c) * factorial(c));
            System.out.printf("P(%d customers): %.4f%n", n, Pn);
        }
    }

    public static long factorial(int n) {
        if (n == 0) return 1;
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
 }
