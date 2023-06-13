package users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import users.model.Role;
import users.model.User;
import users.repo.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

   private UserRepo userRepo;
   @Autowired
   private PasswordEncoder passwordEncoder;
   @Autowired
   JwtService jwtService;
   public String generateToken(String username){
      return jwtService.generateToken(username);
   }
   public void validateToken(String token){
      jwtService.validateToken(token);
   }

   public UserService(UserRepo userRepo) {
      this.userRepo = userRepo;
   }
   public String addRh(User user){
      user.setRole(Role.RH);
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      Optional<User> test=userRepo.findById(user.getMatricule());
      if(test.isPresent()){
         return "Rh by Matricule :  "+user.getMatricule()+" exist !!!";
      }
      userRepo.save(user);
      return "saved !!";


   }
   public  void delateRhBy_Matre(Integer matrecule){
      userRepo.deleteById(matrecule);

   }
   public void updateRh(User user){
      user.setRole(Role.RH);
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userRepo.save(user);
   }

   public List<User> getAllRh(){
      return userRepo.findByUserRole(Role.RH);
   }
   public  void  deleteRh(Integer matr ){
      userRepo.deleteById(matr);
   }


   public String addEmployer(User user) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      user.setRole(Role.EMPLOYER);
      Optional<User> test=userRepo.findById(user.getMatricule());
      if(test.isPresent()){
         return "Employer by Matricule :  "+user.getMatricule()+" exist !!!";
      }
      userRepo.save(user);
      return "Employer saved seccefully !!" ;
   }

   public List<User> getAllEmployer() {
      return userRepo.findByUserRole(Role.EMPLOYER);
   }

   public void deleteEmployer(Integer matr) {
      userRepo.deleteById(matr);
   }

   public void updateEmployer(User user) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      user.setRole(Role.EMPLOYER);
      userRepo.save(user);
   }
}
