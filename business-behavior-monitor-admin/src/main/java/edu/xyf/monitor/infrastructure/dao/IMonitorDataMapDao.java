package edu.xyf.monitor.infrastructure.dao;

import edu.xyf.monitor.infrastructure.po.MonitorDataMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IMonitorDataMapDao {
    String queryMonitorNameByMonitorId(String monitorId);

    List<MonitorDataMap> queryMonitorDataMapEntityList();

}
