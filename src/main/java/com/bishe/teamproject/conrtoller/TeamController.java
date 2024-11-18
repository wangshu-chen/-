package com.bishe.teamproject.conrtoller;

import com.bishe.teamproject.jwt.JwtUtil;
import com.bishe.teamproject.model.Activity;
import com.bishe.teamproject.model.ApplyInfo;
import com.bishe.teamproject.model.TeamUser;
import com.bishe.teamproject.service.ITeamUserService;
import com.bishe.teamproject.util.JsonObject;
import com.bishe.teamproject.util.R;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.bishe.teamproject.service.ITeamService;
import com.bishe.teamproject.model.Team;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.spring.web.json.Json;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 社团信息管理 前端控制器
 * </p>
 *
 * @author kappy
 * @since 2022-10-31
 */
@Api(tags = {"社团信息管理"})
@RestController
@RequestMapping("/team")
public class TeamController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ITeamService teamService;

    @Resource
    private ITeamUserService teamUserService;

    @Value("${myserver.host}")
    private String host;

    @Value("${server.port}")
    private String port;

    /**
     * 上传图片 其他模块也能直接使用
     * @return
     */
    @RequestMapping("/fileUpload")
    public R fileUploed(@RequestParam(value = "file")
                                MultipartFile file){
            //判断上传内容是否为空
        if (file.isEmpty()){
            System.out.println("图片不存在");
        }
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //随机生成文件名称
        fileName = UUID.randomUUID() + suffixName;
        //设置文件上传路径
        String filePath="D:\\毕设项目图片\\";

        //上传
        File dest = new File(filePath,fileName);
        //判断文件路径是否存在,不存在就创建
        if (!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }

        try {
            file.transferTo(dest);
        } catch (IOException e) {
            System.out.println("上传出错了");
        }

        //设置最终的文件名称，返回前端
        String name = "http://" + host + ":" + port + "/images/" + fileName;
        return R.ok(name,null);
    }


    @RequestMapping("/queryTeamList")
    public JsonObject queryTeamList(@RequestParam(defaultValue = "1") int page,
                                    @RequestParam(defaultValue = "10") int limit,Team team){

        //创建返回对象模型

        JsonObject jo = new JsonObject();

        //放回结果集
        PageInfo<Team> pageInfo = teamService.queryTeamList(page, limit, team);

        jo.setCode(20000);
        jo.setData(pageInfo.getList());
        jo.setTotal(pageInfo.getTotal());
        return jo;
    }

    @ApiOperation(value = "新增社团信息管理")
    @RequestMapping("/addInfo")
    public R add(@RequestBody Team team){
        int num = teamService.add(team);
        if (num>0){
            return R.ok();
        }
        return R.fail("添加失败");
    }

    @ApiOperation(value = "删除社团信息管理")
    @RequestMapping("deleteById")
    public R delete(Long id){
        int num = teamService.delete(id);
        if (num>0){
            return R.ok();
        }
        return R.fail("删除失败");
    }

    @ApiOperation(value = "更新社团信息管理")
    @RequestMapping("/updateInfo")
    public R update(@RequestBody Team team){
        int num = teamService.updateData(team);
        if (num>0){
            return R.ok();
        }
        return R.fail("更新失败");
    }

    @ApiOperation(value = "查询社团信息管理分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Team> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return teamService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询社团信息管理")
    @GetMapping("{id}")
    public Team findById(@PathVariable Long id){
        return teamService.findById(id);
    }

    /**
     * 以下方法为前端页面所需接口
     */

    @RequestMapping("/queryTeamAll")
    public JsonObject queryTeamList(){

        //创建返回对象模型

        JsonObject jo = new JsonObject();

        //放回结果集
        PageInfo<Team> pageInfo = teamService.queryTeamList(1, 100, null);

        jo.setCode(20000);
        jo.setData(pageInfo.getList());
        jo.setTotal(pageInfo.getTotal());
        System.out.println(jo);
        return jo;
    }

    @RequestMapping("/findTeamById")
    public Map findTeamById(Integer id){
        Team team = teamService.findById(new Long(id));
        Map map = new HashMap();
        map.put("code",20000);
        map.put("data",team);
        return map;
    }

    /**
     * 获取我的社团列表
     */
    @RequestMapping("/queryMyTeamList")
    public JsonObject queryMyTeamList(HttpServletRequest request){

        //通过header获取token
        String token = request.getHeader("token");
        //获取用户ID
        Integer userId = JwtUtil.getUserId(token);
        //根据用户ID 获取用户对象
        TeamUser teamUser = teamUserService.getById(userId);
        //通过对象获取电话
        List<Team> list = teamService.queryMyteamList(teamUser.getTel());

        //创建返回结果集对象
        JsonObject jo = new JsonObject();
        jo.setCode(20000);
        jo.setData(list);
        return jo;

    }

}
