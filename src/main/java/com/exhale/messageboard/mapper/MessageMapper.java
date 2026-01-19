package com.exhale.messageboard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.exhale.messageboard.entity.Message;
import com.exhale.messageboard.vo.MessageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface MessageMapper extends BaseMapper<Message> {

    //负责的sql查询需要使用xml文件，这里使用注解负责简单的sql

    /**
     * 关联 User 表查询留言列表（VO版）
     * @param page 分页参数对象，MyBatis-Plus 自动提取 current 和 size
     * @return 返回封装了 MessageVO 的分页结果
     */
    @Select("SELECT m.id, u.username, m.content, m.create_time " +
    "FROM message m " +
    "JOIN user u ON m.user_id " +
    "ORDER BY m.create_time DESC")//MyBatis-Plus 的分页插件会自动在 SQL 末尾追加 LIMIT ?, ?，所以你不需要在注解里写 LIMIT
    IPage<MessageVO> selectPageWithUser(IPage<MessageVO> page);

}
