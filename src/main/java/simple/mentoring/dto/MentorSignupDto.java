package simple.mentoring.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MentorSignupDto {


    private String email;

    private String password;

    private String name;

    private String phone;
}
