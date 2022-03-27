package com.xiaomi.gateway.service.impl;

import com.xiaomi.gateway.dao.TokenDao;
import com.xiaomi.gateway.entity.TokenEntity;
import com.xiaomi.gateway.service.TokenService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaomi.common.utils.PageUtils;
import com.xiaomi.common.utils.Query;



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