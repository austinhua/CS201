CompSci 201 README for Markov Assignment, Fall 2014

Name: Austin Hua (ah335)

Date of submission: 9/30/14

Number of hours worked (leave just one of the choices below)

  between 5 and 10

With whom did you collaborate with on this assignment and what
information did you exchange?
UTAs during help hour on 9/30, help debugging

What other resources did you use?
Piazza, Java API, Lecture exercises

What are your thoughts about this assignment?


Timings:

1. 
  A. On the shakespeare text, an order-1 model takes roughly 1.25 seconds on average. An order-5 model takes roughly 1.00 seconds on average, 
  		20% less time than the order-1 model. An order-10 model takes roughly 0.95 seconds, 24% less time than the order-1 model.
  B. Using an order-1 model on Alice, it takes roughly 0.032 seconds on average. Using the order-1 model on Hawthorne
  		takes roughly 0.100 seconds on average, which is around 3.125 times as long as Alice. Hawthorne has around 3.128 times
  		as many characters as Alice, suggesting a linear relationship between time and number of characters.
  C. Using an order-1 model and the shakespeare text, 100 characters takes around 1.25 seconds. 200 characters takes
  		around 2.50 sdconds. 400 characters take around 5.00 seconds. It appears that doubling the number of characters
  		also doubles the time it takes, meaning that there is a lienar relationship between number of characters
  		generated and time. 800 and 1600 characters should then take around 10 and 20 seconds, respectively.
---
2. An order-5 Markov model on the shakespeare text takes roughly .95 seconds with 100 random characters. 1600 random 
		characters should then take 16 times as long, which is around 15.2 seconds. In addition, if you only know the
		time for another text, you can just find the ratio of characters of shakespeare to characters of that text and
		multiply the time by that.
---
3. Alice.txt created the map and generated characters too quickly, so shakespeare.txt was used instead. Creating a map 
		with 200 characters takes around 5 seconds. Creating a map with 400 charactes takes around 10 seconds. This is 
		expected since twice as many characters to create a map with should take twice as long because each character 
		is passed through and added to the map, meaning a linear relationship. Generating random characters still happened way too quickly, which is
		to be expected since a map implementation is designed to be very fast after initial map creation. In addition,
		the time to generate random characters should not change regardless of the size of the text.
---
4. Using an order-3 WordMarkovModel on hawthorne, with the current hashcode method takes around .1 seconds. Using a hashcode
		method that merely returns 15 for all objects results in around .25 seconds. 

Provide timings for the WordMarkovModel with different hashcode methods. 
Time the method you are given and compare the results that you achieve
with the better hashcode method that you developed.

---