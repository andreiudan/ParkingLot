package com.parking.parkinglot.common;

import com.parking.parkinglot.entities.Car;

public class CarPhotoDto {

    private Long id;

    private String fileName;

    private String fileType;

    private byte[] fileContent;

    public CarPhotoDto(Long id, String fileName, String fileType, byte[] fileContent) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileContent = fileContent;
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public byte[] getFileContent() {
        return fileContent;
    }
}
