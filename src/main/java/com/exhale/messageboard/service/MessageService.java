package com.exhale.messageboard.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.exhale.messageboard.entity.Message;
import com.exhale.messageboard.vo.MessageVO;

import java.util.List;


public interface MessageService {
    void add(Message message, Long userId);
    List<Message> list();
    void delete(Long messageId, Long userId);
    public IPage<MessageVO> listPage(int pageNum, int pageSize);
}
