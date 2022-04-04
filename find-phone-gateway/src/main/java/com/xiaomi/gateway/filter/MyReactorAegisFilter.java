package com.xm.gateway.filter;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.xm.aegis.utils.AegisSignUtil;
import com.xm.common.constant.GateWayAndManagerConstant;
import com.xm.gateway.vo.UserInfoVO;
import com.xm.common.entity.UserEntity;
import com.xm.common.utils.R;
import com.xm.gateway.exception.BizCodeEnum;
import com.xm.gateway.service.UserService;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**

 * @Description 由于小米本身提供的AegisFilter SDK 无法满足需求，固参考原本的SDK重写Filter过滤方法
 * @date 2021 年 11 月 03 日 上午11:03
 */
public class MyReactorAegisFilter implements GlobalFilter, Ordered {

    private UserService userService;
    private static final Logger log = LoggerFactory.getLogger(com.xm.aegis.filter.AegisFilter.class);
    private static final String MI_PUBLIC_KEY = "DELETE"

    public MyReactorAegisFilter(UserService userService) {
        this.userService = userService;
    }


    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        try {
            ServerHttpResponse response = exchange.getResponse();
            ServerHttpRequest request = exchange.getRequest();
            String url = request.getPath().contextPath().value();
            HttpHeaders headers = request.getHeaders();
            if (ObjectUtils.isEmpty(headers)) {
                DataBuffer buffer = getDataBufferFromObject(R.error(BizCodeEnum.MI_DUN_INVALIDATOR.getCode(), BizCodeEnum.MI_DUN_INVALIDATOR.getMessage()), exchange.getResponse());
                return exchange.getResponse().writeWith(Flux.just(buffer));
            }
            String verifyIdentitySignData = "";
            verifyIdentitySignData = headers.get("X-Proxy-Midun").get(0);
            String verifyIdentityData = AegisSignUtil.verifySignGetInfo(verifyIdentitySignData, MI_PUBLIC_KEY);
            if (ObjectUtils.isEmpty(verifyIdentityData)) {
                DataBuffer buffer = getDataBufferFromObject(R.error(BizCodeEnum.MI_DUN_ERROR.getCode(), BizCodeEnum.MI_DUN_ERROR.getMessage()), exchange.getResponse());
                return exchange.getResponse().writeWith(Flux.just(buffer));
            }
            List<String> signAndUserSignDataList = headers.get("x-proxy-userdetail");
            String signAndUserSignData = "";
            if (ObjectUtils.isEmpty(signAndUserSignDataList)) {
                log.info("确认为米盾请求，没有签名用户数据(bypass|静态资源)，url{}", url);
                return chain.filter(exchange);
            } else {
                signAndUserSignData = headers.get("x-proxy-userdetail").get(0);
            }
            String userJson = AegisSignUtil.verifySignGetInfo(signAndUserSignData, MI_PUBLIC_KEY);
            if (ObjectUtils.isEmpty(userJson)) {
                log.error("获取用户数据，验签失败，signData:{}", signAndUserSignData);
                DataBuffer buffer = getDataBufferFromObject(R.error(BizCodeEnum.MI_DUN_GET_USER_INFO_ERROR.getCode(), BizCodeEnum.MI_DUN_GET_USER_INFO_ERROR.getMessage()), exchange.getResponse());
                return exchange.getResponse().writeWith(Flux.just(buffer));
            } else {
                UserInfoVO userInfo = this.getUserInfo(userJson);

                //做一些业务的处理，判断数据库中是否存在用户,不存在则入库算登录成功
                loginAndSaveUserInfo(userInfo);

                //添加email为了findManger做权限认证
                ServerHttpRequest mutateRequest = request.mutate().header(GateWayAndManagerConstant.USER_EMAIL,
                        userInfo.getEmail()).build();
                ServerWebExchange serverWebExchange = exchange.mutate().request(mutateRequest).build();
                log.info("AegisFilter check success");
                return chain.filter(serverWebExchange);
            }
        } catch (Throwable e) {
            log.error("AegisFilter check exception", e);
            DataBuffer buffer = getDataBufferFromObject(R.error(BizCodeEnum.GATEWAY_ERROR.getCode(), BizCodeEnum.GATEWAY_ERROR.getMessage()), exchange.getResponse());
            return exchange.getResponse().writeWith(Flux.just(buffer));
        }
    }

    private void loginAndSaveUserInfo(UserInfoVO userInfoVO) {
        UserEntity userEntity = userService.getOne(new QueryWrapper<UserEntity>().eq("email", userInfoVO.getEmail()));
        if (!ObjectUtils.isEmpty(userEntity)) {
            return;
        }
        UserEntity entity = new UserEntity();
        entity.setNickName(userInfoVO.getDisplayName());
        entity.setDepartment(userInfoVO.getDepartmentName());
        entity.setAvatarUrl(userInfoVO.getAvatar());
        entity.setEmail(userInfoVO.getEmail());
        entity.setType(userInfoVO.getType());
        entity.setMiId(userInfoVO.getMiID());
        userService.save(entity);
    }

    private DataBuffer getDataBufferFromObject(R e, ServerHttpResponse response) {
        byte[] bytes = e.toString().getBytes(CharsetUtil.UTF_8);
        return response.bufferFactory().wrap(bytes);
    }

    private UserInfoVO getUserInfo(String userJson) {
        Gson gson = new Gson();
        JsonObject jsonObject = (JsonObject) gson.fromJson(userJson, JsonObject.class);
        UserInfoVO userInfo = new UserInfoVO();
        userInfo.setUser(jsonObject.get("cas:user").getAsString());
        userInfo.setName(jsonObject.get("cas:name").getAsString());
        userInfo.setDisplayName(jsonObject.get("cas:displayName").getAsString());
        userInfo.setDepartmentName(jsonObject.get("cas:departmentName").getAsString());
        userInfo.setEmail(jsonObject.get("cas:email").getAsString());
        userInfo.setMiID(jsonObject.get("cas:miID").getAsString());
        userInfo.setType(jsonObject.get("cas:type").getAsString());
        userInfo.setAvatar(jsonObject.get("cas:avatar").getAsString());
        return userInfo;
    }
}
