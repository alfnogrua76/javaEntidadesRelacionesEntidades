package com.example.demo2Prueba.dto;

import com.example.demo2Prueba.entities.Persona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private boolean status;

    public PersonaDTO(Persona person) {
        this.id= person.getId();
        this.nombre = person.getNombre();
        this.apellido = person.getApellido();
        this.status = person.getStatus();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
