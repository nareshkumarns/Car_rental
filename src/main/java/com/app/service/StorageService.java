package com.app.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.app.entity.cars.Car;
import com.app.entity.cars.CarImage;
import com.app.payload.ImageDto;
import com.app.repository.CarImageRepository;
import com.app.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarImageRepository carImageRepository;

    public ImageDto.FileResponseDTO uploadFile(Long carId, MultipartFile file) throws Exception {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new Exception("Car not found with ID: " + carId));

        File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        // Upload to S3
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();

        // Generate URL
        String fileUrl = s3Client.getUrl(bucketName, fileName).toString();

        // Save metadata to DB
        CarImage carImage = new CarImage();
        carImage.setCar(car);
        carImage.setFileName(fileName);
        carImage.setFileUrl(fileUrl);
        carImageRepository.save(carImage);

        return new ImageDto.FileResponseDTO(carId, fileName, fileUrl);
    }

    public List<ImageDto.FileResponseDTO> getFileUrlsByCarId(Long carId) {
        List<CarImage> images = carImageRepository.findByCarId(carId);
        return images.stream()
                .map(image -> new ImageDto.FileResponseDTO(carId, image.getFileName(), image.getFileUrl()))
                .collect(Collectors.toList());
    }

    public String deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        carImageRepository.findAll().stream()
                .filter(image -> image.getFileName().equals(fileName))
                .forEach(carImageRepository::delete);
        return fileName + " removed.";
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertedFile;
    }
}
