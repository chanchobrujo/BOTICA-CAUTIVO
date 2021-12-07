/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Rol;
import java.util.List;
import java.util.Optional; 
import repository.rolRepository;

/**
 *
 * @author kpalmall
 */
public class rolService {
    private rolRepository repository;
    
    public rolService(){
        repository = new rolRepository();
    }
    
    public String save(String name){
        return repository.insert(new Rol(name));
    }
    
    public List<Rol> findAll(){
        return repository.findAll();
    }
    
    public Optional<Rol> findByName(String name){
        return repository.findAll().stream()
                .filter(rol->rol.getName().equals(name))
                .findFirst();
    }
    
    public Optional<Rol> findById(int id){
        return repository.findAll().stream()
                .filter(rol->rol.getId()==id )
                .findFirst();
    }
}
