#include <bits/stdc++.h>

using namespace std;

const int maxn = 210;

int N, M;
vector<string> names[maxn];
string ans[maxn];

string transform(string s) {
  string t;
  int n = s.size();

  for (int i = 0; i < n; i++)
    if (isalpha(s[i]))
      t.push_back(tolower(s[i]));
  return t;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int tc = 1;

  while (cin >> N >> M) {
    if ((N|M) == 0)
      break;
    cin.get();
    for (int i = 0; i < N; i++) {
      string ln;
      getline(cin, ln);
      stringstream ss(ln);
      names[i].clear();
      while (ss >> ln)
        names[i].push_back(transform(ln));
    }

    set<string> seen;
    for (int i = 0; i < N; i++) {
      string curr;
      curr.push_back(names[i][0][0]);

      string last_name = names[i].back();
      for (int j = 0; j < last_name.size(); j++) {
        if (curr.size() == M)
          break;
        curr.push_back(last_name[j]);
      }

      if (seen.count(curr)) {
        int m = curr.size();
        if (m < M)
          curr.push_back('_');
        m = curr.size();
        for (char ch = '1'; ch <= '9'; ch++) {
          curr[m-1] = ch;
          if (!seen.count(curr))
            break;
        }

        if (seen.count(curr)) {
          if (m < M)
            curr.push_back('_');
          m = curr.size();
          for (char ch1 = '1'; ch1 <= '9'; ch1++) {
            curr[m-2] = ch1;
            bool done = false;
            for (char ch2 = '0'; ch2 <= '9'; ch2++) {
              curr[m-1] = ch2;
              if (!seen.count(curr)) {
                done = true;
                break;
              }
            }
            if (done)
              break;
          }
        }
      }
      ans[i] = curr;
      seen.insert(curr);
    }

    cout << "Case " << tc++ << '\n';
    for (int i = 0; i < N; i++)
      cout << ans[i] << endl;
  }

  return 0;
}
