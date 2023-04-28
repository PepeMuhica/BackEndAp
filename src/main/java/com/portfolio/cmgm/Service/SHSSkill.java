/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.cmgm.Service;

import com.portfolio.cmgm.Entity.HSSkill;
import com.portfolio.cmgm.Repository.RHSSkill;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SHSSkill {
    @Autowired
    RHSSkill rHSSkill;
    
    public List<HSSkill> list(){
        return rHSSkill.findAll();
    }
    
    public Optional<HSSkill> getOne(int id){
        return rHSSkill.findById(id);
    }
    
    public Optional<HSSkill> getByNombreS(String nombreS){
        return rHSSkill.findByNombreS(nombreS);
    }
    
    public void save(HSSkill skill){
        rHSSkill.save(skill);
    }
    
    public void delete(int id){
        rHSSkill.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rHSSkill.existsById(id);
    }
    
    public boolean existsByNombreS(String nombreS){
        return rHSSkill.existsByNombreS(nombreS);
    }
}
