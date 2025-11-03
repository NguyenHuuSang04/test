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

        UserDetails customer = User.builder()
                .username("customer")
                .password(encoder.encode("111"))
                .roles("CUSTOMER")
                .build();

        return new InMemoryUserDetailsManager(admin, customer);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth ->auth
                        //Quy tắc mở các trang static resource
                        .requestMatchers("/css/**", "/js/**","/images/**", "/static/uploads/uploads/**").permitAll()

                        //PHân quyền cho các chức năng
                        .requestMatchers("/", "/home", "/login").permitAll()
                        .requestMatchers("/products", "/products/detail/**", "/products/search").hasAnyRole("CUSTOMER", "ADMIN")
                        .requestMatchers("/products/add", "products/edit/**", "products/delete/**").hasAnyRole("ADMIN")

                        //Các request còn lại yêu cầu xác thực
                        .anyRequest().authenticated()

                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/products", true)
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