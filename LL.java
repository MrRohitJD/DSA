
public class LL {

    private Node head;
    private Node tail;
    private int size;

    public LL() {
        this.size = 0;
    }

    private class Node {

        private int val;
        private Node next;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    public void insertFirst(int val) {
        Node node = new Node(val);
        node.next = head;
        head = node;
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.val + "-->");
            temp = temp.next;
        }
    }

    public void insertLast(int val) {
        if (tail == null) {
            insertFirst(val);
            return;
        }
        Node node = new Node(val);
        tail.next = node;
        tail = node;
        size++;
    }
    public void insertAtindex(int val, int index){
        if(index ==0){
            insertFirst(val);
            return;
        }
        if(index == size){
            insertLast(val);
            return;
        }
        Node temp =head;
        for(int i=1;i<=index-1;i++){
            temp = temp.next;
        }
        Node node = new Node(val);
        node.next = temp.next;
        temp.next = node;
        size++;

    }
    public int deleteFirst(){
       int val = head.val;
       head = head.next;
       if(head == null){
        tail =null;
       }
       size--;
       return val;
    }
     public Node get(int index){
        Node node  = head;
        for (int i = 0; i < index; i++) {
            node =node.next;
        }
        return  node;
     }
     public int deleteLast(){
        if(size <= 1){
            return deleteFirst();
        }
        Node node =get(size-2);
        int val = node.val;

        tail =node;
        tail.next = null;
        size--;
        return val;
     }

     public int delete(int index){
        if(index == 0){
            return deleteFirst();
        }
        if(index ==size){
            return deleteLast();
        }
        Node previousNode = get(index-1);
        int val =previousNode.next.val;
        previousNode.next = previousNode.next.next;

        return  val;
     }

     public int  find(int val){
        Node temp = head;
        int size =0;
        while (temp != null) { 
            size++;
            if (temp.val == val) {
                return size;
            }
            temp = temp.next;
        }
        return -1;
     }


     public void removeDuplicate(){
        Node node = head;
        while (node.next != null) {
            if (node.next != null && node.val == node.next.val) {
                node.next = node.next.next;
                size--;
            }
            else{
                node = node.next;
            }
        }
        tail = node;
        tail.next = null;
     }

     public LL merge_two_ll(LL ll1, LL ll2){
        Node f = ll1.head;
        Node s = ll2.head;
        LL ans = new LL();
        while (f != null && s != null) {
            if(f.val <= s.val){
                ans.insertLast(f.val);
                f = f.next;
            }
            else{
                ans.insertLast(s.val);
                s = s.next;
            }
        }
        while(f != null){
            ans.insertLast(f.val);
            f = f.next;
        }
        while(s != null){
            ans.insertLast(s.val);
            s = s.next;
        }
        return ans;
     }
    
    public static void main(String[] args) {
        LL ll =new LL();
        ll.insertLast(1);
        ll.insertLast(2);
        ll.insertLast(4);
        
        LL ll2 =new LL();
        ll2.insertLast(1);
        ll2.insertLast(3);
        ll2.insertLast(4);
        // ll.insertAtindex(100, 0);
        ll.display();
        System.out.println();
        ll2.display();
        System.out.println();
        // ll.deleteFirst();
        // ll.deleteLast();
        // ll.delete(2);
        // System.out.println();
        // System.out.println(ll.find(700));
        // System.out.println();
        // ll.display();

        // ll.removeDuplicate();
        ll.merge_two_ll(ll, ll2).display();

        
        
        


    }
}
