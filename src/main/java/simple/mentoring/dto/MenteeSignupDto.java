package simple.mentoring.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenteeSignupDto {

    private String email;
    private String password;
    private String name;
    private String phone;
}
