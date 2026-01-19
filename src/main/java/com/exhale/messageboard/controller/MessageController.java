package com.exhale.messageboard.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.exhale.messageboard.common.Result;
import com.exhale.messageboard.entity.Message;
import com.exhale.messageboard.entity.User;
import com.exhale.messageboard.service.MessageService;
import com.exhale.messageboard.util.UserContext;
import com.exhale.messageboard.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/add")
    public Result<String> add(
            @Valid @RequestBody Message message,
            HttpSession session){
        User user = UserContext.getLoginUser(session);
        messageService.add(message, user.getId());
        return Result.success("发布成功");
    }

    @GetMapping("/list")
    public Result<IPage<MessageVO>> list(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize){
       //调用转化后的Service 方法，每页pageSize条，显示第pageNum页，获取完整分页对象
        IPage<MessageVO> messages = messageService.listPage(pageNum, pageSize);
        return Result.success(messages);
    }

    @PostMapping("/delete/{id}")
    public Result<String> delete(
            @PathVariable Long id,
            HttpSession session){
        User user = UserContext.getLoginUser(session);
        messageService.delete(id, user.getId());
        return Result.success("删除成功");
    }

}
