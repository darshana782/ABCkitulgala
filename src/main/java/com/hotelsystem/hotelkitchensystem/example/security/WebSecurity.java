package com.hotelsystem.hotelkitchensystem.example.security;

import com.hotelsystem.hotelkitchensystem.example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

//security class
@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    //service that uses authorization to create token
//    @Autowired
//    private AuthService userDetailsService;

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService);
    }

    //add password encoder to the web security
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
//        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //disable authenticate for below APIs
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().and().authorizeRequests()
                //Add paths here to remove authentication
                .antMatchers("/auth/gettoken").permitAll()
                .antMatchers("/auth/signup").permitAll()
                .antMatchers("/customers").permitAll()
                .antMatchers("/auth/customer/login").permitAll()
                .antMatchers("/addCustomersss").permitAll()
                .antMatchers("/api/v1/addEmployee").permitAll()
                .antMatchers("/api/v1/viewEmployees").permitAll()
                .antMatchers("/api/v1/viewEmployee/{id}").permitAll()
                .antMatchers("/api/v1/updateEmployee/{id}").permitAll()
                .antMatchers("/api/v1/deleteEmployee/{id}").permitAll()
                .antMatchers("/auth/registerEmployee").permitAll()
                .antMatchers("/users").permitAll()
                .antMatchers("/foods").permitAll()
                .antMatchers("/addFood").permitAll()
                .antMatchers("/addIngredient").permitAll()
                .antMatchers("/ingredients").permitAll()
                .antMatchers("/addFoodIngredients").permitAll()

                .antMatchers("/receptionist/getCustomer").permitAll()
                .antMatchers("/manager/addRoomType").permitAll()
                .antMatchers("/manager/addRooms").permitAll()

                .anyRequest().authenticated()
                .and().exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
