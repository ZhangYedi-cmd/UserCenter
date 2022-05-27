package com.zhang.model.request;

import com.zhang.base.request.PageRequest;
import com.zhang.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class SearchUserRequest extends PageRequest {
    private String userName;
    private String nickName;
}
