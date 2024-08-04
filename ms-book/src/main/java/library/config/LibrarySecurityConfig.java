package library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class LibrarySecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() {return NoOpPasswordEncoder.getInstance();}
    @Bean
    UserDetailsService userDetailsService(DataSource dataSource) {
//        UserDetails Admin= User
//                .withUsername("admin")
//                .password("admin")
//                .roles("ADMIN")
//                .build();
//        UserDetails user= User
//                .withUsername("user")
//                .password("user")
//                .roles("REGULAR")
//                .build();
//        return new InMemoryUserDetailsManager(Admin,user);
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager();
        userDetailsManager.setDataSource(dataSource);
        userDetailsManager.setUsersByUsernameQuery("SELECT username, password, 'true' AS enabled FROM Users WHERE username = ?");
        userDetailsManager.setAuthoritiesByUsernameQuery("SELECT username, role FROM Roles WHERE username = ?");
        return userDetailsManager;
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests(request->request
                        .requestMatchers(HttpMethod.POST,"/webapi/book").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/webapi/library").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/webapi/libraries").authenticated()
                        .anyRequest()
                        .permitAll()
                )
                .httpBasic()
                .and()
                .build();
    }

}

