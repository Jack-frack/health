package com.lizhao.service;

import com.lizhao.entity.PageResult;
import com.lizhao.entity.QueryPageBean;
import com.lizhao.pojo.Setmeal;

import java.util.List;

public interface SetmealService {

    public void add(Setmeal setmeal, Integer[] checkgroupIds);

    public PageResult pageQuery(QueryPageBean queryPageBean);

    public List<Setmeal> findAll();

    public Setmeal findById(Integer id);
}
