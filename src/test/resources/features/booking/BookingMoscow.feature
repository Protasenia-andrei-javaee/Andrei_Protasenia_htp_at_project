Feature: Finding hotels in Moscow
Scenario: Finding hotel with minimal budget in Moscow

Given I go to booking.com
Then I set dropbox data
Then I do actions with data
And I choose hotels from min budget
And I check price of hotel and price in filters