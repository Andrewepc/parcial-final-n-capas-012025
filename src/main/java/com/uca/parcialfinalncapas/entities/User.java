package com.uca.parcialfinalncapas.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correo;
    private String password;
    private String nombreRol; // USER o TECH

<<<<<<< HEAD
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

=======
>>>>>>> 402b49b (added middleware and config)
    public String getPassword() {
        return password;
    }

    @Override
<<<<<<< HEAD
=======
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
>>>>>>> 402b49b (added middleware and config)
    public String getUsername() {
        return correo;
    }
}
