#include<iostream>
#include<algorithm>
#include<vector>

using namespace std;

const int SIZE = 110;
int matrix[SIZE][SIZE];

struct Rect{
  int xMin,yMin,xMax,yMax;
  Rect() {}
  Rect(int xMin,int yMin,int xMax,int yMax) : xMin(xMin),yMin(yMin),xMax(xMax),yMax(yMax) {}
  Rect(const Rect &r) : xMin(r.xMin),yMin(r.yMin),xMax(r.xMax),yMax(r.yMax) {}
};

int intersect(Rect &a, Rect &b){
  return max(0,   min(a.xMax, b.xMax) - max(a.xMin, b.xMin)) * max(0, min(a.yMax, b.yMax) - max(a.yMin, b.yMin));
}

void rellenar(Rect r){
  for(int i=r.xMin;i<r.xMax;i++ ){
    for(int j=r.yMin;j<r.yMax;j++ ){
      matrix[i][j] = 1;
    }
  }
}

bool valido(int x,int y){
  return x >=0 && x < SIZE && y>=0 && y<SIZE;
}

int dx[] ={-1,-1,-1,0,1,1,1};
int dy[] ={-1,0,1,1,1,0,-1,-1};
int area = 0;

void dfs(int x,int y) {
  if(matrix[x][y] == 0) return;

  matrix[x][y] = 0;
  area++;
  for(int i = 0; i < 8; i++) {
    int xn,yn;
    xn = x+dx[i];
    yn = y+dy[i];
    if(valido(xn,yn)){
      if(matrix[xn][yn] == 0) continue;
      if(matrix[xn][yn] == 1)
        dfs(xn,yn);
    }
  }
}

int main(){

  int t;
  cin >> t;
  bool primerCaso = true;
  while(t--){
    if(!primerCaso)
      cout << endl;
    else
      primerCaso = false;
    int n;
    cin >> n;
    vector<Rect> rects;
    for(int i=0;i<n;i++) {
      int xMin,yMin,xMax,yMax;
      cin >> xMin >> yMin >> xMax >> yMax;
      Rect temp(xMin,yMin,xMax,yMax);

      rects.push_back(temp);
    }

    for(int i=0; i<SIZE;i++) {
      for(int j=0; j<SIZE;j++) {
        matrix[i][j] = 0;
      }
    }

    area = 0;

    for(int i=0;i<rects.size();i++) {
      rellenar(rects[i]);
    }

    dfs(rects[0].xMin,rects[0].yMin);

    cout << area << endl;
  }
  return 0;
}
