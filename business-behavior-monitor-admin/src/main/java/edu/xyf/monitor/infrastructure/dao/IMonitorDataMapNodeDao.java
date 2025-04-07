package edu.xyf.monitor.infrastructure.dao;

import edu.xyf.monitor.infrastructure.po.MonitorDataMapNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IMonitorDataMapNodeDao {

    List<MonitorDataMapNode> queryMonitorDataMapNodeList(MonitorDataMapNode monitorDataMapNodeReq);

}
