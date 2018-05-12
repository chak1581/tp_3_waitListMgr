# SEIS 635 TP3 - GPS WaitList Manager
Spring 2018 | Authors:
<a href="https://github.com/chak1581">Ipshita Chakrabarty</a>, <a href="https://github.com/zhan7829">Shanqi Zhan</a>, <a href="https://github.com/hoveronica">Veronica Ho</a> & <a href="https://github.com/brananbrett">Brett Branan</a>  
TP-3 Repo: https://github.com/chak1581/tp_3_waitListMgr

## Artifacts
#### 1. Code Smells (See <a href="https://github.com/chak1581/tp_3_waitListMgr/commit/69513fdfba329fc314929bcacb44614fbdc5d691">69513fd</a> & <a href="https://github.com/chak1581/tp_3_waitListMgr/commit/af84627b50a972c76641456426b513b477b4fec2">af84627</a>)
1. Renaming of Variables to avoid the need to add comments
2. Extraction of Methods to make the methods shorter
3. Refactor code to make it testable
4. Refactoring to add more class variables
5. All print statements moved to the UI class  

#### 2. User app help and documentation
User can choose to read through `user guide` when the app first starts to run. Console prompts user to type Y or N. 
============================User Manual=============================  
• Provide the filepath for the desired registration report when prompted  
• Console will return the courses that have new open spots. 
• Provide the filepath for the desired waitlist report when prompted (only when there is open spots).  
• Customize email message to students.  
• Send emails by typing each student's email address.  

#### 3. JUnit Tests
ECLEmma shows `77.2%` of code coverage. CourseRegistration, CourseWaitList and EmailManager classes all have `90%` code coverage. 

#### 4. Reverse-engineer UML Class Diagram using ObjectAid UML Explorer
Class diagrams in TP-3.2 and TP-3.3 are both included in the project folder
