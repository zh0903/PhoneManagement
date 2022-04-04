package com.xm.gateway.service.impl;

import com.xm.gateway.dao.TokenDao;
import com.xm.gateway.entity.TokenEntity;
import com.xm.gateway.service.TokenService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xm.common.utils.PageUtils;
import com.xm.common.utils.Query;



@Service("tokenService")
public class TokenServiceImpl extends ServiceImpl<TokenDao, TokenEntity> implements TokenService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TokenEntity> page = this.page(
                new Query<TokenEntity>().getPage(params),
                new QueryWrapper<TokenEntity>()
        );

        return new PageUtils(page);
    }

}