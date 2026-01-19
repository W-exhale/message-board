package com.exhale.messageboard.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageVO {
    private Long id;
    private String username;
    private String content;
    private LocalDateTime createTime;

}
