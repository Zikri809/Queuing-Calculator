package org.example;


import java.util.Scanner;
abstract class Queue{
    double lambda;
    double mu;
    int c ;
    int n;
    double rho;
    double L  ;
    double Lq ;
    double W  ;
    double Wq ;
    double [] Pn;
    String system_type;

    public abstract void Calculate();
}
class  MM1 extends Queue{

    public MM1(){
        this.c = 1;
        this.system_type = "MM1";
        Scanner scanner = new Scanner(System.in);

        do{
            System.out.print("Enter arrival rate (lambda): ");
            this.lambda = scanner.nextDouble();
            while(this.lambda<=0){
                System.out.println("Invalid Input Lambda cannot be <=0. Please enter again.");
                System.out.print("Enter arrival rate (lambda): ");
                this.lambda = scanner.nextDouble();
            }

            System.out.print("Enter service rate (mu): ");
            this.mu = scanner.nextDouble();
            while(this.mu<=0){
                System.out.println("Invalid Input Mu cannot be <=0. Please enter again.");
                System.out.print("Enter service rate (mu): ");
                this.mu = scanner.nextDouble();
            }

            // checking if the traffic intensity is greater or equal then 1
            if( this.lambda / (this.c * this.mu) >= 1) {
                System.out.println("The system is unstable because the traffic intensity is greater then 1");
                System.out.println("Please reenter all the necessary parameters!");
            }
        }while(this.lambda / (this.c * this.mu) >= 1);
            
        
        int choice;
        do{
          System.out.print("Would you like to use a custom value of n for calculating P(n)? (1 = yes, 0 = no): ");
          choice = scanner.nextInt();
            if(choice == 1 || choice == 0){
                do{
                 if(choice == 1){
                     System.out.print("Enter the number of customers (n): ");
                     this.n = scanner.nextInt();
                        if(n <= 0){
                            System.out.print("n should not be below or equal than zero!\n");
                        }
                 }
                        else if(choice == 0){
                             this.n = 5;
                        }
                
                 
                }while(n<= 0);   
            }
            else{     
                 System.out.print("Enter 1 or 0 only! please try again\n");
            }
            this.Pn = new double[this.n +1];
        }while((choice!=1 && choice!=0 ));
                 

    }
    public void Calculate(){
        this.rho= this.lambda / this.mu;
        this.L= this.lambda / (this.mu - this.lambda);
        this.Lq= this.lambda * this.lambda / (this.mu * (this.mu - this.lambda));
        this.W= 1 / (this.mu - this.lambda);
        this.Wq= this.lambda / (this.mu * (this.mu - this.lambda));
        for (int i = 0; i <= n; i++) {
            this.Pn[i] = (1 - this.rho) * Math.pow(this.rho, i);
        }

    }
    public void Display(){
        Calculate();
        System.out.printf("M/M/1 Results:%n");
        System.out.printf("Traffic Intensity (rho): %.4f%n", this.rho);
        System.out.printf("Expected Number in System (L): %.4f%n", this.L);
        System.out.printf("Expected Queue Length (Lq): %.4f%n", this.Lq);
        System.out.printf("Expected Time in System (W): %.4f hours%n", this.W);
        System.out.printf("Expected Waiting Time in Queue (Wq): %.4f hours%n", this.Wq);
        Display_probability();

    }
    public void Display_probability(){
        Calculate();
        for (int i = 0; i <= n; i++) {
            System.out.printf("P(%d customers): %.4f%n", i, this.Pn[i]);
        }
    }
}


class MMC extends Queue{
    double P0;

    public MMC(){
        Scanner scanner = new Scanner(System.in);


        do{
            System.out.print("Enter arrival rate (lambda): ");
            this.lambda = scanner.nextDouble();
            while(this.lambda<=0){
                System.out.println("Invalid Input Lambda cannot be <=0. Please enter again.");
                System.out.print("Enter arrival rate (lambda): ");
                this.lambda = scanner.nextDouble();
            }


            System.out.print("Enter service rate (mu): ");
            this.mu = scanner.nextDouble();
            while(this.mu<=0){
                System.out.println("Invalid Input Mu cannot be <=0. Please enter again.");
                System.out.print("Enter service rate (mu): ");
                this.mu = scanner.nextDouble();
            }
            System.out.print("Enter number of servers (c): ");
            this.c = scanner.nextInt();
            while(c<1){
                System.out.println("Invalid Input Number of Servers cannot be <1. Please enter again.");
                System.out.print("Enter number of servers (c): ");
                this.c = scanner.nextInt();
            }

            // checking if the traffic intensity is greater or equal then 1
            if( this.lambda / (this.c * this.mu) >= 1) {
                System.out.println("The system is unstable because the traffic intensity is greater then 1");
                System.out.print("Please reenter all the necessary parameters!");
            }
        }while(this.lambda / (this.c * this.mu) >= 1);
        this.system_type = "MM"+this.c;
        

        int choice;
        do{
          System.out.print("Would you like to use a custom value of n for calculating P(n)? (1 = yes, 0 = no): ");
          choice = scanner.nextInt();
            if(choice == 1 || choice == 0){
                do{
                 if(choice == 1){
                     System.out.print("Enter the number of customers (n): ");
                     this.n = scanner.nextInt();
                        if(n <= 0){
                            System.out.print("n should not be below or equal than zero!\n");
                        }
                 }
                        else if(choice == 0){
                             this.n = 5;
                        }
                
                 
                }while(n<= 0);   
            }
            else{     
                 System.out.print("Enter 1 or 0 only! please try again\n");
            }
            this.Pn = new double[this.n + 1];
        }while((choice!=1 && choice!=0 ));
        
    }
    public void Calculate(){
        this.rho = this.lambda / (this.c * this.mu);
        double sum = 0;
        for (int i = 0; i < this.c; i++) {
            sum += Math.pow(this.c * this.rho, i) / factorial(i);
        }
        this.P0 = 1 / (sum + Math.pow(this.c * this.rho, this.c) / (factorial(this.c)
                * (1 - this.rho)));
        this.Lq = this.P0 * Math.pow(this.c * this.rho, this.c) * this.rho / (factorial(this.c)
                * Math.pow(1 - this.rho, 2));
        this.L = this.Lq + this.c * this.rho;
        this.Wq = Lq / lambda;
        this.W = this.Wq + 1 / this.mu;
        for (int i = 0; i <= this.n; i++) {
            this.Pn[i] = (i < this.c) ? this.P0 * Math.pow(this.c * this.rho, i) / factorial(i) : this.P0 * Math.pow(this.c * this.rho, i) / (Math.pow(this.c, i -this.c) * factorial(this.c));
        }

    }
    public void Display(){
        Calculate();
        System.out.printf("M/M/%d Results:%n", this.c);
        System.out.printf("Traffic Intensity (rho): %.4f%n", this.rho);
        System.out.printf("Expected Number in System (L): %.4f%n", this.L);
        System.out.printf("Expected Queue Length (Lq): %.4f%n", this.Lq);
        System.out.printf("Expected Time in System (W): %.4f hours%n", this.W);
        System.out.printf("Expected Waiting Time in Queue (Wq): %.4f hours%n", this.Wq);
        Display_probability();
        

    }
    public void Display_probability(){
        Calculate();
        for (int i = 0; i <= this.n; i++) {
            System.out.printf("P(%d customers): %.4f%n", i, this.Pn[i]);
        }
    }
    public long factorial(int i) {
        if (i == 0) return 1;
        long result = 1;
        for (int s = 1; s <= i; s++) {
            result *= s;
        }
        return result;
    }
}




public class QueueingCalculator {
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int proceed = 1;
    while(proceed!=0){
        System.out.println("=======================================================================================");
        System.out.printf("%sWelcome to Queue Calculator%s%n", "", "");
        System.out.println("Calculation Mode:");
        System.out.println("1 - Single calculation Mode\n2 - Comparison calculation");

        System.out.print("Enter desired calculation Mode: ");
        int calculationMode = scanner.nextInt();
        while(calculationMode<1 || calculationMode>2){
            System.out.printf("Invalid Input! Please enter again.%n");
            System.out.print("Enter desired calculation Mode: ");
            calculationMode = scanner.nextInt();
        }

        if(calculationMode == 1){
            System.out.println("=======================================================================================");
            System.out.println("System calculation Mode:");
            System.out.println("1 - MM1 (Single server system) \n2 - MMC (Multiple server system) ");
            System.out.print("Enter desired system: ");
            int queue_system = scanner.nextInt();
            while(queue_system<1 || queue_system>2){
                System.out.printf("Invalid Input! Please enter again.%n");
                System.out.println("1 - MM1 (Single server system) \n2 - MMC (Multiple server system) ");
                System.out.print("Enter desired system: ");
                queue_system = scanner.nextInt();

            }
            System.out.println("=======================================================================================");
            if(queue_system == 1){
                final MM1 MM1_object = new MM1();
                System.out.println("=======================================================================================");
                MM1_object.Display();
            }
            else{
                final MMC MMC_object = new MMC();
                System.out.println("=======================================================================================");
                MMC_object.Display();
            }

        }
        else{
            Queue [] arry = new Queue[2];
            for(int i = 0; i < 2; i++){
                System.out.println("=======================================================================================");
                System.out.println("1 - MM1 (Single server system) \n2 - MMC (Multiple server system) ");
                System.out.print("Enter desired system: ");
                int queue_system = scanner.nextInt();
                while(queue_system<1 || queue_system>2){
                    System.out.printf("Invalid Input! Please enter again.%n");
                    System.out.println("1 - MM1 (Single server system) \n2 - MMC (Multiple server system) ");
                    System.out.print("Enter desired system: ");
                    queue_system = scanner.nextInt();

                }
                if(queue_system == 1){
                    arry[i] = new MM1();
                    arry[i].Calculate();
                }
                else{
                    arry[i] = new MMC();
                    arry[i].Calculate();
                }
            }
            System.out.println("=======================================================================================");
            System.out.printf("Comparison Calculation Mode:%n");
            System.out.printf("%50s%10s%10s%n","",arry[0].system_type,arry[1].system_type);
            System.out.printf("%50s%10.4f%10.4f%n","Traffic Intensity (rho): ",arry[0].rho,arry[1].rho);
            System.out.printf("%50s%10.4f%10.4f%n","Expected Number in System (L): ",arry[0].L,arry[1].L);
            System.out.printf("%50s%10.4f%10.4f%n","Expected Queue Length (Lq): ",arry[0].Lq,arry[1].Lq);
            System.out.printf("%50s%10.4f%10.4f%n","Expected Time in System in hours (W): ",arry[0].W,arry[1].W);
            System.out.printf("%50s%10.4f%10.4f%n","Expected Waiting Time in Queue in hours(Wq): ",arry[0].Wq,arry[1].Wq);
           int largest_length = arry[0].Pn.length > arry[1].Pn.length ? arry[0].Pn.length : arry[1].Pn.length ;
            for(int i = 0; i < largest_length; i++){
                String queue_0 = i<arry[0].Pn.length ? String.format("%.4f",arry[0].Pn[i] )  : "NA";
                String queue_1 = i<arry[1].Pn.length ? String.format("%.4f",arry[1].Pn[i] ) : "NA";
                System.out.printf("%34sP(%d customers): %10s%10s%n","",i,queue_0,queue_1);
            }



        }
        System.out.println("=======================================================================================");
        System.out.print("Enter [0-to stop enter 1-to continue] :");
        proceed = scanner.nextInt();


    }






    }
 }
