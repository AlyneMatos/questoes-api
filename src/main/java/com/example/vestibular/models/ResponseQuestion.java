package com.example.vestibular.models;

import com.example.vestibular.models.Alternative;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResponseQuestion {

    private String message;
    private List<Alternative> alternatives;
}
