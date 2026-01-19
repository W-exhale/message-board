package com.exhale.messageboard.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class Message {
    private Long id;
    private Long userId;

    @NotBlank(message = "内容不能为空")
    @Size(max = 500, message = "内容最多500字")
    private String content;
    private LocalDateTime createTime;

}
