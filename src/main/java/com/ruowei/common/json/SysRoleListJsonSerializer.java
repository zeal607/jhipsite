package com.ruowei.common.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ruowei.modules.sys.domain.entity.SysRole;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 把map<SysOffice,SysPost>序列化成
 *     [{sysOfficeId:1,sysPostId:2},{sysOfficeId:3,sysPostId:4}]
 * @author 刘东奇
 * @date 2019/9/24
 */
public class SysRoleListJsonSerializer extends JsonSerializer<List<SysRole>> {
    @Override
    public void serialize(List<SysRole> value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException{

        List<String> list = new ArrayList<>();
        for(SysRole sysRole:value){
            list.add(sysRole.getId().toString());
        }
        String text = list.toString();
        if (text != null) {
            jsonGenerator.writeString(text);
        }
    }
}
