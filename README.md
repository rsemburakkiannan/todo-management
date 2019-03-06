# todo-management

### Prerequisites to compile & run
* JDK 8
* Maven

### How to run?
Follow the following steps
```
git clone https://github.com/rsemburakkiannan/todo-management.git 
cd todo-management
mvn spring-boot:run
```

You will see application running in `http://localhost:8009`. Id and Password for this application is `admin`/`admin`.


### How to run Selenium Test Cases?


```
java -cp <Working Dir>\target\dependency\*;<Working Dir>\target\devops-case-study-0.0.1-SNAPSHOT.jar;<Working Dir>\target\devops-case-study-0.0.1-SNAPSHOT-tests.jar org.testng.TestNG D:\todo-management\src\main\resources\testng.xml

Example - java -cp D:\todo-management\target\dependency\*;D:\todo-management\target\devops-case-study-0.0.1-SNAPSHOT.jar;D:\todo-management\target\devops-case-study-0.0.1-SNAPSHOT-tests.jar org.testng.TestNG D:\todo-management\src\main\resources\testng.xml
```