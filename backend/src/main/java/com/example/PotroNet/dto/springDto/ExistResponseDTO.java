package com.example.PotroNet.dto.springDto;

public class ExistResponseDTO {
    private Boolean exist;

    public ExistResponseDTO() {
    }

    public ExistResponseDTO(Boolean exist) {
        this.exist = exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    public Boolean getExist() {
        return exist;
    }
}
