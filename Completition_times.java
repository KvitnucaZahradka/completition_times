/**
 *
 * @author Pipjak
 */

import java.util.*;
import java.io.*;
import java.lang.*;

public class Completition_times {
    //Integer numOfjobs;
    
    // priority queue
    PriorityQueue<Integer[]> prior;
    
    // custom comparator
    Comparator cust;
    
    /* constructor */
    
    private Completition_times(){
        
        //this.numOfjobs = 10000;
        
        this.cust = (Comparator<Integer[]>) (Integer[] o1, Integer[] o2) -> {
            Integer diff1 = (o1[0]-o1[1]);
            Integer diff2 = (o2[0]-o2[1]);
            
            if(diff1.compareTo(diff2)!=0){
                return -diff1.compareTo(diff2);
            }
            else{
                
                diff1 = o1[0];
                diff2 = o2[0];
                return -diff1.compareTo(diff2);
            }
        };
    
        // priority queue with cust comparator
        this.prior = new PriorityQueue(10000,this.cust);
        
    }
    
    /* methods */
    
    // putting the job into the Priority Queue:
    private void putJob(int a, int b){
    
        Integer[] job = new Integer[2];
        
        job[0] = a;
        job[1] = b;
        
        // put into priority queue
        this.prior.add(job);
    }
    
    // calculating the weighted finishing time:
    private long finTime(){
    
        long result = 0, time = 0;
        Integer[] temp;
        
        while(!this.prior.isEmpty()){
        
            // getting the head of the queue and removing it
            temp = this.prior.poll();
            
            // printing some temp. results
            //System.out.println("weight " + temp[0] + " length/time " + temp[1]);
            
            // callculate the finishing time
            time += temp[1];
            
            // calculate the weighted time, we want to minimise
            result += (temp[0]*time);
        }
        return result;
    }

    
    public static void main(String[] args) throws IOException {
        
        Completition_times g = new Completition_times();
        g.run();
        
        System.out.println("The weighted finishing time is: " + g.finTime());
    }
    
    
    /* reading methods */
    
    // the read-in and run methods:
    public void run() throws IOException {
        
    try {
        
        File file = new File("jobs.txt");
        FileReader fileReader = new FileReader(file);
            
        BufferedReader bufferedReader = new BufferedReader(fileReader);    
        StringBuffer stringBuffer = new StringBuffer();
            
        String line;
            
        // reading the first line = number of jobs and skipping it since
        // I do not need that information   
        line = bufferedReader.readLine();
        
       //this.numOfjobs = Integer.parseInt(line);
        
        // read a line:
        while ((line = bufferedReader.readLine()) != null ) {
                
            int weight,length;
                
            String[] arr = line.split("\\s+");
                
            // weight and length are
            weight = Integer.parseInt(arr[0]);
            length = Integer.parseInt(arr[1]);
                
            // putting into the priority queue
            this.putJob(weight, length);
        }
            
        // closing the fileReader:
        fileReader.close();
            
        }catch (IOException e) {
             e.printStackTrace();
        }
    }
}
