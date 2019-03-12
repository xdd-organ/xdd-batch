package com.java.xdd.batch.aggregator;

import com.java.xdd.batch.bean.DeviceCommand;
import org.springframework.batch.item.file.transform.LineAggregator;

/**
 * @author xdd
 * @date 2019/3/12
 */
public class HelloLineAggregator implements LineAggregator<DeviceCommand> {
    @Override
    public String aggregate(DeviceCommand deviceCommand) {
        StringBuffer sb = new StringBuffer();
        sb.append(deviceCommand.getId());
        sb.append(",,");
        sb.append(deviceCommand.getStatus());
        return sb.toString();
    }
}
