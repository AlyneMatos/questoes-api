package com.example.vestibular.model.search;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class SearchFilter {

    @JsonProperty("order")
    private String order;

    @Getter
    @JsonProperty("size")
    private Integer size;

    @Getter
    @JsonProperty("page")
    private Integer page;

    @Getter
    @JsonProperty("filter")
    private String filter;

    @JsonCreator
    public SearchFilter(@JsonProperty(value = "filter") String filter,
                        @JsonProperty(value = "order") String order,
                        @JsonProperty(value = "page") Integer page,
                        @JsonProperty(value = "size") Integer size) {
        this.filter = ("%" + filter + "%");
        this.order = order;
        this.page = page;
        this.size = size;
    }

    public String getOrder() {
        return order.startsWith("-") ? order.substring(1) : order;
    }

    public Sort.Direction getDirection() {
        return this.order.startsWith("-") ? Sort.Direction.DESC : Sort.Direction.ASC;
    }
}
