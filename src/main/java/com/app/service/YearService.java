package com.app.service;

import com.app.entity.cars.Year;
import com.app.repository.YearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YearService {
    private final YearRepository yearRepository;
    @Autowired
    public YearService(YearRepository yearRepository) {
        this.yearRepository = yearRepository;
    }
    public List<Year> getAllYears() {
        return yearRepository.findAll();
    }
    public Year getYearById(Long id) {
        return yearRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Year not found with id " + id));
    }

    public Year createYear(Year year) {
        return yearRepository.save(year);
    }
    public Year updateYear(Long id, Year yearDetails) {
        Year existingYear = getYearById(id);
        // Assuming Year has a property like 'yearValue'
        return yearRepository.save(existingYear);
    }
    public void deleteYear(Long id) {
        yearRepository.deleteById(id);
    }

    public Page<Year> getYears(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return yearRepository.findAll(pageable);
    }
}

