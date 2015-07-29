#include <bits/stdc++.h>

using namespace std;

const string vowel = "aiyeou";
const string consonant = "bkxznhdcwgpvjqtsrlmf";

bool isvowel(char ch) {
  return vowel.find(ch) != string::npos;
}

string solve(string s) {
  string t;
  int n = s.size();
  
  for (int i = 0; i < n; i++) {
    if (isalpha(s[i])) {
      if (isvowel(tolower(s[i]))) {
        int pos = (vowel.find(tolower(s[i])) + 3) % vowel.size();
        if (isupper(s[i]))
          t.push_back(toupper(vowel[pos]));
        else
          t.push_back(vowel[pos]);
      }
      else {
        int pos = (consonant.find(tolower(s[i])) + 10) % consonant.size();
        if (isupper(s[i]))
          t.push_back(toupper(consonant[pos]));
        else
          t.push_back(consonant[pos]);
      }
    }
    else {
      t.push_back(s[i]);
    }
  }
  
  return t;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  
  string ln;
  
  while (getline(cin, ln))
    cout << solve(ln) << '\n';
  
  return 0;
}
