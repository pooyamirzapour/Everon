## Car Charging Session
This project implements some APIs for submit, update, get, and summarized report of car charging
session with the help of REST api.

### Approach
The using data structure for this assignment is ***ConcurrentSkipListMap***. This construct
allows us to create thread-safe logic in a lock-free way. It's ideal for problems when we
want to make an immutable snapshot of the data while other threads are still inserting
data into the map. For making ***critical section*** it has been used ***ReenrtantLock***.
For more detail please see the below link:

[ConcurrentSkipListMap] https://www.baeldung.com/java-concurrent-skip-list-map

[ReenrtantLock] https://www.baeldung.com/java-concurrent-locks


### Technologies, Framework and Tools
-	Java 8
-	Spring Boot
-   Spring fox
-	JUnit 5
-	Maven
-   Lombok
-   Docker