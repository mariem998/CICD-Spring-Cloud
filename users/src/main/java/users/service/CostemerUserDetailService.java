package users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import users.config.CustomerUserDetails;
import users.model.User;
import users.repo.UserRepo;

import java.util.Optional;

public class CostemerUserDetailService implements UserDetailsService {
    @Autowired
   private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> credential= userRepo.findByEmail(username);
        if (! credential.isPresent()){
            throw new UsernameNotFoundException("user not found");
        }
        User user=credential.get();
        CustomerUserDetails customerUserDetails=new CustomerUserDetails(user.getEmail(),user.getPassword());
        return customerUserDetails;
    }
}
