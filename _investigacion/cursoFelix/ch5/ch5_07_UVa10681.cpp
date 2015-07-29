/* Teobaldo's Trip */

#include <cmath>
#include <cstdio>
#include <cstring>
using namespace std;

int i, N, L, S, E, D;

#define MAX_N 105   // increase this if needed
struct Matrix { int mat[MAX_N][MAX_N]; };   // so that we can return a 2D array

Matrix matMul(Matrix a, Matrix b) {   // O(n^3)
  Matrix ans; int i, j, k;
  for (i = 0; i < MAX_N; i++)
    for (j = 0; j < MAX_N; j++)
      for (ans.mat[i][j] = k = 0; k < MAX_N; k++)   // if necessary, do
        ans.mat[i][j] += a.mat[i][k] * b.mat[k][j];   // modulo arithmetic here
  return ans; }

Matrix matPow(Matrix base, int p) {   // O(n^3 log p)
  Matrix ans; int i, j;
  for (i = 0; i < MAX_N; i++) for (j = 0; j < MAX_N; j++)
    ans.mat[i][j] = (i == j);   // prepare identity matrix
  while (p) {   // iterative version of Divide & Conquer exponentation
    if (p & 1) ans = matMul(ans, base);   // if p is odd (last bit on)
    base = matMul(base, base);   // square the base
    p >>= 1;   // divide p by 2
  }
  return ans; }

int normalExp(int base, int p) {   // for simplicity, we just use int data type
  int ans = 1;
  for (int i = 0; i < p; i++) ans *= base;   // O(p)
  return ans; }

int fastExp(int base, int p) {   // O(log p)
       if (p == 0) return 1;
  else if (p == 1) return base;
  else {           int res = fastExp(base, p / 2); res *= res;
                   if (p % 2 == 1) res *= base;
                   return res; } }

int main() {
  // just for illustration (not part of UVa 10681 solution), uncomment to compare
  // printf("%d vs %d\n", normalExp(2, 4), fastExp(2, 4));
  // printf("%d vs %d\n", normalExp(2, 9), fastExp(2, 9));
  // printf("%d vs %d\n", normalExp(2, 22), fastExp(2, 22));

  while (scanf("%d %d", &N, &L), (N || L)) {
    Matrix g;
    memset(g.mat, 0, sizeof g.mat);
    for (i = 0; i < L; i++) {
      scanf("%d %d", &S, &E); S--; E--;
      g.mat[S][E] = g.mat[E][S] = 1; // there is an edge
    }
    scanf("%d %d %d", &S, &E, &D); S--; E--;

    g = matPow(g, D);

    // number of paths of length D is stored in AdjMatrix after raising AdjMatrix to the power of D
    printf(g.mat[S][E] ? "Yes, Teobaldo can travel.\n"  : "No, Teobaldo can not travel.\n");
  }

  return 0;
}
