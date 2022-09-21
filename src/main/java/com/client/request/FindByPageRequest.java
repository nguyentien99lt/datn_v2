package com.client.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindByPageRequest {
    private Integer pageSize;
    private Integer pageNumber;
}
