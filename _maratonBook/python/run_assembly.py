import mmap
import ctypes as ct
# https://leetcode.com/problems/valid-palindrome/
shell_code = bytes.fromhex("F3 0F 1E FA 55 48 89 E5 48 89 7D D8 48 C7 45 F0 00 00 00 00 48 C7 45 F8 00 00 00 00 EB 05 48 83 45 F8 01 48 8B 45 F8 48 8D 50 01 48 8B 45 D8 48 01 D0 0F B6 00 84 C0 75 E5 E9 EB 00 00 00 48 8B 55 D8 48 8B 45 F0 48 01 D0 0F B6 00 3C 40 7E 25 48 8B 55 D8 48 8B 45 F0 48 01 D0 0F B6 00 3C 5A 7F 13 48 8B 55 D8 48 8B 45 F0 48 01 D0 0F B6 00 83 C0 20 EB 0E 48 8B 55 D8 48 8B 45 F0 48 01 D0 0F B6 00 88 45 EE 80 7D EE 2F 7E 06 80 7D EE 39 7E 16 80 7D EE 60 7E 06 80 7D EE 7A 7E 0A 48 83 45 F0 01 E9 81 00 00 00 48 8B 55 D8 48 8B 45 F8 48 01 D0 0F B6 00 3C 40 7E 25 48 8B 55 D8 48 8B 45 F8 48 01 D0 0F B6 00 3C 5A 7F 13 48 8B 55 D8 48 8B 45 F8 48 01 D0 0F B6 00 83 C0 20 EB 0E 48 8B 55 D8 48 8B 45 F8 48 01 D0 0F B6 00 88 45 EF 80 7D EF 2F 7E 06 80 7D EF 39 7E 13 80 7D EF 60 7E 06 80 7D EF 7A 7E 07 48 83 6D F8 01 EB 1A 0F B6 45 EE 3A 45 EF 74 07 B8 00 00 00 00 EB 1D 48 83 45 F0 01 48 83 6D F8 01 48 8B 45 F0 48 3B 45 F8 0F 82 07 FF FF FF B8 01 00 00 00 5D C3")

mm = mmap.mmap(-1, len(shell_code), flags=mmap.MAP_SHARED | mmap.MAP_ANONYMOUS, prot=mmap.PROT_WRITE | mmap.PROT_READ | mmap.PROT_EXEC)
mm.write(shell_code)
ct_buffer = ct.c_char.from_buffer(mm)
function = ct.CFUNCTYPE(ct.c_int, ct.c_char_p)(ct.addressof(ct_buffer))

class Solution:
    def isPalindrome(self, s: str) -> bool:

        return bool(function(s.encode()))