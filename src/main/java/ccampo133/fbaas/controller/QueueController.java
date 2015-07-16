package ccampo133.fbaas.controller;

import ccampo133.fbaas.model.User;
import ccampo133.fbaas.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Chris Campo
 */
@RestController
@RequestMapping("/queue")
public class QueueController {

    private final QueueService queueService;

    @Autowired
    public QueueController(final QueueService queueService) {
        this.queueService = queueService;
    }

    // Enqueue (add) a user to a queue
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<User> addUser(final long id, final long timestamp) {
        final User user = new User(id, timestamp);
        queueService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    // Dequeue (pop) a user from the front of the queue
    @RequestMapping(value = "/dequeue", method = RequestMethod.POST)
    public ResponseEntity<User> dequeue() {
        final User user = queueService.dequeue();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // List all users in the queue
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<User[]> getUsers() {
        final User[] users = queueService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Removes a specific user from the queue
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable final long userId) {
        queueService.removeUserById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get the position of a single user
    @RequestMapping(value = "/users/{userId}/position", method = RequestMethod.GET)
    public ResponseEntity<Integer> getUserPosition(@PathVariable final long userId) {
        final int position = queueService.getUserPositionById(userId);
        return new ResponseEntity<>(position, HttpStatus.OK);
    }

    // Get the average wait time
    @RequestMapping(value = "/waitTime", method = RequestMethod.GET)
    public ResponseEntity<Double> getAverageWaitTime(@RequestParam final long timestamp) {
        final double waitTime = queueService.getAverageWaitTime(timestamp);
        return new ResponseEntity<>(waitTime, HttpStatus.OK);
    }

    // Get information of a single user
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable final long userId) {
        final User user = queueService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
