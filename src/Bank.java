import java.time.LocalTime;


public class Bank {
    public static void main(String[] args) {
    BankAccount sbi = new BankAccount(500.0);
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                sbi.withdraw(43.20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    };

    Thread thread1 = new Thread(runnable);
    Thread thread2 = new Thread(runnable);
    thread1.start();
    thread2.start();
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public synchronized void withdraw(double amount) throws InterruptedException {
        StringBuilder message = new StringBuilder()
                .append(Thread.currentThread().getName())
                .append(" is attempting to withdraw money.");
        System.out.println(message.toString());

        if (balance >= amount) {

            System.out.println("Processing Withdraw");
            balance -= amount;
            Thread.sleep(200);

            StringBuilder message2 = new StringBuilder()
                    .append("The amount ")
                    .append(amount)
                    .append(" was withdrawn by ")
                    .append(Thread.currentThread().getName())
                    .append(" at ")
                    .append(LocalTime.now());

            System.out.println(message2.toString());

            System.out.println("Current balance is " + balance);
        } else {
            StringBuilder message2 = new StringBuilder().
                    append(Thread.currentThread().getName())
                    .append(" can't withdraw - Insufficient funds.");
            System.out.println(message2.toString());
        }
    }

    public double getBalance() {
        return balance;
    }
}