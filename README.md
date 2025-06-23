  
---

# Queuing Calculator (Java CLI)

A command-line Java application for analyzing queuing systems using M/M/1 and M/M/c models. This tool helps calculate performance metrics like average waiting time, number of customers in the system, and server utilization.

> 🧪 Built for learning and experimentation with basic queueing theory models.

---

## 💡 Features

- Supports two calculation modes:
  - ✅ **Single Calculation Mode**: Evaluate a single queue (M/M/1 or M/M/c)
  - 📊 **Comparison Calculation Mode**: Compare M/M/1 with M/M/c configurations
- Systems supported:
  - **M/M/1**: Single-server queue
  - **M/M/c**: Multi-server queue
- Outputs key metrics:
  - L, Lq, W, Wq, ρ, and P₀ up to 5 customers

---

## 🧮 Queuing Formulas Used

### M/M/1

- **Utilization** (ρ) = λ / μ
- **L** = λ / (μ - λ)
- **Lq** = λ² / [μ (μ - λ)]
- **W** = 1 / (μ - λ)
- **Wq** = λ / [μ (μ - λ)]
- **P₀** = 1 - ρ

### M/M/c

- **Utilization**: ρ = λ / (c * μ)
- **P₀** = [ Σₖ₌₀⁽ᶜ⁻¹⁾ ( (λ/μ)ᵏ / k! ) + ( (λ/μ)ᶜ / (c! * (1 - ρ)) ) ]⁻¹
- **Lq** = [ (λμ)(λ/μ)ᶜ / (c! (1 - ρ)²) ] * P₀
- **L** = Lq + λ / μ
- **Wq** = Lq / λ
- **W** = Wq + 1 / μ

---

## 🏁 Getting Started

### ✅ Requirements

- Java 8+
- Optional: Maven or any IDE

### 🔧 Run Instructions

```bash
git clone https://github.com/Zikri809/Queuing-Calculator.git
cd Queuing-Calculator/queuing-calculator/src/main/java/org/example

javac QueueingCalculator.java
java QueueingCalculator
```

---

🧑‍💻 Example Outputs

🔹 Single Calculation Mode (M/M/1)
```bash
=======================================================================================
Welcome to Queue Calculator
Calculation Mode:
1 - Single calculation Mode
2 - Comparison calculation
Enter desired calculation Mode: 1
=======================================================================================
System calculation Mode:
1 - MM1 (Single server system) 
2 - MMC (Multiple server system) 
Enter desired system: 1
=======================================================================================
Enter arrival rate (lambda): 0.4
Enter service rate (mu): 0.5
Would you like to use a custom value of n for calculating P(n)? (1 = yes, 0 = no): 1
Enter the number of customers (n): 7
=======================================================================================
M/M/1 Results:
Traffic Intensity (rho): 0.8000
Expected Number in System (L): 4.0000
Expected Queue Length (Lq): 3.2000
Expected Time in System (W): 10.0000 hours
Expected Waiting Time in Queue (Wq): 8.0000 hours
P(0 customers): 0.2000
P(1 customers): 0.1600
P(2 customers): 0.1280
P(3 customers): 0.1024
P(4 customers): 0.0819
P(5 customers): 0.0655
P(6 customers): 0.0524
P(7 customers): 0.0419
=======================================================================================
Enter [0-to stop enter 1-to continue] :
```

---

🔹 Comparison Calculation Mode

```bash
=======================================================================================
Welcome to Queue Calculator
Calculation Mode:
1 - Single calculation Mode
2 - Comparison calculation
Enter desired calculation Mode: 2
=======================================================================================
1 - MM1 (Single server system) 
2 - MMC (Multiple server system) 
Enter desired system: 1
Enter arrival rate (lambda): 0.4
Enter service rate (mu): 0.5
Would you like to use a custom value of n for calculating P(n)? (1 = yes, 0 = no): 1
Enter the number of customers (n): 10
=======================================================================================
1 - MM1 (Single server system) 
2 - MMC (Multiple server system) 
Enter desired system: 2
Enter arrival rate (lambda): 0.4
Enter service rate (mu): 0.5
Enter number of servers (c): 3
Would you like to use a custom value of n for calculating P(n)? (1 = yes, 0 = no): 1
Enter the number of customers (n): 12
=======================================================================================
Comparison Calculation Mode:
                                                         MM1       MM3
                         Traffic Intensity (rho):     0.8000    0.2667
                   Expected Number in System (L):     4.0000    0.8189
                      Expected Queue Length (Lq):     3.2000    0.0189
            Expected Time in System in hours (W):    10.0000    2.0473
     Expected Waiting Time in Queue in hours(Wq):     8.0000    0.0473
                                  P(0 customers):     0.2000    0.4472
                                  P(1 customers):     0.1600    0.3577
                                  P(2 customers):     0.1280    0.1431
                                  P(3 customers):     0.1024    0.0382
                                  P(4 customers):     0.0819    0.0102
                                  P(5 customers):     0.0655    0.0027
                                  P(6 customers):     0.0524    0.0007
                                  P(7 customers):     0.0419    0.0002
                                  P(8 customers):     0.0336    0.0001
                                  P(9 customers):     0.0268    0.0000
                                 P(10 customers):     0.0215    0.0000
                                 P(11 customers):         NA    0.0000
                                 P(12 customers):         NA    0.0000
=======================================================================================
Enter [0-to stop enter 1-to continue] :
```

---

📁 Directory Structure

```bash
Queuing-Calculator/
└── queuing-calculator/
    └── src/
        └── main/
            └── java/
                └── org/
                    └── example/
                        └── QueueingCalculator.java
```


---

📜 License

This project is licensed under the MIT License. See the LICENSE file for more information.


---

🙋‍♂️ Author

Zikri – GitHub Profile
Hamzi - GitHub Profile

For questions or improvements, feel free to open an issue or pull request!

---


