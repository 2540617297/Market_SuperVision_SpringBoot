package market.init.dao;

import market.constant.CheckInfo;
import market.constant.EnterPriseInfo;
import market.constant.RouteInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MobileLawDao {
    @Select("select * from enterprise_info where epName=#{epName}")
    public EnterPriseInfo getEnterpriseInfo(@Param("epName") String epName);

    @Insert("INSERT INTO `market_supervision`.`check_list` (`checkEnterprise`, `checkKind`, `checkCase`, `checkOpion`, `checkUser`,`checkTime`) VALUES ( #{checkEnterprise}, #{checkKind}, #{checkCase}, #{checkOpion}, #{checkUser},#{checkTime});")
    public int saveCheckList(CheckInfo checkInfo);

    @Insert("INSERT INTO `market_supervision`.`route_info` (`routeId`, `mLatitude`, `mLongitude`, `mHorizontalAccuracyMeters`, `address`, `addTime`,`addUser`) VALUES (#{routeId}, #{mLatitude}, #{mLongitude}, #{mHorizontalAccuracyMeters}, #{address}, #{addTime},#{addUser}); ")
    public int saveRouteInfo(RouteInfo routeInfo);

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

}
