package com.zhang.base.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DelParams implements Serializable {
    private static final long serialVersionUID = -1242493306307174690L;
    private long id;
}
