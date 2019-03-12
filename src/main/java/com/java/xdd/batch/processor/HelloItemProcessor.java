package com.java.xdd.batch.processor;

import com.java.xdd.batch.bean.DeviceCommand;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class HelloItemProcessor implements ItemProcessor<DeviceCommand, DeviceCommand> {
    @Override
    public DeviceCommand process(DeviceCommand deviceCommand) throws Exception {
        // 模拟下发命令给设备
        System.out.println("send command to device, id=" + deviceCommand.getId());
        // 更新命令状态
        deviceCommand.setStatus("SENT");
        // 返回命令对象
        return deviceCommand;
    }
     
}