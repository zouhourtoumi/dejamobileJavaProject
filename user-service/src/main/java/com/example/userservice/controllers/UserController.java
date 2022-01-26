package com.example.userservice.controllers;

import com.example.userservice.entities.Role;
import com.example.userservice.entities.User;
import com.example.userservice.services.UserService;
import lombok.Data;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.regex.Pattern;

@Controller
public class UserController {
    private UserService userService;

    public  UserController (UserService userService){
        this.userService=userService;

    }
    public UserController(){
    }

    @GetMapping("/profile")
  //  @PostAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public User profile(Principal principal){
        return userService.loadUserByUsername(principal.getName());
    }

    @RequestMapping(value = "/addUser")
    public String addUser (User user){
//        if(!user.getPassword().equals(transporteurDto.getPasswordConfirmation()))
//            return "Error the two passwords do not match";
//        else
        if(userService.getUser(user.getUsername()) != null)
            return "Error this username already exists";
        //Checking for non alphanumerical characters in the username.
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        if(pattern.matcher(user.getUsername()).find())
            return "No special characters are allowed in the username";
        userService.addUser(user);
        System.out.println("added successfully");
        return user.getUsername()+": has been added successfully ";
    }

    @PostMapping("addRole")
    public Role addRole( Role role){
        return userService.addRole(role);
    }

    @PostMapping("registration")
    public void addRoleUser ( RoleUserForm roleUserForm){
         userService.addRoleToUser(roleUserForm.getUsername(),roleUserForm.getRoleName());
    }
//
//    @RequestMapping(value="/logout", method=RequestMethod.GET)
//    public Boolean logout(HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//            return true;
//        }
//        else
//            return false;
//    }

}
@Data
class RoleUserForm{
    private String username;
    private String roleName;
}