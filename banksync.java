import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class banksync {
    public static void main(String args[]){

        Bank b = new Bank();
        for(int i = 0; i < 100; i++){

            exec_transfer r = new exec_transfer(b, i, 2000);

            Thread t = new Thread(r);

            t.start();

        }
    }
}

class Bank{

    public Bank(){
        
        accounts = new double[100];
        for(int i = 0; i < accounts.length; i++){
            accounts[i] = 2000;
        }
    }

    public void transfer(int from,int to, double qty){
        
        threadLock.lock();

        try {
            if(accounts[from] < qty ){
                //check if we have the money to do it
                return;
            }else{
                System.out.println(Thread.currentThread());
    
                accounts[from] -= qty;
                System.out.printf("%10.2f de %d para %d", qty, from, to);
                accounts[to] += qty;
                
                System.out.printf("Grand total: %10.2f%n", getTotal());
            }
        } finally{
            threadLock.unlock();
        }

        
    }

    public double getTotal(){
        double total = 0;
        for(double a: accounts){
            total += a;
        }
        return total;
    }

    private final double[] accounts;

    private Lock threadLock = new ReentrantLock();
}

class exec_transfer implements Runnable{


    public exec_transfer(Bank b, int from, double max){
        this.b = b;
        this.from = from;
        this.max = max;
    }

    public void run(){
        while(true){
            //random destination account
            int to = (int)(100*Math.random());
            //random quantity
            double qty = max*Math.random();

            b.transfer(from, to, qty);

            try {
                Thread.sleep((int)(Math.random()*10));
            } catch (InterruptedException e) {
                
            }
            
        }
    }

    private Bank b;
    private int from;
    private double max;
}