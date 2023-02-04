package simple.mentoring.dto;

import lombok.Data;
import lombok.Getter;

@Getter
public class MenteeSignupDto {

    private String email;
    private String password;
    private String name;
    private String phone;
}
