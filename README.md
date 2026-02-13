# Type-B-Digital-Assignment

## How To Run

### Prerequisites

- Java 17 - Check Java version using this command.
    ```bash
    java -version
    ```
- Maven - Check if Maven is installed using this command.
    ```bash
    mvn -v
    ```
- Port 8080 - Find if the port is occupied and kill that task using this commends.

  Mac:
  ```bash
  kill -9 $(lsof -t -i :8080)
  ```
  Windows:
    ```bash
    netstat -ano | findstr :8080
    ``` 
  And then,
    ```bash
    taskkill /PID PID /F
    ```
  Replace PID with the actual PID from the above command's response.

### Start Application

Run this command from the project location to start the application.
```bash
mvn spring-boot:run
```
Or open the project with IntelliJ Idea IDE, wait for the files to load and indexing to complete. Then click on the run button.

Then your application will start on ```http://localhost:8080``` and the API URL is
http://localhost:8080/api/hello-world?name=alice.

---

## Run Test

Run below command to run test cases.
```bash
mvn test
```
Or open the project with IntelliJ Idea and open the Maven options menu. Select 'clean' and 'test' under Lifecycle and click on the Maven run button.

---

## Assumptions

- If the name starts with a white space, white space is the considered starting character.
- If the name starts with a numeric character or a URL-safe special character, the name is an invalid entry.
- If the name is empty, it is an invalid entry.
- The name cannot be null.
- The name cannot have non URL-friendly special characters. _E.g.,: &, %, #_.
