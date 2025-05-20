package com.example.jpa_specification.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestDto {
    private List<SearchRequestDto> searchRequestDtoList;

    private GlobalOperator globalOperator;

    private PageRequestDto pageDto;

    public enum GlobalOperator{
        AND, OR;
    }
}
