package com.thecms.mapper.manage;

import com.thecms.entity.manage.ManageColumn;
import com.thecms.entity.manage.ManageUser;
import com.thecms.entity.manage.SystemMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ManageMapper {

   ManageUser checkUserByUsernameAndPassword(@Param("username")String username,@Param("password")String password);
   ManageUser getUserByUsername(@Param("username")String username);

   List<SystemMenu> getMenuList();

   List<ManageColumn> getColumnList();

   boolean addColumn(@Param("column_name")String column_name, @Param("status")int status, @Param("sort_rank")int sort_rank);

   boolean checkColumn(
           @Param("id")int id,
           @Param("column_name")String column_name,
           @Param("status")int status,
           @Param("sort_rank")int sort_rank
   );

   boolean deleteColumn(
           @Param("id")int id,
           @Param("column_name")String column_name,
           @Param("status")int status,
           @Param("sort_rank")int sort_rank
   );

   boolean updateColumn(
           @Param("update_column_name")String update_column_name,
           @Param("update_status")int update_status,
           @Param("update_sort_rank")int update_sort_rank,
           @Param("id")int id,
           @Param("column_name")String column_name,
           @Param("status")int status,
           @Param("sort_rank")int sort_rank
   );

   boolean updateColumnStatus(
           @Param("id")int id,
           @Param("column_name")String column_name,
           @Param("status")int status,
           @Param("sort_rank")int sort_rank
   );

}
