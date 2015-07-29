#include <cstdlib>
#include <cstring>
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

typedef long long int64;

struct Trie {
    bool is_word;
    struct Trie* edges[26];
};

void initialize(Trie* vertex) {
    vertex->is_word = false;
    for(int i = 0; i < 26; i++)
        vertex->edges[i] = NULL;
}

void addWord(Trie* vertex, const char* word) {
    if(*word == 0) {
        vertex->is_word = true;
    }
    else {
        int k = *word - 'a';
        if(vertex->edges[k] == NULL) {
            vertex->edges[k] = new Trie;
            initialize(vertex->edges[k]);
        }
        addWord(vertex->edges[k], ++word);
    }
}
int64 pressed_g;

void dfs(Trie* t, bool first, int64 cnt) {
    int sons = 0;
    
    for(int i = 0; i < 26; i++)
        if(t->edges[i] != NULL)
            sons++;
    for(int i = 0; i < 26; i++) {
        if(t->edges[i] != NULL) {
            int next = cnt;
            if(first) next++;
            else if(t->is_word || sons > 1)
                next++;
            dfs(t->edges[i], false, next);
        }
    }
    if(t->is_word) {
        pressed_g += cnt;
    }
}


int main() {
#ifdef LOCAL
    freopen("c.in", "r", stdin);
    freopen("c.out", "w", stdout);
#else
    ios_base::sync_with_stdio(false);
#endif
    int n;
    cout.precision(2);
    cout.setf(ios::fixed);
    while(cin >> n) {
        Trie t;
        initialize(&t);

        for(int i = 0; i < n; i++) {
            string w;
            cin >> w;
            addWord(&t, w.c_str());
        }
        pressed_g = 0;
        dfs(&t, true, 0);

        double ans = (double)pressed_g / (double)n;
        cout << ans << endl;
    }
}
