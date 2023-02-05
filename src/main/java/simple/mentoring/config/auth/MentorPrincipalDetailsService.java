package simple.mentoring.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import simple.mentoring.domain.Mentor;
import simple.mentoring.repository.MentorRepository;

@Service
@RequiredArgsConstructor
public class MentorPrincipalDetailsService implements UserDetailsService {

    private final MentorRepository mentorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Mentor mentor = mentorRepository.findByEmail(username);

        if (mentor == null) {
            return null;
        } else {
            return new MentorPrincipalDetails(mentor);
        }
    }
}