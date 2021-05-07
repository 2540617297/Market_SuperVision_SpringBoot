package market.init.dao;

import market.constant.EnterPriseInfo;
import market.constant.WorkDetails;
import market.constant.WorkStatus;
import org.apache.ibatis.annotations.*;
import org.apache.poi.ss.formula.atp.WorkdayCalculator;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WorkDao {

    @Select({"<script>","SELECT h1.workId,h1.addWho,h1.solveDetails,h1.workDetails,h1.workEndTime,h3.userNameCN AS addWhoName,h2.userNameCN AS workWhoName,h1.workName,h1.workWho,h1.workStatus ",
            "FROM back_log h1 LEFT JOIN market_user h2 ON h1.workWho = h2.userId ",
            "LEFT JOIN  market_user h3 ON h1.addWho = h3.userId WHERE 1=1 ",
            "<if test='workId != null and workId != \"\"'>",
            " and workId=#{workId} " ,"</if>",
            "<if test='workDetails != null and workDetails != \"\"'>",
            " and workDetails like concat('%',#{workDetails},'%') " ,"</if>",
            "<if test='workWho != null and workWho != \"\"'>",
            " and workWho = #{workWho} " ,"</if>",
            "<if test='addWho != null and addWho != \"\"'>",
            " and addWho = #{addWho} " ,"</if>",
            "<if test='workName != null and workName != \"\"'>",
            "and workName=#{workName}","</if>","</script>"})
    public List<WorkDetails> getBackLog(WorkDetails workDetails);

    @Select({"<script>","SELECT h1.workId,h1.addWho,h1.solveDetails,h1.workDetails,h1.workEndTime,h3.userNameCN AS addWhoName,h2.userNameCN AS workWhoName,h1.workName,h1.workWho,h1.workStatus " ,
            "FROM back_log h1 LEFT JOIN market_user h2 ON h1.workWho = h2.userId " ,
            "LEFT JOIN  market_user h3 ON h1.addWho = h3.userId <where>" ,
            "<if test='search != null and search != \"\"'>",
            "  workId like concat('%',#{search},'%') " ,
            " or workDetails like concat('%',#{search},'%') " ,
            " or h2.userNameCN like concat('%',#{search},'%') " ,
            " or h3.userNameCN like concat('%',#{search},'%') " ,
            "or workName like concat('%',#{search},'%')","</if></where>",
            "LIMIT #{pageInfo.startpage},#{pageInfo.endpage}","</script>"})
    public List<WorkDetails> searchBackLog(WorkDetails workDetails);

    @Select({"<script>","SELECT COUNT(*) " ,
            "FROM back_log h1 LEFT JOIN market_user h2 ON h1.workWho = h2.userId " ,
            "LEFT JOIN  market_user h3 ON h1.addWho = h3.userId <where>" ,
            "<if test='search != null and search != \"\"'>",
            "  workId like concat('%',#{search},'%') " ,
            " or workDetails like concat('%',#{search},'%') " ,
            " or workWho like concat('%',#{search},'%') " ,
            " or addWho like concat('%',#{search},'%') " ,
            "or workName like concat('%',#{search},'%')","</if></where>","</script>"})
    public int allNum(WorkDetails workDetails);

    @Insert("INSERT INTO `market_supervision`.`back_log` (`workId`, `workName`, `workDetails`, `addWho`, `workWho`, `workEndTime`, `solveDetails`, `workStatus`) VALUES (#{workId}, #{workName}, #{workDetails}, #{addWho}, #{workWho}, #{workEndTime}, #{solveDetails}, #{workStatus}); ")
    public int saveTask(WorkDetails workDetails);

    @Update("UPDATE `market_supervision`.`back_log` SET `workName` = #{workName},`workDetails`=#{workDetails},`workWho`=#{workWho},`workEndTime`=#{workEndTime},`solveDetails`=#{solveDetails},`workStatus`=#{workStatus},workSaveTime=#{workSaveTime} WHERE `workId` = #{workId};")
    public int updateTask(WorkDetails workDetails);

    @Select("SELECT * FROM `market_supervision`.`work_status`")
    public List<WorkStatus> getWorkStatus();


}
