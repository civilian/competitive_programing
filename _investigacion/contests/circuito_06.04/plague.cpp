#include <iostream>
#include <string>
#include <map>
#include <algorithm>

#include <tr1/unordered_map>

#define foreach(it,v) for(__typeof((v).begin()) it=(v).begin(); it != (v).end(); it++)

using namespace std;

bool check(std::tr1::unordered_map<string,int> a, std::tr1::unordered_map<string,int> b) {
  foreach(it, a) {
    if(b.count(it->first)) {
      if(b[it->first] != a[it->first])
        return false;
    }
    else return false;
  }
  return true;
}

int main() {
  ios_base::sync_with_stdio(false);

  int nm, ni, nc, n;
  while(cin >> nm >> ni >> nc >> n) {
    if(!(nm|ni|nc|n)) break;

    std::tr1::unordered_map<string, string>  trans;
    std::tr1::unordered_map<string, string>::iterator it;
    bool nondeterministic = false;
    for(int i = 0; i < nm; i++) {
      string p, q;
      cin >> p >> q;
      it = trans.find(p);
      if(it == trans.end())
        trans[p] = q;
      else if(it->first == p && it->second == q)
        continue;
      else
        nondeterministic = true;
    }

    std::tr1::unordered_map<string, int> config, sol;

    for(int i = 0; i < ni; i++) {
      string p;
      int c;
      cin >> p >> c;
      config[p] += c;
    }

    for(int i = 0; i < nc; i++) {
      string p;
      int c;
      cin >> p >> c;
      sol[p] += c;
    }

    if(nondeterministic) {
      cout << "Protein mutations are not deterministic\n";
      continue;
    }

    bool has_sol = false;
    int k = 0;
    while(k <= n) {
      if(check(config, sol)) {
        has_sol = true;
        break;
      }

      std::tr1::unordered_map<string,int> curr;
      foreach(s, config) {
        if(trans.count(s->first)) {
          curr[trans[s->first]] += config[s->first];
        }
        else {
          curr[s->first] += config[s->first];
        }
      }
      config = curr;
      k++;
    }
    if(has_sol) cout << "Cure found in " << k << " mutation(s)\n";
    else cout << "Nostalgia for Infinity is doomed\n";
  }
  return 0;
}
