package org.example.dejimanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.dejimanage.entity.DTO.UserDTO;
import org.example.dejimanage.entity.User;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    //查询机加部人数
    @Select("SELECT COUNT(*) " +
            "FROM t_liteweb_user " +
            "WHERE groupNames = '机加部'")
    int SelectNumbersMachining();

    //查询质检部人数
    @Select("SELECT COUNT(*) " +
            "FROM t_liteweb_user " +
            "WHERE groupNames = '质检部'")
    int SelectNumbersQualityInspection();

    //查询仓储部人数
    @Select("SELECT COUNT(*) " +
            "FROM t_liteweb_user " +
            "WHERE groupNames = '仓储部'")
    int SelectNumbersStore();

    //查询管理员人数
    @Select("SELECT COUNT(*) " +
            "FROM t_liteweb_user " +
            "WHERE groupNames = '管理员'")
    int SelectNumbersAdministrators();

    //查询IT部门人数
    @Select("SELECT COUNT(*) " +
            "FROM t_liteweb_user " +
            "WHERE groupNames = 'IT'")
    int SelectNumbersIT();
    /***
     * 查询所有部门及其人数
     */
    @Select("SELECT groupNames,COUNT(groupNames) as count " +
            "FROM t_liteweb_user " +
            "GROUP BY groupNames")
    List<UserDTO> SelectNumberTotal();
}
