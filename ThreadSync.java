public class ThreadSync{
    public static void main(String[] args){
        MultipleThreads h1 = new MultipleThreads();
        
        MultipleThreadsII h2 = new MultipleThreadsII(h1);
        
        h2.start();
        //we are placing these lines on porpuse to demonstrate that h2
        //wont start until h1 is done
        h1.start();

    }

}

class MultipleThreads extends Thread{
    public void run(){
        for(int i = 0; i < 15; i++){
            System.out.println("Executing thread: " + getName());

            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            
        }
    }
}

class MultipleThreadsII extends Thread{
    
    public MultipleThreadsII(Thread h){
        this.h = h;
    }
    public void run(){

        try{
            h.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        

        for(int i = 0; i < 15; i++){
            System.out.println("Executing thread: " + getName());

            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            
        }
    }
    private Thread h;
}