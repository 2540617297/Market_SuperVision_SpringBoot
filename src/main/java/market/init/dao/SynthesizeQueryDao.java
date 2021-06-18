package market.init.dao;

import market.constant.CheckInfo;
import market.constant.EnterPriseInfo;
import market.constant.FileChoose;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Repository
public interface SynthesizeQueryDao {

    @Select({"<script>","SELECT * FROM `check_list` h1 LEFT JOIN `enterprise_info` h2 ON h1.`checkEnterprise`=h2.`epId` LEFT JOIN `market_user` h3 ON h1.`checkUser`=h3.`userId` <where>",
            "<if test='search != null and search != \"\"'>",
            " or checkId like concat('%',#{search},'%') " ,
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
    public List<CheckInfo> searchCheck(CheckInfo checkInfo);

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
            " or epName like concat('%',#{search},'%') " ,
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

    @Select({"<script>","SELECT * FROM `check_list` h1 LEFT JOIN `enterprise_info` h2 ON h1.`checkEnterprise`=h2.`epId` LEFT JOIN `market_user` h3 ON h1.`checkUser`=h3.`userId` <where>",
            "<if test='search != null and search != \"\"'>",
            "  checkId like concat('%',#{search},'%') " ,
            " or epName like concat('%',#{search},'%') " ,
            " or userNameCN like concat('%',#{search},'%') " ,
            "</if>",
            "<if test='id != null and id != \"\"'>",
            "and h1.checkId=#{id}",
            "</if>" ,
            "</where>",
            "limit 10",
            "</script>"})
    @Results(id = "getcheck" , value = {
            @Result(column="checkId", property="id"),
            @Result(column="checkTime", property="dateTime"),
            @Result(column="epName", property="obj"),
            @Result(column="userNameCN", property="userNameCN"),
            @Result(column="fileUrl", property="fileUrl"),
    })
    public List<FileChoose> getCheck(@Param("search") String search,@Param("id") String id);

    @Update("update check_list set fileUrl=#{fileUrl} where checkId=#{id}")
    public int updateCheck(@Param("id")String id,@Param("fileUrl")String fileUrl);

    @Select({"<script>","SELECT * FROM spotnotice h1 LEFT JOIN market_user h2 ON h1.`addWho`=h2.`userId` <where>",
            "<if test='search != null and search != \"\"'>",
            "or h1.noticeId like concat('%',#{search},'%')",
            "or h1.noticeEP like concat('%',#{search},'%')",
            "or h2.userNameCN like concat('%',#{search},'%')",
            "</if>",
            "<if test='id != null and id != \"\"'>",
            "and h1.noticeId=#{id}",
            "</if>" ,
            "</where>",
            "limit 10",
            "</script>"})
    @Results(id = "getnotice" , value = {
            @Result(column="noticeId", property="id"),
            @Result(column="noticeTime", property="dateTime"),
            @Result(column="noticeEP", property="obj"),
            @Result(column="userNameCN", property="userNameCN"),
            @Result(column="fileUrl", property="fileUrl"),
    })
    public List<FileChoose> getNotice(@Param("search") String search,@Param("id") String id);

    @Update("update spotnotice set fileUrl=#{fileUrl} where noticeId=#{id}")
    public int updateNotice(@Param("id")String id,@Param("fileUrl")String fileUrl);

    @Select({"<script>","SELECT * FROM initiate_application h1 LEFT JOIN market_user h2 ON h1.`RecordId`=h2.`userId` <where>",
            "<if test='search != null and search != \"\"'>",
            "or h1.IAId like concat('%',#{search},'%')",
            "or h1.IAName like concat('%',#{search},'%')",
            "or h2.userNameCN like concat('%',#{search},'%')",
            "</if>",
            "<if test='id != null and id != \"\"'>",
            "and h1.IAID=#{id}",
            "</if>" ,
            "</where>",
            "limit 10",
            "</script>"})
    @Results(id = "getia" , value = {
            @Result(column="IAID", property="id"),
            @Result(column="IATime", property="dateTime"),
            @Result(column="IAName", property="obj"),
            @Result(column="userNameCN", property="userNameCN"),
            @Result(column="fileUrl", property="fileUrl"),
    })
    public List<FileChoose> getIA(@Param("search") String search,@Param("id") String id);

    @Update("update initiate_application set fileUrl=#{fileUrl} where IAID=#{id}")
    public int updateIA(@Param("id")String id,@Param("fileUrl")String fileUrl);

    @Select({"<script>","SELECT * FROM record_question h1 LEFT JOIN market_user h2 ON h1.`recordName`=h2.`userId` <where>",
            "<if test='search != null and search != \"\"'>",
            "or h1.recordId like concat('%',#{search},'%')",
            "or h1.BRDName like concat('%',#{search},'%')",
            "or h2.userNameCN like concat('%',#{search},'%')",
            "</if>" ,
            "<if test='id != null and id != \"\"'>",
            "and h1.recordId=#{id}",
            "</if>" ,
            "</where>",
            "limit 10",
            "</script>"})
    @Results(id = "getrecord" , value = {
            @Result(column="recordId", property="id"),
            @Result(column="recordTime", property="dateTime"),
            @Result(column="BRDName", property="obj"),
            @Result(column="userNameCN", property="userNameCN"),
            @Result(column="fileUrl", property="fileUrl"),
    })
    public List<FileChoose> getRecord(@Param("search") String search,@Param("id") String id);

    @Update("update record_question set fileUrl=#{fileUrl} where recordId=#{id}")
    public int updateRecord(@Param("id")String id,@Param("fileUrl")String fileUrl);
}
