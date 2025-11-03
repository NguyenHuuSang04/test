package iuh.fit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        PasswordEncoder encoder = passwordEncoder();

        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder.encode("123"))
                .roles("ADMIN")
                .build();

        UserDetails employee = User.builder()
                .username("employee")
                .password(encoder.encode("111"))
                .roles("EMPLOYEE")
                .build();

        return new InMemoryUserDetailsManager(admin, employee);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth ->auth
                        //Quy tắc mở các trang static resource
                        .requestMatchers("/css/**", "/js/**","/images/**", "/static/uploads/uploads/**").permitAll()

                        //Phân quyền cho các chức năng
                        .requestMatchers("/", "/home", "/login").permitAll()
                        .requestMatchers("/busroutes", "/busroutes/detail/**", "/busroutes/search").hasAnyRole("EMPLOYEE", "ADMIN")
                        .requestMatchers("/busroutes/add", "/busroutes/edit/**", "/busroutes/delete/**").hasRole("ADMIN")

                        //Các request còn lại yêu cầu xác thực
                        .anyRequest().authenticated()

                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/busroutes", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );
        return  http.build();
    }
}