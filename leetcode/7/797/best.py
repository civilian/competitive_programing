from typing import List

class Solution:
    def allPathsSourceTarget(self, graph: List[List[int]]) -> List[List[int]]:

        def count_paths(start, end, temp, graph):
            # print(temp)
            if start == end:
                temp += str(start)
            else:
                temp += str(start) + ","
            if start == end:
                results.append([temp])
                return None
            for i in graph[start]:
                count_paths(i, end, temp, graph)

        results = []
        count_paths(0, len(graph) - 1, "", graph)
        return results

g = [[1,2],[3],[3],[]]
s = Solution()
print(s.allPathsSourceTarget(g))
g = [[4,3,1],[3,2,4],[3],[4],[]]
print(s.allPathsSourceTarget(g))
g = [[1],[]]
print(s.allPathsSourceTarget(g))
g = [[1,2,3],[2],[3],[]]
print(s.allPathsSourceTarget(g))
g = [[1,3],[2],[3],[]]
print(s.allPathsSourceTarget(g))
g = [[], []]
print(s.allPathsSourceTarget(g))