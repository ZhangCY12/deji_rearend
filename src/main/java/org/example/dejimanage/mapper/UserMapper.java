package org.example.dejimanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.dejimanage.entity.DTO.UserDTO;
import org.example.dejimanage.entity.User;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /***
     * 查询所有部门及其人数
     */
    @Select("SELECT groupNames,COUNT(groupNames) as count " +
            "FROM t_liteweb_user " +
            "GROUP BY groupNames")
    List<UserDTO> SelectNumberTotal();
}
