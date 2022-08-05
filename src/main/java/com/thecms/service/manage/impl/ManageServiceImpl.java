package com.thecms.service.manage.impl;

import com.thecms.entity.manage.ManageColumn;
import com.thecms.entity.manage.SystemMenu;
import com.thecms.mapper.manage.ManageMapper;
import com.thecms.compenont.Result;
import com.thecms.service.manage.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    ManageMapper manageMapper;


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


    /**
     * 获取菜单列表
     * @return
     */
    @Override
    public Result getMenuList() {
        List<SystemMenu> menuList = manageMapper.getMenuList();;

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

    /**
     * 获取栏目列表
     * @return Result
     */
    @Override
    public Result getColumnList() {
        List<ManageColumn> columnList = manageMapper.getColumnList();
        return Result.success(columnList);
    }

    /**
     * 添加菜单
     * @return
     */
    @Override
    public Result addColumn(ManageColumn manageColumn) {
        boolean status = manageMapper.addColumn(manageColumn.getColumn_name(), manageColumn.getStatus(), manageColumn.getSort_rank());
        if (status){
            return Result.success(200,"添加完成",null);
        }
        return Result.fail(400,"添加失败",null);
    }

    @Override
    public Result deleteColumn(ManageColumn manageColumn){
        if (!manageMapper.checkColumn(
                manageColumn.getId(),
                manageColumn.getColumn_name(),
                manageColumn.getStatus(),
                manageColumn.getSort_rank()))
        {
            return Result.fail("未查询到栏目");
        }
        if (manageMapper.deleteColumn(
                manageColumn.getId(),
                manageColumn.getColumn_name(),
                manageColumn.getStatus(),
                manageColumn.getSort_rank()))
        {
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }

    /**
     * 更新栏目
     * @param updateManageColumn    更新数据
     * @param manageColumn          目标数据
     * @return                      Result
     */
    @Override
    public Result updateColumn(ManageColumn updateManageColumn,ManageColumn manageColumn) {
        boolean status = manageMapper.updateColumn(
                updateManageColumn.getColumn_name(),
                updateManageColumn.getStatus(),
                updateManageColumn.getSort_rank(),
                manageColumn.getId(),
                manageColumn.getColumn_name(),
                manageColumn.getStatus(),
                manageColumn.getSort_rank()
        );
        if (status){
            return Result.success("更新完成");
        }
        return Result.fail("更新失败");
    }

    @Override
    public Result updateColumnStatus(ManageColumn manageColumn) {
        if (manageMapper.updateColumnStatus(
                manageColumn.getId(),
                manageColumn.getColumn_name(),
                manageColumn.getStatus(),
                manageColumn.getSort_rank()))
        {
            return Result.success("修改状态成功");
        }
        return Result.fail("修改状态失败");
    }

}
