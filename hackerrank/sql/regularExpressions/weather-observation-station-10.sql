/** Not, Negate regular expression*/
SELECT DISTINCT city
FROM   station
WHERE  city RLIKE '^.*[^aeiouAEIOU]$'