package com.example.projekt_koncowy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

   private Integer status;

   private String error;

   private String message;

   private List<ViolationError> validationErrors;

   public ApiError(Integer status, String error, String message) {
      this.status = status;
      this.error = error;
      this.message = message;
   }
}
