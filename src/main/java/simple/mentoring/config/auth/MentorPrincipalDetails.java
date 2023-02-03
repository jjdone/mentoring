package simple.mentoring.config.auth;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import simple.mentoring.domain.Mentor;

import java.util.Collection;

@Data
public class MentorPrincipalDetails implements UserDetails {

    private Mentor mentor;

    public MentorPrincipalDetails(Mentor mentor) {
        this.mentor = mentor;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return mentor.getPassword();
    }

    @Override
    public String getUsername() {
        return mentor.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
