package com.app.payload;

public class ImageDto {

    public static class FileResponseDTO {
        private Long carId;
        private String fileName;
        private String fileUrlOrMessage;

        // Constructor
        public FileResponseDTO(Long carId, String fileName, String fileUrlOrMessage) {
            this.carId = carId;
            this.fileName = fileName;
            this.fileUrlOrMessage = fileUrlOrMessage;
        }

        // Getters and Setters
        public Long getCarId() {
            return carId;
        }

        public void setCarId(Long carId) {
            this.carId = carId;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFileUrlOrMessage() {
            return fileUrlOrMessage;
        }

        public void setFileUrlOrMessage(String fileUrlOrMessage) {
            this.fileUrlOrMessage = fileUrlOrMessage;
        }
    }
}
