package edu.xyf.monitor.domain.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import edu.xyf.monitor.domain.model.entity.MonitorDataEntity;
import edu.xyf.monitor.domain.model.entity.MonitorDataMapEntity;
import edu.xyf.monitor.domain.model.valobj.GatherNodeExpressionVO;
import edu.xyf.monitor.domain.model.valobj.MonitorTreeConfigVO;
import edu.xyf.monitor.domain.repository.IMonitorRepository;
import edu.xyf.monitor.types.Constants;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author Xuyifeng
 * @description
 * @date 2025/4/7 20:12
 */
@Service
public class LogAnalyticalService implements ILogAnalyticalService {

    @Resource
    private IMonitorRepository repository;

    @Override
    public void doAnalytical(String systemName, String className, String methodName, List<String> logList) throws OgnlException {
        // 查询匹配解析节点
        List<GatherNodeExpressionVO> gatherNodeExpressionVOs = repository.queryGatherNodeExpressionVO(systemName, className, methodName);

        if (null == gatherNodeExpressionVOs || gatherNodeExpressionVOs.isEmpty()) return;

        for (GatherNodeExpressionVO gatherNodeExpressionVO : gatherNodeExpressionVOs) {
            String monitorName = repository.queryMonitorNameByMonitorId(gatherNodeExpressionVO.getMonitorId());

            List<GatherNodeExpressionVO.Field> fields = gatherNodeExpressionVO.getFields();
            for (GatherNodeExpressionVO.Field field : fields) {
                Integer logIndex = field.getLogIndex();

                String logName = logList.get(0);
                if (!logName.equals(field.getLogName())) {
                    continue;
                }

                String attributeValue = "";
                String logStr = logList.get(logIndex);
                if ("Object".equals(field.getLogType())) {
                    OgnlContext context = new OgnlContext();
                    context.setRoot(JSONObject.parseObject(logStr));
                    Object root = context.getRoot();
                    attributeValue = String.valueOf(Ognl.getValue(field.getAttributeOgnl(), context, root));
                } else {
                    attributeValue = logStr.trim();
                    if (attributeValue.contains(Constants.COLON)) {
                        attributeValue = attributeValue.split(Constants.COLON)[1].trim();
                    }
                }

                MonitorDataEntity monitorDataEntity = MonitorDataEntity.builder()
                        .monitorId(gatherNodeExpressionVO.getMonitorId())
                        .monitorName(monitorName)
                        .monitorNodeId(gatherNodeExpressionVO.getMonitorNodeId())
                        .systemName(gatherNodeExpressionVO.getGatherSystemName())
                        .clazzName(gatherNodeExpressionVO.getGatherClazzName())
                        .methodName(gatherNodeExpressionVO.getGatherMethodName())
                        .attributeName(field.getAttributeName())
                        .attributeField(field.getAttributeField())
                        .attributeValue(attributeValue)
                        .build();

                repository.saveMonitorData(monitorDataEntity);
            }
        }
    }

    @Override
    public List<MonitorDataMapEntity> queryMonitorDataMapEntityList() {
        return repository.queryMonitorDataMapEntityList();
    }

    @Override
    public MonitorTreeConfigVO queryMonitorFlowData(String monitorId) {
        return repository.queryMonitorFlowData(monitorId);
    }

}
