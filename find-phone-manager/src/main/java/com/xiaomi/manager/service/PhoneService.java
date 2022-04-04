package com.xm.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xm.common.utils.PageUtils;
import com.xm.manager.entity.PhoneEntity;
import com.xm.manager.req.ScanBorrowPhoneReq;

//import java.util.List;
import java.util.List;
import java.util.Map;

/**
 * 
 *

 *
 * @date 2021-10-27 10:56:17
 */
public interface PhoneService extends IService<PhoneEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<PhoneEntity> fuzzySearch(String name);

    void scanBorrowPhone(ScanBorrowPhoneReq req);

    List<PhoneEntity> getMyPhone(String email);
}

