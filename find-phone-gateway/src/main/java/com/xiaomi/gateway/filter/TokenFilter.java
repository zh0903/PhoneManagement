//package com.xm.gateway.filter;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.xm.aegis.utils.AegisFacade;
//import com.xm.aegis.vo.UserInfoVO;
//import com.xm.common.utils.R;
//import com.xm.common.utils.TokenManager;
//import com.xm.gateway.entity.TokenEntity;
//import com.xm.gateway.exception.BizCodeEnum;
//import com.xm.gateway.exception.InvalidateTokenException;
//import com.xm.gateway.service.TokenService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//import reactor.core.publisher.Operators;
//
//import javax.servlet.http.HttpServletRequest;
//import java.nio.charset.StandardCharsets;
//import java.util.Date;
//import java.util.List;
//
///**
//
// * @Description  暂时不使用
// * @date 2021 年 10 月 28 日 下午4:34
// */
//public class TokenFilter implements GlobalFilter, Ordered {
//
//    Logger logger = LoggerFactory.getLogger(TokenFilter.class);
//
//    private TokenService tokenService;
//
//    public TokenFilter(TokenService tokenService) {
//        this.tokenService = tokenService;
//    }
//
//    private DataBuffer getDataBufferFromObject(R e, ServerHttpResponse response) {
//        byte[] bytes = e.toString().getBytes(StandardCharsets.UTF_8);
//        return response.bufferFactory().wrap(bytes);
//    }
//
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//
//    //理解起来有一定的难度，使用的是webflux编程
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) throws InvalidateTokenException {
//
//        HttpServletRequest request = (HttpServletRequest)exchange.getRequest();
//        UserInfoVO userInfo = AegisFacade.getUserInfo(request);
////        List<String> token = exchange.getRequest().getHeaders().get(TokenManager.TOKEN_NAME);
////        if (ObjectUtils.isEmpty(token)) {
////            DataBuffer buffer = getDataBufferFromObject(R.error(BizCodeEnum.TOKEN_INVALIDATE.getCode(), BizCodeEnum.TOKEN_INVALIDATE.getMessage()), exchange.getResponse());
////            return exchange.getResponse().writeWith(Flux.just(buffer));
////        }
////        String userInfo = TokenManager.getUserInfoFromToken(token.get(0));
////        if (ObjectUtils.isEmpty(userInfo)) {
////            TokenEntity dbToken = tokenService.getOne(new QueryWrapper<TokenEntity>().eq("token", token));
////            if (dbToken == null || dbToken.getExpireTime().getTime() < System.currentTimeMillis()) {  //大于两倍时间表示过期
////                DataBuffer buffer = getDataBufferFromObject(R.error(BizCodeEnum.TOKEN_EXPIRED.getCode(), BizCodeEnum.TOKEN_EXPIRED.getMessage()), exchange.getResponse());
////                return exchange.getResponse().writeWith(Flux.just(buffer));
////            } else {
////                String newToken = TokenManager.createToken(dbToken.getUserId());
////                TokenEntity newTokenEntity = new TokenEntity(null, newToken, dbToken.getUserId(), new Date(System.currentTimeMillis() * 2));
////                tokenService.removeById(dbToken.getId());
////                tokenService.save(newTokenEntity);
////                exchange.getResponse().getHeaders().add(TokenManager.NEW_TOKEN, newToken);
////            }
////        }
//        return chain.filter(exchange);
//    }
//}
