package com.lizhao.service;

import com.lizhao.entity.PageResult;
import com.lizhao.entity.QueryPageBean;
import com.lizhao.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {
    public void add(CheckGroup checkGroup,Integer[] checkitemIds);

    public PageResult pageQuery(QueryPageBean queryPageBean);

    public CheckGroup findById(Integer id);

    public List<Integer> findCheckItemIdsByCheckgroupId(Integer id);

    public void edit(CheckGroup checkGroup, Integer[] checkitemIds);

    public void deleteById(Integer id);

    public List<CheckGroup> findAll();


}
