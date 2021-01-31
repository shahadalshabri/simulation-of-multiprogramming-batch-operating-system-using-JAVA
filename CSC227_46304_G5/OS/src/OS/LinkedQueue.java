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
public class LinkedQueue<T>{

private Node<T> head, tail;
private int size,storage;
	
public LinkedQueue(int s) {
head = tail = null;
size=0;
storage=s;

}


public boolean full() {
		return false;
}
	

public int length (){
		return size;
}  

public void enqueue(T e){
if(tail == null)
{head = tail = new Node<>(e);}
		
else {tail.next = new Node<>(e);
      tail = tail.next;
}
       
size++;}

public T serve() {
		T x = head.data;
		head = head.next;
		size--;
		if(size == 0)
		tail = null;
		return x;
}
/*private T serve(){
    if (head == null){
        return null;
    }
    Node<T> current = head;
    head = head.next;
    size--;
    if(empty()){
        tail = null;
        head = null;
    }
    return current;
}

    private boolean empty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}
