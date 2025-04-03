package edu.xyf.monitor.sdk.push;

import edu.xyf.monitor.sdk.model.LogMessage;

/**
 * @author Xuyifeng
 * @description 发布消息
 * @date 2025/4/3 15:51
 */

public interface IPush {

    void open(String host, int port);

    void send(LogMessage logMessage);

}
