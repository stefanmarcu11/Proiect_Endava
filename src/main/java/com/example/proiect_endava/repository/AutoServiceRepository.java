package com.example.proiect_endava.repository;


import com.example.proiect_endava.entity.AutoService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoServiceRepository extends CrudRepository<AutoService,Long> {

    AutoService getAutoServiceById(Long id);
}
