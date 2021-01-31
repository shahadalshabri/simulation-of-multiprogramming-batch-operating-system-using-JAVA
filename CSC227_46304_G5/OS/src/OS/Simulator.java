/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author maha
 */
public class Simulator {
    
   public LinkedQueue jobs ;
   public LinkedQueue readyQueue;
   public int countJobs =0;
   public int normal = 0;
   public int abnormal = 0;
    
    public void generateFile() throws IOException{
        
    int maxValue=512;
    int minValue=16;
    int range= maxValue - minValue + 1;
    
        try {
            PrintWriter report = null;
            report = new PrintWriter(new File("jobs.txt"));
            
            for (int i = 0; i < 101; i++) {
                
                int val = (int)(Math.random()*range)+minValue;
                report.println("JID: "+ (i + 1) + ", ECU: " + val);
            }
            report.close();
}catch (FileNotFoundException e){
            System.out.println(e);
        }
       readFile();   
    }
    
      public void readFile() throws IOException{
 
        jobs = new LinkedQueue(2000000);
        File programs = new File("jobs.txt");
        Scanner scan;
        int ECU;
        
        String JID, j;
        
        try{
        scan = new Scanner(new FileReader(programs));
        
        while(scan.hasNextLine()){
            j = scan.nextLine();
            JID = j.substring(j.indexOf(":")+1,j.indexOf(","));
            ECU = Integer.parseInt(j.substring(j.indexOf("ECU:")+5));
            jobs.enqueue(new Job(JID, ECU));
        }
         scan.close();
         
        }catch(java.util.NoSuchElementException e){
          System.out.println(e);
        }
        
       generatePCB();

    }
   public void generatePCB(){
    
       readyQueue= new LinkedQueue(192000);
       PCB pcb;
       
       while(jobs.length() != 0){
           Job job= (Job) jobs.serve(); //casting 
           String JID = job.getJID();
           int ECU = job.getECU();
           pcb = new PCB(JID,"New", ECU,"");
           
           System.out.println("New PCB is created: "+ JID);
           
           pcb.setState("Ready");
           readyQueue.enqueue(pcb);
           System.out.println("PCB is added to ready queue: "+ JID);
           dispatchPCB();
       }
            results();
   }
   
      public boolean terminatesNor(){
		double tn = Math.random();
		if(tn <= 0.1) {
			return true;
		}
		return false;
	}                                                             
	public boolean terminatesAbn(){
                double ta = Math.random();
		if(ta <= 0.05) {
			return true;
		}
		return false;
	}
        
   public void dispatchPCB(){
       
       while(readyQueue.length() != 0){
           
           PCB newPCB= (PCB)readyQueue.serve();
           countJobs++;
           while(!newPCB.getState().equals("Terminated")){

               newPCB.setState("Running");
               if(newPCB.CUT > newPCB.getECU()){
                   abnormal++;
                   newPCB.setState("Terminated");

               }
                boolean f = true;
               if (terminatesNor()){ 
                   
                   normal++;
                   newPCB.setState("Terminated");
                   newPCB.setStatus("Normally");
                   f=false;
               }
               if (terminatesAbn()&&f){
                   abnormal++;
                   newPCB.setState("Terminated");
                   newPCB.setStatus("Abnormally");
                   
             
                   }
               
               newPCB.CUT++;
           }
    
                System.out.println("Process is dispatched");
                System.out.println("Process Information:\nPID:" + newPCB.getPID()+ " \nState: " + newPCB.getState() + " " + newPCB.getStatus() + "\nCUT: " + newPCB.CUT);
                System.out.println("******************************************");
       }
   }
   
   public void results(){
   
        try {
            PrintWriter report = null;
            report = new PrintWriter(new File("Result.txt"));
            report.println("Total number of jobs processed: " + countJobs);
            report.println("Number of jobs that completed normally: " + normal);
            report.println("Number of jobs that completed abnormally: " + abnormal);
            
            report.close();
            
        }catch (FileNotFoundException e){
            System.out.println(e);
        }
   } 
}
    