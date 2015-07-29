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

void create_rhymes() {
  rhymes.insert(string("ABBAABBACDECDE"));
  rhymes.insert(string("ABBAABBACDEDCE"));
  rhymes.insert(string("ABBAABBACDCDCD"));
}

wstring lower_wstr(const wstring& s) {
  wstring t;
  int n = s.size();
  for(int i = 0; i < n; i++) {
    t.push_back(towlower(s[i]));
  }
  return t;
}

wstring clean(const wstring& s) {
  wstring t;
  int n = s.size();
  int pos;
  for(int i = 0; i < n; i++) {
    if(iswalpha(s[i])) {
      t.push_back(s[i]);
    }
  }

  pos = t.rfind(wstring(L"s"));
  if(pos == t.size()-1) {
    t = t.substr(0, pos);
  }

  return lower_wstr(t);
  //return t;
}

wchar_t ln[1010];

int main() {
  assert(setlocale(LC_ALL, "") != NULL);
  //ios_base::sync_with_stdio(false);
  //std::locale::global(std::locale(""));
  //cout << std::locale().name() << endl;
  //wcout.imbue(std::locale());
  //wcin.imbue(std::locale());
  create_rhymes();

  //wstring ln;

  //while(getline(wcin, ln)) {
  while(fgetws(ln, 1010, stdin)) {
    ln[wcslen(ln)-1] = 0;
    map<wstring, char> suffix;
    wstringstream ss(ln);

    wstring w;
    while(ss >> w) {
      w = lower_wstr(w);
      suffix[w] = 'A' + suffix.size() - 1;
    }
    //assert(wcin.good());
    fgetws(ln, 1010, stdin);
    ln[wcslen(ln)-1] = 0;
    wstring title(ln);
    //getline(wcin, title);

    vector<wstring> poem;
    //while(getline(wcin, ln)) {
    while(fgetws(ln, 1010, stdin)) {
      //if(ln.size() == 0) break;
      ln[wcslen(ln)-1] = 0;
      if(wcslen(ln) == 0) break;
      poem.push_back(clean(ln));
      //wcerr << poem.back() << endl;
    }

    int n = poem.size();

    // first condition
    if(n != 14) {
      //wcout << title << ": Not a chance!" << endl;
      wprintf(L"%ls: Not a chance!\n", title.c_str());
      continue;
    }

    string ans;
    for(int i = 0; i < n; i++) {
      int length = poem[i].size();
      foreach(s, suffix) {
        int m = s->first.size();
        wstring curr = poem[i].substr(length - m);
        if(curr == s->first) {
          ans.push_back(s->second);
          break;
        }
      }
    }

    if(ans.size() != 14 || !rhymes.count(ans)) {
      //wcout << title << ": Not a chance!" << endl;
      wprintf(L"%ls: Not a chance!\n", title.c_str());
    }
    else {
      //wcout << title << ": " << ans.c_str() << endl;
      wprintf(L"%ls: %s\n", title.c_str(), ans.c_str());
    }
  }

  return 0;
}
