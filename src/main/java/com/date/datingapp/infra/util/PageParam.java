package com.date.datingapp.infra.util;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PageParam {

    @Min(0)
    @Schema(description = "Page number")
    Integer page;

    @Min(1)
    @Schema(description = "Page size")
    Integer size;
}
