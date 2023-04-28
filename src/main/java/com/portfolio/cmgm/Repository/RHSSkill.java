/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.cmgm.Repository;

import com.portfolio.cmgm.Entity.HSSkill;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RHSSkill extends JpaRepository<HSSkill, Integer>{
    public Optional<HSSkill>findByNombreS(String nombreS);
    public boolean existsByNombreS(String nombreS);
}
