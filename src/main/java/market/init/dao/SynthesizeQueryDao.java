package market.init.dao;

import market.constant.CheckInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SynthesizeQueryDao {

    @Select({"<script>","SELECT * FROM `check_list` h1 LEFT JOIN `enterprise_info` h2 ON h1.`checkEnterprise`=h2.`epId`","</script>"})
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
            @Result(column="epBusinessScop", property="enterPriseInfo.epBusinessScop")
    })
    public List<CheckInfo> getCheck();
}
