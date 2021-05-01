package market.init.dao;

import com.sun.istack.internal.Nullable;
import market.constant.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
