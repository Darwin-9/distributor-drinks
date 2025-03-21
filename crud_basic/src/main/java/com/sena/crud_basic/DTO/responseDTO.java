package com.sena.crud_basic.DTO;

public class responseDTO {

    private String status;
    private String message;

    public responseDTO(String message, String status) {
        this.message = message;
        this.status = status;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    

}
