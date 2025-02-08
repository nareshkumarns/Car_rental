package com.app.service;

import com.app.entity.cars.Transmission;
import com.app.repository.TransmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransmissionService {

    private final  TransmissionRepository transmissionRepository;

    @Autowired
    public TransmissionService(TransmissionRepository transmissionRepository) {
        this.transmissionRepository = transmissionRepository;
    }
    public List<Transmission> getAllTransmissions() {
        return transmissionRepository.findAll();
    }
    public Transmission getTransmissionById(Long id) {
        return transmissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transmission not found with id " + id));
    }
    public Transmission createTransmission(Transmission transmission) {
        return transmissionRepository.save(transmission);
    }

    public Transmission updateTransmission(Long id, Transmission transmissionDetails) {
        Transmission existingTransmission = getTransmissionById(id);
        // Assuming Transmission has a 'type' property
        existingTransmission.setType(transmissionDetails.getType());
        return transmissionRepository.save(existingTransmission);
    }

    public void deleteTransmission(Long id) {
        transmissionRepository.deleteById(id);
    }
    public Page<Transmission> getTransmissions(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return transmissionRepository.findAll(pageable);
    }
}

