package com.bishe.teamproject.conrtoller;


import com.bishe.teamproject.util.JsonObject;
import com.bishe.teamproject.util.R;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import com.bishe.teamproject.service.ITypeInfoService;
import com.bishe.teamproject.model.TypeInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 社团分类和介绍 前端控制器
 * </p>
 *
 * @author kappy
 * @since 2022-10-31
 */
@Api(tags = {"社团分类和介绍"})
@RestController
@RequestMapping("/typeInfo")
//@CrossOrigin
public class TypeInfoController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ITypeInfoService typeInfoService;


    /**
     * 分页高级查询
     * @param typeInfo
     * @return
     */
    @RequestMapping("/queryTypeList")
    public JsonObject queryTypeList(@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "15") Integer limit,
                                    String name){
        //创建对象模型
        JsonObject jo = new JsonObject();
        //包装对象
        TypeInfo info = new TypeInfo();
        info.setName(name);
        //获取结果集合
        PageInfo<TypeInfo> pageInfo = typeInfoService.queryTypeList(page, limit, info);
        jo.setCode(20000);
        jo.setTotal(pageInfo.getTotal());
        jo.setData(pageInfo.getList());
        return jo;
    }

    @ApiOperation(value = "新增社团分类和介绍")
    @RequestMapping("/addInfo")
    public R add(@RequestBody TypeInfo typeInfo){
        int num = typeInfoService.add(typeInfo);
        if (num>0){
            return R.ok();
        }
        return R.fail("操作失败");
    }

    @ApiOperation(value = "删除社团分类和介绍")
    @RequestMapping("/deleteById")
    public R delete(Long id){
        int num = typeInfoService.delete(id);
        if (num > 0){
            return R.ok();
        }
        return R.fail("操作失败");
    }

    @ApiOperation(value = "更新社团分类和介绍")
    @RequestMapping("/updateInfo")
    public R update(@RequestBody TypeInfo typeInfo){
        int num = typeInfoService.updateData(typeInfo);
        if (num>0){
            return R.ok();
        }
        return R.fail("操作失败");
    }

    @ApiOperation(value = "查询社团分类和介绍分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<TypeInfo> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return typeInfoService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询社团分类和介绍")
    @GetMapping("{id}")
    public TypeInfo findById(@PathVariable Long id){
        return typeInfoService.findById(id);
    }

}
