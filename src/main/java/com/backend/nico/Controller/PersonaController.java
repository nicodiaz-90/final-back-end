package com.backend.nico.Controller;

import com.backend.nico.Entity.Persona;
import com.backend.nico.Interface.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://portfolio-nicolas-diaz.web.app")
public class PersonaController {
    @Autowired IPersonaService ipersonaService;
    
    //GetMapping trae objetos de la base de datos al front
    @GetMapping("personas/traer")
    public List<Persona> getPersona(){
        return ipersonaService.getPersona();
    }
    
    //PostMapping lleva objetos desde el front a la base de datos
     @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/personas/crear")
    public String createPersona(@RequestBody Persona persona){
        ipersonaService.savePersona(persona);
        return "La persona fue agregada exitosamente";
    }
    
     @PreAuthorize("hasRole('ADMIN')")
     @DeleteMapping("/personas/borrar/{id}")
        public String deletePersona(@PathVariable Long id){
            ipersonaService.deletePersona(id);
            return "La persona fue eliminada correctamente";
        }
     
     //Url:Puerto/personas/editar/id nombre&apellido&img
     @PreAuthorize("hasRole('ADMIN')")   
     @PutMapping("/personas/editar/{id}")
      public Persona editPersona(@PathVariable Long id,
                                   @RequestParam("nombre") String nuevoNombre,
                                   @RequestParam("apellido") String nuevoApellido,
                                   @RequestParam("img") String nuevoImg){
            Persona persona = ipersonaService.findPersona(id);
            
            persona.setNombre(nuevoNombre);
            persona.setApellido(nuevoApellido);        
            persona.setImg(nuevoImg);
            
            ipersonaService.savePersona(persona);
            return persona;
        }
      
      
      @GetMapping("/personas/traer/perfil")
        public Persona findPersona(){
            return ipersonaService.findPersona((long)1 );
        }
        
    
}
