from typing import  List

class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        min_ = 10**4 +1
        max_profit = - 1
        for price in prices:
            if price < min_:
                min_ = price

            current_profit = price - min_
            if current_profit > max_profit:
                max_profit = current_profit

        return max_profit

s = Solution()
a = [0]
print(s.maxProfit(a)) # 0
a = [7,1,5,3,6,4]
print(s.maxProfit(a))# 5

a = [7,6,4,3,1]
print(s.maxProfit(a))
a = [10**4]
print(s.maxProfit(a))
a = [10**4, 1]
print(s.maxProfit(a))
a = [10**4, 1, 10**4]
print(s.maxProfit(a))


class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        best_buy = prices[0]
        best_sell = prices[0]
        best_pnl = 0
        new_pnl = 0
        for price in prices:
            if price < best_buy:
                new_pnl = best_sell - best_buy
                if new_pnl > best_pnl:
                    best_pnl = new_pnl
                best_buy = price
                best_sell = price
            if price > best_sell:
                best_sell = price
                new_pnl = best_sell - best_buy
        if new_pnl > best_pnl:
            best_pnl = new_pnl
        return best_pnl