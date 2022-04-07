package com.example.proiect_endava.service;


import com.example.proiect_endava.entity.ServicesInAutoService;
import com.example.proiect_endava.repository.ServicesInAutoServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicesInAutoServiceService {

    private final ServicesInAutoServiceRepository _repo;

    public void saveServicesInAutoService(ServicesInAutoService servicesAutoService) {
        _repo.save(servicesAutoService);
    }

    public List<ServicesInAutoService> getAutoServiceById(long id) {

        return _repo.findByAutoServiceId(id);
    }

}
