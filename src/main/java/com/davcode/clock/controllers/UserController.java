package com.davcode.clock.controllers;

import com.davcode.clock.mappers.dto.RequestDTO;
import com.davcode.clock.mappers.dto.UserResponse;
import com.davcode.clock.models.User;
import com.davcode.clock.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @GetMapping(path = "/{userId}")
    public RequestDTO getUser(@PathVariable Long userId){
        return userService.getUser(userId);
    }

    @PutMapping(path = "/invalidate/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void invalidateUser(@PathVariable Long userId){
        userService.invalidateUser(userId);
    }

    @GetMapping(path = "/get-username/{userName}")
    public UserResponse getUserByUserName(@PathVariable String userName){
        return userService.getByUserName(userName);
    }

    @PutMapping(path = "/confirm-email/{userId}")
    public void updateEmailConfirmation(@PathVariable Long userId){
        userService.updateEmailConfirmation(userId);
    }

    @PutMapping(path = "/active/{userId}")
    public void updateActiveFlag(@PathVariable Long userId, @RequestBody boolean activeFlag){
        userService.updateActiveFlag(userId,activeFlag);
    }

    @PutMapping(path = "/updateStatus/{userId}")
    public void updateUserStatus(@PathVariable Long userId, @RequestBody char status){
        userService.updateUserStatus(userId,status);
    }

    @PutMapping(path = "/updatePassword/{userId}")
    public void updatePassword(@PathVariable Long userId, @RequestBody String password){
        userService.updatePassword(userId,password);
    }

}
