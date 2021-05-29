package market.init.dao;

import market.constant.CheckInfo;
import market.constant.EnterPriseInfo;
import market.constant.RecordQuestionInfo;
import market.constant.RouteInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MobileLawDao {
    @Select("select * from enterprise_info where epName=#{epName}")
    public EnterPriseInfo getEnterpriseInfo(@Param("epName") String epName);

    @Insert("INSERT INTO `market_supervision`.`check_list` (checkId,`checkEnterprise`, `checkKind`, `checkCase`, `checkOpion`, `checkUser`,`checkTime`) VALUES ( #{checkId},#{checkEnterprise}, #{checkKind}, #{checkCase}, #{checkOpion}, #{checkUser},#{checkTime});")
    public int saveCheckList(CheckInfo checkInfo);

    @Insert("INSERT INTO `market_supervision`.`route_info` (`routeId`, `mLatitude`, `mLongitude`, `mHorizontalAccuracyMeters`, `address`, `addTime`,`addUser`) VALUES (#{routeId}, #{mLatitude}, #{mLongitude}, #{mHorizontalAccuracyMeters}, #{address}, #{addTime},#{addUser}); ")
    public int saveRouteInfo(RouteInfo routeInfo);

    @Select("SELECT * FROM `route_info` h1 WHERE h1.`routeId`=#{routeId}")
    public List<RouteInfo> getLatLng(@Param("routeId") String routeId);

    @Select("SELECT * FROM `route_info` WHERE routeId=#{routeId} AND mLatitude=#{mLatitude} AND mLongitude=#{mLongitude};")
    public RouteInfo removeRouteSame(RouteInfo routeInfo);

    @Select({"<script>","SELECT h1.`address`,h1.`addTime`,h1.`addUser`,h1.`mHorizontalAccuracyMeters`,h1.`mLatitude`,h1.`mLongitude`,h1.`routeId`,h2.`userNameCN` AS userNameCN FROM `route_info` h1 LEFT JOIN `market_user` h2 ON h1.`addUser`=h2.`userId` <where>" ,
            "<if test='search != null and search != \"\"'>",
            " or h1.routeId like concat('%',#{search},'%') " ,
            " or h2.userNameCN like concat('%',#{search},'%') " , "</if></where>",
            "GROUP BY h1.`routeId` ORDER BY h1.`addTime` DESC LIMIT #{pageInfo.startpage},#{pageInfo.endpage}","</script>"})
    public List<RouteInfo> getRouteInfo(RouteInfo routeInfo);

    @Select({"<script>","SELECT COUNT(*) FROM (SELECT * FROM `route_info` h1 LEFT JOIN `market_user` h2 ON h1.`addUser`=h2.`userId` <where>" ,
            "<if test='search != null and search != \"\"'>",
            " or h1.routeId like concat('%',#{search},'%') " ,
            " or h2.userNameCN like concat('%',#{search},'%') " , "</if></where>",
            " GROUP BY h1.`routeId`) AS h3","</script>"})
    public int getRouteNum(RouteInfo routeInfo);

    @Insert({"<script>","INSERT INTO `market_supervision`.`record_question` (`recordId`, `recordTime`, `recordAddress`, `BRDName`, `recordName`, `RQuestion1`, `RAnswer1`, `RQuestion2`, `RAnswer2`, `BRDSex`, `BRDIDCard`, `BRDPhone`, `other`) " ,
            "VALUES (#{recordId},#{recordTime}, #{recordAddress}, #{BRDName}, #{recordName}, #{RQuestion1}, #{RAnswer1}, #{RQuestion2}, #{RAnswer2}, #{BRDSex}, #{BRDIDCard}, #{BRDPhone}, #{other});","</script>"})
    public int saveRecordQUestion(RecordQuestionInfo questionInfo);

    @Update({"UPDATE `market_supervision`.`record_question` SET  `recordTime`=#{recordTime}, `recordAddress`=#{recordAddress}, `BRDName`=#{BRDName},  `RQuestion1`=#{RQuestion1}, `RAnswer1`=#{RAnswer1}, `RQuestion2`=#{RQuestion2}, `RAnswer2`=#{RAnswer2}, `BRDSex`=#{BRDSex}, `BRDIDCard`=#{BRDIDCard}, `BRDPhone`=#{BRDPhone}, `other`=#{other} WHERE `recordId`=#{recordId}; "})
    public int updateRecordQuestion(RecordQuestionInfo recordQuestionInfo);

    @Select({"<script>","SELECT * FROM `record_question` h1 LEFT JOIN market_user h2 ON h1.`recordName`=h2.`userId` <where>" ,
            "<if test='search != null and search != \"\"'>",
            " or h1.recordId like concat('%',#{search},'%') " ,
            " or h2.userNameCN like concat('%',#{search},'%') " ,
            " or h1.BRDName like concat('%',#{search},'%') " , "</if></where>",
            "ORDER BY h1.`recordTime` DESC LIMIT #{pageInfo.startpage},#{pageInfo.endpage}","</script>"})
    @Results(id = "RecordQuestion" , value = {
            @Result(column="recordId", property="recordId"),
            @Result(column="recordTime", property="recordTime"),
            @Result(column="recordAddress", property="recordAddress"),
            @Result(column="BRDName", property="BRDName"),
            @Result(column="recordName", property="recordName"),
            @Result(column="RQuestion1", property="RQuestion1"),
            @Result(column="RAnswer1", property="RAnswer1"),
            @Result(column="RQuestion2", property="RQuestion2"),
            @Result(column="RAnswer2", property="RAnswer2"),
            @Result(column="BRDSex", property="BRDSex"),
            @Result(column="BRDIDCard", property="BRDIDCard"),
            @Result(column="BRDPhone", property="BRDPhone"),
            @Result(column="other", property="other"),
            @Result(column="userNameCN", property="userInfo.userNameCN")
    })
    public List<RecordQuestionInfo> getRecordQuestions(RecordQuestionInfo recordQuestionInfo);

    @Select({"<script>","SELECT COUNT(*) FROM `record_question` h1 LEFT JOIN market_user h2 ON h1.`recordName`=h2.`userId` <where>",
            "<if test='search != null and search != \"\"'>",
            " or h1.recordId like concat('%',#{search},'%') " ,
            " or h2.userNameCN like concat('%',#{search},'%') " ,
            " or h1.BRDName like concat('%',#{search},'%') " , "</if></where>", "</script>"})
    public int getRecordQuestionNum(RecordQuestionInfo recordQuestionInfo);

    @Select("SELECT * FROM `record_question` h1 LEFT JOIN market_user h2 ON h1.`recordName`=h2.`userId` where recordId=#{recordId}")
    @ResultMap(value = "RecordQuestion")
    public RecordQuestionInfo getRecordQuestion(@Param("recordId") String recordId);
}
