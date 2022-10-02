package com.client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindByPageResponse<T> implements Serializable {
    private List<T> pageResponse;
    private Integer pageSize;
    private Long totalElement;
    private Integer currentPage;
    private Integer totalPage;
}
