package com.argviewer.domain.model.responses.user;

import java.util.List;

import com.argviewer.domain.model.entities.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetAllResponse {
    private List<User> users;

    public GetAllResponse(List<User> users) {
        this.users = users;
    }
}
