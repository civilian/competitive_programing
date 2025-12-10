#include<vector>
#include<stack>
#include<set>
#include<map>
#include<queue>
#include<deque>
#include<string>
#include<iostream>
#include<algorithm>
#include<cstring>
#include<cassert>
#include<cstdlib>
#include<cstdio>
#include<cmath>
#include<string>

using namespace std;

#define s(n)					scanf("%d",&n)
#define sl(n) 					scanf("%lld",&n)
#define sf(n) 					scanf("%lf",&n)
#define ss(n) 					scanf("%s",n)
#define INF						(int)1e9
#define LINF					(long long)1e18
#define EPS						1e-9
#define maX(a,b)				((a)>(b)?(a):(b))
#define miN(a,b)				((a)<(b)?(a):(b))
#define abS(x)					((x)<0?-(x):(x))
#define FOR(i,a,b)				for(int i=a;i<b;i++)
#define REP(i,n)				FOR(i,0,n)
#define foreach(v,c)            for( typeof((c).begin()) v = (c).begin();  v != (c).end(); ++v)
#define mp						make_pair
#define FF						first
#define SS						second
#define tri(a,b,c)				mp(a,mp(b,c))
#define XX						first
#define YY						second.first
#define ZZ						second.second
#define pb						push_back
#define fill(a,v) 				memset(a,v,sizeof a)
#define all(x)					x.begin(),x.end()
#define SZ(v)					((int)(v.size()))
#define DREP(a)					sort(all(a)); a.erase(unique(all(a)),a.end())
#define INDEX(arr,ind)			(lower_bound(all(arr),ind)-arr.begin())
#define debug(args...)			{dbg,args; cerr<<endl;}
#define dline					cerr<<endl	

void sc(char &c){
	char temp[4];	ss(temp);	
	c=temp[0];
}

struct debugger
{
	template<typename T> debugger& operator , (const T& v)
	{	
		cerr<<v<<" ";	
		return *this;	
	}
} dbg;




typedef long long LL;
typedef pair<int,int> PII;
typedef pair<LL,LL> PLL;
typedef pair<int,PII> TRI;

typedef vector<int> VI;
typedef vector<LL> VL;
typedef vector<PII> VII;
typedef vector<PLL> VLL;
typedef vector<TRI> VT;

typedef vector<VI> VVI;
typedef vector<VL> VVL;
typedef vector<VII> VVII;
typedef vector<VLL> VVLL;
typedef vector<VT> VVT;


/*Main code begins now */

int testnum;
LL N;
LL M,K;
LL A,B,C,D;
LL X,Y;

const int MAX = 10000005;
int xval[MAX], yval[MAX], tval[MAX];
int xind[MAX], yind[MAX];
LL xoff,yoff,off;
LL xcycle,ycycle;
LL uptox,uptoy;

int xmin[MAX],xmax[MAX];
LL xminc[MAX],xmaxc[MAX];

VI ys[MAX];
LL g,xg,yg;



LL gcd(LL a,LL b)
{
	if(b==0) return a;
	return gcd(b,a%b);
}

LL lcm(LL a,LL b)
{
	return a*b/(gcd(a,b));
}

void preprocess()
{

}

inline int getx(LL n)
{
	if(n<uptox) return xval[n];
	else return xval[(n-xoff)%xcycle + xoff];
}

inline int gety(LL n)
{
	if(n<uptoy) return yval[n];
	return yval[(n-yoff)%ycycle + yoff];
}

int onceMore(int beg,int len,int ind)
{
	if(ind<beg) ind+=yg;
	if(beg <= ind && ind < beg+len) return 1;
	return 0;
}

void update(int i,int j,int lst,LL cntlst,int mst,LL cntmst)
{
	//debug("update : i j",i,j,"least",lst,cntlst,"most",mst,cntmst);
	int yindex = (i + xcycle*j) % ycycle;
	for(int zi=yindex; zi<xcycle; zi+=ycycle)
	{
		int z = xval[zi%xcycle];
		if(lst<xmin[z])
		{
			xmin[z]=lst;
			xminc[z]=cntlst;
		}
		else if(lst==xmin[z])
			xminc[z]+=cntlst;
			
		if(mst>xmax[z])
		{
			xmax[z]=mst;
			xmaxc[z]=cntmst;
		}
		else
			xmaxc[z]+=cntmst;
	}
}

void update(LL i)
{
	//debug(i,i%xcycle,i%ycycle);
	int x = xval[i%xcycle];
	int y = yval[i%ycycle];
	//debug(i,x,y,xmin[x],xminc[x],xmax[x],xmaxc[x]);
	
	if(y<xmin[x])
	{
		xmin[x]=y;
		xminc[x]=1;
	}
	else if(y==xmin[x])
		xminc[x]++;
		
	if(y>xmax[x])
	{
		xmax[x]=y;
		xmaxc[x]=1;
	}
	else if(y==xmax[x])
		xmaxc[x]++;
}

void solve()
{
	fill(xval,-1);
	fill(yval,-1);
	fill(xind,-1);
	fill(yind,-1);
	
	// Generate x
	uptox=N;
	xoff=N;
	xcycle=-1;
	for(int i=0;i<N;i++)
	{
		if(xind[X]>=0)
		{
			xoff = xind[X];
			xcycle = i-xoff;
			uptox = i;
			break;
		}
		xval[i]=X;
		xind[X]=i;
		X = (A*X+B)%M + 1;
	}
	
	// Generate y
	uptoy=N;
	yoff=N;
	ycycle=-1;
	for(int i=0;i<N;i++)
	{
		if(yind[Y]>=0)
		{
			yoff = yind[Y];
			ycycle = i-yoff;
			uptoy = i;
			break;
		}
		yval[i]=Y;
		yind[Y]=i;
		Y = (C*Y+D)%K + 1;
	}
	
	//debug(xoff,yoff,xcycle,ycycle);
	
	
	// Init hulls
	for(int i=0;i<MAX;i++)
	{
		xmin[i]=INF;
		xmax[i]=-1;
	}
	
	// Update hulls for offset portion
	
	LL off = max(xoff,yoff);
	for(int i=0;i<off;i++)
	{
		int x = getx(i);
		int y = gety(i);
		
		if(y<xmin[x])
		{
			xmin[x]=y;
			xminc[x]=1;
		}
		else if(y==xmin[x])
			xminc[x]++;
			
		if(y>xmax[x])
		{
			xmax[x]=y;
			xmaxc[x]=1;
		}
		else if(y==xmax[x])
			xmaxc[x]++;
	}
	
	
	// Update hulls for cycle portion
	//debug("entering cycles");
	
	if(xcycle>0 && ycycle>0)
	{
		N-=off;
		int allmin=INF,allmax=-1;
		
		for(int i=0;i<xcycle;i++)
			tval[i] = getx(i+off);
		for(int i=0;i<xcycle;i++)
			xval[i] = tval[i];
			
		for(int i=0;i<ycycle;i++)
			tval[i] = gety(i+off);
		for(int i=0;i<ycycle;i++)
		{
			yval[i] = tval[i];
			allmin = min(allmin, yval[i]);
			allmax = max(allmax, yval[i]);
		}
		//debug("off portion removed");
		
		
		
		
		for(int i=0;i<MAX;i++)
			ys[i].clear();
			
		g = gcd(xcycle,ycycle);
		yg = ycycle/g;
		xg = xcycle/g;
		LL rounds = N/xcycle;
		
		//debug("xcycle ycycle",xcycle,ycycle,"g xg yg",g,xg,yg,"rounds",rounds);
		
		for(int i=0;i<g;i++)
		{
			int cur = i;
			for(int j=0;j<yg;j++)
			{
				ys[i].pb(yval[cur]);
				cur = (cur+xcycle)%ycycle;
			}
			//debug(i,ys[i])
		}
		//debug("ys formed");
		
		if(rounds>=yg)
		{
			//debug("in full circle");
			LL yrounds = rounds/yg;
			LL yleft = rounds%yg;
			
			for(int i=0;i<g;i++)
			{
				int lst=INF,lsti=-1;
				int mst=-1,msti=-1;
				for(int j=0;j<yg;j++)
				{
					if(ys[i][j]<lst)
					{
						lst=ys[i][j];
						lsti=j;
					}
					if(ys[i][j]>mst)
					{
						mst=ys[i][j];
						msti=j;
					}
				}
				
				for(int j=0;j<yg;j++)
				{
					LL cntlst = yrounds + onceMore(j,yleft,lsti);
					LL cntmst = yrounds + onceMore(j,yleft,msti);
					update(i,j,lst,cntlst,mst,cntmst);
				}
			}
			//debug("out full circle");
		}
		else
		{
			//debug("in part");
			for(int i=0;i<g;i++)
			{
				set<int> heap;
				for(int j=0;j<rounds;j++)
					heap.insert(ys[i][j]);
				for(int j=0;j<yg;j++)
				{
					update(i,j,*heap.begin(),1,*heap.rbegin(),1);
					heap.erase(ys[i][j]);
					heap.insert(ys[i][(j+rounds)%yg]);
				}
			}
			//debug("out part");
		}
		
		//debug(rounds,xcycle,N,rounds*xcycle);
		for(LL i=rounds*xcycle; i<N; i++)
			update(i);
			
					
		//debug("remainder updates done");
		
		
		
		
			
		
		
		
		
		
		/*	
		
		for(int i=0;i<N;i++)
		{
			int x = xval[i%xcycle];
			int y = yval[i%ycycle];
			
			if(y<xmin[x])
			{
				xmin[x]=y;
				xminc[x]=1;
			}
			else if(y==xmin[x])
				xminc[x]++;
				
			if(y>xmax[x])
			{
				xmax[x]=y;
				xmaxc[x]=1;
			}
			else if(y==xmax[x])
				xmaxc[x]++;
		}
		
		*/
		
		
	}
	
		


	// Calculating lower and upper convex hulls	
	//debug("calcing result");
		
	LL lo=0,hi=0;
	int xlo=INF,xhi=-1;
	for(int i=1;i<=M;i++)
	{
		//debug("result",xmin[i],xmax[i],i,xminc[i],xmaxc[i]);
		if(xmin[i]<xlo)
		{
			lo+=xminc[i];
			xlo=xmin[i];
		}
		
		int j = M-i+1;
		if(xmax[j]>xhi)
		{
			hi+=xmaxc[j];
			xhi=xmax[j];
		}
	}
	//debug("result calc done");
	
	cout<<"Case #"<<testnum<<": "<<hi<<" "<<lo<<endl;
	
	
	
	
		
}



bool input()
{
	cin>>N>>X>>Y>>M>>K>>A>>B>>C>>D;
	//debug(N,X,Y,M,K,A,B,C,D);
	return true;
}


int main()
{
	preprocess();
	int T; s(T);
	for(testnum=1;testnum<=T;testnum++)
	{
		if(!input()) break;
		solve();
	}
}
