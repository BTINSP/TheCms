package com.thecms.service.manage.impl;

import com.thecms.entity.manage.SystemMenu;
import com.thecms.mapper.manage.InitManageMapper;
import com.thecms.compenont.Result;
import com.thecms.service.manage.InitManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InitManageServiceImpl implements InitManageService {

    @Autowired
    InitManageMapper initManageMapper;


    /****
     * 菜单递归
     * @param menu
     * @param menuList
     * @return List<SystemMenu>
     */
    public List<SystemMenu> getChildren(SystemMenu menu, List<SystemMenu> menuList){
        List<SystemMenu> childList = new ArrayList<>();
        menuList.forEach( e -> {
            if (menu.getId() == e.getParent()){
                childList.add(e);
            }
        });
        childList.forEach( e -> {
            e.setChildren(getChildren(e, menuList));
        });
        return childList;
    }



    @Override
    public Result getMenuList() {
        List<SystemMenu> menuList = initManageMapper.getMenuList();;

        ArrayList<SystemMenu> rootMenus = new ArrayList<>();
        menuList.forEach( menu -> {
            if (menu.getParent() == 0 && menu.getStatus() == 0){
                rootMenus.add(menu);
            }
        });

        rootMenus.forEach( menu -> {
            menu.setChildren(getChildren(menu, menuList));
        });

        return Result.success(rootMenus);
    }
}
