package com.lizhao.service;

import com.lizhao.entity.Result;

import java.util.Map;

public interface OrderService {

   public Result order(Map map);

   public Map findById(Integer id);
}
