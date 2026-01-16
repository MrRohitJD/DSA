
public class LL {

    private ListNode head;
    private ListNode tail;
    private int size;

    public LL() {
        this.size = 0;
    }

    private class ListNode {

        private int val;
        private ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public void insertFirst(int val) {
        ListNode ListNode = new ListNode(val);
        ListNode.next = head;
        head = ListNode;
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    public void display() {
        ListNode temp = head;
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
        ListNode ListNode = new ListNode(val);
        tail.next = ListNode;
        tail = ListNode;
        size++;
    }

    public void insertAtindex(int val, int index) {
        if (index == 0) {
            insertFirst(val);
            return;
        }
        if (index == size) {
            insertLast(val);
            return;
        }
        ListNode temp = head;
        for (int i = 1; i <= index - 1; i++) {
            temp = temp.next;
        }
        ListNode ListNode = new ListNode(val);
        ListNode.next = temp.next;
        temp.next = ListNode;
        size++;

    }

    public int deleteFirst() {
        int val = head.val;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return val;
    }

    public ListNode get(int index) {
        ListNode ListNode = head;
        for (int i = 0; i < index; i++) {
            ListNode = ListNode.next;
        }
        return ListNode;
    }

    public int deleteLast() {
        if (size <= 1) {
            return deleteFirst();
        }
        ListNode ListNode = get(size - 2);
        int val = ListNode.val;

        tail = ListNode;
        tail.next = null;
        size--;
        return val;
    }

    public int delete(int index) {
        if (index == 0) {
            return deleteFirst();
        }
        if (index == size) {
            return deleteLast();
        }
        ListNode previousNode = get(index - 1);
        int val = previousNode.next.val;
        previousNode.next = previousNode.next.next;

        return val;
    }

    public int find(int val) {
        ListNode temp = head;
        int size = 0;
        while (temp != null) {
            size++;
            if (temp.val == val) {
                return size;
            }
            temp = temp.next;
        }
        return -1;
    }

    public void removeDuplicate() {
        ListNode ListNode = head;
        while (ListNode.next != null) {
            if (ListNode.next != null && ListNode.val == ListNode.next.val) {
                ListNode.next = ListNode.next.next;
                size--;
            } else {
                ListNode = ListNode.next;
            }
        }
        tail = ListNode;
        tail.next = null;
    }

    public LL merge_two_ll(LL ll1, LL ll2) {
        ListNode f = ll1.head;
        ListNode s = ll2.head;
        LL ans = new LL();
        while (f != null && s != null) {
            if (f.val <= s.val) {
                ans.insertLast(f.val);
                f = f.next;
            } else {
                ans.insertLast(s.val);
                s = s.next;
            }
        }
        while (f != null) {
            ans.insertLast(f.val);
            f = f.next;
        }
        while (s != null) {
            ans.insertLast(s.val);
            s = s.next;
        }
        return ans;
    }

    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                return true;
            }
        }
        return false;

    }

    public int lenghtOfCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (slow == fast) {
                int len = 1;
                ListNode temp = slow.next;

                while (temp != slow) {
                    temp = temp.next;
                    len++;
                }
                return len;
            }
        }
        return -1;
    }

    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        int len = 0;

        while (fast != null && fast.next != null) {

            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {

                len = lenghtOfCycle(slow);
                break;
            }
        }
        if (len == 0) {
            return null;
        }
        ListNode s = head;
        ListNode f = head;

        while (len > 0) {
            s = s.next;
            len--;
        }

        while (s != f) {
            s = s.next;
            f = f.next;
        }
        return s;
    }

    private int findSquare(int num) {
        int ans = 0;

        while (num > 0) {
            int rem = num % 10;
            ans += rem * rem;
            num /= 10;
        }
        return ans;
    }

    public boolean isHappy(int n) {

        int slow = n;
        int fast = n;

        do {
            slow = findSquare(slow);
            fast = findSquare(findSquare(fast));
        } while (slow != fast);

        if (slow == 1) {
            return true;
        }
        return false;
    }

    public ListNode mid(ListNode head) {
        ListNode s = head;
        ListNode f = head;

        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
        }
        return s;
    }



    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode mid = mid(head);
        ListNode rightHead = mid.next;
        mid.next = null; // split

        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);

        return merge(left, right);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        curr.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    public void reverseRec(ListNode node) {
        if (node == tail) {
            head = tail;
            return;
        }
        reverse(node.next);
        tail.next = node;
        tail = node;
        tail.next = null;

    }

    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode mid = mid(head);
        ListNode midHead = reverse(mid);
        ListNode temp = head;
        while (midHead != null) {
            if (temp.val != midHead.val) {
                return false;
            }
            temp = temp.next;
            midHead = midHead.next;
        }
        return true;

    }

    public ListNode rev(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public void reorderList(ListNode head) {
        if (head ==null || head.next ==null) {
            return;
        }
        ListNode mid = mid(head);
        ListNode hs = rev(mid);
        ListNode hf =head;
        
        while(hf !=null && hs != null){
            
            ListNode temp = hf.next;
            hf.next = hs;
            hf =temp;

            temp = hs.next;
            hs.next = hf;
            hs =temp;
            
        }
        if(hf != null){
            hf.next =null;
        }

    }



    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode headnode =head;
        while(headnode != null){
            ListNode last =null;

            for (int i = 1; i <= k; i++) {
                last = headnode;
                headnode = headnode.next;
            }
            ListNode temp =headnode;
            ListNode newhead = rev(temp);
            temp.next =last.next;
        }
        return head;
    }
    public static void main(String[] args) {
        LL ll = new LL();
        ll.insertLast(1);
        ll.insertLast(2);
        ll.insertLast(3);
        // ll.insertLast(3);
        // ll.insertLast(2);
        ll.insertLast(1);

        LL ll2 = new LL();
        ll2.insertLast(1);
        ll2.insertLast(3);
        ll2.insertLast(4);

        System.out.println(ll.isPalindrome(ll.head));

    }
}
