/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.portfolio.cmgm.Controller;

import com.portfolio.cmgm.Dto.dtoHSSkill;
import com.portfolio.cmgm.Entity.HSSkill;
import com.portfolio.cmgm.Security.Controller.Mensaje;
import com.portfolio.cmgm.Service.SHSSkill;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skills")
@CrossOrigin(origins = "https://fronendap3.web.app")
public class CHSSkill {
    @Autowired
    SHSSkill sHSSkill;
    
    @GetMapping("/lista")
    public ResponseEntity<List<HSSkill>> list(){
        List<HSSkill> list = sHSSkill.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<HSSkill> getById(@PathVariable("id") int id){
        if(!sHSSkill.existsById(id)){
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        
        HSSkill hsskill = sHSSkill.getOne(id).get();
        return new ResponseEntity(hsskill, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sHSSkill.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        sHSSkill.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHSSkill dtoskill){      
        if(StringUtils.isBlank(dtoskill.getNombreS())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(sHSSkill.existsByNombreS(dtoskill.getNombreS())){
            return new ResponseEntity(new Mensaje("Esa skill existe"), HttpStatus.BAD_REQUEST);
        }
        
        HSSkill hsskill = new HSSkill(dtoskill.getNombreS(), dtoskill.getPorcentajeS());
        sHSSkill.save(hsskill);
        return new ResponseEntity(new Mensaje("Skill agregada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHSSkill dtoskill){
        if(!sHSSkill.existsById(id)){
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        if(sHSSkill.existsByNombreS(dtoskill.getNombreS()) && sHSSkill.getByNombreS(dtoskill.getNombreS()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoskill.getNombreS())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        HSSkill hsskill = sHSSkill.getOne(id).get();
        hsskill.setNombreS(dtoskill.getNombreS());
        hsskill.setPorcentajeS((dtoskill.getPorcentajeS()));
        
        sHSSkill.save(hsskill);
        
        return new ResponseEntity(new Mensaje("Skill actualizada"), HttpStatus.OK);
    }
}