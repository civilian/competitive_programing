#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <cmath>
#include <ctime>
#include <cctype>
#include <cassert>
#include <iostream>
#include <sstream>
#include <iomanip>
#include <string>
#include <vector>
#include <stack>
#include <queue>
#include <deque>
#include <list>
#include <set>
#include <map>
#include <bitset>
#include <algorithm>
#include <numeric>
#include <complex>

#define D(x) cerr << #x << " = " << (x) << endl;
#define REP(i,a,n) for(int i=(a); i<(int)(n); i++)
#define FOREACH(it,v) for(__typeof((v).begin()) it=(v).begin(); it!=(v).end(); ++it)
#define ALL(v) (v).begin(), (v).end()

using namespace std;

typedef long long int64;

const int INF = (int)(1e9);
const int64 INFLL = (int64)(1e18);
const double EPS = 1e-13;

#define x 0
#define y 1
#define DIM     2               /* Dimension of points */
typedef int     tPointi[DIM];   /* type integer point */
typedef double  tPointd[DIM];   /* type double point */

int twocircles( tPointd c1, int r1, tPointd c2, int r2, tPointd p );
int twocircles0a( int r1, tPointd c2, int r2, tPointd p );
int twocircles0b( int r1, tPointd c2, int r2, tPointd p );
void twocircles00( int r1, double a2, int r2, tPointd p );


double length2( tPointd v )
{
   int   i;
   double   ss;

   ss = 0;
   for ( i=0; i < DIM; i++ )
      ss += v[i] * (double)v[i];
   return ss;
}

void   subvec( tPointd a, tPointd b, tPointd c )
{
   int   i;

   for ( i=0; i < DIM; i++ ) {
      c[i] = a[i] - b[i];
   }
}


/*---------------------------------------------------------------------
twocircles finds an intersection point between two circles.
general routine: no assumptions. returns # of intersections; point in p.
---------------------------------------------------------------------*/
int     twocircles( tPointd c1, int r1, tPointd c2, int r2, tPointd p)
{
   tPointd c;
   tPointd q;
   int nsoln = -1;

   /* translate so that c1={0,0}. */
   subvec( c2, c1, c );
   nsoln = twocircles0a( r1, c, r2, q );
   /* translate back. */
   p[x] = q[x] + c1[x];
   p[y] = q[y] + c1[y];
   return nsoln;
}

/*---------------------------------------------------------------------
twocircles0a assumes that the first circle is centered on the origin.
returns # of intersections: 0, 1, 2, 3 (inf); point in p.
---------------------------------------------------------------------*/
int     twocircles0a( int r1, tPointd c2, int r2, tPointd p )
{
   double dc2;              /* dist to center 2 squared */
   double rplus2, rminus2;  /* (r1 +/- r2)^2 */
   double f;                /* fraction along c2 for nsoln=1 */

   /* handle special cases. */
   dc2 = length2( c2 );
   rplus2  = (r1 + r2) * (r1 + r2);
   rminus2 = (r1 - r2) * (r1 - r2);

   /* no solution if c2 out of reach + or -. */
   if ( ( dc2 > rplus2 ) || ( dc2 < rminus2 ) )
      return   0;

   /* one solution if c2 just reached. */
   /* then solution is r1-of-the-way (f) to c2. */
   if ( dc2 == rplus2 ) {
      f = r1 / (double)(r1 + r2);
      p[x] = f * c2[x];   p[y] = f * c2[y];
      return 1;
   }
   if ( dc2 == rminus2 ) {
      if ( rminus2 == 0 ) {   /* circles coincide. */
         p[x] = r1;    p[y] = 0;
         return 3;
      }
      f = r1 / (double)(r1 - r2);
      p[x] = f * c2[x];   p[y] = f * c2[y];
      return 1;
   }

   /* two intersections. */
   return twocircles0b( r1, c2, r2, p );
}

/*---------------------------------------------------------------------
twocircles0b also assumes that the 1st circle is origin-centered.
---------------------------------------------------------------------*/
int     twocircles0b( int r1, tPointd c2, int r2, tPointd p )
{
   double a2;          /* center of 2nd circle when rotated to x-axis */
   tPointd q;          /* one solution when c2 on x-axis */
   double cost, sint;  /* sine and cosine of angle of c2 */

   /* rotate c2 to a2 on x-axis. */
   a2 = sqrt( length2( c2 ) );
   cost = c2[x] / a2;
   sint = c2[y] / a2;

   twocircles00( r1, a2, r2, q );

   /* rotate back */
   p[x] =  cost * q[x] + -sint * q[y];
   p[y] =  sint * q[x] +  cost * q[y];
   
   return 2;
}

/*---------------------------------------------------------------------
twocircles00 assumes circle centers are (0,0) and (a2,0).
---------------------------------------------------------------------*/
void     twocircles00( int r1, double a2, int r2, tPointd p )
{
   double r1sq, r2sq;
   r1sq = r1*r1;
   r2sq = r2*r2;

   /* return only positive-y soln in p. */
   p[x] = ( a2 + ( r1sq - r2sq ) / a2 ) / 2;
   p[y] = sqrt( r1sq - p[x]*p[x] );
   printf("%%twocircles00: p=(%g,%g)\n", p[x], p[y]);
}

int main()d{
    
}

