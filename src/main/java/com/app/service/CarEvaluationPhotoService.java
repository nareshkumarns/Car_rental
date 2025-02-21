package com.app.service;

import com.app.entity.evaluation.CarEvaluationPhoto;
import com.app.repository.CarEvaluationPhotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
public class CarEvaluationPhotoService {

    private final CarEvaluationPhotoRepository photoRepository;
    private final S3Service s3Service;  // Assuming S3 integration

    public CarEvaluationPhotoService(CarEvaluationPhotoRepository photoRepository, S3Service s3Service) {
        this.photoRepository = photoRepository;
        this.s3Service = s3Service;
    }

//    public CarEvaluationPhoto uploadPhoto(Long carId, MultipartFile file) {
//        System.out.println("Uploading file: " + file.getOriginalFilename());
//
//        String fileUrl = s3Service.uploadFile(file);
//        System.out.println("File uploaded successfully: " + fileUrl);
//
//        CarEvaluationPhoto photo = new CarEvaluationPhoto();
//        photo.setCarId(carId);
//        //photo.setPhotoUrl(fileUrl);
//
//        return photoRepository.save(photo);
//    }

    public CarEvaluationPhoto uploadPhoto(Long carId, MultipartFile file) {
        System.out.println("Uploading file: " + file.getOriginalFilename());

        String fileUrl = s3Service.uploadFile(file);
        System.out.println("File uploaded successfully: " + fileUrl);

        CarEvaluationPhoto photo = new CarEvaluationPhoto();
        photo.setCarId(carId);
        photo.setPhotoUrl(fileUrl); // ✅ Make sure this line is not commented out!

        return photoRepository.save(photo);
    }








    public List<CarEvaluationPhoto> getPhotosByCarId(Long carId) {
        return photoRepository.findByCarId(carId);
    }

    public void deletePhoto(Long photoId) {
        photoRepository.deleteById(photoId);
    }
}
