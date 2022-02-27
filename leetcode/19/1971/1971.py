class Solution:
    def validPath(self, n: int, edges: List[List[int]], source: int, destination: int) -> bool:
        adjacency = {}
        visited = [False] *n
        for edge in edges:
            if edge[0] in adjacency