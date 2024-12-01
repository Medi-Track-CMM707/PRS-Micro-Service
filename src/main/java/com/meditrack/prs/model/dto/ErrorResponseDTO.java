package com.meditrack.prs.model.dto;

public record ErrorResponseDTO(String errorCode, String errorMessage, Object additionalInformation) {

}

