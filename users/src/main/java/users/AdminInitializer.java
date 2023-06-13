package users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import users.model.Role;
import users.model.User;
import users.repo.UserRepo;
@Component
public class AdminInitializer implements CommandLineRunner {
    private final UserRepo userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AdminInitializer(UserRepo userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        User user=new User();
        user.setPassword(passwordEncoder.encode("123456"));
        user.setNom("admin");
        user.setMatricule(10);
        user.setRole(Role.ADMIN);
        user.setEmail("admin@gmail.com");
        user.setAdresse("");
        user.setPrenom("admin");

        userRepository.save(user);

    }
}
