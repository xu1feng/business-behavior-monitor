package edu.xyf.monitor.domain.service;

import edu.xyf.monitor.domain.model.entity.MonitorDataMapEntity;
import ognl.OgnlException;

import java.util.List;

/**
 * @author Xuyifeng
 * @description 解析数据接口
 * @date 2025/4/7 19:38
 */

public interface ILogAnalyticalService {

    void doAnalytical(String systemName, String className, String methodName, List<String> logList) throws OgnlException;

    List<MonitorDataMapEntity> queryMonitorDataMapEntityList();

}
