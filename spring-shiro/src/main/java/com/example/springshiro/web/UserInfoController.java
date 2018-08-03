package com.example.springshiro.web;

import com.example.springshiro.entity.UserInfo;
import com.example.springshiro.sevice.UserInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    @Resource
    UserInfoService userInfoService;

    /**
     * 用户查询.
     * @return
     */
    @RequestMapping("/userList")
    //角色的权限管理，如果查询到该角色有权限就执行此方法
    @RequiresPermissions("userInfo:view")//权限管理;
    public String userInfo(Model model){
        List<UserInfo> users=userInfoService.getUserList();
        model.addAttribute("users", users);
        return "userInfo";
    }

    /**
     * 用户添加;
     * @return
     */
    @RequestMapping("/userAdd")
    @RequiresPermissions("userInfo:add")//权限管理;
    public String userInfoAdd(){
        return "userInfoAdd";
    }

    /**
     * 用户删除;
     * @return
     */
    @RequestMapping("/userDel")
    @RequiresPermissions("userInfo:del")//权限管理;
    public String userDel(){
        return "userInfoDel";
    }

    /**
     * 用户修改
     * @param model
     * @param username
     * @return
     */
    @RequestMapping("/toEdit")
    @RequiresPermissions("userInfo:update")//权限管理;
    public String toEdit(Model model,String username) {
        UserInfo user=userInfoService.findByUsername(username);
        model.addAttribute("user", user);
        return "userInfoEdit";
    }

    /*
    return "user/userEdit"; 代表会直接去resources目录下找相关的文件。
    return "redirect:/list"; 代表转发到对应的controller，这个示例就相当于删除内容之后自动调整到list请求，然后再输出到页面。
     */
    @RequestMapping("/add")
    public String add(UserInfo user) {
        user.setSalt(UUID.randomUUID().toString().replaceAll( "-" , "" ));
        userInfoService.save(user);
        return "redirect:/userInfo/userList";
    }

    @RequestMapping("/edit")
    public String edit(UserInfo user) {
        user.setSalt(UUID.randomUUID().toString().replaceAll( "-" , "" ));
        userInfoService.edit(user);
        return "redirect:/userInfo/userList";
    }


    @RequestMapping("/delete")
    public String delete(String username) {
        userInfoService.deleteByUsername(username);
        return "redirect:/userInfo/userList";
    }
}