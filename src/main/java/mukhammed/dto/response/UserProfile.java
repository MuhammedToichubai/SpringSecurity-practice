package mukhammed.dto.response;

import lombok.Data;

import java.util.List;

/**
 * @author Mukhammed Asantegin
 */
@Data
public class UserProfile{
    private String email;
    private String phoneNumber;
    private List<ResponseCarsPage> carsPageList;

    public UserProfile(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

}
