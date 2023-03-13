package mukhammed.dto.request;

import lombok.Builder;

/**
 * @author Mukhammed Asantegin
 */

@Builder
public record UserRequest(
        String email,
        String password,
        String phoneNumber
        ) {
}
