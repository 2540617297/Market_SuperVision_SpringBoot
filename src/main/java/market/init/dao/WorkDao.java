package market.init.dao;

import market.constant.WorkDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WorkDao {

    @Select({"<script>","SELECT h1.workId,h1.addWho,h1.solveDetails,h1.workDetails,h1.workEndTime,h3.userNameCN AS addWhoName,h2.userNameCN AS workWhoName,h1.workName,h1.workWho,h1.workStatus ",
            "FROM back_log h1 LEFT JOIN market_user h2 ON h1.workWho = h2.userId ",
            "LEFT JOIN  market_user h3 ON h1.addWho = h3.userId WHERE 1=1 ",
            "<if test='workId != null and workId != \"\"'>",
            " or workId=#{workId} " ,"</if>",
            "<if test='workDetails != null and workDetails != \"\"'>",
            " or workDetails like concat('%',#{workDetails},'%') " ,"</if>",
            "<if test='workWho != null and workWho != \"\"'>",
            " or workWho = #{workWho} " ,"</if>",
            "<if test='addWho != null and addWho != \"\"'>",
            " or addWho = #{addWho} " ,"</if>",
            "<if test='workName != null and workName != \"\"'>",
            "or workName=#{workName}","</if>","</script>"})
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
}
