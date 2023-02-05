package simple.mentoring.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import simple.mentoring.config.auth.MentorPrincipalDetailsService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MentorPrincipalDetailsService mentorPrincipalDetailsService;


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain FilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/", "/mentors/**", "/mentees/**", "/mentorings/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/mentors/login")
                .loginProcessingUrl("/mentorLogin")
                .defaultSuccessUrl("/mentorings/add");

        return http.build();
    }
}
