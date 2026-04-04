class Solution {
    func decodeCiphertext(_ encodedText: String, _ rows: Int) -> String {
        if rows == 1 { return encodedText }
        
        let encodedSize = encodedText.count
        let columns = encodedSize / rows
        var matrix = Array(repeating: Array(repeating: Character("\0"), count: columns), count: rows)
        
        let chars = Array(encodedText)
        var c = 0
        
        // Fill the matrix row-wise
        for i in 0..<rows {
            if c == encodedSize { break }
            for j in 0..<columns {
                if c == encodedSize { break }
                matrix[i][j] = chars[c]
                c += 1
            }
        }
        
        var originalText = ""
        
        var row = 0
        var column = 0
        var columnCounter = 0
        
        // Traverse diagonally
        while column < columns {
            let character = matrix[row][column]
            if character == "\0" { break }
            
            originalText.append(character)
            row += 1
            column += 1
            
            if row == rows {
                row = 0
                columnCounter += 1
                column = columnCounter
            }
        }
        
        // Trim trailing spaces
        var result = Array(originalText)
        var limit = result.count
        
        for i in stride(from: result.count - 1, through: 0, by: -1) {
            if result[i] == " " {
                limit -= 1
            } else {
                break
            }
        }
        
        return String(result[0..<limit])
    }
}
