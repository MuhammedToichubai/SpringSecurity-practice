package mukhammed.services;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import mukhammed.dto.request.UserRequest;
import mukhammed.dto.response.ResponseUserInnerPage;
import mukhammed.dto.response.SimpleResponse;
import mukhammed.enums.Role;
import mukhammed.models.User;
import mukhammed.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Mukhammed Asantegin
 */
@Service
@Transactional
public class UserServiceImpl {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void saveAdmin(){
        User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setPhoneNumber("0997997750");
        admin.setRole(Role.ADMIN);
        admin.setPassword(passwordEncoder.encode("admin"));
        userRepository.save(admin);
        System.out.println("Successfully admin saved");
    }


    public SimpleResponse save(UserRequest request) {
        User user = new User();
        user.setEmail(request.email());
        user.setPhoneNumber(request.phoneNumber());
        user.setRole(Role.USER);
        String password = request.password();
        String encodePassword = passwordEncoder.encode(password);
        user.setPassword(encodePassword);
        User saveUser = userRepository.save(user);
        return SimpleResponse.builder().status("SAVE").massage("Successfully saved user, with email: "+saveUser.getEmail()).build();

    }

    public SimpleResponse deleteById(Long userId) {
        if (!userRepository.existsById(userId)){
            throw new RuntimeException("User with id: "+userId+" does not exist");
        }
        userRepository.deleteById(userId);
        return SimpleResponse.builder().status("DELETE").massage("Successful user deleted, with id: "+userId).build();
    }

    public ResponseUserInnerPage changeRole(Long userId, Role role) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UsernameNotFoundException("User whit id: " + userId + " not found!"));
        user.setRole(role);

        return userRepository.findUserByID(userId).orElseThrow();
    }
}
