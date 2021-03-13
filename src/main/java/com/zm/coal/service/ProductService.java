package com.zm.coal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zm.coal.entity.Contract;
import com.zm.coal.entity.Product;

/**
 * @Author ZhuMei
 * @Date 2021/03/02 2:26
 * @Version 1.0
 */
public interface ProductService extends IService<Product> {

    /**
     * 根据出厂信息来更新产品的总数量
     * @param id
     * @return
     */
    Product getProductById(Long id);
}
