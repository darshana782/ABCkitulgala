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
                .antMatchers("/api/v1/**").permitAll()
                .antMatchers("/auth/registerEmployee").permitAll()
                .antMatchers("/users").permitAll()
                .antMatchers("/user/{id}").permitAll()

                .antMatchers("/foods").permitAll()
                .antMatchers("/addFood").permitAll()
                .antMatchers("//deleteFood/{foodId}").permitAll()
                .antMatchers("/updateFoodPrice").permitAll()

                .antMatchers("/addIngredient").permitAll()
                .antMatchers("/ingredients").permitAll()
                .antMatchers("/updateIngredientQty").permitAll()
                .antMatchers("/ingredientsHaveToReFill").permitAll()
                .antMatchers("/ingredientsStillNotHaveToReFill").permitAll()
                .antMatchers("/addFoodIngredients").permitAll()
                .antMatchers("/deleteIngredient/{ingredientId}").permitAll()


                .antMatchers("/report/**").permitAll()


                .antMatchers("/receptionist/**").permitAll()
//                .antMatchers("/api/v1/viewEmployeess/{type}").permitAll()
                .antMatchers("/manager/**").permitAll()
                .antMatchers("/foodIngredients").permitAll()
                .antMatchers("/manager/getRoomsByRoomTypes/{roomType}").permitAll()
                .antMatchers("/foodIngredientById/{foodId}").permitAll()
                .antMatchers("/manager/viewUpdateRoomTypeDetails/{id}").permitAll()
                .antMatchers("/manager/updateRoomType/{id}").permitAll()
                .antMatchers("/manager/deleteRoomType/{id}").permitAll()
                .antMatchers("/manager/viewUpdateRoomDetails/{id}").permitAll()
                .antMatchers("/updateRecipe/{id}").permitAll()
                .antMatchers("/cusfoodmenu").permitAll()
                .antMatchers("/listOfAddedFoods").permitAll()
                .antMatchers("/order/**").permitAll()
                .antMatchers("/order/createOrderId").permitAll()
                .antMatchers("/order/placeOrder/{orderTime}").permitAll()
                .antMatchers("/manager/**").permitAll()
                .antMatchers("/customer/review/**").permitAll()
                .antMatchers("/customer/booking/**").permitAll()
                .antMatchers("/outdoor-activities").permitAll()
                .antMatchers("/outdoor-activity-schedules").permitAll()
                .antMatchers("/outdoor-activity-schedules/{customerId}/{outdoorActivityScheduleId}").permitAll()
                .antMatchers("/outdoor-activity-schedules/available").permitAll()
                .antMatchers("/outdoor-activity-schedules/customer-schedules").permitAll()
                .antMatchers("/outdoor-activity-schedules/customer-schedules/set-completed").permitAll()
                .antMatchers("/outdoor-activity-schedules/customer-schedules/completed").permitAll()
                .antMatchers("/outdoor-activity-schedules/customer-schedules/in-complete").permitAll()
                .antMatchers("/auth/forgetpassword").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
