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
        @ApiResponse(responseCode = "200", description = "Request Ok"),
        @ApiResponse(responseCode = "401", description = "Not authenticated agent (missing or invalid credentials)"),
        @ApiResponse(responseCode = "403", description = "Ops! You do not have permission to access this feature! :("),
        @ApiResponse(responseCode = "404", description = "Resource not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
})
public @interface CommonResponse {
}
