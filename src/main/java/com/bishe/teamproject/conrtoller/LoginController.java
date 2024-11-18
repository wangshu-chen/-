package com.bishe.teamproject.conrtoller;

import com.bishe.teamproject.jwt.JwtUtil;
import com.bishe.teamproject.model.TeamUser;
import com.bishe.teamproject.model.UserInfo;
import com.bishe.teamproject.service.ITeamUserService;
import com.bishe.teamproject.service.IUserinfoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class LoginController {

    /**
     * 登录验证相关方法
     */

    @Resource
    private IUserinfoService userinfoService;

    @Resource
    private ITeamUserService teamUserService;

    @RequestMapping("/loginIn")
    public Map loginIn(@RequestBody UserInfo info){
        /**
         * 获取info对象值
         * 通过用户名 密码 类型查询当前对象是否存在
         *   不存在 返回提示信息
         *   存在 生成token对象 然后返回前端
         */
        Map map = new HashMap();
        if (info.getType()!=2){//如果不是普通社团成员
            UserInfo userInfo = userinfoService.queryUserInfoByUserNameAndPasswordAndType(info);

            if (userInfo!=null){

                //生成token对象
                String Token = JwtUtil.getJwtToken(userInfo);
                map.put("token",Token);
                map.put("userId",userInfo.getId());
                map.put("username",userInfo.getUsername());
                map.put("type",userInfo.getType());
                Map map1 = new HashMap();
                map1.put("code",20000);
                map1.put("data",map);
                return map1;

            }else{
                map.put("code",300);
                map.put("msg","用户名密码不存在");
                return map;
            }
        }else{

            //获取用户名密码查询teamUser对象是否有当前账号，有登录成功，生成token
            TeamUser tu = teamUserService.queryByUserNameAndPwd(info.getUsername(), info.getPassword());
            if (tu!=null){
                //生成token对象
                UserInfo ui=new UserInfo();
                ui.setId(tu.getId());
                ui.setType(2);
                ui.setUsername(tu.getRealname());
                String Token = JwtUtil.getJwtToken(ui);
                map.put("token",Token);
                map.put("userId",tu.getId());
                map.put("username",tu.getRealname());
                map.put("type",2);
                Map map1 = new HashMap();
                map1.put("code",20000);
                map1.put("data",map);
                return map1;
            }else{
                //如果没有返回用户名名称或密码错误
                map.put("code",300);
                map.put("msg","用户名密码不存在");
                return map;
            }
        }
    }

    /**
     * 通过token获取用户对象和角色的信息
     */
    @RequestMapping("/queryuserInfo")
    public Map queryuserInfo(String token){

        Integer userType = JwtUtil.getUserType(token);
        List list = new ArrayList();
        if (userType == 1){
            //超级管理员
            list.add("Admin");
        }else{
            //社团管理员
            list.add("manager");
        }
        Map map = new HashMap();
        Map data = new HashMap();

        map.put("code",20000);
        data.put("roles",list);
        map.put("data",data);
        return map;
    }

    /**
     * 退出
     */
    @RequestMapping("/loginout")
    public Map loginout(){
        Map map = new HashMap();
        String token = null;
        map.put("code",20000);
        map.put("token",token);
        return map;


    }

}
