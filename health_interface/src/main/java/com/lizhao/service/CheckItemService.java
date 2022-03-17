package com.lizhao.service;

import com.lizhao.entity.PageResult;
import com.lizhao.entity.QueryPageBean;
import com.lizhao.pojo.CheckItem;

import java.util.List;

public interface  CheckItemService {
    public void add(CheckItem checkItem);
    public PageResult pageQuery(QueryPageBean queryPageBean);
    public void deleteById(Integer id);
    public void edit(CheckItem checkItem);
    public CheckItem findById(Integer id);

    public List<CheckItem> findAll();
}
