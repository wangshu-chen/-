package com.bishe.teamproject.conrtoller;

import com.bishe.teamproject.jwt.JwtUtil;
import com.bishe.teamproject.model.ApplyInfo;
import com.bishe.teamproject.model.Team;
import com.bishe.teamproject.service.ITeamService;
import com.bishe.teamproject.util.JsonObject;
import com.bishe.teamproject.util.R;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import com.bishe.teamproject.service.IActivityService;
import com.bishe.teamproject.model.Activity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 社团活动管理 前端控制器
 * </p>
 *
 * @author kappy
 * @since 2022-10-31
 */
@Api(tags = {"社团活动管理"})
@RestController
@RequestMapping("/activity")
public class ActivityController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IActivityService activityService;

    @Resource
    private ITeamService teamService;

    /**
     * 高级分页查询接口
     */
    @RequestMapping("/queryActivityInfoList")
    public JsonObject queryActivityInfoList(String name,String tel,
                                         @RequestParam(defaultValue = "1")int page,
                                         @RequestParam(defaultValue = "15")int limit,
                                         HttpServletRequest request){


        //通过header 获取token
        String token = request.getHeader("token");
        //通过token获取角色类型和用户id
        Integer userType = JwtUtil.getUserType(token);
        Integer userId = null;
        if (userType == 0){
            //获取当前用户账号的ID
            userId = JwtUtil.getUserId(token);
        }

        //创建返回结果集对象
        JsonObject jo = new JsonObject();
        PageInfo<Activity> PageInfo = activityService.queryList(page,limit,name,tel,userId);
        jo.setCode(20000);
        jo.setTotal(PageInfo.getTotal());
        jo.setData(PageInfo.getList());
        return jo;
    }

    @RequestMapping("/updateStatusById")
    public R updateStatusById(int id){
        int status = 1;//默认只要审核就通过
        boolean b = activityService.updateStatus(status, id);
        if (b){
            return R.ok();
        }
        return R.fail("失败");
    }


    @ApiOperation(value = "新增社团活动管理")
    @RequestMapping("/addInfo")
    public R add(@RequestBody Activity activity,HttpServletRequest request){
        //通过header 获取token
        String token = request.getHeader("token");
        //获取用户ID
        Integer userId = JwtUtil.getUserId(token);
        //根据用户id获取用户所在社团信息
        Team team = teamService.queryTeamInfoByUserId(userId);
        activity.setUserId(userId);
        activity.setCreateTime(new Date());
        activity.setTeamId(team.getId());
        activity.setStatus(0);
        int num =  activityService.add(activity);
        if (num>0){
            return R.ok();
        }
        return R.fail("失败");
    }

    @ApiOperation(value = "删除社团活动管理")
    @RequestMapping("/deleteById")
    public R delete(Long id){
        int num =  activityService.delete(id);
        if (num>0){
            return R.ok();
        }
        return R.fail("失败");
    }

    @ApiOperation(value = "更新社团活动管理")
    @RequestMapping("/updateInfo")
    public R update(@RequestBody Activity activity){
        int num = activityService.updateData(activity);
        if (num>0){
            return R.ok();
        }
        return R.fail("失败");
    }

    /**
     * 前端页面接口方法
     */

    @RequestMapping("/findByTeamId")
    public Map findByTeamId(Integer id){
        Activity activity = activityService.findByTeamId(id);
        Map map = new HashMap();
        map.put("code",20000);
        map.put("data",activity);
        return map;
    }

}
