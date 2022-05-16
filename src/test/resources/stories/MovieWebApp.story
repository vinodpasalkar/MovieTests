Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal


Scenario: Add a new movie from the web app
!-- This scenario can be reused for another items as well
Meta:
@001 @AddMovie @movieUITests
Given I am on a movie list page
When I add a new movie with details as movieName movieRating and movieTime
Then the movieName should be added successfully
Examples:
|movieName       |movieRating    |movieTime    |
|Harry Potter    |4              |1 hr 30 mins |
|Inception       |5              |1 hr 55 mins |

Scenario: Update a movie from the web app
!-- This scenario can be reused for another items as well
Meta:
@002 @UpdateMovie @movieUITests
Given I am on a movie list page
When I update rating for movie movieName as movieRating
Then the movie should be updated successfully for movieRating
Examples:
|movieName       |movieRating    |
|Harry Potter    |5              |

Scenario: Delete a movie from the web app
!-- This scenario can be reused for another items as well
Meta:
@003 @DeleteMovie @movieUITests
Given I am on a movie list page
When I delete the movieName from UI
Then the movieName should be deleted successfully
Examples:
|movieName  |
|Inception  |