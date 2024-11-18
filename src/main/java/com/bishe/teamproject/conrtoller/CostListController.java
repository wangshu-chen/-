package com.bishe.teamproject.conrtoller;

import com.bishe.teamproject.jwt.JwtUtil;
import com.bishe.teamproject.model.ApplyInfo;
import com.bishe.teamproject.model.Team;
import com.bishe.teamproject.service.ITeamService;
import com.bishe.teamproject.service.Impl.TeamServiceImpl;
import com.bishe.teamproject.util.JsonObject;
import com.bishe.teamproject.util.R;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import com.bishe.teamproject.service.ICostListService;
import com.bishe.teamproject.model.CostList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kappy
 * @since 2022-10-31
 */
@RestController
@RequestMapping("/costlist")
public class CostListController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ICostListService costListService;

    @Resource
    private ITeamService teamService;

    @RequestMapping("/queryCostListAll")
    public JsonObject queryCostListAll(@RequestParam(defaultValue = "1")Integer page,
                                       @RequestParam(defaultValue = "15")Integer limit,
                                       String name,
                                       HttpServletRequest request){
        String token = request.getHeader("token");
        Integer userId = JwtUtil.getUserId(token);
        Team team = teamService.queryTeamInfoByUserId(userId);
        Integer teamId=null;
        if (team!=null){
            teamId=team.getId();
        }
        //创建返回结果集对象
        JsonObject jo = new JsonObject();
        PageInfo<CostList> CostListPageInfo = costListService.queryCostListAll(name,teamId,page,limit);
        jo.setCode(20000);
        jo.setTotal(CostListPageInfo.getTotal());
        jo.setData(CostListPageInfo.getList());
        return jo;
    }

    @ApiOperation(value = "新增")
    @RequestMapping("/addInfo")
    public R add(@RequestBody CostList costList,HttpServletRequest request){
        String token = request.getHeader("token");
        Integer userId = JwtUtil.getUserId(token);
        Team team = teamService.queryTeamInfoByUserId(userId);
        System.out.println(team);
        //加入当前社团的ID
        costList.setTeamId(team.getId());//获取当前社团ID
        costList.setCtime(new Date());
        int num = costListService.add(costList);
        if (num>0){
            return R.ok();
        }
        return R.fail("失败");
    }

    @ApiOperation(value = "删除")
    @RequestMapping("/deleteById")
    public R delete(Long id){
        int num = costListService.delete(id);
        if (num>0){
            return R.ok();
        }
        return R.fail("失败");
    }

    @ApiOperation(value = "更新")
    @RequestMapping("/updateInfo")
    public R update(@RequestBody CostList costList){
        int num = costListService.updateData(costList);
        if (num>0){
            return R.ok();
        }
        return R.fail("失败");
    }

}
