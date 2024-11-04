package com.example.demo2Prueba.Services;

import com.example.demo2Prueba.dto.PersonaDTO;
import com.example.demo2Prueba.entities.Persona;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IPersonaServices {

    List<PersonaDTO> buscarAllPersonas();

    PersonaDTO buscarPersonasId(Long id);

    List<PersonaDTO> buscarPersonasActivas();

    PersonaDTO crearPersona(Persona persona);

    Persona actualizarPersona(Persona person);

    PersonaDTO updatePersona(Persona person, Long id);

    boolean eliminarPersona(Long id);

    /////////JPA QUERY Derivadas

    PersonaDTO ejemploQueryDerivada(String nombre,String apellido);


/*
    PersonaDTO setData (Persona persona);

    boolean deletePersona(Long id);

    ///metodo para el query derivado
    Persona ejemploQueryDerivada(String nombre, String apellido);

    Page<PersonDTO> buscarPaginado(Pageable pageable);

    List<PersonDTO> usarQueryDerivada(String nombre);

*/
}
