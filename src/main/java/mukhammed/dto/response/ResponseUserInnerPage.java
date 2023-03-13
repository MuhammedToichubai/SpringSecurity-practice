package mukhammed.dto.response;

import lombok.Builder;
import mukhammed.enums.Role;

/**
 * @author Mukhammed Asantegin
 */
@Builder
public record ResponseUserInnerPage(String email, String phoneNumber, Role role) {}
