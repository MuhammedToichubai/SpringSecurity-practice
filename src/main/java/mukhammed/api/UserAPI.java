package mukhammed.api;

import mukhammed.dto.request.UserRequest;
import mukhammed.dto.response.SimpleResponse;
import mukhammed.dto.response.ResponseUserInnerPage;
import mukhammed.enums.Role;
import mukhammed.services.UserServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mukhammed Asantegin
 */
@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserAPI {
    private final UserServiceImpl userService;

    public UserAPI(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PreAuthorize("permitAll()")
    @PostMapping
    public SimpleResponse save(@RequestBody UserRequest request) {
        return userService.save(request);
    }

    @PutMapping("/edit/role/{userId}")
    public ResponseUserInnerPage changeRole(@PathVariable Long userId,
                                            @RequestParam Role role){
        return userService.changeRole(userId, role);
    }

    @DeleteMapping("/{userId}")
    public SimpleResponse deleteById(@PathVariable Long userId) {
        return userService.deleteById(userId);
    }
}
