#include <bits/stdc++.h>

using namespace std;

int get_val(char ch) {
  if (isdigit(ch))
    return ch - '0';
  return 10;
}

int get_max_score(int cnt, int aces) {
  for (int i = 0; i < aces; i++)
    if (cnt + 10 <= 21)
      cnt += 10;
  return cnt;
}

void increase(int& p, int& a, char card) {
  if (card == 'A') p++, a++;
  else p += get_val(card);
}

int score_dealer(int cnt, int aces, string s, int pos) {
  int n = s.size();
  int best_aces = aces;
  int best_score = cnt;

  for (int i = pos; i < n; i++) {
    if (get_max_score(best_score, best_aces) >= 17)
      break;
    increase(best_score, best_aces, s[i]);
  }
  return get_max_score(best_score, best_aces);
}

bool solve(string s) {
  int n, player, dealer, player_aces, dealer_aces;

  n = s.size();
  player = dealer = player_aces = dealer_aces = 0;

  for (int i = 0; i < 4; i++) {
    if (i & 1)
      increase(dealer, dealer_aces, s[i]);
    else
      increase(player, player_aces, s[i]);
  }

  for (int take = 0; take + 4 <= n; take++) {
    int curr_aces = player_aces;
    int curr = player;

    for (int i = 0; i < take; i++)
      increase(curr, curr_aces, s[i+4]);
    curr = get_max_score(curr, curr_aces);

    if (curr > 21) continue;

    int curr_dealer = score_dealer(dealer, dealer_aces, s, take+4);

    if (curr_dealer > 21 || curr_dealer <= curr)
      return true;
  }

  return false;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  string s;
  while (cin >> s) {
    if (s == "JOKER") break;
    cout << (solve(s)? "Yes" : "No") << '\n';
  }

  return 0;
}
