package com.ruowei.common.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 将字符串转为Set<String>
 * @author 刘东奇
 * @date 2019/9/24
 */
public class StringSetJsonDeserializer extends JsonDeserializer<Set<String>> {
    private static final Logger logger = LoggerFactory.getLogger(StringSetJsonDeserializer.class);

    @Override
    public Set<String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Set<String> stringSet = mapper.readValue(jsonParser, HashSet.class);
        return stringSet;
    }
}

