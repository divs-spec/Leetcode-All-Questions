/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public var val: Int
 *     public var next: ListNode?
 *     public init() {
 *         self.val = 0
 *         self.next = nil
 *     }
 *     public init(_ val: Int) {
 *         self.val = val
 *         self.next = nil
 *     }
 *     public init(_ val: Int, _ next: ListNode?) {
 *         self.val = val
 *         self.next = next
 *     }
 * }
 */

class Solution {
    func deleteMiddle(_ head: ListNode?) -> ListNode? {
        guard let head = head else { return nil }
        if head.next == nil { return nil }

        var slow: ListNode? = head
        var fast: ListNode? = head
        var prev: ListNode? = nil

        while fast != nil && fast?.next != nil {
            prev = slow
            slow = slow?.next
            fast = fast?.next?.next
        }

        prev?.next = slow?.next
        return head
    }
}
