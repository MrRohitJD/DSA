
import java.util.HashMap;

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

    // https://leetcode.com/problems/remove-duplicates-from-sorted-list/
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

    // https://leetcode.com/problems/merge-two-sorted-lists/
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

    // https://leetcode.com/problems/linked-list-cycle/
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

    // https://leetcode.com/problems/linked-list-cycle-ii/
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

    // https://leetcode.com/problems/happy-number/
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

    // https://leetcode.com/problems/middle-of-the-linked-list/
    public ListNode mid(ListNode head) {
        ListNode s = head;
        ListNode f = head;

        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
        }
        return s;
    }

    // https://leetcode.com/problems/sort-list/
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

    // https://leetcode.com/problems/reverse-linked-list/
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

    // https://leetcode.com/problems/palindrome-linked-list/
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

    // https://leetcode.com/problems/reorder-list/
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode mid = mid(head);
        ListNode hs = rev(mid);
        ListNode hf = head;

        while (hf != null && hs != null) {

            ListNode temp = hf.next;
            hf.next = hs;
            hf = temp;

            temp = hs.next;
            hs.next = hf;
            hs = temp;

        }
        if (hf != null) {
            hf.next = null;
        }

    }

    // https://leetcode.com/problems/reverse-nodes-in-k-group/
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode headnode = head;
        while (headnode != null) {
            ListNode last = null;

            for (int i = 1; i <= k; i++) {
                last = headnode;
                headnode = headnode.next;
            }
            ListNode temp = headnode;
            ListNode newhead = rev(temp);
            temp.next = last.next;
        }
        return head;
    }

    public ListNode rev(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
        return head;

    }

    // https://leetcode.com/problems/remove-nth-node-from-end-of-list/
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        }
        ListNode newhead = rev(head);
        if (n == 1) {
            return rev(newhead.next);
        }

        ListNode temp = newhead;
        for (int i = 1; temp.next != null && i < n - 1; i++) {
            temp = temp.next;
        }
        if (temp != null) {
            temp.next = temp.next.next;
        }
        newhead = rev(newhead);
        return newhead;

    }

    // https://leetcode.com/problems/delete-node-in-a-linked-list/
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    // https://leetcode.com/problems/remove-linked-list-elements/
    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }

        ListNode temp = head;

        while (temp != null && temp.next != null) {
            if (temp.next.val == val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return head;
    }

    // https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
    public ListNode deleteDuplicates_82(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = head;

        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.next.val) {
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                prev.next = cur.next;
            } else {
                prev = cur.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    // https://leetcode.com/problems/delete-nodes-from-linked-list-present-in-array/
    public ListNode modifiedList(int[] nums, ListNode head) {
        java.util.HashMap hm = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            hm.put(nums[i], 1);
        }

        ListNode node = head;
        while (head != null && hm.containsKey(head.val)) {
            head = head.next;
        }

        while (node != null && node.next != null) {
            if (hm.get(node.next.val) != null) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
        return head;
    }

    // https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/
    public ListNode deleteMiddle(ListNode head) {
        if (head == null && head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow.next = slow.next.next;

        return head;
    }

    // https://leetcode.com/problems/swapping-nodes-in-a-linked-list/
    public ListNode swapNodes(ListNode head, int k) {

        ListNode node = head;
        ListNode nodef = head;
        ListNode nodel = head;

        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
        }
        for (int i = 1; i < k; i++) {
            nodef = nodef.next;
        }
        for (int i = 1; i < (len - k) + 1; i++) {
            nodel = nodel.next;
        }
        int temp = nodef.val;
        nodef.val = nodel.val;
        nodel.val = temp;
        return head;
    }

    // https://leetcode.com/problems/merge-in-between-linked-lists/
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
          if (list1 ==null ) {
            return list2;
        }
        if (list2 ==null ) {
            return list1;
        }

        ListNode l2tail = list2;
         while (l2tail != null && l2tail.next != null) { 
            l2tail = l2tail.next;
        }

        ListNode l1endNode = list1;
        for (int i = 0; i < b; i++) {
            l1endNode = l1endNode.next;
        }

        ListNode temp = list1;
        for (int i = 0; i < a - 1; i++) {
            temp = temp.next;
        }
        temp.next = list2;
        l2tail.next = l1endNode.next;
        return list1;
    }
    
    public ListNode revlist11(ListNode head){
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev =null;
        ListNode curr =head;
        
        while (curr != null) {
            ListNode next = curr.next;
            curr.next =prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    // https://leetcode.com/problems/double-a-number-represented-as-a-linked-list/
    public ListNode doubleIt(ListNode head) {
        int sum =0;
        ListNode temp = head;

        while (temp != null) {
        sum = sum * 10 + temp.val;  
        temp = temp.next;
        }

        long getnum = sum*2;
            if (getnum == 0) {
        return new ListNode(0);
    }

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (getnum >0) {
            long rem =(int)(getnum%10);
            curr.next = new ListNode((int) rem);
            curr = curr.next;
            getnum /=10;
        }
        ListNode newhead = dummy.next;
        return  revlist11(newhead);
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
