package com.fh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.model.Area;

import java.util.List;

public interface AreaMapper extends BaseMapper<Area> {
    List<Area> queryList();
}
