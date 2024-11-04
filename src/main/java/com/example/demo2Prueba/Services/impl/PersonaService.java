package com.example.demo2Prueba.Services.impl;

import com.example.demo2Prueba.Services.IPersonaServices;
import com.example.demo2Prueba.config.ExceptionHandlerCustom;
import com.example.demo2Prueba.converter.PersonaConverter;
import com.example.demo2Prueba.dto.PersonaDTO;
import com.example.demo2Prueba.entities.Persona;
import com.example.demo2Prueba.repositories.PersonaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service("personaService")
public class PersonaService implements IPersonaServices {

    public final PersonaRepository personaRepository;

    public  final PersonaConverter personaConverter;

    public PersonaService(PersonaRepository personaRepository,
                          PersonaConverter personaConverter) {
        this.personaRepository = personaRepository;
        this.personaConverter = personaConverter;
    }


    public List<PersonaDTO> buscarAllPersonas(){
        List<Persona> personas = personaRepository.findAll()
                .stream()
                .toList();
        return personaConverter.listConverter(personas);
    }

    public List<PersonaDTO> buscarPersonasActivas(){
        List<Persona> personas = personaRepository.findAll()
                .stream()
                .filter(x -> x.getStatus() == true)
                .toList();
        return personaConverter.listConverter(personas);
    }

    public PersonaDTO buscarPersonasId(Long id){
        Persona personaDTO = personaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Persoa no existe"));
        return setData(personaDTO);
    }

    public PersonaDTO crearPersona(Persona persona)
    {
        //PersonaDTO pers =  setData (persona);
        personaRepository.save(persona);
        return  setData(persona);

    }/*
    //---------------------------manera 1 de hacer el update-----------------------
    //@Transactional(readOnly = true)
//    public Persona updatePersona(Persona persona) {
//        Persona personaEncontrada = personaRepository.findById(persona.getId())
//                .orElseThrow(() -> new RuntimeException("Persona no existe"));
//
//        personaEncontrada.setApellido(persona.getApellido());
//        personaEncontrada.setNombre(persona.getNombre());
//        personaEncontrada.setDireccion(persona.getDireccion());
//        personaEncontrada.setNombre(persona.getNombre());
//        personaEncontrada.setFechaNacimiento(persona.getFechaNacimiento());
//
//
//        personaRepository.save(personaEncontrada);
//        return personaEncontrada;
//    }
*/
    //Otra manera de hacer el update ojo BeanUtils-------------------------------------------//
    public Persona actualizarPersona(Persona persona) {
        Optional<Persona> personaEncontrada = personaRepository.findById(persona.getId());
       // if(personaEncontrada.isEmpty()) {
             new RuntimeException("Persona no existe");
        //}

        //Persona personaExistente = personaEncontrada.get();
        BeanUtils.copyProperties(persona,personaEncontrada);//metodo para setear un objeto con otro

        return personaRepository.save(persona);
    }
//Manera 3 de hacer update con una transformacion interna

    public PersonaDTO updatePersona(Persona persona, Long id) {
        Persona personaEncontrada = personaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Persoa no existe"));//esto es pqara ahorrarme la validacion
                    //y que directamente me traiga un objeto en vez del Optonal

        personaEncontrada.setNombre(persona.getNombre());
        personaEncontrada.setApellido(persona.getApellido());
        personaEncontrada.setStatus(persona.getStatus());
        personaRepository.save(personaEncontrada);

        return setData(personaEncontrada);

    }

    @Override
    public boolean eliminarPersona(Long id) {
        return false;
    }

    //iNVESTIGAR ModelMapper libreria  para estos mapeos
    private PersonaDTO setData (Persona persona){
        PersonaDTO personaNueva = new PersonaDTO(persona);
        personaNueva.setNombre(persona.getNombre());
        personaNueva.setApellido(persona.getApellido());
        personaNueva.setId(persona.getId());
        return personaNueva;

    }

//Primer metodo el mas sencillo para el delete
    //    public boolean deletePersona(Long id) {
//
//        if(repo.existsById(id)){
//            repo.deleteById(id);
//            return true;
//        }
//        return false;
//
//    }
//segundo metodo para el delete
    public boolean deletePersona(Long id) {
        Persona personaEncontrada = personaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no existe"));

        personaEncontrada.setStatus(false);
        personaRepository.save(personaEncontrada);

        return true;
    }

    ///metodo para el query derivado
    public PersonaDTO ejemploQueryDerivada(String nombre, String apellido) {
        Persona persona = personaRepository.findByNombreAndApellido(nombre, apellido);
//        if(persona == null){
//            return new PersonaDTO();
//        }
            return setData(persona);
    }
/*
    public Page<PersonDTO> buscarPaginado(Pageable pageable) {
        Page<Persona> page = personaRepository.findAll(pageable);
        //mapeando la entidad con un lambda
        return page.map(x -> new PersonDTO(
                x.getId(),
                x.getNombre(),
                x.getApellido()

        ));
    }

    public List<PersonDTO> usarQueryDerivada(String nombre) {

        return personaRepository.encontrarCoincidenciasNombre(nombre)
                .stream()
                .map(x -> new PersonDTO(x.getId(), x.getNombre(),x.getApellido())).toList();
    }

//    public LocalDate fechaParaSerMayorDeEdad(){
//        //LocalDate
//    }
*/

}
