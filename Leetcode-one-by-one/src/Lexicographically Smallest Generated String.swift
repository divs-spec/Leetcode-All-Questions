class Solution {
    func generateString(_ str1: String, _ str2: String) -> String {
        let n = str1.count
        let m = str2.count
        
        let str1Arr = Array(str1)
        let str2Arr = Array(str2)
        
        var s = Array(repeating: Character("a"), count: n + m - 1)
        var fixed = Array(repeating: 0, count: n + m - 1)
        
        // Apply 'T'
        for i in 0..<n {
            if str1Arr[i] == "T" {
                for j in i..<(i + m) {
                    if fixed[j] == 1 && s[j] != str2Arr[j - i] {
                        return ""
                    } else {
                        s[j] = str2Arr[j - i]
                        fixed[j] = 1
                    }
                }
            }
        }
        
        // Apply 'F'
        for i in 0..<n {
            if str1Arr[i] == "F" {
                var flag = false
                var idx = -1
                
                for j in stride(from: i + m - 1, through: i, by: -1) {
                    if str2Arr[j - i] != s[j] {
                        flag = true
                    }
                    if idx == -1 && fixed[j] == 0 {
                        idx = j
                    }
                }
                
                if flag {
                    continue
                } else if idx != -1 {
                    s[idx] = "b"
                } else {
                    return ""
                }
            }
        }
        
        return String(s)
    }
}
