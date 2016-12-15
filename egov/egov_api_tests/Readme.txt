Machine Set up details to execute on your machine locally:
-----------------------------------------------------------
Pre-requisites:

1.) Install latest JDK (https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html)
2.) Set up JAVA_HOME variable with proper path as system variable
3.) Install IntelliJ Community edition (https://www.jetbrains.com/idea/download/)
4.) Check out the project from "https://bitbucket.org/gojek/gojek-consumer-api-tests"
5.) Rebuild the project on your machine




Detailed steps for running the test on your LOCAL Machine:
------------------------------------
1.) Using gradle.

Run: gradle clean build allTests -Denv=staging
This will run all tests against staging environment. 