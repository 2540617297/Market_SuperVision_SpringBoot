package market.init.dao;

import market.constant.NavF;
import market.constant.NavS;
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
}
