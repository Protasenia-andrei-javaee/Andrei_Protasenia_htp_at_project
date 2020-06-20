Feature: Finding hotels in Paris
Scenario: Finding hotel with minimal price but from max budget in Paris

Given I go to booking.com
Then I set dropbox data
And I sort hotels by max budget
And I check price of hotel and price in filters