#include <cstdio>
#include <unistd.h>

class _out
{
  public:
    _out()
    {
      buffend = buff + sizeof(buff);
      ptr = buff;
    }
    ~_out()
    {
      Flush();
    }
    void WriteChar(char c)
    {
      if (ptr+1 >= buffend)
        Flush();

      *(ptr++) = c;
    }

    void WriteString(char * str)
    {
      while (*str && ptr < buffend)
        *(ptr++) = *(str++);

      if (ptr >= buffend)
        Flush();

      while (*str) // assume that buffer is large enough
        *(ptr++) = *(str++);
    }

    void WriteUInt(unsigned int value, char sep)
    {
      if (ptr+10+1 >= buffend)
        Flush();

      char txt[16];
      char * p = txt;
      int len = 0;
      do
      {
        *(p++) = (value%10) + '0'; len++;
        value /= 10;
      } while (value > 0);

      while (len--)
        (*ptr++) = *(--p);

      *(ptr++) = sep;
    }

    void Flush()
    {
      if (ptr-buff > 0)
      {
        write(1, buff, (int)(ptr-buff));
        ptr = buff;
      }
    }

  protected:

    char buff[32768];
    char * buffend, * ptr;
} out;



#define MAX 1000000000
#define LMT 31623

int flag[MAX>>6];

#define ifc(x) (flag[x>>6]&(1<<((x>>1)&31)))
#define isc(x) (flag[x>>6]|=(1<<((x>>1)&31)))

void sieve() {
  int i, j, k, last = 2, sz = 2, m = 1;
  out.WriteUInt(last, '\n');
  for(i=3; i<LMT; i+=2)
    if(!ifc(i)) {
      for(j=i*i,k=i<<1; j<MAX; j+=k)
        isc(j);
      sz++;
      if(sz - m == 500) {
        last = i;
        out.WriteUInt(last, '\n');
        m = sz;
      }
    }

  j = k = 1;
  for(i=last+1; i<MAX; i+=2) {
    if(!ifc(i)) {
      j++;
      if(j-k==500) {
        out.WriteUInt(i, '\n');
        k = j;
      }
    }
  }
}

int main() {
  sieve();
  return 0;
}
