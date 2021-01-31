/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OS;

/**
 *
 * @author maha
 */

public class PCB {

   private String PID;
   public int CUT;
   private String State;
   private int ECU;
   private String Status;

    public PCB(String PID, String State, int ECU, String Status) {
        this.PID = PID;
        this.CUT = 0;
        this.State = State;
        this.ECU = ECU;
        this.Status= Status;
    }

    public String getPID() {
        return PID;
    }

    public String getState() {
        return State;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public void setState(String State) {
        this.State = State;
    }

    public int getECU() {
        return ECU;
    }

    public void setECU(int ECU) {
        this.ECU = ECU;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
   
   










}