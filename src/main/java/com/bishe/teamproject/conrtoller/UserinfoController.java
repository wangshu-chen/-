package com.bishe.teamproject.conrtoller;

import com.bishe.teamproject.jwt.JwtUtil;
import com.bishe.teamproject.model.Activity;
import com.bishe.teamproject.util.JsonObject;
import com.bishe.teamproject.util.R;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import com.bishe.teamproject.service.IUserinfoService;
import com.bishe.teamproject.model.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 管理员信息表 前端控制器
 * </p>
 *
 * @author chen
 * @since 2022-10-31
 */
@Api(tags = {"管理员信息表"})
@RestController
@RequestMapping("/userinfo")
public class UserinfoController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IUserinfoService userinfoService;


    /**
     * 获取用户列表接口
     * 所有的用户类型为社团管理员的用户
     * @return
     */
    @RequestMapping("/queryUserInfoList")
    public JsonObject queryUserInfoList(){
        List<UserInfo> list = userinfoService.list();
        JsonObject jo = new JsonObject();
        jo.setCode(20000);
        jo.setData(list);
        return jo;
    }


    @ApiOperation(value = "新增管理员信息表")
    @RequestMapping("/addInfo")
    public R add(@RequestBody UserInfo userinfo){
        int num = userinfoService.add(userinfo);
        if (num>0){
            return R.ok();
        }
        return R.fail("失败");
    }

    @ApiOperation(value = "删除管理员信息表")
    @RequestMapping("/deleteById")
    public R delete(Long id){
        int num = userinfoService.delete(id);
        if (num>0){
            return R.ok();
        }
        return R.fail("失败");
    }

    @ApiOperation(value = "更新管理员信息表")
    @RequestMapping("/updateInfo")
    public R update(@RequestBody UserInfo userinfo){
        int num = userinfoService.updateData(userinfo);
        if (num>0){
            return R.ok();
        }
        return R.fail("失败");
    }

    /**
     * 高级分页查询接口
     */
    @RequestMapping("/queryInfoList")
    public JsonObject queryInfoList(String username,String tel,
                                            @RequestParam(defaultValue = "1")int page,
                                            @RequestParam(defaultValue = "15")int limit,
                                            HttpServletRequest request){


        //通过header 获取token
        String token = request.getHeader("token");
        //通过token获取角色类型和用户id
        Integer userType = JwtUtil.getUserType(token);
        Integer userId = null;
        //创建userinfo对象
        UserInfo userInfo = new UserInfo();
        if (userType == 0){
            //获取当前用户账号的ID
            userId = JwtUtil.getUserId(token);
            userInfo.setId(userId);
        }
        if(username!=null){
            userInfo.setUsername(username);
        }
        if (tel!=null){
            userInfo.setTel(tel);
        }
        //创建返回结果集对象
        JsonObject jo = new JsonObject();
        PageInfo<UserInfo> PageInfo = userinfoService.queryUserInfoAll(page,limit,userInfo);
        jo.setCode(20000);
        jo.setTotal(PageInfo.getTotal());
        jo.setData(PageInfo.getList());
        return jo;
    }

    /**
     * 修改密码
     */
    @RequestMapping("/updatePassword")
    public R updatePassword(@RequestBody UserInfo userinfo){
        int num = userinfoService.updatepassword(userinfo.getId(), userinfo.getPassword());
        if (num>0){
            return R.ok();
        }
        return R.fail("失败");

    }

}
