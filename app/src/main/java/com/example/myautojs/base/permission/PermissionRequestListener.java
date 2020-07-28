package com.example.myautojs.base.permission;

import java.util.Map;

/**
 * @className: PermissionRequestListener
 * @classDescription:
 * @author: Pan_
 * @createTime: 2018/10/25
 */
public abstract class PermissionRequestListener {
    /**
     * 权限申请结果的回调，map里面对应着需要申请的权限和结果
     * @param result
     */
    public abstract void onGrant(Map<String,GrantResult> result);

}
