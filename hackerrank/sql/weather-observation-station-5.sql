select city c, length(city) l
from   station
order by l desc, c asc
limit 1;

select city c, length(city) l
from   station
order by l asc, c asc
limit 1;

/** OR **/
(select city, length(city) 
from station 
order by length(city) asc , city asc limit 1) 
union
(select city,length(city) 
from station 
order by length(city) desc, city asc limit 1)