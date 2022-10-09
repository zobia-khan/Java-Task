//package studentCoding;
//import stdlib.*;

public class MyLinked {
    static class Node {
        public Node() { }
        public double item;
        public Node next;
    }

    int N;
    Node first;
    
    public MyLinked () {
        first = null;
        N = 0;
        checkInvariants ();
    }

    private void myassert (String s, boolean b) { if (!b) throw new Error ("Assertion failed: " + s); }
    private void checkInvariants() {
        myassert("Empty <==> first==null", (N == 0) == (first == null));
        Node x = first;
        for (int i = 0; i < N; i++) {
            if (x==null) {
                throw new Error ("List too short!");
            }
            x = x.next;
        }
        myassert("EndOfList == null", x == null);
    }

    public boolean isEmpty () { return first == null; }
    public int size () { return N; }
    public void add (double item) {
        Node newfirst = new Node ();
        newfirst.item = item;
        newfirst.next = first;
        first = newfirst;
        N++;
    }

    // return Double.NEGATIVE_INFINITY if the linked list is empty
    public double max () { return max (first); }
    private static double max (Node x) {
        if (x == null){
            return Double.NEGATIVE_INFINITY;
        }
        else {
            double num = max(x.next);
            if (x.item > num){
                num = x.item;
            }
            return num;
        
        }
        //return 0;
    }

    public double maxRecursive () { return maxRecursive (first, Double.NEGATIVE_INFINITY); }
    private static double maxRecursive (Node x, double result) {
        if(x==null){
            return result;
        }else{
            double v = x.item;
            if(v > result){
                return maxRecursive(x.next,v);
            }else{
                return maxRecursive(x.next,result);
            }
        }
        //return 0;
    }

    // delete the kth element
    public void delete (int k) {
        if (k < 0 || k >= N) throw new IllegalArgumentException ();
   
        if (first == null)
            return;
  
        // Store head node
        Node temp = first;
  
        // If head needs to be removed
        if (k == 0) {
            first = temp.next; // Change head
            return;
        }
  
        // Find previous node of the node to be deleted
        for (int i = 0; temp != null && i < k - 1;
             i++)
            temp = temp.next;
  
        // If position is more than number of nodes
        if (temp == null || temp.next == null)
            return;
  
        // Node temp->next is the node to be deleted
        // Store pointer to the next of node to be deleted
        Node next = temp.next.next;
  
        temp.next = next; // Unlink the deleted node from list
        //checkInvariants ();
    }

    // reverse the list "in place"... without creating any new nodes
    public void reverse () {
        Node previous = null;
		Node current = first;
		Node next;
		while (current != null) {
			next = current.next;
			current.next = previous;
			previous = current;
			current = next;
		}
		first = previous;
        checkInvariants ();
	}
        
    

    // remove 
    public void remove (double item) {
        
        Node temp = first, prev = null;

        // If head node itself holds the key
        // or multiple occurrences of key
        while (temp != null && temp.item == item) 
        {
            first = temp.next; // Changed head
            temp = first; // Change Temp
        }
  
        // Delete occurrences other than head
        while (temp != null) 
        {
            // Search for the key to be deleted,
            // keep track of the previous node
            // as we need to change 'prev->next'
            while (temp != null && temp.item != item) 
            {
                prev = temp;
                temp = temp.next;
            }
  
            // If key was not present in linked list
            if (temp == null)
                return;
  
            // Unlink the node from linked list
            prev.next = temp.next;
  
            // Update Temp for next iteration of outer loop
            temp = prev.next;
        }
        //checkInvariants ();
    }


    private static void print (String s, MyLinked b) {
        System.out.print(s + ": ");
        for (Node x = b.first; x != null; x = x.next)
            System.out.print(x.item + " ");
        System.out.println();
    }
    private static void print (String s, MyLinked b, double i) {
        System.out.print(s + ": ");
        for (Node x = b.first; x != null; x = x.next)
            System.out.print(x.item + " ");
        System.out.println(": " + i);
    }


    private static void testMax () {
        MyLinked b = new MyLinked ();
        print ("empty", b, b.max());
        b.add (-1);
        print ("singleton", b, b.max());
        b.add (-2);
        b.add (-3);
        b.add (-4);
        print ("at end", b, b.max());
        b.add (5);
        print ("at beginning", b, b.max());
        b.add (3);
        b.add (2);
        b.add (4);
        print ("in the middle", b, b.max());
    }
    private static void testMaxRecursive () {
        MyLinked b = new MyLinked ();
        print ("empty", b, b.maxRecursive());
        b.add (-1);
        print ("singleton", b, b.maxRecursive());
        b.add (-2);
        b.add (-3);
        b.add (-4);
        print ("at end", b, b.maxRecursive());
        b.add (5);
        print ("at beginning", b, b.maxRecursive());
        b.add (3);
        b.add (2);
        b.add (4);
        print ("in the middle", b, b.maxRecursive());
    }
    private static void testDelete () {
        MyLinked b = new MyLinked ();
        b.add (1);
        print ("singleton", b);
        b.delete (0);
        print ("deleted", b);
        for (double i = 1; i < 13; i++) {
            b.add (i);
        }
        print ("bigger list", b);
        b.delete (0);
        print ("deleted at beginning", b);
        b.delete (10);
        print ("deleted at end", b);
        b.delete (4);
        print ("deleted in middle", b);
    }
    private static void testReverse () {
        MyLinked b = new MyLinked ();
        b.reverse ();
        print ("reverse empty", b);
        b.add (1);
        print ("singleton", b);
        b.reverse ();
        print ("reverse singleton", b);
        b.add (2);
        print ("two", b);
        b.reverse ();
        print ("reverse two", b);
        b.reverse ();
        print ("reverse again", b);
        for (double i = 3; i < 7; i++) {
            b.add (i);
            b.add (i);
        }
        print ("bigger list", b);
        b.reverse ();
        print ("reversed", b);
    }
    private static void testRemove () {
        MyLinked b = new MyLinked ();
        b.remove (4);
        print ("removed 4 from empty", b);
        b.add (1);
        b.remove (4);
        print ("removed 4 from singelton", b);
        b.remove (1);
        print ("removed 1 from singelton", b);
        for (double i = 1; i < 5; i++) {
            b.add (i);
            b.add (i);
        }
        for (double i = 1; i < 5; i++) {
            b.add (i);
            b.add (i);
            b.add (i);
            b.add (i);
            b.add (i);
        }
        print ("longer list", b);
        b.remove (9);
        print ("removed all 9s", b); // does nothing
        b.remove (3);
        print ("removed all 3s", b);
        b.remove (1);
        print ("removed all 1s", b);
        b.remove (4);
        print ("removed all 4s", b);
        b.remove (2);
        print ("removed all 2s", b); // should be empty
    }

    public static void main (String args[]) {
        testMax ();
        testMaxRecursive ();
         testDelete ();
         testReverse ();
         testRemove ();
    }
}