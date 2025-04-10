package edu.xyf.monitor.domain.repository;

import edu.xyf.monitor.domain.model.entity.MonitorDataEntity;
import edu.xyf.monitor.domain.model.entity.MonitorDataMapEntity;
import edu.xyf.monitor.domain.model.entity.MonitorFlowDesignerEntity;
import edu.xyf.monitor.domain.model.valobj.GatherNodeExpressionVO;
import edu.xyf.monitor.domain.model.valobj.MonitorTreeConfigVO;

import java.util.List;

public interface IMonitorRepository {

    List<GatherNodeExpressionVO> queryGatherNodeExpressionVO(String systemName, String className, String methodName);

    String queryMonitorNameByMonitorId(String monitorId);

    void saveMonitorData(MonitorDataEntity monitorDataEntity);

    List<MonitorDataMapEntity> queryMonitorDataMapEntityList();

    MonitorTreeConfigVO queryMonitorFlowData(String monitorId);

    List<MonitorDataEntity> queryMonitorDataEntityList(MonitorDataEntity monitorDataEntity);

    void updateMonitorFlowDesigner(MonitorFlowDesignerEntity monitorFlowDesignerEntity);

}
