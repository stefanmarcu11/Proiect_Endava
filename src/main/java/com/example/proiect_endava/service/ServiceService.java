package com.example.proiect_endava.service;


import com.example.proiect_endava.entity.ServiceEntity;
import com.example.proiect_endava.exceptions.NotFoundException;
import com.example.proiect_endava.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceService{

    private final ServiceRepository serviceRepository;
//    private final AutoGarageService autoGarageService;

    public List<ServiceEntity> getServiceList(){

        return (List<ServiceEntity>) serviceRepository.findAll();
    }

    public Optional<ServiceEntity> getServiceById(Long id) throws NotFoundException {

        return Optional.ofNullable(serviceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Service.class, id)));
    }

    public ServiceEntity saveService(ServiceEntity service){
        /*ServiceEntity serviceEntity=new ServiceEntity();
        serviceEntity.setService(service.getService());
        serviceEntity.setPrice(service.getPrice());
        serviceEntity.getAutoServiceList().
                addAll(service.getAutoServiceList()
                        .stream()
                        .map(item->{
                            AutoService as=autoGarageService.getAutoServiceById(item.getId());
                            as.getServiceList().add(serviceEntity);
                            return as;
                        }).collect(Collectors.toList()));
        //serviceRepository.save(service);
        return serviceRepository.save(serviceEntity);*/
        return serviceRepository.save(service);
    }

    public void deleteService(Long id){

        serviceRepository.deleteById(id);
    }

}
