package com.thecms.service.manage;

import com.thecms.compenont.Result;
import com.thecms.entity.manage.ManageColumn;

public interface ColumnService {

    Result getMenuList();

    Result getColumnList();

    Result addColumn(ManageColumn manageColumn);

    Result deleteColumn(ManageColumn manageColumn);

    Result updateColumn(ManageColumn updateManageColumn,ManageColumn manageColumn);

    Result updateColumnStatus(ManageColumn manageColumn);

}
