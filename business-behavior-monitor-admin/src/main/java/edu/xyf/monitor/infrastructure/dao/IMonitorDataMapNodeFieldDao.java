package edu.xyf.monitor.infrastructure.dao;

import edu.xyf.monitor.infrastructure.po.MonitorDataMapNodeField;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IMonitorDataMapNodeFieldDao {

    List<MonitorDataMapNodeField> queryMonitorDataMapNodeList(MonitorDataMapNodeField monitorDataMapNodeFieldReq);

}
