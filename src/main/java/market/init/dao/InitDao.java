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

    @Select({"<script>","select * from MARKET_USER <where> 1=1 " ,
            "<if test='userName != null and userName != \"\"'>",
            " and userName=#{userName} " ,"</if>",
            "<if test='roleId != null and roleId != \"\"'>",
            " and roleId=#{roleId} " ,"</if>",
            "<if test='userPassword != null and userPassword != \"\"'>",
            "and userPassword=#{userPassword}","</if></where>","</script>"})
    public UserInfo adminLogin(UserInfo userInfo);

    @Insert("INSERT INTO `market_supervision`.`market_user` (`userId`, `userName`, `userPassword`, `roleId`, `phoneNo`, `email`,'userNameCN') VALUES (#{userId}, #{userName}, #{userPassword}, #{roleId}, #{phoneNo}, #{email},#{userNameCN});")
    public int register(UserInfo userInfo);

    //根据用户Id查找用户信息
    @Select("select * from MARKET_USER where userId=#{userId}")
    public UserInfo getUserInfo(@Param("userId")String userId);

    @Select("select count(*) from MARKET_USER where userName=#{userName} AND phoneNo=#{phoneNo}")
    public int retrievePwd(@Param("userName")String userName,@Param("phoneNo")String phoneNo);

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
            " or h1.userNameCN like concat('%',#{search},'%') " ,
            " or h1.userName like concat('%',#{search},'%') ",
            "LIMIT #{pageInfo.startpage},#{pageInfo.endpage}", "</if></where>", "</script>"})
    public List<UserInfo> searchUser(UserInfo userInfo);

    @Select({"<script>","select count(*) from MARKET_USER h1 <where>" ,
            "<if test='search != null and search != \"\"'>",
            " or h1.userId like concat('%',#{search},'%') " ,
            " or h1.userNameCN like concat('%',#{search},'%') " ,
            " or h1.userName like concat('%',#{search},'%') " , "</if></where>", "</script>"})
    public int searchUserNum(UserInfo userInfo);

    @Update("update market_user set userPassword=#{userPassword} where userName=#{userName}")
    public int resetPwd(@Param("userName") String userName,@Param("userPassword") String userPassword);

}
