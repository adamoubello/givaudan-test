package com.adamoubello.givaudantest.controller;

import com.adamoubello.givaudantest.model.User;
import com.adamoubello.givaudantest.model.UserDto;
import com.adamoubello.givaudantest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //@Secured({"ROLE_ADMIN", "ROLE_USER"})
    //@PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List<User> listUser(){
        return userService.findAll();
    }

    //@Secured("ROLE_USER")
    //@PreAuthorize("hasRole('USER')")
    //@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User getOne(@PathVariable(value = "id") Long id) {
        //public Optional<User> getOne(@PathVariable(value = "id") Long id){

        return userService.findById(id);
    }

    //@Secured("ROLE_USER")
    //@PreAuthorize("hasRole('USER')")
    /*@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    //@ResponseBody
    //public User getOneUserByUsername(@PathVariable(value = "username") String username) {
    public ResponseEntity<User> getOneUserByUsername(@PathVariable(value = "username") String username) {
        //User user = userDao.findByUsername(username);
        //return user;

        //return userService.findOne(username);
        User userDetail = userService.findOne(username);
        return new ResponseEntity<User>(userDetail, HttpStatus.OK);
    }*/

    //@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/user/{email:.+}", method = RequestMethod.GET)
    //public ResponseEntity<User> findByEmail(@PathVariable(value = "email") String email) {
    public ResponseEntity<User> findByEmail(@PathVariable String email) {
        User userDetail = userService.findByEmail(email);
        return new ResponseEntity<User>(userDetail, HttpStatus.OK);
    }

    //@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value="/users/signup", method = RequestMethod.POST)
    public User saveUser(@RequestBody UserDto user){
        return userService.save(user);
    }

    //@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value="/users/update", method = RequestMethod.PATCH)
    public User updateUser(@RequestBody User user){ return userService.update(user); }


    //@Secured("ROLE_USER")
    //@PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteOne(@PathVariable(value = "id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            userService.delete(id);
            return new ResponseEntity<Void>(HttpStatus.GONE);
        }
    }

}
