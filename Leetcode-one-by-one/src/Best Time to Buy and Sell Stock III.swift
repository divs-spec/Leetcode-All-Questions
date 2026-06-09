class Solution {
    func maxProfit(_ prices: [Int]) -> Int {
        var buy1 = Int.min
        var sell1 = 0

        var buy2 = Int.min
        var sell2 = 0

        for price in prices {
            buy1 = max(buy1, -price)
            sell1 = max(sell1, buy1 + price)

            buy2 = max(buy2, sell1 - price)
            sell2 = max(sell2, buy2 + price)
        }

        return sell2
    }
}
