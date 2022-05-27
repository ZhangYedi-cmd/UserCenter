package com.zhang.model.response;

import com.zhang.base.response.PageResponse;
import com.zhang.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class SearchUserResponse extends PageResponse {

    private List<User> userList;

    public SearchUserResponse(long pageNo, long pageTotal, List<User> userList) {
        this.userList = userList;
        this.setPageNo(pageNo);
        this.setPageTotal(pageTotal);
    }

    public SearchUserResponse(List<User> userList) {
        this.userList = userList;
    }
}
