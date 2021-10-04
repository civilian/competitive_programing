from typing import List


class Solution:

    def allPathsSourceTarget(self, graph: List[List[int]]) -> List[List[int]]:
        return self._allPathsSourceTarget(0, graph)

    def _allPathsSourceTarget(self, node: int, graph: List[List[int]]) -> List[List[int]]:
        if node == len(graph) - 1:
            return [[node]]
        elif graph[node] is []:
            return None
        else:
            ans = []
            for n in graph[node]:
                tmp = self._allPathsSourceTarget(n, graph)
                if tmp is not None:
                    for l in tmp:
                        l.insert(0, node)
                        ans.append(l)
            return ans

# g = [[1,2],[3],[3],[]]
# s = Solution()
# print(s.allPathsSourceTarget(g))
# g = [[4,3,1],[3,2,4],[3],[4],[]]
# print(s.allPathsSourceTarget(g))
# g = [[1],[]]
# print(s.allPathsSourceTarget(g))
# g = [[1,2,3],[2],[3],[]]
# print(s.allPathsSourceTarget(g))
# g = [[1,3],[2],[3],[]]
# print(s.allPathsSourceTarget(g))
# g = [[], []]
# print(s.allPathsSourceTarget(g))

