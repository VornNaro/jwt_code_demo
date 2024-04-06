package com.kshrd.btb_jwt_class_demo.repository;

import com.kshrd.btb_jwt_class_demo.model.entity.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserRepository {

    @Select("""
             SELECT r.role_name from user_role ur 
             INNER JOIN roles r ON r.id = ur.role_id
             WHERE ur.user_id = #{userId}
         """)
    List<String> getRolesByUserId(@Param("userId") Integer userId);

    @Select("SELECT * FROM user_tb WHERE username = #{name}")
    @Results(
            id = "mappUser",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "name", column = "username"),
                    @Result(property = "password", column = "password"),
                    @Result(property = "roles", column = "id",
                        many = @Many(select = "getRolesByUserId")
                    )
            }
    )
    UserInfo findUserByUserName(@Param("name") String userName);


}
