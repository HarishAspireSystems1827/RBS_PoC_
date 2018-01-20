# RBS_PoC_

Prerequisite on Required Software:
1) Install Java
2) Install Maven
3) Install Eclipse
4) Download Chrome Driver from this like and place in a folder - (https://chromedriver.storage.googleapis.com/index.html?path=2.35/) / download chromedriver_win32.zip
5) Download Selenium standalone Driver from this link and place in same folder as chromedriver - (http://www.seleniumhq.org/download/) / download 3.81 version 

java -jar selenium-server-standalone-3.5.0.jar -role hub 


Import to Eclipse and DO necessary plugin installation on Eclipse:
1)File -> Import -> Maven (Existing maven Project) -> click import
2)Right click project -> Maven -> update project
3)Help > Eclipse Marketplace -> search 'cucumber' and install two result s/w
4)Help > Eclipse Marketplace -> search 'TestNG' and install


How to Run script:
1) Open Command Prompt. In CMD, navigate to folder location where you stored selenium standalone driver (like "C:\>cd Grid")
2) Then enter this command , to start selenium standalone server - java -jar selenium-server-standalone-3.5.0.jar -role hub
3) Open second Command Prompt. In CMD, navigate to folder location where you stored chromdriver (like "C:\>cd Grid")
4) Then enter this command , to start chromdriver - java -Dwebdriver.chrome.driver=C:/Grid/chromedriver.exe -jar selenium-server-standalone-3.5.0.jar -port 5555 -role node -hub http://localhost:4444/grid/register -browser "browserName=chrome, version=ANY, maxInstances=10, platform=WINDOWS"
5) Right click on the TestNG.xml>Run as>TestNG Suite (you can find this file under "src/test/resources") and click 
6) Now two scenarios will get executed
7) You can see log message in Console


Reports:
1) Once execution is completed
2) Go to "RBS_PoC\test-output\RBS_Report.html"
3) Open it and you can see log message along captured details and screenshot for both scenarios