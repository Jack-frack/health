package com.lizhao.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lizhao.constant.MessageConstant;
import com.lizhao.constant.RedisConstant;
import com.lizhao.entity.PageResult;
import com.lizhao.entity.QueryPageBean;
import com.lizhao.entity.Result;
import com.lizhao.pojo.Setmeal;
import com.lizhao.service.SetmealService;
import com.lizhao.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 体检套餐管理
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    //新增
  //  @Reference
    //使用jedispool操作redis服务
    @Autowired
    private JedisPool jedisPool;
    @Reference
    private SetmealService setmealService;
    //文件上传
    @RequestMapping("/upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile){
       // System.out.println(imgFile);
        String originalFilename = imgFile.getOriginalFilename();//原始文件名 3bd90d2c-4e82-42a1-a401-882c88b06a1a2.jpg
        int index = originalFilename.lastIndexOf(".");
        String extention = originalFilename.substring(index - 1);//.jpg
        String fileName = UUID.randomUUID().toString() + extention;//	FuM1Sa5TtL_ekLsdkYWcf5pyjKGu.jpg
        try {
            //将文件上传到七牛云服务器
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),fileName);
            if(fileName!=null){
                jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
        return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,fileName);
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal,Integer[] checkgroupIds) {

        try {
            setmealService.add(setmeal,checkgroupIds);
            System.out.println(setmeal);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return  setmealService.pageQuery(queryPageBean);

    }
//    //删除检查组
//    @RequestMapping("/delete")
//    public Result delete(Integer id){
//        try{
//            checkGroupService.deleteById(id);
//        }catch (Exception e){
//            e.printStackTrace();
//            //服务调用失败
//            return new Result(false, MessageConstant.DELETE_CHECKGROUP_FAIL);
//        }
//        return  new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
//    }
//    @RequestMapping("/findAll")
//    public Result findAll(){
//        try{
//            List<CheckGroup> list = checkGroupService.findAll();
//            return  new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);
//        }catch (Exception e){
//            e.printStackTrace();
//            //服务调用失败
//            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
//        }


}
