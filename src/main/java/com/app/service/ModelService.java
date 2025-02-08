package com.app.service;

import com.app.entity.cars.Model;
import com.app.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService {
    private final ModelRepository modelRepository;

@Autowired
    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }
    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }
    public Model getModelById(Long id) {
        return modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Model not found with id " + id));
    }
    public Model createModel(Model model) {
        return modelRepository.save(model);
    }
    public Model updateModel(Long id, Model modelDetails) {
        Model existingModel = getModelById(id);
        // Example: assuming Model has a 'name' property. Adjust accordingly.
        existingModel.setName(modelDetails.getName());
        return modelRepository.save(existingModel);
    }
    public void deleteModel(Long id) {
        modelRepository.deleteById(id);
    }
    public Page<Model> getModels(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return modelRepository.findAll(pageable);
    }
}
