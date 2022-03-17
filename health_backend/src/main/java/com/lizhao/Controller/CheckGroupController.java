package com.lizhao.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lizhao.constant.MessageConstant;
import com.lizhao.entity.PageResult;
import com.lizhao.entity.QueryPageBean;
import com.lizhao.entity.Result;
import com.lizhao.pojo.CheckGroup;
import com.lizhao.service.CheckGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 检查组管理
 */
@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {
    //新增
    @Reference
    private CheckGroupService checkGroupService;

    @RequestMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds) {

        try {
            checkGroupService.add(checkGroup, checkitemIds);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    //检查项分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return  checkGroupService.pageQuery(queryPageBean);

    }

    //根据ID查询检查组
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            CheckGroup checkGroup = checkGroupService.findById(id);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }
    //根据检查组id查询检查项id
    @RequestMapping("/findCheckItemIdsByCheckgroupId")
    public Result findCheckItemIdsByCheckgroupId(Integer id){
        try {
            List<Integer> CheckItemIds = checkGroupService.findCheckItemIdsByCheckgroupId(id);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,CheckItemIds);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds) {

        try {
            checkGroupService.edit(checkGroup, checkitemIds);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }

    //删除检查组
    @RequestMapping("/delete")
    public Result delete(Integer id){
        try{
            checkGroupService.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
        return  new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }
    @RequestMapping("/findAll")
    public Result findAll(){
        try{
            List<CheckGroup> list = checkGroupService.findAll();
            return  new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

}
