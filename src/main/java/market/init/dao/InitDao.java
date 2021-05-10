package market.init.dao;

import com.sun.istack.internal.Nullable;
import market.constant.CheckInfo;
import market.constant.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Mapper
@Repository
public interface InitDao {
    @Select({"<script>","select * from MARKET_USER where 1=1 " ,
            "<if test='username != null and username != \"\"'>",
            " and userName=#{username} " ,"</if>",
            "<if test='password != null and password != \"\"'>",
            "and userPassword=#{password}","</if>","</script>"})
    public UserInfo login(@Param("username") String username, @Nullable @Param("password") String password);

    @Insert("INSERT INTO `market_supervision`.`market_user` (`userId`, `userName`, `userPassword`, `roleId`, `phoneNo`, `email`,'userNameCN') VALUES (#{userId}, #{userName}, #{userPassword}, #{roleId}, #{phoneNo}, #{email},#{userNameCN});")
    public int register(UserInfo userInfo);

    //根据用户Id查找用户信息
    @Select("select * from MARKET_USER where userId=#{userId}")
    public UserInfo getUserInfo(@Param("userId")String userId);

    @Update("UPDATE `market_supervision`.`market_user` SET `phoneNo` = #{phoneNo}, `userName`=#{userName}, `userPassword`=#{userPassword}, `roleId`=#{roleId}, `email`=#{email},`userNameCN`=#{userNameCN} WHERE `userId` = #{userId};")
    public int updateUserInfo(UserInfo userInfo);

    @Select({"<script>","select * from MARKET_USER where 1=1 " ,
            "<if test='username != null and username != \"\"'>",
            " and userName=#{username} " ,"</if>",
            "</script>"})
    public List<UserInfo> findUser(@Param("username") String username);

    @Select({"<script>","select * from MARKET_USER h1 <where>" ,
            "<if test='search != null and search != \"\"'>",
            " or h1.userId like concat('%',#{search},'%') " ,
            " or h1.userName like concat('%',#{search},'%') " , "</if></where>", "</script>"})
    public List<UserInfo> searchUser(@Param("search")String search);


}
