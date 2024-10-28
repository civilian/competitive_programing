from typing import List

class Solution:
    def sortArrayByParityII(self, nums: List[int]) -> List[int]:
        n = len(nums)
        i = 0
        j = 0
        ans =[]
        while nums:
            if i % 2 == 1:
                while nums[j] % 2 != 1:
                    j += 1
                ans.append(nums.pop(j))
                j = 0
                i += 1
            else:
                while nums[j] % 2 != 0:
                    j += 1
                ans.append(nums.pop(j))
                j = 0
                i += 1

        return ans

s = Solution()

a = [4,2,5,7]
print(s.sortArrayByParityII(a))
a = [2,3]
print(s.sortArrayByParityII(a))

class Solution:
    def sortArrayByParityII(self, nums: List[int]) -> List[int]:
        i, j = 0, 1  # Inicializamos dos punteros, i para las posiciones pares y j para las posiciones impares.
        while i < len(nums):  # Iteramos mientras i esté dentro del rango de la lista.
            if nums[i] % 2 == 0:  # Verificamos si el número en la posición 'i' es par.
                i += 2  # Si es par, avanzamos 'i' a la siguiente posición par (i + 2).
            else:
                # Si nums[i] es impar, intercambiamos nums[i] con nums[j].
                nums[i], nums[j] = nums[j], nums[i]
                j += 2  # Después del intercambio, avanzamos j a la siguiente posición impar.
        return nums  # Devolvemos la lista ordenada.