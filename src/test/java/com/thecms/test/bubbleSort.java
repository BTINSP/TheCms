package com.thecms.test;

import com.thecms.entity.manage.SystemMenu;
import com.thecms.mapper.manage.InitManageMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class bubbleSort {

    @Autowired
    InitManageMapper initManageMapper;

    @Test
    public void TestBubbleSort(){
        List<SystemMenu> menuList = initManageMapper.getMenuList();



    }

}
