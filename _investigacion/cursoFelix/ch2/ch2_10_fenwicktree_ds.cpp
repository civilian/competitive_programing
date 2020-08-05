#include <cstdio>
#include <vector>
using namespace std;
typedef vector<int> vi;
#define LSOne(S) (S & (-S))

void ft_create(vi &t, int n) { t.assign(n + 1, 0); }   // initially n + 1 zeroes

int ft_rsq(const vi &t, int b) {                            // returns RSQ(1, b)
  int sum = 0; for (; b; b -= LSOne(b)) sum += t[b];
  return sum; }

int ft_rsq(const vi &t, int a, int b) {                     // returns RSQ(a, b)
  return ft_rsq(t, b) - (a == 1 ? 0 : ft_rsq(t, a - 1)); }

// adjusts value of the k-th element by v (v can be +ve/inc or -ve/dec).
void ft_adjust(vi &t, int k, int v) {
  for (; k <= (int)t.size(); k += LSOne(k)) t[k] += v; }

int main() {               // idx   1 2 3 4 5 6 7 8 , index 0 is ignored!
  vi ft; ft_create(ft, 8); // ft = {0,0,0,0,0,0,0,0}
  ft_adjust(ft, 2, 1);     // ft = {0,1,0,1,0,0,0,1}, index 2,4,8 => +1
  ft_adjust(ft, 4, 1);     // ft = {0,1,0,2,0,0,0,2}, index 4,8 => +1
  ft_adjust(ft, 5, 2);     // ft = {0,1,0,2,2,2,0,4}, index 5,6,8 => +2
  ft_adjust(ft, 6, 3);     // ft = {0,1,0,2,2,5,0,7}, index 6,8 => +3
  ft_adjust(ft, 7, 2);     // ft = {0,1,0,2,2,5,2,9}, index 7,8 => +2
  ft_adjust(ft, 8, 1);     // ft = {0,1,0,2,2,5,2,10}, index 8 => +1
  printf("%d\n", ft_rsq(ft, 1, 1)); // 0 => ft[1] = 0
  printf("%d\n", ft_rsq(ft, 1, 2)); // 1 => ft[2] = 1
  printf("%d\n", ft_rsq(ft, 1, 6)); // 7 => ft[6] + ft[4] = 5 + 2 = 7
  printf("%d\n", ft_rsq(ft, 1, 8)); // 10 => ft[8]
  printf("%d\n", ft_rsq(ft, 3, 6)); // 6 => rsq(1, 6) - rsq(1, 2) = 7 - 1
} // return 0;
