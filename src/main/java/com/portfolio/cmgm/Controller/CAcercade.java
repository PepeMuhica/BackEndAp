/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.cmgm.Controller;

import com.portfolio.cmgm.Dto.dtoAcercade;
import com.portfolio.cmgm.Entity.Acercade;
import com.portfolio.cmgm.Security.Controller.Mensaje;
import com.portfolio.cmgm.Service.SAcercade;
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
@RequestMapping("/acercademi")
@CrossOrigin(origins = "https://fronendap3.web.app")
public class CAcercade {

    @Autowired
    SAcercade sAcercade;

    @GetMapping("/lista")
    public ResponseEntity<List<Acercade>> list() {
        List<Acercade> list = sAcercade.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Acercade> getById(@PathVariable("id") int id) {
        if (!sAcercade.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }

        Acercade acercade = sAcercade.getOne(id).get();
        return new ResponseEntity(acercade, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sAcercade.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        sAcercade.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoAcercade dtoacercad) {
        if (StringUtils.isBlank(dtoacercad.getTituloA())) {
            return new ResponseEntity(new Mensaje("El espacio no debe ir en blanco"), HttpStatus.BAD_REQUEST);
        }
        if (sAcercade.existsByTituloA(dtoacercad.getTituloA())) {
            return new ResponseEntity(new Mensaje("Este titulo existe"), HttpStatus.BAD_REQUEST);
        }

        Acercade acercade = new Acercade(dtoacercad.getTituloA(), dtoacercad.getDescripcionA());
        sAcercade.save(acercade);
        return new ResponseEntity(new Mensaje("Información agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoAcercade dtoacercad) {
        if (!sAcercade.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if (sAcercade.existsByTituloA(dtoacercad.getTituloA()) && sAcercade.getByTituloA(dtoacercad.getTituloA()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa información ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoacercad.getDescripcionA())) {
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }

        Acercade acercade = sAcercade.getOne(id).get();
        acercade.setTituloA(dtoacercad.getTituloA());
        acercade.setDescripcionA(dtoacercad.getDescripcionA());

        sAcercade.save(acercade);

        return new ResponseEntity(new Mensaje("Información actualizada"), HttpStatus.OK);
    }
}
