# https://leetcode.com/problems/lru-cache/#

class Node:
    def __init__(self, value):
        self.value = value
        self.next = None
        self.prev = None

    def __repr__(self):
        if not self:
            return "{}()".format(self.__class__.__name__)
        return "{}({})".format(self.__class__.__name__, self.value)


class LinkedList:
    def __init__(self, iterable=None):
        self.sentinel = Node(None)
        # at the top next left
        self.sentinel.next = self.sentinel
        # at the top previous right
        self.sentinel.prev = self.sentinel
        self.__len = 0
        if iterable is not None:
            self += iterable

    def get_node(self, index):
        node = self.sentinel
        i = 0
        while i <= index:
            node = node.next
            if node == self.sentinel:
                break
            i += 1
        if node == self.sentinel:
            node = None
        return node

    def __getitem__(self, index):
        node = self.get_node(index)
        return node.value

    def __len__(self):
        return self.__len

    def __setitem__(self, index, value):
        node = self.get_node(index)
        node.value = value

    def __delitem__(self, index):
        node = self.get_node(index)
        self.del_node(node)

    def del_node(self, node):
        if node:
            node.prev.next = node.next
            if node.next:
                node.next.prev = node.prev
            node.prev = None
            node.next = None
            node.value = None
            self.__len -= 1

    def __repr__(self):
        return str(self.to_list())

    def to_list(self):
        elts = []
        curr = self.sentinel.next
        while curr != self.sentinel:
            elts.append(curr.value)
            curr = curr.next
        return elts

    def append(self, value):
        node = Node(value)
        self.insert_between(node, self.sentinel.prev, self.sentinel)

    def appendleft(self, value):
        node = Node(value)
        self.insert_between(node, self.sentinel, self.sentinel.next)
        return node

    def insert(self, index, value):
        new_node = Node(value)
        len_ = len(self)
        if len_ == 0:
            self.insert_between(new_node, self.sentinel, self.sentinel)
        elif index >= 0 and index < len_:
            node = self.get_node(index)
            self.insert_between(new_node, node.prev, node)
        elif index == len_:
            self.insert_between(new_node, self.sentinel.prev, self.sentinel)
        else:
            raise IndexError
        self.__len += 1

    def insert_between(self, node, left_node, right_node):
        if node and left_node and right_node:
            node.prev = left_node
            node.next = right_node
            left_node.next = node
            right_node.prev = node
        else:
            raise IndexError

    def merge_left(self, other):
        self.sentinel.next.prev = other.sentinel.prev
        other.sentinel.prev.next = self.sentinel.next
        self.sentinel.next = other.sentinel.next
        self.sentinel.next.prev = self.sentinel

    def merge_right(self, other):
        self.sentinel.prev.next = other.sentinel.next
        other.sentinel.next.prev = self.sentinel.prev
        self.sentinel.prev = other.sentinel.prev
        self.sentinel.prev.next = self.sentinel

    def pop_node(self):
        value = self.sentinel.prev.value
        self.del_node(self.sentinel.prev)
        return value


class LRUCache:

    def __init__(self, capacity: int):
        self.access = {}
        self.data = LinkedList()
        self.capacity = capacity

    def get(self, key: int) -> int:
        if key in self.access:
            node = self.access[key]
            value_pair = node.value
            self.data.del_node(node)
            self.access[key] = self.data.appendleft(value_pair)
            return value_pair[1]
        else:
            return -1

    def put(self, key: int, value: int) -> None:
        if not key in self.access and len(self.access) == self.capacity:
            remove_node_value = self.data.pop_node()
            del self.access[remove_node_value[0]]
        if key in self.access:
            node = self.access[key]
            self.data.del_node(node)
        self.access[key] = self.data.appendleft((key, value))

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
#
# # ["LRUCache","put","put","get","put","get","get"]
# [[2],[2,1],[1,1],[2],[4,1],[1],[2]]
# lRUCache = LRUCache(2)
# lRUCache.put(2,1)
# lRUCache.put(1,1)
# print(lRUCache.get(2))
# lRUCache.put(4,1)
# print(lRUCache.get(1))
# print(lRUCache.get(2))