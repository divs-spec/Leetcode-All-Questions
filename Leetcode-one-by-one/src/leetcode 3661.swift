class Solution {
    
    struct Robot {
        let position: Int
        let distance: Int
    }
    
    let INF = Int.max
    let NEG_INF = Int.min
    
    func maxWalls(_ robots: [Int], _ distance: [Int], _ walls: [Int]) -> Int {
        let n = robots.count
        var robotRecords = [Robot]()
        
        for i in 0..<n {
            robotRecords.append(Robot(position: robots[i], distance: distance[i]))
        }
        
        robotRecords.sort { $0.position < $1.position }
        let sortedWalls = walls.sorted()
        
        return solve(robotRecords, n, sortedWalls)
    }
    
    private func solve(_ robots: [Robot], _ n: Int, _ walls: [Int]) -> Int {
        var dpLeft = Array(repeating: 0, count: n)
        var dpRight = Array(repeating: 0, count: n)
        
        let leftWall = prepareLeftWall(robots, n, walls)
        let rightWall = prepareRightWall(robots, n, walls)
        let common = prepareCommon(robots, n, walls)
        
        dpLeft[0] = leftWall[0]
        dpRight[0] = rightWall[0]
        
        for i in 1..<n {
            dpLeft[i] = leftWall[i] + max(
                dpLeft[i - 1],
                dpRight[i - 1] - common[i]
            )
            
            dpRight[i] = rightWall[i] + max(
                dpLeft[i - 1],
                dpRight[i - 1]
            )
        }
        
        return max(dpLeft[n - 1], dpRight[n - 1])
    }
    
    private func prepareLeftWall(_ robots: [Robot], _ n: Int, _ walls: [Int]) -> [Int] {
        var leftWall = Array(repeating: 0, count: n)
        let wallSize = walls.count
        
        var left = 0
        var right = -1
        
        for i in 0..<n {
            let robot = robots[i]
            let prevRobotPos = (i == 0) ? NEG_INF : robots[i - 1].position
            
            let shootStart = max(prevRobotPos + 1, robot.position - robot.distance)
            let shootEnd = robot.position
            
            while right + 1 < wallSize && walls[right + 1] <= shootEnd {
                right += 1
            }
            
            while left < wallSize && walls[left] < shootStart {
                left += 1
            }
            
            if left <= right {
                leftWall[i] = right - left + 1
            }
        }
        
        return leftWall
    }
    
    private func prepareRightWall(_ robots: [Robot], _ n: Int, _ walls: [Int]) -> [Int] {
        var rightWall = Array(repeating: 0, count: n)
        let wallSize = walls.count
        
        var left = 0
        var right = -1
        
        for i in 0..<n {
            let robot = robots[i]
            let nextRobotPos = (i == n - 1) ? INF : robots[i + 1].position
            
            let shootStart = robot.position
            let shootEnd = min(nextRobotPos - 1, robot.position + robot.distance)
            
            while right + 1 < wallSize && walls[right + 1] <= shootEnd {
                right += 1
            }
            
            while left < wallSize && walls[left] < shootStart {
                left += 1
            }
            
            if left <= right {
                rightWall[i] = right - left + 1
            }
        }
        
        return rightWall
    }
    
    private func prepareCommon(_ robots: [Robot], _ n: Int, _ walls: [Int]) -> [Int] {
        var common = Array(repeating: 0, count: n)
        let wallSize = walls.count
        
        var left = 0
        var right = -1
        
        for i in 1..<n {
            let prev = robots[i - 1]
            let curr = robots[i]
            
            let shootStart = max(prev.position + 1, curr.position - curr.distance)
            let shootEnd = min(curr.position - 1, prev.position + prev.distance)
            
            if shootStart > shootEnd {
                continue
            }
            
            while right + 1 < wallSize && walls[right + 1] <= shootEnd {
                right += 1
            }
            
            while left < wallSize && walls[left] < shootStart {
                left += 1
            }
            
            if left <= right {
                common[i] = right - left + 1
            }
        }
        
        return common
    }
}
