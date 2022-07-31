package com.thecms.mapper;

import com.thecms.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DialectMapper {

    List<UserEntity> getAllUser();
}
