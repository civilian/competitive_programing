#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <map>
#include <set>
#include <utility>

#define foreach(it,v) for (__typeof((v).begin()) it=(v).begin(); it!=(v).end(); it++)

using namespace std;

int n;
set< pair<string,string> > people;
map< pair<string,string>, vector<string> > phones, emails;

string format_number(string s) {
  string t("(");
  int n = s.size();
  
  for (int i = 0; i < n; i++) {
    if (i == 3) t.push_back(')');
    if (i == 6) t.push_back('-');
    t.push_back(s[i]);
  }
  
  return t;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int tc = 1;

  while (cin >> n) {
    if (n == 0)
      break;
      
    phones.clear();
    emails.clear();
    people.clear();

    string s, t, v; // name, lastname, value
    for (int i = 0; i < n; i++) {
      cin >> s >> t >> v;
      if (v.find('@') != string::npos)
        emails[make_pair(t,s)].push_back(v);
      else
        phones[make_pair(t,s)].push_back(v);
      people.insert(make_pair(t,s));
    }
    
    cout << "Contact list #" << tc++ << ":\n";
    
    foreach(it, people) {
      pair<string,string> key = *it;
      vector<string> values = phones[key];
      
      cout << key.second << ' ' << key.first << '\n';
      cout << "Phone:\n";
      if (!values.empty()) {
        sort(values.begin(), values.end());

        for (int i = 0; i < values.size(); i++)
          cout << format_number(values[i]) << '\n';
      }

      values = emails[key];
      cout << "E-Mail:\n";
      if (!values.empty()) {
        sort(values.begin(), values.end());
      
        for (int i = 0; i < values.size(); i++)
          cout << values[i] << '\n';
      }
        
      cout << "###\n";
    }

    cout << "\n";
  }

  return 0;
}
