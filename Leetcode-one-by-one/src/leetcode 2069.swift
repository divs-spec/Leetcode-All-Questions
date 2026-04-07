class Robot {
    
    var direction: Int
    let directionName: [String]
    let width: Int
    let height: Int
    var position: [Int]
    var stepMovement: [[Int]]
    var num: Int

    init(_ width: Int, _ height: Int) {
        self.width = width
        self.height = height
        self.position = [0, 0]
        self.direction = 0
        self.directionName = ["East", "North", "West", "South"]
        self.stepMovement = [[1,0], [0,1], [-1,0], [0,-1]]
        self.num = 0
    }
    
    private func lazyStep(_ steps: Int) {
        var num = steps
        let perimeter = 2 * (width + height) - 4
        
        num = num % perimeter
        if num == 0 {
            num = perimeter
        }
        
        while num > 0 {
            switch direction {
            case 0: // East
                if position[0] + num > width - 1 {
                    num -= (width - position[0] - 1)
                    direction = (direction + 1) % 4
                    position[0] = width - 1
                } else {
                    position[0] += num
                    num = 0
                }
                
            case 2: // West
                if position[0] - num < 0 {
                    num -= position[0]
                    direction = (direction + 1) % 4
                    position[0] = 0
                } else {
                    position[0] -= num
                    num = 0
                }
                
            case 1: // North
                if position[1] + num > height - 1 {
                    num -= (height - position[1] - 1)
                    direction = (direction + 1) % 4
                    position[1] = height - 1
                } else {
                    position[1] += num
                    num = 0
                }
                
            case 3: // South
                if position[1] - num < 0 {
                    num -= position[1]
                    direction = (direction + 1) % 4
                    position[1] = 0
                } else {
                    position[1] -= num
                    num = 0
                }
                
            default:
                break
            }
        }
        
        self.num = 0
    }
    
    func step(_ num: Int) {
        self.num += num
    }
    
    func getPos() -> [Int] {
        if num > 0 {
            lazyStep(num)
        }
        return position
    }
    
    func getDir() -> String {
        if num > 0 {
            lazyStep(num)
        }
        return directionName[direction]
    }
}
