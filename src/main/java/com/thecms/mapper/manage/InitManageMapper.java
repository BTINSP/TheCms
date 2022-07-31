package com.thecms.mapper.manage;

import com.thecms.entity.manage.SystemMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InitManageMapper {


   List<SystemMenu> getMenuList();

}
