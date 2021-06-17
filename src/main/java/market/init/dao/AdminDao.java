package market.init.dao;

import market.constant.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminDao {

    @Select("select navf.nf_id navfid,nf_name,ns_id,ns_name,navs.nf_id nafsid,nf_mark,ns_herf from navf,navs where navf.nf_id=navs.nf_id and navs.nf_id=#{navfid}")
    @Results(id = "NAVS" , value = {
            @Result(column="navfid", property="navfid"),
            @Result(column="nf_name", property="navfname"),
            @Result(column="nf_mark", property="navfmark"),
            @Result(column="ns_id", property="navss.navsid"),
            @Result(column="ns_name", property="navss.navsname"),
            @Result(column="nafsid", property="navss.navsfid"),
            @Result(column="ns_herf", property="navss.navsherf"),
    })
    public List<NavF> findNavs(int navfid);

    @Select("select nf_id navfid,nf_name navfname,nf_mark from navf")
    @Results(id = "NAVF" , value = {
            @Result(column="navfid", property="navfid"),
            @Result(column="navfname", property="navfname"),
            @Result(column="nf_mark", property="navfmark"),
    })
    public List<NavF> findNavf();

    @Select("select * from navs where nf_id=#{nfid}")
    @Results(id = "kind" , value = {
            @Result(column="ns_id", property="navsid"),
            @Result(column="ns_name", property="navsname"),
            @Result(column="nf_id", property="navsfid"),
            @Result(column="ns_herf", property="navsherf"),
    })
    public List<NavS> findKind(int nfid);

    @Insert("insert into service(service_title,service_content,service_time,service_admin,img_fileurl) values(#{posttitle},#{postcontent},#{posttime},#{postuser},#{postfileurl})")
    public int serviceAdd(PostInformation postInformation);

    @Select({"<script>","SELECT * FROM service h1 LEFT JOIN market_user h2 ON service_admin = userId <where>" ,
            "<if test='search != null and search != \"\"'>",
            " or h1.service_title like concat('%',#{search},'%') " ,
            " or h2.userNameCN like concat('%',#{search},'%') " ,
            " or h2.userName like concat('%',#{search},'%') ",
             "</if></where>","order by service_time desc ","LIMIT #{pageInfo.startpage},#{pageInfo.endpage}", "</script>"})
    @Results(id="service",value = {
            @Result(column = "service_id",property = "postid"),
            @Result(column = "service_title",property = "posttitle"),
            @Result(column = "service_time",property = "posttime"),
            @Result(column = "service_content",property = "postcontent"),
            @Result(column = "userNameCN",property = "userInfo.userNameCN"),
            @Result(column = "service_viewnum",property = "postview"),
            @Result(column = "img_fileurl",property = "postfileurl"),
    })
    public List<PostInformation> getService(PostInformation postInformation);

    @Select({"<script>","SELECT * FROM service h1 LEFT JOIN market_user h2 ON service_admin = userId where h1.service_id=#{postid}" ,
             "</script>"})
    @ResultMap(value = "service")
    public PostInformation getServiceById(@Param("postid") int postid);


    @Select({"<script>","SELECT count(*) FROM service h1 LEFT JOIN market_user h2 ON service_admin = userId <where>" ,
            "<if test='search != null and search != \"\"'>",
            " or h1.service_title like concat('%',#{search},'%') " ,
            " or h2.userNameCN like concat('%',#{search},'%') " ,
             "</if></where>", "</script>"})
    public int getServiceNum(PostInformation postInformation);

    @Delete("DELETE FROM market_user WHERE userId=#{userId}")
    public int deleteUser(@Param("userId") String userId);

    @Delete("DELETE FROM `market_supervision`.`enterprise_info` WHERE epId=#{epId}")
    public int deleteEnterprise(@Param("epId") String epId);

    @Delete("DELETE FROM `market_supervision`.`service` WHERE `service_id` =#{service_id}")
    public int deleteService(@Param("service_id") String service_id);

    @Delete("DELETE FROM `market_supervision`.`back_log` WHERE `workId` =#{workId}")
    public int deleteBackLog(@Param("workId") String workId);

    @Delete("DELETE FROM `market_supervision`.`route_info` WHERE `routeId` =#{routeId}")
    public int deletePatrolRecord(@Param("routeId") String routeId);

    @Insert("INSERT INTO `market_supervision`.`law` (`lawId`, `lawName`, `lawContent`, `lawKind`, `lawSubKind`) VALUES (NULL, #{lawName}, #{lawContent}, #{lawKind}, #{lawSubKind});  ")
    public int insertLaw(Law law);

    @Select({"<script>","SELECT * FROM law <where>" ,
            "<if test='search != null and search != \"\"'>",
            " or lawName like concat('%',#{search},'%') " ,
            " or lawKind like concat('%',#{search},'%') " ,
            " or lawSubKind like concat('%',#{search},'%') ",
            "</if></where>","LIMIT #{pageInfo.startpage},#{pageInfo.endpage}", "</script>"})
    public List<Law> searchLaw(Law law);

    @Select({"<script>","SELECT count(*) FROM law <where>" ,
            "<if test='search != null and search != \"\"'>",
            " or lawName like concat('%',#{search},'%') " ,
            " or lawKind like concat('%',#{search},'%') " ,
            " or lawSubKind like concat('%',#{search},'%') ",
            "</if></where>", "</script>"})
    public int searchLawNum(Law law);
}
