package edu.xyf.monitor.trigger.http.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xuyifeng
 * @description
 * @date 2025/4/8 19:26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonitorDataMapDTO {

    private String monitorId;
    private String monitorName;

}
