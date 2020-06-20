Feature: Registration new user in booking
Scenario: create trash email and registration on booking

Given I go to booking.com
Then I register new user
And I go to mail.yandex.by
Then I go to booking.com second time
And I go to dashboard
And I check banner