package com.app.service;

import com.app.entity.cars.Model;
import com.app.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
