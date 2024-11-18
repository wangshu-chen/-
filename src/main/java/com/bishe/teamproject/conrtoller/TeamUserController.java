package com.bishe.teamproject.conrtoller;

import com.bishe.teamproject.jwt.JwtUtil;
import com.bishe.teamproject.model.Team;
import com.bishe.teamproject.service.ITeamService;
import com.bishe.teamproject.util.JsonObject;
import com.bishe.teamproject.util.R;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import com.bishe.teamproject.service.ITeamUserService;
import com.bishe.teamproject.model.TeamUser;
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
 * 社团成员管理 前端控制器
 * </p>
 *
 * @author kappy
 * @since 2022-10-31
 */
@Api(tags = {"社团成员管理"})
@RestController
@RequestMapping("/teamuser")
public class TeamUserController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ITeamUserService teamUserService;

    @Resource
    private ITeamService teamService;


    @ApiOperation(value = "新增社团成员管理")
    @RequestMapping("/addInfo")
    public R add(@RequestBody TeamUser teamUser,HttpServletRequest request){

        teamUser.setJoinTime(new Date());//添加加入时间

        //根据用户ID查询所属的社团ID
        //通过request获取token
        String token = request.getHeader("token");

        //通过token获取当前用户ID
        Integer userId = JwtUtil.getUserId(token);
        Team team = teamService.queryTeamInfoByUserId(userId);
        //把获取的对象ID加入到社团用户的对象中
        teamUser.setTeamId(team.getId());
        int num = teamUserService.add(teamUser);
        if (num>0){
            return R.ok();
        }
        return R.fail("失败");
    }

    @ApiOperation(value = "删除社团成员管理")
    @RequestMapping("/deleteById")
    public R delete(Long id){
        int num =  teamUserService.delete(id);
        if (num>0){
            return R.ok();
        }
        return R.fail("失败");
    }

    @ApiOperation(value = "更新社团成员管理")
    @RequestMapping("/updateInfo")
    public R update(@RequestBody TeamUser teamUser){
        int num = teamUserService.updateData(teamUser);
        if (num>0){
            return R.ok();
        }
        return R.fail("失败");
    }

    @RequestMapping("/queryTeamUserList")
    public JsonObject queryTeamUserList(@RequestParam(defaultValue = "1") int page,
                                        @RequestParam(defaultValue = "10") int limit, String tel, String email,
                                        HttpServletRequest request){

        //通过request获取token
        String token = request.getHeader("token");

        //通过token获取当前用户ID
        Integer userId = JwtUtil.getUserId(token);

        //创建返回对象模型

        JsonObject jo = new JsonObject();

        //放回结果集
        PageInfo<TeamUser> pageInfo = teamUserService.queryPageInfo(page, limit, tel,email,userId);

        jo.setCode(20000);
        jo.setData(pageInfo.getList());
        jo.setTotal(pageInfo.getTotal());
        return jo;
    }

}
