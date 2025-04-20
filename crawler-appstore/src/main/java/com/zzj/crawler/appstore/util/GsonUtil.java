package com.zzj.crawler.appstore.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import us.codecraft.webmagic.selector.Html;

import java.io.IOException;
import java.lang.reflect.Type;

public class GsonUtil {
    // 配置Gson实例（线程安全）
    private static final Gson GSON = new GsonBuilder().serializeNulls()             // 序列化null值
            .disableHtmlEscaping()         // 不转义HTML字符
            .setDateFormat("yyyy-MM-dd HH:mm:ss") // 日期格式
            .registerTypeAdapter(Html.class, new HtmlTypeAdapter()) // 自定义Html适配器
            .create();

    /**
     * 将对象序列化为JSON字符串
     *
     * @param object 要序列化的对象
     *
     * @return JSON字符串
     */
    public static String serialize(Object object) {
        return GSON.toJson(object);
    }

    /**
     * 将JSON字符串反序列化为指定类型对象
     *
     * @param json  JSON字符串
     * @param clazz 目标类类型
     * @param <T>   泛型类型
     *
     * @return 反序列化后的对象
     */
    public static <T> T deserialize(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }

    /**
     * 将JSON字符串反序列化为复杂类型（如带泛型的集合）
     *
     * @param json JSON字符串
     * @param type 类型标记，使用TypeToken获取
     * @param <T>  泛型类型
     *
     * @return 反序列化后的对象
     */
    public static <T> T deserialize(String json, Type type) {
        return GSON.fromJson(json, type);
    }

    // 自定义Html类型适配器（可选）
    private static class HtmlTypeAdapter extends TypeAdapter<Html> {
        @Override
        public void write(JsonWriter out, Html value) throws IOException {
            if (value == null) {
                out.nullValue();
            } else {
                out.value(value.toString());
            }
        }

        @Override
        public Html read(JsonReader in) throws IOException {
            String htmlText = in.nextString();
            return new Html(htmlText);
        }
    }
}
