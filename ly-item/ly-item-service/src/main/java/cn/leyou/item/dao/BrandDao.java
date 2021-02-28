package cn.leyou.item.dao;

import cn.leyou.item.domain.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface BrandDao extends Mapper<Brand> {
    @Insert("insert into tb_category_brand values (#{cid},#{bid})")
    void insertCidAndBrandId(@Param("cid") Long cid,@Param("bid") Long bid);
}
