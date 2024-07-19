package com.example.vestibular.common;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.annotation.*;

@ResponseStatus(HttpStatus.OK)
@Target({ElementType.METHOD, ElementType.TYPE})
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Request OK"),
        @ApiResponse(responseCode = "401", description = "Not Authenticated"),
        @ApiResponse(responseCode = "403", description = "Permission Denied"),
        @ApiResponse(responseCode = "404", description = "Resource Not Found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json"))
})
public @interface CommonResponse {
}
