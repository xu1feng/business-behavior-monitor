package edu.xyf.monitor.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xuyifeng
 * @description
 * @date 2025/4/8 19:28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonitorDataMapEntity {

    // 监控ID
    private String monitorId;
    // 监控名称
    private String monitorName;

}
