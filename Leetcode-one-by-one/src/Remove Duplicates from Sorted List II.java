class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        Map<Integer, Integer> freq = new HashMap<>();
        ListNode curr = head;
        while (curr != null) {
            freq.put(curr.val, freq.getOrDefault(curr.val, 0) + 1);
            curr = curr.next;
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        curr = head;
        while (curr != null) {
            if (freq.get(curr.val) == 1) {
                tail.next = new ListNode(curr.val);
                tail = tail.next;
            }
            curr = curr.next;
        }
        return dummy.next;
    }
}
