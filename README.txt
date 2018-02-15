[Food Cost Calculator]

 This program was created to calculate the cost of food.
 This is done by loading various recipe text files into the HashMap, and multiplying the # of ingredients by the
 amount of each item sold.

[Examples]

 (These text files contain the following text for this example.)

 sandwiches.txt -> BLT
                   Bacon,4
                   Lettuce,2

 spuds.txt      -> Chicken Spud
                   Chicken,4
                   Cheese Mix,2

 salads.txt     -> SW Salad
                   Chicken,4
                   Cheese Mix,2



 sales.txt      -> BLT,5
                -> Chicken Spud,10
                -> SW Salad,15
------------------------------------------------------------------
[Example User Input and Output -> (#) user input]
 (2) To perform a calculation
 (1) To calculate cost of Sandwiches
  For Sandwich: BLT, 20 ounces of Bacon was used
  For Sandwich: BLT, 10 ounces of Lettuce was used
 (2) To calculate cost of Spuds
  For Sandwich: Chicken Spud, 400 ounces of Chicken was used
  For Sandwich: Chicken Spud, 200 ounces of Cheese Mix was used
 (3) To calculate cost of Salads
  For Sandwich: SW Salad, 100 ounces of Chicken was used
  For Sandwich: SW Salad, 50 ounces of Cheese Mix was used

[Notes]
 *Program will loop until user enters (0) at the question "Do you wish to do something else?"
 *Program will catch an invalid input such as any characters/too big numbers, and ask user to try again
 *Text files are for testing purposes only, but real data can be used

[Author]
 Jonah Waschek

[Date]
 February 13th, 2018