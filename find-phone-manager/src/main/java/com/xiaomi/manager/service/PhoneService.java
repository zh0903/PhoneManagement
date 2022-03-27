package com.xiaomi.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaomi.common.utils.PageUtils;
import com.xiaomi.manager.entity.PhoneEntity;
import com.xiaomi.manager.req.ScanBorrowPhoneReq;

//import java.util.List;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lyj
 * @email 1499755237@qq.com
 * @date 2021-10-27 10:56:17
 */
public interface PhoneService extends IService<PhoneEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<PhoneEntity> fuzzySearch(String name);

    void scanBorrowPhone(ScanBorrowPhoneReq req);

    List<PhoneEntity> getMyPhone(String email);
}

