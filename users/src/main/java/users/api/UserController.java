package users.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import users.dto.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import users.model.User;
import users.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;




    @PostMapping("/rh/addEmployer")
    public String addEmployer(@RequestBody User user){

        System.out.println(user);
       return userService.addEmployer(user);

    }
    @GetMapping("/rh/getAlllEmployer")
    public List<User> getAllEmployer(){
        return userService.getAllEmployer();
    }
    @DeleteMapping("/rh/deleteEmployer/{matr}")
    public void delateEmployer(@PathVariable Integer matr){
        userService.deleteEmployer(matr);
    }
    @PutMapping("/rh/updateEployer/{matr}")
    public void updateEmployer(@PathVariable Integer matr,@RequestBody User user){
        user.setMatricule(matr);
        userService.updateEmployer(user);


    }


    @PostMapping("/admin/addRh")
    public String addRh(@RequestBody User user){

        System.out.println(user);

       return userService.addRh(user);

    }
    @GetMapping("/admin/getAllRh")
    public List<User> getAllRh(){
        return userService.getAllRh();
    }
    @DeleteMapping("/admin/deleteRh/{matr}")
    public void delateRh(@PathVariable Integer matr){
        userService.deleteRh(matr);
    }
    @PutMapping("/admin/updateRh/{matr}")
    public void updateRh(@PathVariable Integer matr,@RequestBody User user){
        user.setMatricule(matr);
        userService.updateRh(user);


    }

    // _____________________________token api ___________________________________________________________
    @PostMapping("/login")
    public String getToken(@RequestBody AuthRequest user){
        Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        if (auth.isAuthenticated()){
            return userService.generateToken(user.getEmail());
        }else {
            throw new RuntimeException("invalid acces");
        }


    }
    @GetMapping("/validatetoken")
    public String getToken(@RequestParam("token") String token){
         userService.validateToken(token);
         return "token is valid";
    }

    @GetMapping("/register")
    public String register(){
        return "yes is valid http secuirity";
    }

}
