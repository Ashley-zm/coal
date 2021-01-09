package com.zm.coal.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author ZhuMei
 * @Date 2021/1/7 21:43
 * @Version 1.0
 */
public class ResultUtil {

    /**
     * 构建分页查询返回的结果
     * @param page
     * @return
     */
    public static R<Map<String,Object>> buildPageR(IPage<?> page){
        HashMap<String, Object> hashMap = new HashMap<>();
        System.out.println("总数量："+page.getTotal());
        hashMap.put("count",page.getTotal());
        hashMap.put("records",page.getRecords());
        System.out.println(hashMap);

        return R.ok(hashMap);
    }

    /**
     * 成功或失败的响应信息
     * @param success
     * @return
     */
    public static R<Object> buildR(boolean success){
        if (success){
            return R.ok(null);
        }
        return R.failed("操作失败");
    }
}
