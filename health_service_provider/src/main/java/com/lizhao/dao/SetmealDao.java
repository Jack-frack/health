package com.lizhao.dao;

import com.github.pagehelper.Page;
import com.lizhao.pojo.CheckGroup;
import com.lizhao.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealDao {

    public void add(Setmeal setmeal);

    public void setSetmealAndCheckGroup(Map map);

    public Page<Setmeal> findByCondition(String queryString);

    public List<Setmeal> findAll();

    public Setmeal findById(int id);


}