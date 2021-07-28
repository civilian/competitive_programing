# cache system

The cache has a size; max 5

| Web page       | content           |when|
| ------------- |:-------------:|:-------------:|
| * www.google.com |"1"|1| 
| www.amazon.com |"2"|2|
| www.wikipedia.com |"3"|3|
| a |"3"|4|
| b |"3"|5|
| c |"3"|1|

So what happens if you get the same web page twice

| Web page       | content           |when|
| ------------- |:-------------:|:-------------:|
| a |"1"|1| 
| a |"1"|2|
| c |"3"|3|

## solutions a

order_of_loading :web page => integer

content: web page => content

a, b,c

So what happens if you get the same web page twice

| Web page       | content           |when|
| ------------- |:-------------:|:-------------:|
| 1 |"1"|1| 
| 1 |"1"|1|
| 3 |"2"|2|
| 4 |"3"|3|
| 5 |"4"|4|
| 6 |"5"|5|
| 1 ** |"1"|1|
| 2 |"5"|1|

## solution b

call_page(1, 1)
call_page(2, 5)
### what needs to happen?
order_of_loading[1] = size

you don't need to do:
order_of_loading[6] = size - 1

In every object

## solution c
| Web page       | content           |when=> time|how many|
| ------------- |:-------------:|:-------------:|:-------------:|
| *1 |"1"|1|| 
| *1 |"1"|2||
| *3 |"2"|4||
| 4 |"3"|7|2|
| 5 |"4"|8|3|
| 6 |"5"|9|4|
| 1 |"1"|10|5|
| 2 |"5"|1|5|



