package com.exhale.messageboard.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exhale.messageboard.entity.Message;
import com.exhale.messageboard.mapper.MessageMapper;
import com.exhale.messageboard.service.MessageService;
import com.exhale.messageboard.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;


    @Override
    public void add(Message message, Long userId) {
        //1. 设置关联用户ID
        message.setUserId(userId);

        //2. XSS防御：对内容进行转义，内容入库前先过滤
        String escapeContent = HtmlUtils.htmlEscape(message.getContent());
        message.setContent(escapeContent);

        //4. 存入数据库
        messageMapper.insert(message);
    }

    @Override
    public List<Message> list() {
        return messageMapper.selectList(null);
    }

    @Override
    public void delete(Long messageId, Long userId) {
        Message message = messageMapper.selectById(messageId);
        if(message == null){
            throw new RuntimeException("留言不存在");
        }
        if(!message.getUserId().equals(userId)){
            throw new RuntimeException("无权删除他人留言");
        }
        messageMapper.deleteById(messageId);

    }

    @Override
    public IPage<MessageVO> listPage(int pageNum, int pageSize) {
        //创建分页对象，参数为当前页，每页条数
        Page<MessageVO> page = new Page<>(pageNum, pageSize);

        //第二个参数为查询条件(Wrapper)，传null表示查询所有数据
        //messageMapper.selectPage(page, null);
        //返回分页后的记录列表
        return messageMapper.selectPageWithUser(page);
    }
}
