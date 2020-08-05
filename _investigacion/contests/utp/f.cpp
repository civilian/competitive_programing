#include <iostream>
#include <string>
#include <queue>
#include <set>
#include <algorithm>

#define D(x) cerr << #x " = " << x << endl

using namespace std;

string g, e;

int bfs() {
  set<string> seen;
  queue< pair<int, string> > q;

  q.push(make_pair(0, g));
  seen.insert(g);

  while(!q.empty()) {
    int moves = q.front().first;
    string curr = q.front().second;

    q.pop();

    if(curr == e)
      return moves;

    for(int i = 0; i < 8; i++) {
      if(curr[i] == '.') continue;
      string next = curr;

      if(curr[i] == 'A') {
        if(i+1 < 8 && curr[i+1] == '.') {
          next[i] = '.';
          next[i+1] = 'A';
        }
        else if(i+2 < 8 && curr[i+2] == '.') {
          next[i] = '.';
          next[i+2] = 'A';
        }
      }
      else if(curr[i] == 'V') {
        if(i-1 >= 0 && curr[i-1] == '.') {
          next[i] = '.';
          next[i-1] = 'V';
        }
        else if(i-2 >= 0 && curr[i-2] == '.') {
          next[i] = '.';
          next[i-2] = 'V';
        }
      }
      else if(curr[i] == 'R') {
        if(i+1 < 8 && curr[i+1] == '.') {
          next[i] = '.';
          next[i+1] = 'R';
        }
        else if(i+2 < 8 && curr[i+2] == '.') {
          next[i] = '.';
          next[i+2] = 'R';
        }

        string next_r = curr;
        if(i-1 >= 0 && curr[i-1] == '.') {
          next_r[i] = '.';
          next_r[i-1] = 'R';
        }
        else if(i-2 >= 0 && curr[i-2] == '.') {
          next_r[i] = '.';
          next_r[i-2] = 'R';
        }

        if(!seen.count(next_r)) {
          seen.insert(next_r);
          q.push(make_pair(moves+1, next_r));
        }
      }

      if(!seen.count(next)) {
        seen.insert(next);
        q.push(make_pair(moves+1, next));
      }
    }
  }

  return -1;
}

int main() {
  ios_base::sync_with_stdio(false);

  int t;
  cin >> t;

  for(int run = 1; run <= t; run++) {
    cin >> g >> e;
    int ans = bfs();

    cout << "Caso " << run << ": ";
    if(ans < 0) cout << "imposible" << endl;
    else cout << ans << endl;
  }

  return 0;
}
