SELECT IF(A+B>C AND A+C>B AND B+C>A,
          IF(A=B AND B=C, "Equilateral",
            IF(A=B OR C=B OR A=C, "Isosceles", "Scalene")), 
    "Not A Triangle")
FROM triangles;