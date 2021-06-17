package market.init.dao;

import market.constant.CheckInfo;
import market.constant.EnterPriseInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Repository
public interface SynthesizeQueryDao {

    @Select({"<script>","SELECT * FROM `check_list` h1 LEFT JOIN `enterprise_info` h2 ON h1.`checkEnterprise`=h2.`epId` LEFT JOIN `market_user` h3 ON h1.`checkUser`=h3.`userId` <where>",
            "<if test='search != null and search != \"\"'>",
            "  checkId like concat('%',#{search},'%') " ,
            " or workDetails like concat('%',#{search},'%') " ,
            " or epName like concat('%',#{search},'%') " ,
            " or userNameCN like concat('%',#{search},'%') " ,
            "</if></where>",
            "LIMIT #{pageInfo.startpage},#{pageInfo.endpage}","</script>"})
    @Results(id = "CheckInfo" , value = {
            @Result(column="checkId", property="checkId"),
            @Result(column="checkEnterprise", property="checkEnterprise"),
            @Result(column="checkKind", property="checkKind"),
            @Result(column="checkCase", property="checkCase"),
            @Result(column="checkOpion", property="checkOpion"),
            @Result(column="checkUser", property="checkUser"),
            @Result(column="checkTime", property="checkTime"),
            @Result(column="epId", property="enterPriseInfo.epId"),
            @Result(column="epName", property="enterPriseInfo.epName"),
            @Result(column="epAddress", property="enterPriseInfo.epAddress"),
            @Result(column="epArea", property="enterPriseInfo.epArea"),
            @Result(column="epCredit", property="enterPriseInfo.epCredit"),
            @Result(column="epKind", property="enterPriseInfo.epKind"),
            @Result(column="epLegalName", property="enterPriseInfo.epLegalName"),
            @Result(column="epRegisterAssets", property="enterPriseInfo.epRegisterAssets"),
            @Result(column="epBusinessScop", property="enterPriseInfo.epBusinessScop"),
            @Result(column="userNameCN", property="userInfo.userNameCN")
    })
    public List<CheckInfo> getCheck(CheckInfo checkInfo);

    @Select({"<script>","SELECT COUNT(*) FROM `check_list` h1 LEFT JOIN `enterprise_info` h2 ON h1.`checkEnterprise`=h2.`epId` LEFT JOIN `market_user` h3 ON h1.`checkUser`=h3.`userId` <where>",
            "<if test='search != null and search != \"\"'>",
            "  checkId like concat('%',#{search},'%') " ,
            " or workDetails like concat('%',#{search},'%') " ,
            " or epName like concat('%',#{search},'%') " ,
            " or userNameCN like concat('%',#{search},'%') " ,
            "</if></where>"
            ,"</script>"})
    public int allNum(@Param("search") String search);

    @Select({"<script>","SELECT * FROM `check_list` h1 LEFT JOIN `enterprise_info` h2 ON h1.`checkEnterprise`=h2.`epId` LEFT JOIN `market_user` h3 ON h1.`checkUser`=h3.`userId` where checkId=#{checkId}", "</script>"})
    @ResultMap(value = "CheckInfo")
    public CheckInfo getCheckPost(@Param("checkId")String checkId);

    @Select({"<script>","SELECT * FROM `enterprise_info` <where>",
            "<if test='search != null and search != \"\"'>",
            "  epName like concat('%',#{search},'%') " ,
            " or epKind like concat('%',#{search},'%') " ,
            " or epLegalName like concat('%',#{search},'%') " ,
            " or epId like concat('%',#{search},'%') " ,
            "</if></where>",
            "LIMIT #{pageInfo.startpage},#{pageInfo.endpage}","</script>"})
    public List<EnterPriseInfo> getEPList(EnterPriseInfo enterPriseInfo);

    @Select({"<script>","SELECT COUNT(*) FROM `enterprise_info` <where>",
            "<if test='search != null and search != \"\"'>",
            "  epName like concat('%',#{search},'%') " ,
            " or epKind like concat('%',#{search},'%') " ,
            " or epLegalName like concat('%',#{search},'%') " ,
            " or epId like concat('%',#{search},'%') " ,
            "</if></where>"
            ,"</script>"})
    public int EPallNum(@Param("search")String search);

    @Select({"<script>","SELECT * FROM `enterprise_info` where epId=#{epId}", "</script>"})
    public EnterPriseInfo getEPPost(@Param("epId")String epId);

    @Update("UPDATE `market_supervision`.`enterprise_info` SET `epCredit` = #{epCredit},epAddress=#{epAddress},epArea=#{epArea},epKind=#{epKind},epLegalName=#{epLegalName},epRegisterAssets=#{epRegisterAssets},epBusinessScop=#{epBusinessScop} ,epName=#{epName} WHERE `epId` = #{epId}; ")
    public int updateEnterPrise(EnterPriseInfo enterPriseInfo);

    @Insert("INSERT INTO `market_supervision`.`enterprise_info` (`epId`, `epName`, `epAddress`, `epArea`, `epCredit`, `epKind`, `epLegalName`, `epRegisterAssets`, `epBusinessScop`) VALUES (null, #{epName}, #{epAddress}, #{epArea}, #{epCredit}, #{epKind}, #{epLegalName}, #{epRegisterAssets}, #{epBusinessScop});")
    public int saveEnterprise(EnterPriseInfo enterPriseInfo);
}
