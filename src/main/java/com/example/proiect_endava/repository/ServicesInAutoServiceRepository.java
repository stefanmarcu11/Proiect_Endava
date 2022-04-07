package com.example.proiect_endava.repository;


import com.example.proiect_endava.entity.ServicesInAutoService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicesInAutoServiceRepository extends CrudRepository<ServicesInAutoService, Long> {

    List<ServicesInAutoService> findByAutoServiceId(long id);
}
