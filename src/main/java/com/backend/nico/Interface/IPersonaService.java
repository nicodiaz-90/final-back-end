package com.backend.nico.Interface;

import com.backend.nico.Entity.Persona;
import java.util.List;



public interface IPersonaService {
    //Traer una lista de peronas
    public List<Persona> getPersona();
    
    //Guardar un objeto persona
    public void savePersona(Persona persona);
    
    //Eliminar un objeto por ID
    public void deletePersona(Long id);
    
    //Buscar perona por ID
    public Persona findPersona(Long id);
}
