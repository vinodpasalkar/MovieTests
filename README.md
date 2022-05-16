# Movie Docker application installation instructions
This application has been taken from [one of the tutorials](https://github.com/sidpalas/devops-directive/tree/master/2020-08-31-docker-compose)  on DevOps Directive YouTube channel.

# Getting Setup
The application is containerized with Docker. You need the following software installed:
* Docker
* make

# Running the application
clone the repo
Open it in VisualStudioCode ,open terminal and run below commands
To Build containers:
Execute `make build`
To Start the application:
Execute `make run`

# MovieTests
Sample UI automation of Movie application site and few APIs
This repo consists a customized automation framework for executing the test cases on web browser ,
the framework uses Serenity BDD libraries for integrating Jbehave, REST-Assured, Selenium and Appium.
This maven project demonstrates various styles of writing jbehave scenarios within Serenity BDD framework# MovieTests
Sample UI automation of Movie application site and few APIs
This repo consists a customized automation framework for executing the test cases on web browser ,
the framework uses Serenity BDD libraries for integrating Jbehave, REST-Assured, Selenium and Appium.
This maven project demonstrates various styles of writing jbehave scenarios within Serenity BDD framework

Please Install below
# Install java
# Install brew or home-brew:
# Install Maven


Set up instructions :

1. Download and install IntelliJ Idea Community version 

2. Clone the below repository in IntelliJ Idea : https://github.com/vinodpasalkar/MovieTests (Just clone this project in InteliJ idea community Edition 
and Go to Menu > Build > Build Project Once the build is completed/compiled successfully)

3. Ensure you have maven installed in your system - command from terminal -> brew install maven

4. Install below IntelliJ plugins by going to below navigation ( This will help in code navigation)

IntelliJ Idea > Menu > IntelliJ Idea > Preferences > Plugins > Marketplace 

Search below plugins one by one and install them 
>Cucumber for java 
>Gherkin
>Jbehave BDD Plugin
>Jbehave Support 


5. Running your first test

Please download the appropriate chromedriver file depending on your OS type and provide correct path for the same in the serenity.properties file.

To execute a particular test by tag run below command on terminal by going to the project directory

mvn clean verify -Dmetafilter="+scenario name"

For e.g. In our framework to execute the scenario 001 , Execute below command

mvn clean verify -Dmetafilter="+001"

Multiple scenarios can be run with below command:

mvn clean verify -Dmetafilter="+001 && +002 && +003"

Meta filtering the stories/scenario mvn clean verify -Dmetafilter="+SmokeTest" -- This command will run the scenarios with the tag, "SmokeTest" This way we can use this on CI-CD by tagging the test case under the different tags like movieAPITests, movieUITests regression , smoke , sanity etc...

To see the Results after execution

Go to /SampleWebTest/Reports/Today's date time For E.g the folder name will be "report - 09-11-2019_16-06-36" Every time you run a scenario or scenarios , it will generate the report folder with current date and time

Open index.html file in any browser to see the results.

Below are sample report snapshots


I have used serenity framework here.

Serenity documentation Below can be referred for a further reading on serenity libraries http://thucydides.info/docs/serenity-staging/

