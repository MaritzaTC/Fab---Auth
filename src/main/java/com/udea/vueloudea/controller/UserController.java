package com.udea.vueloudea.controller;

import com.udea.vueloudea.model.Role;
import com.udea.vueloudea.service.RoleService;
import com.udea.vueloudea.service.UserService;
import com.udea.vueloudea.model.UserF;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController{

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {this.userService = userService;
    this.roleService = roleService;}


    @QueryMapping
    public List<UserF> searchUsers(){
        return userService.findUsers();
    }

    @QueryMapping
    private UserF searchUser(@Argument Long user_id ){
        return userService.findUserById(user_id);
    }

    @MutationMapping
    public UserF createUser(
                            @Argument String name,
                            @Argument String email,
                            @Argument String password,
                            @Argument String address,
                            @Argument String document_number,
                            @Argument Long role_id) {

        Role role =roleService.findRoleById(role_id).orElseThrow(()-> new IllegalArgumentException("Role not found"));
        return userService.createUser(name, email, password, address, document_number, role);
    }
}
