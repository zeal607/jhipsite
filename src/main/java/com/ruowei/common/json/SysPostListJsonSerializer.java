package com.ruowei.common.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ruowei.modules.sys.domain.table.SysOffice;
import com.ruowei.modules.sys.domain.table.SysPost;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 把map<SysOffice,SysPost>序列化成
 *     [{sysOfficeId:1,sysPostId:2},{sysOfficeId:3,sysPostId:4}]
 * @author 刘东奇
 * @date 2019/9/24
 */
public class SysPostListJsonSerializer extends JsonSerializer<List<SysPost>> {
    @Override
    public void serialize(List<SysPost> value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        List<String> list = new ArrayList<>();
        for(SysPost sysPost:value){
            list.add(sysPost.getId().toString());
        }
        String text = list.toString();
        if (text != null) {
            jsonGenerator.writeString(text);
        }
    }
}
