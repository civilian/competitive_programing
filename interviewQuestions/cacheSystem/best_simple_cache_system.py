from collections import defaultdict

class LRUCache:

    def __init__(self, capacity: int):
        self.cap = capacity
        # default dict mantains the order of the elements
        self.cache = defaultdict(int)

    def get(self, key: int) -> int:
        if self.cache.get(key) != None:
            val = self.cache[key]
            self.cache.pop(key)
            self.cache[key] = val
            return val
        else:
            return -1

    def put(self, key: int, value: int) -> None:
        if self.cache.get(key) != None:
            self.cache.pop(key)
            self.cache[key] = value
        else:
            if len(self.cache) >= self.cap:
                self.cache.pop(next(iter(self.cache)))
            self.cache[key] = value

lRUCache = LRUCache(2)
lRUCache.put(1, 1) # cache is {1=1}
lRUCache.put(2, 2) # cache is {1=1, 2=2}
# lRUCache.put(3, 3) # cache is {1=1, 2=2}
# lRUCache.put(4, 4) # cache is {1=1, 2=2}
print(lRUCache.get(1))    # return 1
lRUCache.put(3, 3) # LRU key was 2, evicts key 2, cache is {1=1, 3=3}
print(lRUCache.get(2))    # returns -1 (not found)
lRUCache.put(4, 4) # LRU key was 1, evicts key 1, cache is {4=4, 3=3}
print(lRUCache.get(1))    # return -1 (not found)
print(lRUCache.get(3))    # return 3
print(lRUCache.get(4))    # return 4