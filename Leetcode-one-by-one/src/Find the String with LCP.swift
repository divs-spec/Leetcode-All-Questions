class Solution {
    func findTheString(_ lcp: [[Int]]) -> String {
        let n = lcp.count
        var word = Array(repeating: Character("\0"), count: n)
        var current = Character("a")

        // Construct string
        for i in 0..<n {
            if word[i] == "\0" {
                if current > "z" {
                    return ""
                }
                word[i] = current
                for j in (i + 1)..<n {
                    if lcp[i][j] > 0 {
                        word[j] = word[i]
                    }
                }
                current = Character(UnicodeScalar(current.asciiValue! + 1))
            }
        }

        // Verify LCP matrix
        for i in stride(from: n - 1, through: 0, by: -1) {
            for j in stride(from: n - 1, through: 0, by: -1) {
                if word[i] != word[j] {
                    if lcp[i][j] != 0 {
                        return ""
                    }
                } else {
                    if i == n - 1 || j == n - 1 {
                        if lcp[i][j] != 1 {
                            return ""
                        }
                    } else {
                        if lcp[i][j] != lcp[i + 1][j + 1] + 1 {
                            return ""
                        }
                    }
                }
            }
        }

        return String(word)
    }
}
