package com.example.proiect_endava.service;



import com.example.proiect_endava.entity.AutoService;
import com.example.proiect_endava.exceptions.NotFoundException;
import com.example.proiect_endava.repository.AutoServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutoGarageService {


    private final AutoServiceRepository autoServiceRepository;

    public List<AutoService> getAutoServiceList(){
        return (List<AutoService>) autoServiceRepository.findAll();
    }
    public Optional<AutoService> findAutoServiceById(Long id) throws NotFoundException {

        return Optional.ofNullable(autoServiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(AutoService.class, id)));
    }

    public AutoService getAutoServiceById(Long id){
        return autoServiceRepository.getAutoServiceById(id);
    }

    public void saveAutoService(AutoService autoService){

        autoServiceRepository.save(autoService);
    }

    public void deleteAutoService(Long id){

        autoServiceRepository.deleteById(id);
    }


}
