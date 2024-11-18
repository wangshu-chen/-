package com.bishe.teamproject.conrtoller;

import com.bishe.teamproject.jwt.JwtUtil;
import com.bishe.teamproject.model.Activity;
import com.bishe.teamproject.util.JsonObject;
import com.bishe.teamproject.util.R;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import com.bishe.teamproject.service.ILeaveInfoService;
import com.bishe.teamproject.model.LeaveInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 * 在线留言管理 前端控制器
 * </p>
 *
 * @author kappy
 * @since 2022-10-31
 */
@Api(tags = {"在线留言管理"})
@RestController
@RequestMapping("/leaveinfo")
public class LeaveInfoController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ILeaveInfoService leaveInfoService;

    /**
     * 高级分页查询接口
     */
    @RequestMapping("/queryLeaveInfoAll")
    public JsonObject queryLeaveInfoAll(@RequestParam(defaultValue = "1")int page,
                                        @RequestParam(defaultValue = "15")int limit,
                                        String tel,
                                        HttpServletRequest request){

        //创建返回结果集对象
        JsonObject jo = new JsonObject();
        PageInfo<LeaveInfo> PageInfo = leaveInfoService.queryLeaveInfoAll(page,limit,tel);
        jo.setCode(20000);
        jo.setTotal(PageInfo.getTotal());
        jo.setData(PageInfo.getList());
        return jo;
    }

    @ApiOperation(value = "新增在线留言管理")
    @RequestMapping("/addInfo")
    public R add(@RequestBody LeaveInfo leaveInfo){
        //加入留言时间
        leaveInfo.setLeaveTime(new Date());
        int num = leaveInfoService.add(leaveInfo);
        if (num > 0){
            return R.ok();
        }
        return R.fail("失败");
    }

    @ApiOperation(value = "删除在线留言管理")
    @RequestMapping("/deleteById")
    public R delete(Long id){
        int num = leaveInfoService.delete(id);
        if (num > 0){
            return R.ok();
        }
        return R.fail("失败");
    }
}
