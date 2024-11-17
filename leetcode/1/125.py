class Solution:
    def isPalindrome(self, s: str) -> bool:
        new_s = [letra.lower() for letra in s if letra.isalnum()]
        mitad = len(new_s) // 2
        a1 = new_s[:mitad]
        mitad = mitad + 1 if len(new_s) % 2 == 1 else mitad
        a2 = new_s[mitad:]
        a2 = a2[::-1]
        return a1 == a2

s = Solution()
st = "A man, a plan, a canal: Panama"
print(s.isPalindrome(st))
st = "race a car"
print(s.isPalindrome(st))
st = " "
print(s.isPalindrome(st))
st = "a"
print(s.isPalindrome(st))
st = "aa"
print(s.isPalindrome(st))
st = "a"* (2*10**5)
print(s.isPalindrome(st))