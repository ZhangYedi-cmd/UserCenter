package com.zhang.base.request;

import lombok.Data;

@Data
public class PageRequest {
    private int pageNo = 1;
    private int pageSize = 10;
}
