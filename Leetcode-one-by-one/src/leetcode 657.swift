class Solution {
    func judgeCircle(_ moves: String) -> Bool {
        var ch = Array(repeating: 0, count: 26)
        
        for move in moves {
            let index = Int(move.asciiValue! - Character("A").asciiValue!)
            ch[index] += 1
        }
        
        return ch[Int(Character("U").asciiValue! - Character("A").asciiValue!)] ==
               ch[Int(Character("D").asciiValue! - Character("A").asciiValue!)] &&
               ch[Int(Character("L").asciiValue! - Character("A").asciiValue!)] ==
               ch[Int(Character("R").asciiValue! - Character("A").asciiValue!)]
    }
}
