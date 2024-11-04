package com.example.demo2Prueba.converter;

import com.example.demo2Prueba.dto.PersonaDTO;
import com.example.demo2Prueba.entities.Persona;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("personDtoConverter")
public class PersonaConverter {
    public List<PersonaDTO> listConverter(List<Persona> list){
        List<PersonaDTO> listModels = new ArrayList<>();
        list.forEach(person -> listModels.add(new PersonaDTO(person)));
        return listModels;
    }
}
