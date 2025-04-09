package edu.xyf.monitor.infrastructure.dao;

import edu.xyf.monitor.infrastructure.po.MonitorDataMapNodeLink;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IMonitorDataMapNodeLinkDao {
    List<MonitorDataMapNodeLink> queryMonitorNodeLinkConfigByMonitorId(String monitorId);

    void deleteLinkFromByMonitorId(String monitorId);

    void insert(MonitorDataMapNodeLink monitorDataMapNodeLinkReq);
}
