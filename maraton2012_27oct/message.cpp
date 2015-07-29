#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <cmath>
#include <vector>
#include <queue>
#include <utility>
#include <algorithm>

#define REP(i,a,n) for(int i=(a); i<(int)(n); i++)

using namespace std;

struct point {
	int x, y;
	point(int _x = 0, int _y = 0) {
		x = _x; y = _y;
	}
};

int dr[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
int dc[] = { -1, 0, 1, -1, 1, -1, 0, 1 };

const int maxn = 1010;

vector<point> poly;
char board[maxn][maxn];
bool visited[maxn][maxn];
int n, m, xs, ys, xl, yl;

int calculate_dist(int x, int y) {
	return x*x + y*y;
}

bool valid(int nr, int nc) {
	return (nr >= 0 && nr < maxn && nc >= 0 && nc < maxn);
}

void dfs(int r, int c, int pr, int pc) {
	if(visited[r][c]) return;
	
	if(calculate_dist(pr - r, pc - c) > m*m)
		return;
		
	visited[r][c] = true;
	board[r][c] = '#';
	
	for(int k = 0; k < 8; k++) {
		int nr = r + dr[k];
		int nc = c + dc[k];
		
		if(valid(nr, nc) && !visited[nr][nc]) {
			dfs(nr, nc, pr, pc);
		}
	}
}

bool bfs(int xs, int ys, int xl, int yl) {
	queue< pair<int,int> > Q;
	Q.push( make_pair(ys, xs) ); // ys = fila, xs = col
	visited[ys][xs] = true;
	
	while(!Q.empty()) {
		pair<int, int> current = Q.front();
		Q.pop();
		
		if(current.first == yl && current.second == xl)
			return true;
			
		for(int k = 0; k < 8; k++) {
			int nr = current.first + dr[k];
			int nc = current.second + dc[k];
			
			if(valid(nr, nc) && !visited[nr][nc] && board[nr][nc] == '.') {
				Q.push(make_pair(nr, nc));
				visited[nr][nc] = true;
			}
		}
 	}
	
	return false;
}

int main() {
	freopen("message.in", "r", stdin);
	
	while(scanf("%d%d", &n, &m) && (n|m)) {
		poly.clear();
		REP(i,0,n) {
			int x, y;
			scanf("%d%d", &x, &y);
			poly.push_back(point(x,y));
		}
		poly.push_back(poly[0]);
		
		scanf("%d%d%d%d", &xs, &ys, &xl, &yl);
		
		memset(board, '.', sizeof(board));
		memset(visited, false, sizeof(visited));
		
		REP(i, 0, n) {			
			point p1 = poly[i];
			point p2 = poly[i+1];
			// x = col, y = row
			if(p1.x == p2.x) { // Paralelo x
				for(int y = min(p1.y, p2.y); y <= max(p1.y, p2.y); y++) {
					board[y][p1.x] = '.';
					visited[y][p1.x] = false;
					dfs(y, p1.x, y, p1.x);
				}
			}
			else if(p1.y == p2.y) { // Paralelo y
				for(int x = min(p1.x, p2.x); x <= max(p1.x, p2.x); x++) {
					board[p1.y][x] = '.';
					visited[p1.y][x] = false;
					dfs(p1.y, x, p1.y, x);
				}
			}
		}

		if(bfs(xs, ys, xl, yl)) puts("Yes");
		else puts("No");
	}
}
