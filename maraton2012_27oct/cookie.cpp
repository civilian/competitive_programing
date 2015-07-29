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

const double EPS = 1e-10;
const double PI = acos(-1.0);

struct point {
    double x, y;
    point(double _x = 0.0, double _y = 0.0) {
        x = _x; y = _y;
    }
};

typedef pair< point, point > line;

int L, C, K;
vector<line> lines;
vector<point> chips;
vector<bool> chips_used;
vector< vector<point> > polygon_list;

double cross(point p, point q, point r) {
    return (r.x - q.x) * (p.y - q.y) - (r.y - q.y) * (p.x - q.x);
}

point lineIntersectSeg(point p, point q, point A, point B) {
    double a = B.y - A.y;
    double b = A.x - B.x;
    double c = B.x * A.y - A.x * B.y;
    double u = fabs(a * p.x + b * p.y + c);
    double v = fabs(a * q.x + b * q.y + c);
    
    return point((p.x * v + q.x * u) / (u + v),
                 (p.y * v + q.y * u) / (u + v));
}

vector<point> cutPolygonLeft(point a, point b, vector<point> Q) {
    vector<point> P;
    for(int i = 0; i < (int)Q.size(); i++) {
        double left1 = cross(a, b, Q[i]), left2 = 0.0;
        if(i != (int)Q.size() - 1) left2 = cross(a, b, Q[i+1]);
        if(left1 > -EPS) P.push_back(Q[i]);
        if(left1 * left2 < -EPS)
            P.push_back(lineIntersectSeg(Q[i], Q[i+1], a, b));
    }
    
    if(P.empty()) return P;
    if (fabs(P.back().x - P.front().x) > EPS ||
        fabs(P.back().y - P.front().y) > EPS)
        P.push_back(P.front());
    
    return P;
}

vector<point> cutPolygonRight(point a, point b, vector<point> Q) {
    vector<point> P;
    for(int i = 0; i < (int)Q.size(); i++) {
        double right1 = cross(a, b, Q[i]), right2 = 0.0;
        if(i != (int)Q.size() - 1) right2 = cross(a, b, Q[i+1]);
        if(right1 <= EPS) P.push_back(Q[i]); // !!!!!! MAXIMUM MACHETE. Aplicando las enseñanzas del coach
        if(right1 * right2 < -EPS)
            P.push_back(lineIntersectSeg(Q[i], Q[i+1], a, b));
    }
    
    if(P.empty()) return P;
    if (fabs(P.back().x - P.front().x) > EPS ||
        fabs(P.back().y - P.front().y) > EPS)
        P.push_back(P.front());
    
    return P;
}

vector<point> init() {
    vector<point> P;
    P.push_back(point(0,0));
    P.push_back(point(L,0));
    P.push_back(point(L,L));
    P.push_back(point(0,L));
    
    return P;
}

vector< vector<point> > cut(int k) { // k = recta con la que corto
    vector< vector<point> > ret;
    
    REP(i, 0, polygon_list.size()) {
        vector<point> P = cutPolygonLeft(lines[k].first, lines[k].second, polygon_list[i]);
        
        if(P.empty()) {
            ret.push_back( polygon_list[i] );
        }
        else {
            ret.push_back( P );
            P = cutPolygonRight(lines[k].first, lines[k].second, polygon_list[i]);
            if(!P.empty()) {
                ret.push_back( P );
            }
        }
    }
    return ret;
}

double area(vector<point> P) {
    double result = 0.0, x1, y1, x2, y2;
    
    for(int i = 0; i < (int)P.size() - 1; i++) {
        x1 = P[i].x; x2 = P[(i+1)].x;
        y1 = P[i].y; y2 = P[(i+1)].y;
        result += (x1 * y2 - x2 * y1);
    }
    
    return fabs(result) / 2.0;
}

double angle(point a, point b, point c) {
    double ux = b.x - a.x, uy = b.y - a.y;
    double vx = c.x - a.x, vy = c.y - a.y;
    
    return acos((ux*vx + uy*vy) /
                sqrt((ux*ux + uy*uy) * (vx*vx + vy*vy)));
}

bool inPolygon(point p, vector<point> P) {
    if((int)P.size() == 0) return false;
    
    double sum = 0;
    
    for(int i = 0; i < (int)P.size(); i++) {
        if(cross(p, P[i], P[i+1]) < 0)
            sum -= angle(p, P[i], P[i+1]);
        else
            sum += angle(p, P[i], P[i+1]);
    }
    return (fabs(sum - 2*PI) < EPS || fabs(sum + 2*PI) < EPS);
}

int main() {
    freopen("cookie.in", "r", stdin);
    
    while(~scanf("%d%d%d", &L, &C, &K) && (L|C|K)) {
        lines.clear();
        chips.clear();
        chips_used.clear();
        
        REP(i, 0, C) {
            int x, y;
            scanf("%d%d", &x, &y);
            chips.push_back(point(x, y));
            chips_used.push_back(false);
        }
        
        REP(i, 0, K) {
            int x1, y1, x2, y2;
            scanf("%d%d%d%d", &x1, &y1, &x2, &y2);
            lines.push_back(make_pair(point(x1, y1), point(x2, y2)));
        }
        
        polygon_list.clear();
        polygon_list.push_back(init());
        
        REP(i, 0, K) {
            polygon_list = cut(i);
        }
        
        double ans = 0.0;
        
        REP(i, 0, polygon_list.size()) {
            vector<point>& poly = polygon_list[i];
            int cnt = 0;
            REP(j, 0, C) {
                if(!chips_used[j]) {
                    if(inPolygon(chips[j], poly)) {
                        cnt++;
                        chips_used[j] = true;
                    }
                }
            }
            if(cnt > 0) {
                poly.push_back(poly[0]);
                double a = area(poly);
                ans = max(ans, cnt / a);
            }
        }
        
        //ans += 0.0001;
        int ret = round(ans * 1000);
        printf("%d.%02d\n", ret/1000, ret%1000);
    }
}
