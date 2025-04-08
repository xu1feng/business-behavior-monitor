package edu.xyf.monitor.infrastructure.repository;

import edu.xyf.monitor.domain.model.entity.MonitorDataEntity;
import edu.xyf.monitor.domain.model.entity.MonitorDataMapEntity;
import edu.xyf.monitor.domain.model.valobj.GatherNodeExpressionVO;
import edu.xyf.monitor.domain.repository.IMonitorRepository;
import edu.xyf.monitor.infrastructure.dao.*;
import edu.xyf.monitor.infrastructure.po.MonitorData;
import edu.xyf.monitor.infrastructure.po.MonitorDataMap;
import edu.xyf.monitor.infrastructure.po.MonitorDataMapNode;
import edu.xyf.monitor.infrastructure.po.MonitorDataMapNodeField;
import edu.xyf.monitor.infrastructure.redis.IRedisService;
import edu.xyf.monitor.types.Constants;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Xuyifeng
 * @description
 * @date 2025/4/7 20:37
 */
@Repository
public class MonitorRepository implements IMonitorRepository {

    @Resource
    private IMonitorDataDao monitorDataDao;
    @Resource
    private IMonitorDataMapDao monitorDataMapDao;
    @Resource
    private IMonitorDataMapNodeDao monitorDataMapNodeDao;
    @Resource
    private IMonitorDataMapNodeFieldDao monitorDataMapNodeFieldDao;
    @Resource
    private IMonitorDataMapNodeLinkDao monitorDataMapNodeLinkDao;

    @Resource
    private IRedisService redisService;

    @Override
    public List<GatherNodeExpressionVO> queryGatherNodeExpressionVO(String systemName, String className, String methodName) {
        // 1. 查询采集节点
        MonitorDataMapNode monitorDataMapNodeReq = new MonitorDataMapNode();
        monitorDataMapNodeReq.setGatherSystemName(systemName);
        monitorDataMapNodeReq.setGatherClazzName(className);
        monitorDataMapNodeReq.setGatherMethodName(methodName);
        List<MonitorDataMapNode> monitorDataMapNodes = monitorDataMapNodeDao.queryMonitorDataMapNodeList(monitorDataMapNodeReq);
        if (monitorDataMapNodes.isEmpty()) return null;

        List<GatherNodeExpressionVO> gatherNodeExpressionVOS = new ArrayList<>();
        for (MonitorDataMapNode monitorDataMapNodeRes : monitorDataMapNodes) {
            // 2. 查询采集公式
            MonitorDataMapNodeField monitorDataMapNodeFieldReq = new MonitorDataMapNodeField();
            monitorDataMapNodeFieldReq.setMonitorId(monitorDataMapNodeRes.getMonitorId());
            monitorDataMapNodeFieldReq.setMonitorNodeId(monitorDataMapNodeRes.getMonitorNodeId());
            List<MonitorDataMapNodeField> monitorDataMapNodeFieldList = monitorDataMapNodeFieldDao.queryMonitorDataMapNodeList(monitorDataMapNodeFieldReq);

            List<GatherNodeExpressionVO.Field> fields = new ArrayList<>();
            for (MonitorDataMapNodeField monitorDataMapNodeField : monitorDataMapNodeFieldList) {
                fields.add(GatherNodeExpressionVO.Field.builder()
                        .logName(monitorDataMapNodeField.getLogName())
                        .logIndex(monitorDataMapNodeField.getLogIndex())
                        .logType(monitorDataMapNodeField.getLogType())
                        .attributeField(monitorDataMapNodeField.getAttributeField())
                        .attributeName(monitorDataMapNodeField.getAttributeName())
                        .attributeOgnl(monitorDataMapNodeField.getAttributeOgnl())
                        .build());
            }

            gatherNodeExpressionVOS.add(GatherNodeExpressionVO.builder()
                    .monitorId(monitorDataMapNodeRes.getMonitorId())
                    .monitorNodeId(monitorDataMapNodeRes.getMonitorNodeId())
                    .gatherSystemName(monitorDataMapNodeRes.getGatherSystemName())
                    .gatherClazzName(monitorDataMapNodeRes.getGatherClazzName())
                    .gatherMethodName(monitorDataMapNodeRes.getGatherMethodName())
                    .fields(fields)
                    .build());
        }
        return gatherNodeExpressionVOS;
    }

    @Override
    public String queryMonitorNameByMonitorId(String monitorId) {
        return monitorDataMapDao.queryMonitorNameByMonitorId(monitorId);
    }

    @Override
    public void saveMonitorData(MonitorDataEntity monitorDataEntity) {
        MonitorData monitorDataReq = new MonitorData();
        monitorDataReq.setMonitorId(monitorDataEntity.getMonitorId());
        monitorDataReq.setMonitorName(monitorDataEntity.getMonitorName());
        monitorDataReq.setMonitorNodeId(monitorDataEntity.getMonitorNodeId());
        monitorDataReq.setSystemName(monitorDataEntity.getSystemName());
        monitorDataReq.setClazzName(monitorDataEntity.getClazzName());
        monitorDataReq.setMethodName(monitorDataEntity.getMethodName());
        monitorDataReq.setAttributeName(monitorDataEntity.getAttributeName());
        monitorDataReq.setAttributeField(monitorDataEntity.getAttributeField());
        monitorDataReq.setAttributeValue(monitorDataEntity.getAttributeValue());
        monitorDataDao.insert(monitorDataReq);

        String cacheKey = Constants.RedisKey.monitor_node_data_count_key + monitorDataEntity.getMonitorId() + Constants.UNDERLINE + monitorDataEntity.getMonitorNodeId();
        redisService.incr(cacheKey);
    }

    @Override
    public List<MonitorDataMapEntity> queryMonitorDataMapEntityList() {
        List<MonitorDataMap> monitorDataList = monitorDataMapDao.queryMonitorDataMapEntityList();
        List<MonitorDataMapEntity> monitorDataMapEntities = new ArrayList<>();
        for (MonitorDataMap monitorDataMap : monitorDataList) {
            monitorDataMapEntities.add(MonitorDataMapEntity.builder()
                    .monitorId(monitorDataMap.getMonitorId())
                    .monitorName(monitorDataMap.getMonitorName())
                    .build());
        }
        return monitorDataMapEntities;
    }

}
