package edu.xyf.monitor.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IMonitorDataMapDao {
    String queryMonitorNameByMonitorId(String monitorId);
}
