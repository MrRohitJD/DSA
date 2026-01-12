
public class DLL {

    private  Node head;
    private Node tail;
    private int size;
    
    public DLL() {
        this.size = 0;
    }


    private class Node{
        private  int val;
        private Node pre;
        private  Node next;
        
        public Node(int val) {
            this.val = val;
        }
        public Node(int val, DLL.Node pre, DLL.Node next) {
            this.val = val;
            this.pre = pre;
            this.next = next;
        }
    }

    public void insertFirst(int val){
        Node node = new Node(val);
        node.next = head;
        node.pre =null;

        if (head!= null) {
            head.pre = node;
        }
        head =node;
        if (tail == null) {
            tail = head;
        }
        size++;
    }
    public void display(){
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.val +" <-> ");
            temp = temp.next;
        }
        System.out.println("END");
        System.out.println("-------------");
        System.out.println("reverse print");

        Node last = tail;
        while (last != null) {
            System.out.print(last.val +" <-> ");
            last =last.pre;
        }
        System.out.println("END");
    }
    

    public void insertLast(int val){
        if (tail == null) {
            insertFirst(val);
            return;
        }
        Node node =new Node(val, tail , null);
        tail.next = node;
        tail = node;
        size++;
    }
    public static void main(String[] args) {
        DLL dl =new DLL();
        dl.insertFirst(10);
        dl.insertFirst(20);
        dl.insertFirst(40);
        dl.display();
    }

}