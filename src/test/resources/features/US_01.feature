Feature: Default

	Background:
		Given Establish the database connection
	
	@TS25-129 @LibraryCT @db
	Scenario: Verify customer data matched with Database

		    When Execute query to get all IDs from users
		    Then verify all users has a unique ID

	@TS25-130 @LibraryCT @db
	Scenario: verify users' table columns
		When Execute query to get all columns
		Then verify the below columns are listed in the result
			| id            |
			| full_name     |
			| email         |
			| password      |
			| user_group_id |
			| image         |
			| extra_data    |
			| status        |
			| is_admin      |
			| start_date    |
			| end_date      |
			| address       |