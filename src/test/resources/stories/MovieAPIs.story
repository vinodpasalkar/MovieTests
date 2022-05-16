Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: Add a new movie via API and validate the response
!-- This scenario can be reused for another items as well
Meta:
@004 @movieAPITests
Given I create a movie with movieName movieRating and movieTime
Then the movieName should be created successfully
Examples:
|movieName  |movieRating    |movieTime    |
|Spiderman  |4              |2 hrs 2 mins |
|Titanic    |5              |3 hrs 2 mins |

Scenario: Update a movie via API and validate the response
!-- This scenario can be reused for another items as well
Meta:
@005 @movieAPITests
Given Movies are already present
When I update movieName rating to movieRating and time to movieTime
Then the values should be updated successfully
Examples:
|movieName  |movieRating    |movieTime    |
|Spiderman  |5              |2 hrs 3 mins |

Scenario: Delete a movie via API and validate the response
!-- This scenario can be reused for another items as well
Meta:
@006 @movieAPITests
Given Movies are already present
When I delete movieName from API
Then the movieName should be deleted successfully from API
Examples:
|movieName  |
|Titanic  |