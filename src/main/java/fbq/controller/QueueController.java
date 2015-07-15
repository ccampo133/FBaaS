package fbq.controller;

import fbq.model.User;
import fbq.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // Dequeue (pop) a user from the front of the queue
    @RequestMapping(value = "/dequeue", method = RequestMethod.GET)
    public ResponseEntity<User> dequeue() {
        final User user = queueService.dequeue();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Enqueue (add) a user to a queue
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<User> addUser(@RequestParam final long id, @RequestParam final long timestamp) {
        final User user = new User(id, timestamp);
        queueService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // List all users in the queue
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers() {
        final List<User> users = queueService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Get information of a single user
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable final long userId) {
        final User user = queueService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Removes a specific user from the queue
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable final long userId) {
        queueService.removeUserById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
