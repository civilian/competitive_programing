#include <cstdlib>
#include <cstring>
#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

const int INF = (int)1e9;

int R, C;
vector<string> board;
vector<bool> visited;

int dr[] = { 1, 0, -1, 0};
int dc[] = { 0, 1, 0, -1};

bool valid(int nr, int nc) {
    return (nr >= 0 && nr < R && nc >= 0 && nc < C && board[nr][nc] != 'X');
}

int cnt;

int dfs(int r, int c) {
    if(visited[r][c]) return 0;
    visited[r][c] = true;
    cnt++;
    for(int k = 0; k < 4; k++) {
        int nr = r + nr[k];
        int nc = c + nc[k];
        
        if(valid(nr, nc) && !visited[nr][nc]) {
            dfs(nr, nc);
        }
    }
}

int main() {
#ifdef LOCAL
    freopen("in", "r", stdin);
    freopen("out", "w", stdout);
#else
    ios_base::sync_with_stdio(false);
#endif
    while(cin >> R >> C) {
        board.clear();
        for(int i = 0; i < R; i++) {
            string line; cin >> line;
            board.push_back(line);
        
        }
        visited.assign(R, vector<bool>(C, false));
        int ans = INF;
        
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(board[i][j] == 'X')
                    visited[i][j] = true;
                if(!visited[i][j]) {
                    cnt = 0;
                    dfs(i, j);
                    if(cnt & 1) { // odd
                        ans = min(cnt, ans);
                    }
                }
            }
        }
        
        cout << (ans == INF? 2 : 1) << endl;
    }
}
