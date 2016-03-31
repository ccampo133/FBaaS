# Fizz Buzz as a Service (FBaaS)

FBaaS is a standalone REST API that manages users in a priority queue. Their priority is determined using an algorithm
based on the classic interview question "Fizz Buzz". The API endpoint documentation is below.

Requires Java 8

### Enqueue a user `[/queue/users] [POST]`

#### Parameters:

Should be of type `x-www-form-urlencoded`

+ id: the ID of the user; a long (1 to 9223372036854775807).

+ timestamp: the time since the Epoch (Jan 1 1970) in milliseconds; UTC.

Example:

    id=12345&timestamp=1437098605  # Fri 17 Jul 2015 02:03:25
    
#### Response:

+ 201 CREATED: User was added successfully.

+ 400 BAD REQUEST: The user with this ID already exists in the queue.

The location header will contain the URI of the created user resource.


### Dequeue a user `[/queue/dequeue] [POST]`
    
#### Response:

+ 200 OK: User was successfully dequeued (and returned).

+ 204 NO CONTENT: The queue was empty, so nothing was dequeued.


### List all users in the queue, ordered by priority `[/queue/users] [GET]`
    
#### Response:

+ 200 OK: User list was returned.


### Remove a specific user from the queue `[/queue/users/{userId}] [DELETE]`
    
#### Response:

+ 204 NO CONTENT: The user was successfully deleted.

+ 404 NOT FOUND: The user with that ID does not exist in the queue.


### Get the information of a user in the queue `[/queue/users/{userId}] [GET]`
    
#### Response:

+ 200 OK: The user's information was successfully returned.

+ 404 NOT FOUND: The user with that ID does not exist in the queue.


### Get the position of a user in the queue `[/queue/users/{userId}/position] [GET]`
    
#### Response:

+ 200 OK: The user's position was successfully returned.

+ 404 NOT FOUND: The user with that ID does not exist in the queue.

### Get the average wait time in the queue `[/queue/waitTime?timestamp={timestamp}] [GET]`

#### Parameters:

+ timestamp: the time to calculate the wait time against. Format is time 
since the Epoch (Jan 1 1970) in milliseconds; UTC.

#### Response:

+ 200 OK: The wait time was successfully returned.

+ 204 NO CONTENT: The queue is empty.


## Development

### To build:

    ./gradlew build

or if you're on Windows:

    gradlew.bat build

### To run:

    ./gradlew bootRun

or if you're on Windows:

    gradle.bat bootRun    

or build, and then run

    java -jar fizz-buzz-as-a-service-<version>.jar
    
### To generate an IntelliJ IDEA project:

    ./gradlew idea

or if you're on Windows:

    gradlew.bat idea

### To generate an Eclipse project:

    ./gradlew eclipse

or if you're on Windows:

    gradlew.bat eclipse
