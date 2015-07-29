#include <cmath>
#include <cstdio>
#include <vector>
using namespace std;
typedef vector<int> vi;

// Segment Tree Library: The segment tree is stored like a heap array
void st_build(vi &t, const vi &A, int vertex, int L, int R) {
  if (L == R)                                   // as L == R, either one is fine
    t[vertex] = L;                                            // store the index
  else {        // recursively compute the values in the left and right subtrees
    int nL = 2 * vertex, nR = 2 * vertex + 1;
    st_build(t, A, nL, L              , (L + R) / 2);
    st_build(t, A, nR, (L + R) / 2 + 1, R          );
    int lContent = t[nL]      , rContent = t[nR];
    int lValue   = A[lContent], rValue   = A[rContent];
    t[vertex] = (lValue <= rValue) ? lContent : rContent;
} }

void st_create(vi &t, const vi &A) {             // if original array size is N,
  // the required segment tree array length is 2*2^(floor(log2(N)) + 1);
  int len = (int)(2 * pow(2.0, floor((log((double)A.size()) / log(2.0)) + 1)));
  t.assign(len, 0);   // create vector with length `len' and fill it with zeroes
  st_build(t, A, 1, 0, (int)A.size() - 1); // recursively build the segment tree
}

int st_rmq(vi &t, const vi &A, int vertex, int L, int R, int i, int j) {
  if (i >  R || j <  L) return -1;        // current segment outside query range
  if (L >= i && R <= j) return t[vertex];  // current segment inside query range

  // compute the minimum position in the left and right part of the interval
  int p1 = st_rmq(t, A, 2 * vertex    , L              , (L + R) / 2, i, j);
  int p2 = st_rmq(t, A, 2 * vertex + 1, (L + R) / 2 + 1, R          , i, j);

  // return the position where the overall minimum is
  if (p1 == -1) return p2;          // if we try to access segment outside query
  if (p2 == -1) return p1;                                      // same as above
  return (A[p1] <= A[p2]) ? p1 : p2; }

int st_rmq(vi &t, const vi& A, int i, int j) { // overloading, simpler arguments
  return st_rmq(t, A, 1, 0, (int)A.size() - 1, i, j); }

int st_update_point(vi &t, vi &A, int node, int b, int e, int idx, int new_value) {
  // this update code is still preliminary, i == j
  // must be able to update range in the future!
  int i = idx, j = idx;

  // if the current interval does not intersect 
  // the update interval, return this st node value!
  if (i > e || j < b)
    return t[node];

  // if the current interval is included in the update range,
  // update that st[node]
  if (b == i && e == j) {
    A[i] = new_value; // update the underlying array
    return t[node] = b; // this index
  }

  // compute the minimum position in the 
  // left and right part of the interval
  int p1, p2;
  p1 = st_update_point(t, A, 2 * node    , b              , (b + e) / 2, idx, new_value);
  p2 = st_update_point(t, A, 2 * node + 1, (b + e) / 2 + 1, e          , idx, new_value);

  // return the position where the overall minimum is
  return t[node] = (A[p1] <= A[p2]) ? p1 : p2;
}

int st_update_point(vi &t, vi &A, int idx, int new_value) {
  return st_update_point(t, A, 1, 0, (int)A.size() - 1, idx, new_value);
}

int main() {
  int arr[7] = { 8, 7, 3, 9, 5, 1, 10 };                   // the original array
  vi A(arr, arr + 7);
  vi st; st_create(st, A);

  printf("              A is { 8, 7, 3, 9, 5, 1  , 10 }\n");
  printf("RMQ(1, 3) = %d\n", st_rmq(st, A, 1, 3));          // answer is index 2
  printf("RMQ(4, 6) = %d\n", st_rmq(st, A, 4, 6));          // answer is index 5
  printf("RMQ(3, 4) = %d\n", st_rmq(st, A, 3, 4)); // 4
  printf("RMQ(0, 0) = %d\n", st_rmq(st, A, 0, 0)); // 0
  printf("RMQ(0, 1) = %d\n", st_rmq(st, A, 0, 1)); // 1
  printf("RMQ(0, 6) = %d\n", st_rmq(st, A, 0, 6)); // 5

  printf("Now, modify A into { 8, 7, 3, 9, 5, 100, 10 }\n");
  st_update_point(st, A, 5, 100); // update A[5] from 1 to 100
  // these values do not change
  printf("RMQ(1, 3) = %d\n", st_rmq(st, A, 1, 3)); // 2
  printf("RMQ(3, 4) = %d\n", st_rmq(st, A, 3, 4)); // 4
  printf("RMQ(0, 0) = %d\n", st_rmq(st, A, 0, 0)); // 0
  printf("RMQ(0, 1) = %d\n", st_rmq(st, A, 0, 1)); // 1
  // these values change
  printf("RMQ(0, 6) = %d\n", st_rmq(st, A, 0, 6)); // 5->2
  printf("RMQ(4, 6) = %d\n", st_rmq(st, A, 4, 6)); // 5->4
  printf("RMQ(4, 5) = %d\n", st_rmq(st, A, 4, 5)); // 5->4

} // return 0;
