#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool compareString(const string &a, const string &b) {
  if(a.length() == b.length()) {
    string c = a;
    string d = b;
    sort(c.begin(), c.end());
    sort(d.begin(), d.end());
    return c < d;
  }
  return a.length() < b.length();
}

bool cmp(const vector<string>& a, const vector<string>& b) {
  if(a.size() == b.size()) {
    return a[0] < b[0];
  }
  return a.size() > b.size();
}

int main(){
  ios_base::sync_with_stdio(false);

  vector<string> words;
  string w;
  while(cin >> w) {
    words.push_back(w);
  }
  sort(words.begin(),words.end(),compareString);

  int n = words.size();

  vector< vector<string> > ans;

  for(int i = 0; i < n; i++) {
    string s = words[i];
    vector<string> curr_ans;
    curr_ans.push_back(s);

    sort(s.begin(), s.end());
    int j = i+1;
    for(; j < n; j++) {
      string curr = words[j];
      sort(curr.begin(), curr.end());
      if(curr == s) {
        curr_ans.push_back(words[j]);
      }
      else {
        break;
      }
    }
    i = j-1;
    sort(curr_ans.begin(), curr_ans.end());
    ans.push_back(curr_ans);
  }
  sort(ans.begin(), ans.end(), cmp);

  for(int i = 0; i < min(5, (int)ans.size()); i++) {
    cout << "Group of size " << ans[i].size() << ": ";
    sort(ans[i].begin(), ans[i].end());
    ans[i].resize(int(unique(ans[i].begin(), ans[i].end()) - ans[i].begin()));
    for(int j = 0; j < (int)ans[i].size(); j++) {
      cout << ans[i][j] << " ";
    }
    cout << ".\n";
  }

  return 0;
}
