
Feature: As a librarian, I want to know what genre of books is being borrowed the most
  @US_05 @db
  Scenario: verify the common book genre that’s being borrowed
    Given Establish the database connection
    When I execute query to find most popular book genre
    Then verify "Action and Adventure" is the most popular book genre.