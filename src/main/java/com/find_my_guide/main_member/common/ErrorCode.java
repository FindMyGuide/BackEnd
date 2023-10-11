package com.find_my_guide.main_member.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    NOT_FOUND(404, "NOT FOUND"),
    DUPLICATION(400, "DUPLICATED"),
    INTER_SERVER_ERROR(500, "INTER SERVER ERROR");

    private int status;
    private String message;
}
