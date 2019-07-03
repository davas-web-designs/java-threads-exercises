# Threads

_How is this achieved?_

## First way: 

1. Create a class that implements a Runnable interface ( run() method )
2. Write the code inside the run method
3. Create an instance of this class and store it in a variable of type Runnable
4. Build an instance of the Thread class with the Runnable object we just created as parameter (in ints constructor)
5. Run the thread via the start() method built in the Thread class

In the first example program (ThreadsBall, first commit) we practice this technique. It is demonstrated how we can launch simultaneously 3 threads represented as balls and stop them indivually. This is out first glance of what a thread is. Next we will look at thread sync.

## Second way:

1. Create a class that extends Thread
2. Write the code inside the run method
3. Start the thread

We can achieve thread synchronization with the method join(). 
In the second example, ThreadSync, we showcase this functionality of syncing threads, it tourns out to be quite interesting...

## Third example, the bank application:

1. We build a very simple app to make transfers between accounts. We implement this with the thread techniques we learned before...
2. In the commit "corrupt bank" we can see that there was something else to learn as our threads are not been synched successfully leading to a problem in the total money in the bank. It is dissapearing.