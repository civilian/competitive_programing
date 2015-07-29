#include <bits/stdc++.h>

using namespace std;

const int MOD = int(1e6);
const int SIZE = 2;

long long N, K, L;

struct Matrix {
  long long mat[SIZE][SIZE];
  int n;

  Matrix() {
    n = SIZE;
    memset(mat, 0, sizeof(mat));
  }

  long long* operator[](int i) {
    return mat[i];
  }

  static Matrix identity(int n) {
    Matrix ans;
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        if (i == j)
          ans[i][j] = 1;
    return ans;
  }
};

Matrix operator*(Matrix a, Matrix b) {
  Matrix ans;
  for (int i = 0; i < a.n; i++) {
    for (int j = 0; j < a.n; j++) {
      long long sum = 0;
      for (int k = 0; k < a.n; k++)
        sum += ((long long)a[i][k] * b[k][j]) % MOD;
      ans[i][j] = sum % MOD;
    }
  }
  return ans;
}

Matrix pow(Matrix a, long long p) {
  Matrix ans = Matrix::identity(a.n);
  ans[0][0] = K;
  ans[0][1] = L;

  while (p > 0) {
    if (p & 1) ans = ans * a;
    a = a * a;
    p >>= 1;
  }
  return ans;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> N >> K >> L) {
    Matrix a;
    K %= MOD;
    L %= MOD;
    a[0][0] = K, a[0][1] = L;
    a[1][0] = 1LL, a[1][1] = 0;

    N /= 5;
    a = pow(a, N-1);
    long long ans = a[0][0] % MOD;
    printf("%06lld\n", ans);
  }

  return 0;
}
