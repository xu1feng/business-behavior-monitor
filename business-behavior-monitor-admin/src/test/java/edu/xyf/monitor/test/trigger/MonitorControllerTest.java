package edu.xyf.monitor.test.trigger;

import com.alibaba.fastjson2.JSON;
import edu.xyf.monitor.trigger.http.MonitorController;
import edu.xyf.monitor.trigger.http.dto.MonitorDataMapDTO;
import edu.xyf.monitor.types.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Xuyifeng
 * @description
 * @date 2025/4/8 19:39
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MonitorControllerTest {

    @Resource
    private MonitorController monitorController;

    @Test
    public void test_queryMonitorDataMapEntityList() {
        Response<List<MonitorDataMapDTO>> response = monitorController.queryMonitorDataMapEntityList();
        log.info("测试结果: {}", JSON.toJSONString(response));
    }

}
