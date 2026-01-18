from typing import List

class Solution:
    def bestTower(self, towers: List[List[int]], center: List[int], radius: int) -> List[int]:
        best_quality = -1
        best_x = -1
        best_y = -1

        cx, cy = center

        for x, y, q in towers:
            dist = abs(x - cx) + abs(y - cy)

            if dist <= radius:
                if (
                    q > best_quality or
                    (q == best_quality and (x < best_x or (x == best_x and y < best_y)))
                ):
                    best_quality = q
                    best_x = x
                    best_y = y

        if best_quality == -1:
            return [-1, -1]

        return [best_x, best_y]
