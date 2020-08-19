SELECT s.name FROM Students AS s 
JOIN Packages AS sp ON s.id = sp.id
JOIN Friends AS f ON s.id = f.id
JOIN Packages as fp ON f.friend_id = fp.id
WHERE sp.salary < fp.salary
ORDER BY fp.salary;