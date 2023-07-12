package com.co;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration // Esta anotación indica que esta clase es una clase de configuración de Spring
@EnableWebSecurity // Esta anotación habilita la seguridad web en la aplicación
public class SecurityConfig extends WebSecurityConfigurerAdapter { // Esta clase extiende la clase WebSecurityConfigurerAdapter para configurar la seguridad web
    
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{ // Este método configura la autenticación de usuarios
        auth.inMemoryAuthentication() // Se configura la autenticación en memoria
            .withUser("admin") // Se agrega un usuario "admin"
                .password("{noop}1234") // Se establece la contraseña del usuario "admin"
                    .roles("ADMIN","USER") // Se establecen los roles del usuario "admin"
                .and() // Se agrega otro usuario
                    .withUser("user") // Se agrega un usuario "user"
                        .password("{noop}123") // Se establece la contraseña del usuario "user"
                            .roles("USER") // Se establece el rol del usuario "user"
                           ;
    }            
    @Override
    protected void configure(HttpSecurity http) throws Exception{ // Este método configura la autorización de usuarios

        http.authorizeRequests() // Se configuran las solicitudes autorizadas
                .antMatchers("/loginadmin")// Se especifica que solo se permite el acceso a la ruta "/loginadmin"
                    .hasRole("ADMIN")// a usuarios con el rol "ADMIN"
                .and()// Además, se permite:
                    .formLogin()// el inicio de sesión mediante un formulario
                        .loginPage("/login") // Se especifica la ruta del formulario de inicio de sesión
                        .defaultSuccessUrl("/loginadmin")// Se especifica la ruta a la que se redirige al usuario después de iniciar sesión correctamente
                        .permitAll()// Se permite el acceso a todas las páginas a usuarios autenticados
                ;                  
    }
}
