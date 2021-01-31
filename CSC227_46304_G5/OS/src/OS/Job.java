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
public class Job {
    private String JID;
    private int ECU;
    private PCB pcb;// new

    public Job() {
        JID= "";
        ECU=0;
    }
    
public Job(String JID ,int ECU){
       this.JID = JID;
       this.ECU = ECU;
}

  public String getJID() {
        return JID;
    }

    public void setJID(String JID) {
        this.JID = JID;
    }

    public int getECU() {
    return ECU;
    }

    public void setECU(int ECU) {
        this.ECU = ECU;

    }

    public void setPcb(PCB pcb) {//new
        this.pcb = pcb;
    }

    public PCB getPcb() {//new
        return pcb;
    }
}
