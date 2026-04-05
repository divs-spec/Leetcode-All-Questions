class Solution {
    func judgeCircle(_ moves: String) -> Bool {
        var u = 0, d = 0, l = 0, r = 0
        
        for c in moves {
            if c == "U" { u += 1 }
            else if c == "D" { d += 1 }
            else if c == "L" { l += 1 }
            else if c == "R" { r += 1 }
        }
        
        return u == d && l == r
    }
}
