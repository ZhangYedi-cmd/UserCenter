package com.zhang.base.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PageResponse  {
    private long pageNo = 1;

    private long pageTotal = 10;

}
