package com.zm.coal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zm.coal.entity.Product;
import com.zm.coal.mapper.ProductMapper;
import com.zm.coal.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author ZhuMei
 * @Date 2021/03/02 2:28
 * @Version 1.0
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService  {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateProduct(Product product) {
        return false;
    }
}
