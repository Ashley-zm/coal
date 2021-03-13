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
    public Product getProductById(Long id) {
        /**
         * 对应 mapper 中的接口的方法 selectProductById
         */
        return baseMapper.selectProductById(id);
    }
}
