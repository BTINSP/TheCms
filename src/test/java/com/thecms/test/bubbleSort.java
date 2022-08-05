package com.thecms.test;

import com.thecms.entity.manage.ManageColumn;
import com.thecms.entity.manage.SystemMenu;
import com.thecms.mapper.manage.ManageMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class bubbleSort {

    @Autowired
    ManageMapper manageMapper;

    @Test
    public void TestBubbleSort(){
        List<SystemMenu> menuList = manageMapper.getMenuList();
        System.out.println(menuList);

    }

    @Test
    public void  TestManageMapper(){
        List<ManageColumn> columnList = manageMapper.getColumnList();
        System.out.println(columnList);
    }

    @Test
    public void test(){
        boolean b = manageMapper.checkColumn(2,"test",1,51);
        System.out.println(b);
    }

    @Test
    public void TestDelete(){
        boolean b = manageMapper.deleteColumn(2,"test",1,50);
        System.out.println(b);
    }
}
