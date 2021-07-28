from collections import OrderedDict


class LRUCache:

    def __init__(self, capacity: int):
        self.size = capacity
        self.cache = OrderedDict()

    def get(self, key: int) -> int:
        if key not in self.cache:
            return -1
        else:
            self.cache.move_to_end(key)  # Gotta keep this pair fresh, move to end of OrderedDict
            return self.cache[key]

    def put(self, key: int, value: int) -> None:
        if key not in self.cache:
            if len(self.cache) >= self.size:
                self.cache.popitem(last=False)  # last=True, LIFO; last=False, FIFO. We want to remove in FIFO fashion.
        else:
            self.cache.move_to_end(key)  # Gotta keep this pair fresh, move to end of OrderedDict
        self.cache[key] = value

# lRUCache = LRUCache(2)
# lRUCache.put(1, 1) # cache is {1=1}
# lRUCache.put(2, 2) # cache is {1=1, 2=2}
# # lRUCache.put(3, 3) # cache is {1=1, 2=2}
# # lRUCache.put(4, 4) # cache is {1=1, 2=2}
# print(lRUCache.get(1))    # return 1
# lRUCache.put(3, 3) # LRU key was 2, evicts key 2, cache is {1=1, 3=3}
# print(lRUCache.get(2))    # returns -1 (not found)
# lRUCache.put(4, 4) # LRU key was 1, evicts key 1, cache is {4=4, 3=3}
# print(lRUCache.get(1))    # return -1 (not found)
# print(lRUCache.get(3))    # return 3
# print(lRUCache.get(4))    # return 4