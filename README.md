# SDET-test
## Challenge
Create pilot Java test framework for testing NASA's open API.

NASA has an open API: https://api.nasa.gov/index.html#getting-started. It grants access to different features e.g: Astronomy Picture of the Day, Mars Rover Photos, etc.

We would like to test different scenarios that the API offers:
1. Retrieve the first 10 Mars photos made by "Curiosity" on 1000 Martian sol.
2. Retrieve the first 10 Mars photos made by "Curiosity" on Earth date equal to 1000 Martian sol.
3. Retrieve and compare the first 10 Mars photos made by "Curiosity" on 1000 sol and on Earth date equal to 1000 Martian sol.
4. Validate that the amounts of pictures that each "Curiosity" camera took on 1000 Mars sol is not greater than 10 times the amount taken by other cameras on the same date.

## Instructions
You will need to fork the repository and build the solution in Github **publicly**. Once you are finished, let HR know and share a link to your fork or a Zip file with your solution and the URL of the repository.

Implementation deadline is 3 days. Please let us know the time that you spent to achieve the task.

# Implemented Solution

For solving the challenge, it was implemented a framework with the following characteristics
1. Maven as a dependency manager
2. TestNG as a test runner
3. RestAssured java library to make the calls to the target API
4. Allure reporter

## Decisions made

I decided to only implement two tests based on the requirements. 
The items 1. and 2. were solved as part of the api calls abstraction implemented in the class _NasaApiFramework_. 
Also I've created an application properties file, to keep some information as the base url and api key more
maintainable.

## How to build the project and execute the tests
### Preconditions

Have maven, java and allure CLI correctly configured

### Build the project

In a terminal, within the root folder run: *mvn clean install -DskipTests*

### Execute the Tests

After building the project, within the root folder, run: *mvn clean test*

After running the tests, in the terminal the results will be console logged.
Also a folder, allure-results, will be created. To generate the Allure report run: *allure serve*

## Final notes
It took me around 2 days to get the challenge completed.
