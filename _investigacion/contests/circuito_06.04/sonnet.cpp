#include <cstdio>
#include <cstdlib>
#include <cwchar>
#include <cwctype>
#include <cassert>
#include <clocale>

#include <iostream>
#include <sstream>
#include <string>
#include <vector>
#include <map>
#include <set>
#include <algorithm>

#include <locale>

#define foreach(it,v) for(__typeof((v).begin()) it=(v).begin(); it!=(v).end(); it++)

using namespace std;

set<string> rhymes;
vector<string> invalids, alpha;

void create_rhymes() {
  rhymes.insert(string("ABBAABBACDECDE"));
  rhymes.insert(string("ABBAABBACDEDCE"));
  rhymes.insert(string("ABBAABBACDCDCD"));
}

void create_invalids() {
  invalids.push_back(string("¡"));
  invalids.push_back(string("!"));
  invalids.push_back(string(","));
  invalids.push_back(string("."));
  invalids.push_back(string(":"));
  invalids.push_back(string(";"));
  invalids.push_back(string("¿"));
  invalids.push_back(string("?"));
  invalids.push_back(string("-"));
}

void create_alpha() {
  alpha.push_back(string("á"));
  alpha.push_back(string("é"));
  alpha.push_back(string("í"));
  alpha.push_back(string("ó"));
  alpha.push_back(string("ú"));
  alpha.push_back(string("Á"));
  alpha.push_back(string("É"));
  alpha.push_back(string("Í"));
  alpha.push_back(string("Ó"));
  alpha.push_back(string("Ú"));

  alpha.push_back(string("ñ"));
  alpha.push_back(string("Ñ"));
  alpha.push_back(string("ü"));
  alpha.push_back(string("Ü"));
}

string transform(string s) {
  for(int i = 0; i < alpha.size(); i++) {
    int pos, rep;

    if(i < 10) rep = i % 5;
    else if(i < 12) rep = 5;
    else rep = 6;

    char ch = '0' + rep;
    string t(" ");
    t.push_back(ch);
    t += string(" ");

    while((pos = s.find(alpha[i])) != string::npos) {
      s = s.replace(pos, alpha[i].size(), t);
    }
  }
  return s;
}

string lower_str(const string& s) {
  string t;
  int n = s.size();
  for(int i = 0; i < n; i++) {
    t.push_back(tolower(s[i]));
  }
  return t;
}

string clean(string s) {
  string t;
  int pos;

  for(int i = 0; i < invalids.size(); i++) {
    while((pos = s.find(invalids[i])) != string::npos)
      s = s.replace(pos, invalids[i].size(), string("   "));
  }

  int n = s.size();
  for(int i = 0; i < n; i++) {
    if(s[i] != ' ') {
      t.push_back(s[i]);
    }
  }

  pos = t.rfind(string("s"));
  if(pos == t.size()-1) {
    t = t.substr(0, pos);
  }

  return lower_str(t);
  //return t;
}

string trim(string s) {
  string t;
  for(int i = 0; i < s.size(); i++) {
    if(isalnum(s[i])) t.push_back(s[i]);
  }
  return t;
}

//wchar_t ln[1010];

int main() {
  //assert(setlocale(LC_ALL, "") != NULL);
  ios_base::sync_with_stdio(false);
  //std::locale::global(std::locale(""));
  //cout << std::locale().name() << endl;
  //wcout.imbue(std::locale());
  //wcin.imbue(std::locale());
  create_rhymes();
  create_invalids();
  create_alpha();

  string ln;
  vector<string> solution;

  while(getline(cin, ln)) {
    map<string, char> suffix;
    stringstream ss(ln);

    string w;
    while(ss >> w) {
      w = lower_str(trim(transform(w)));
      suffix[w] = 'A' + suffix.size() - 1;
      //cerr << w << endl;
    }
    string title;
    getline(cin, title);

    vector<string> poem;
    while(getline(cin, ln)) {
      if(ln.size() == 0) break;
      poem.push_back(clean(transform(ln)));
      //cerr << poem.back() << endl;
    }

    int n = poem.size();

    // first condition
    if(n != 14) {
      ostringstream oss;
      oss << title << ": Not a chance!";
      //wprintf(L"%ls: Not a chance!\n", title.c_str());
      solution.push_back(oss.str());
      //cerr << oss.str() << endl;
      continue;
    }

    string ans;
    for(int i = 0; i < n; i++) {
      int length = poem[i].size();
      foreach(s, suffix) {
        int m = s->first.size();
        string curr = poem[i].substr(length - m);
        //cerr << i << " curr = " << curr << " " << s->first  << endl;
        if(curr == s->first) {
          ans.push_back(s->second);
          break;
        }
      }
    }

    //cerr << ans << endl;
    ostringstream oss;
    if(ans.size() != 14 || !rhymes.count(ans)) {
      oss << title << ": Not a chance!";
      //wprintf(L"%ls: Not a chance!\n", title.c_str());
    }
    else {
      oss << title << ": " << ans.c_str();
      //wprintf(L"%ls: %s\n", title.c_str(), ans.c_str());
    }
    solution.push_back(oss.str());
  }
  for(int i = 0; i < solution.size(); i++) {
    if(i) cout << endl;
    cout << solution[i];
  }

  return 0;
}
