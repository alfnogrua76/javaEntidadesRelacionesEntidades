package com.example.demo2Prueba.controllers;

import com.example.demo2Prueba.Services.IPersonaServices;
import com.example.demo2Prueba.dto.PersonaDTO;
import com.example.demo2Prueba.entities.Persona;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
@CrossOrigin(origins = "*")
@Slf4j
public class Personacontroller {

    private final IPersonaServices personaService;

    public Personacontroller(IPersonaServices personaService, IPersonaServices personaService1) {
        this.personaService = personaService1;

    }

    @GetMapping()
    public String holaMundo(){
        return "HOA MUNDO";
    }

    @GetMapping("/all")
    public List<PersonaDTO> listarPersonas() {
        return personaService.buscarAllPersonas();
    }

    @GetMapping("/{id}")
    public PersonaDTO biscarPersonasId(@PathVariable Long id) {
        return personaService.buscarPersonasId(id);
    }

    @GetMapping("/activos")//aqui vamos a buscar el listado de personas pero activas osea status ==true
    public List<PersonaDTO> listarPerswonasActivas() {
        return personaService.buscarPersonasActivas();
    }

    @PostMapping("/crear-persona")
    public ResponseEntity<PersonaDTO> publicarPersona(@RequestBody Persona persona){
//        if (persona == null){
//            return null;
//        }
        return ResponseEntity.ok(personaService.crearPersona(persona));
    }


    @PutMapping("/actualizar-persona2")
    public Persona actPersona(@RequestBody Persona persona){
        if (persona == null){
            return null;
        }
        return personaService.actualizarPersona(persona);
    }

    @PutMapping("/actualizar-persona/{id}")
    public PersonaDTO actualizarPersona(@RequestBody Persona persona, @PathVariable Long id){
        if (persona == null){
            return null;
        }
        return personaService.updatePersona(persona, id);
    }

    @DeleteMapping("/eliminar-persona/")
    public boolean eliminarPersona(@RequestParam Long id) {//request Param otra manera de recibir datos en springBoot
        return personaService.eliminarPersona(id);
    }

//Documentacion oficial SpringData JPA Docs en google
    //Metodo para el query derivado
    @GetMapping("/prueba")
    public PersonaDTO ejemploQueryDerivada(@RequestParam String nombre, @RequestParam String apellido){
        return personaService.ejemploQueryDerivada(nombre,apellido);
    }
/*
    //Metodo paginado
    @GetMapping("/mostrar-pagina/")
    public ResponseEntity<Page<PersonDTO>> mostrarPaginasJpaql(@PageableDefault(size = 2, sort = {"nombre"})
                                                          Pageable pageable){
        Page<PersonDTO> pagina = personaService.buscarPaginado(pageable);
        return ResponseEntity.ok(pagina);
    }

    //igual un paginado pero dinamico
    @GetMapping("/mostrar-pagina-dinamica/")
    public ResponseEntity<Page<PersonDTO>> mostrarPaginasDinamicamente(@RequestParam(defaultValue = "2") int tamanio,
                                                                       @RequestParam(defaultValue = "0") int numberPag
                                                                      ){
        Sort sort = Sort.by(Sort.Direction.DESC, "fechaNacimiento");

        Pageable pageable = PageRequest.of(numberPag, tamanio, sort);
        Page<PersonDTO> pagina = personaService.buscarPaginado(pageable);
        return ResponseEntity.ok(pagina);
    }

    //Metodo paginado
    @GetMapping("/query-derivada/")
    public ResponseEntity<List<PersonDTO>> queryDerivadas (@RequestParam String nombre){
        if(nombre.isBlank()){
            ResponseEntity.badRequest();
        }

        return ResponseEntity.ok(personaService.usarQueryDerivada(nombre));
    }
*/
}
