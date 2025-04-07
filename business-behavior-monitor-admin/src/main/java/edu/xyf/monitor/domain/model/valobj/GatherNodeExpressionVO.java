package edu.xyf.monitor.domain.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GatherNodeExpressionVO {

    // 监控ID
    private String monitorId;
    // 节点ID
    private String monitorNodeId;
    // 节点名称
    private String monitorNodeName;
    // 采集，系统名称
    private String gatherSystemName;
    // 采集，类的名称
    private String gatherClazzName;
    // 采集，方法名称
    private String gatherMethodName;
    private List<Field> fields;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Field {
        // 日志名称
        private String logName;
        // 解析顺序；第几个字段
        private Integer logIndex;
        // 字段类型；Object、String
        private String logType;
        // 属性名称
        private String attributeName;
        // 属性字段
        private String attributeField;
        // 解析公式
        private String attributeOgnl;
    }

}
