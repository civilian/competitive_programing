#include <bits/stdc++.h>

using namespace std;

const int maxn = 200010;

int N, M, current_x, current_id; // Segments, Queries

inline
int compareTo(double x, double y, double tol=1e-9) {
  return (x <= y + tol) ? (x + tol < y) ? -1 : 0 : 1;
}

struct point {
  int x, y;

  point(int x = 0, int y = 0) : x(x), y(y) {}
};

struct answer {
  int where, x_value;
  answer(int w=-1, int x=-1) : where(w), x_value(x) {}
};

struct segment {
  int id, nextHit, next_segment;
  bool is_query, in_map;
  point left_most, right_most;
  answer ans;

  segment(const segment & s) :
    id(s.id), nextHit(s.nextHit), next_segment(s.next_segment),
    is_query(s.is_query), in_map(s.in_map), left_most(s.left_most),
    right_most(s.right_most) {}

  segment(const point & a, const point & b) :
    left_most(point(a.x, a.y)), right_most(point(b.x, b.y)),
    id(current_id), next_segment(-1), ans(-1,-1), is_query(false),
    in_map(false)
  {
    if (left_most.x > right_most.x)
      swap(left_most, right_most);
  }

  segment(const point & p) :
    left_most(point(p.x, p.y)), right_most(point(p.x, p.y)),
    is_query(true), id(current_id), next_segment(-1), ans(-1,-1), in_map(false)
  { }

  segment() {}

  double current_y() const {
    if (right_most.x == left_most.x)
      return left_most.y;

    double m = double(right_most.y - left_most.y) /
               double(right_most.x - left_most.x);

    double b = left_most.y - m * left_most.x;
    return m * current_x + b;
  }

  bool operator<(const segment & s) const {
    double a = current_y();
    double b = s.current_y();

    int cmp = compareTo(a, b);
    if (cmp == 0) return id - s.id;
    return cmp < 0;
  }
};

struct event {
  int x_value, y_value, segment;
  int is_closing;

  event() {}

  event(int x, int y, int s, int ic) :
    x_value(x), y_value(y), segment(s), is_closing(ic) {}

  bool operator<(const event & e) const {
    if (x_value == e.x_value) {
      if (is_closing == e.is_closing)
        return e.y_value < y_value;
      return is_closing < e.is_closing;
    }
    return x_value < e.x_value;
  }
};

int E_cnt;
event E[maxn << 2];
segment S[maxn];
vector<int> g[maxn];

void add_events(int i) {
  segment & s = S[i];

  if (s.is_query) {
    E[E_cnt++] = event(s.right_most.x, s.right_most.y, i, 0);
    s.in_map = true;
  }
  else {
    E[E_cnt++] = event(s.left_most.x, s.left_most.y, i, 0);
    E[E_cnt++] = event(s.right_most.x, s.right_most.y, i, 1);
  }
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> N >> M) {
    current_id = 0;

    for (int i = 0; i < N; i++) {
      point a, b;
      cin >> a.x >> a.y >> b.x >> b.y;
      S[current_id] = segment(a, b);
      current_id++;
    }

    vector<int> queries(M);
    for (int i = 0; i < M; i++) {
      point a;
      cin >> a.x >> a.y;
      S[current_id] = segment(a);
      current_id++;
      queries[i] = current_id-1;
    }

    // add ceiling
    int ceiling = current_id;
    S[current_id].id = ceiling;
    S[current_id].left_most = point(-1, 1000001);
    S[current_id].right_most = point(1000001, 1000001);
    S[current_id++].is_query = false;

    E_cnt = 0;
    for (int i = 0; i < current_id; i++) {
      add_events(i);
      g[i].clear();
    }
    sort(E, E + E_cnt);

    set<segment> current_segments;
    for (int i = 0; i < E_cnt; i++) {
      event & e = E[i];
      current_x = e.x_value;
      cerr << "e.s => " << e.segment << '\n';

      if (S[e.segment].in_map)
        current_segments.erase(S[e.segment]);

      int max = std::max(S[e.segment].left_most.y, S[e.segment].right_most.y);

      if (e.y_value == max && e.segment != ceiling &&
          (S[e.segment].is_query || S[e.segment].left_most.y != S[e.segment].right_most.y))
      {
        S[e.segment].next_segment = current_segments.find(S[e.segment])->id;
        S[e.segment].nextHit = e.x_value;
      }

      if (S[e.segment].in_map)
        S[e.segment].in_map = false;
      else {
        S[e.segment].in_map = true;
        current_segments.insert(S[e.segment]);
      }
    }

    queue<int> Q;
    for (int i = 0; i < current_id; i++) {
      segment & s = S[i];
      if (s.next_segment != -1)
        g[s.next_segment].push_back(s.id);
      else {
        Q.push(s.id);
        s.ans.where = s.id;
        s.ans.x_value = -1;
      }
    }

    while (!Q.empty()) {
      int current = Q.front();
      Q.pop();

      for (int i = 0; i < g[current].size(); i++) {
        segment & s = S[g[current][i]];
        if (s.ans.where < 0) {
          if (S[current].ans.x_value == -1) {
            s.ans.where = S[current].ans.where;
            s.ans.x_value = s.nextHit;
          }
          else {
            s.ans.where = S[current].ans.where;
            s.ans.x_value = S[current].ans.x_value;
          }
          Q.push(g[current][i]);
        }
      }
    }

    for (int i = 0; i < M; i++) {
      segment & s = S[queries[i]];
      if (s.ans.where == ceiling)
        cout << int(s.ans.x_value) << '\n';
      else
        cout << int(s.ans.x_value) << ' ' << int(S[s.ans.where].left_most.y) << '\n';
    }
  }

  return 0;
}
