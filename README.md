**Getting Started**
1. Clone the master branch from Github. 
2. Run mvn clean install -Dcucumber.options="--tags @test" -PXendit-qa-ui from CommandLine. 
 
 PXendit-qa-ui : -- > Added maven profile.

Software Installed in system 
Java >1.8
Maven
Chrome browser

**Framework Walkthrough **

1. src/test/resources/com/test/xendit/feature_ui : Consists of Feature file(CalculatorTest.feature) listing the scenarios. 
2. src/test/java/com/test/xendit/feature_ui : Consists of the Runner file(ITRunCukes), Hooks(ManageCommon) and Step Definition(ManageCalculatorTestStepDef)

3. src/main/resources/
    a. ConfigFile : Xendit.properties file , parameters to define waits and Browser type is Defined, Also defined parameters for Selenium Grid ( would share docker compose file to create Grid)
    b. ScreenShots : Saving ScreenShots of Canvas Element to fetch the result out using OCR Libraray
    c. tessData : Adding ENG Translation lib file for OCR lib used in extracting out the text from Image.
    
4. src/main/java
 
 a. driver : Saving the driver Files for different OS. But replaced the logic of using Browser Driver with WebDriverManager JAR which would download the browser automatically based on user machine's OS and Browser Version.
 b. helper : Contains code logic for Logging, Broswer configurations, common utility, waits
        helper/Util : This has two classes 
                i. Calculator Matrix : Created TwoD Array based saving Calculator buttons position index.
                2. OCRLib : Used JavaCPP OCR lib to extract Test from image
 c. PageAction : Contains Calculator page object class 
         Logic Used : 
          i. Based on Canvas Height and width, found the position of each button without any hardcoding of pixels, used Webelement location to fetch relative button position, this would make this code work different screen sizes too.
          ii. Used Action class to perform button clicks based on relative offsets calculated from Canvas start point.
          iii. Saved Screenshot of Canvas Element( not whole webpage, only webelement) and used OCR library to extract result from snapshot.
    
    
Have configured the File Paths relative to both OS MAC and Windows, so logic of file paths reading should work in both OS.   
    
**To Run From IDE **
1. Right click feature file to run 
2. Right the Runner File (ITRunCukes) to run the code.

**Reports **
1. After every successful run, the cucumber report link would come which would be valid for 24 hrs

![image](https://user-images.githubusercontent.com/29858449/129104907-9f98e317-ad60-4fe3-8d0f-69db536f018c.png)




**Improvements ( Run code in Docker , to avoid environment/OS level issues)**

1. Would package the code as Fat jar ( already added maven plugins to package both main and test folders in jar )
2. Create Docker File to create docker image of FAT jar with entry point script using cucuber.api.cli.main to trigger tests
3. Would add Docker compose file for running Selenium Server, Chrome Browser and Automation code image in a network to trigger the tests in Docker Container, already coded the selenium grid logic in automation code, only need to set the docker infra.
