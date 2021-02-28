package cn.leyou.item.service;

import cn.leyou.common.vo.PageResult;
import cn.leyou.item.dao.BrandDao;
import cn.leyou.item.domain.Brand;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional
public class BrandService {
    @Autowired
    private BrandDao brandDao;

    public PageResult<Brand> queryBrandsByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        //初始化example对象
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        //根据name模糊查询，或者根据首字母查询
        if (StringUtils.isNotBlank(key)){
            criteria.andLike("name","%"+key+"%").orEqualTo("letter",key);
        }
        //根据sortBy排序
        if (StringUtils.isNotBlank(sortBy)){
            example.setOrderByClause(sortBy+" "+(desc ? "desc" : "asc"));
        }
        //添加分页条件
        PageHelper.startPage(page,rows);
        List<Brand> brands = brandDao.selectByExample(example);
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());
    }

    public void saveBrand(Brand brand, List<Long> cids) {
        //tb_Brand表中保存brand数据
        Boolean flag = brandDao.insertSelective(brand)==1;
        //向中间表保存数据
        if (flag){
            cids.forEach(cid->{
                brandDao.insertCidAndBrandId(cid,brand.getId());
            });
        }
    }
}
