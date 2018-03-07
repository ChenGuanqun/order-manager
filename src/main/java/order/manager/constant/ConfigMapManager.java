package order.manager.constant;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigMapManager {
    public static final Map<String, String> CONFIG_MAP = new HashMap<String, String>();

    public static final Map<String, String> CONFIG_MAP_REVERSE = new HashMap<String, String>();

    public static final List<String> listConfig = Lists.newArrayList("yq",
            "dz","zz","fz","hbj","zc","gttt","jk","zzzc","dzzc","bz","bp","sc","lc");

    static {
        CONFIG_MAP.put("yq","油漆");
        CONFIG_MAP.put("dz","定子");
        CONFIG_MAP.put("zz","转子");
        CONFIG_MAP.put("fz","风罩");
        CONFIG_MAP.put("hbj","钢板件");
        CONFIG_MAP.put("zc","轴");
        CONFIG_MAP.put("gttt","盖头");
        CONFIG_MAP.put("jk","机壳");
        CONFIG_MAP.put("zzzc","转子总成");
        CONFIG_MAP.put("dzzc","定子总成");
        CONFIG_MAP.put("bz","包装");
        CONFIG_MAP.put("bp","标牌");
        CONFIG_MAP.put("sc","开关");
        CONFIG_MAP.put("lc","线缆");

        CONFIG_MAP_REVERSE.put("油漆","yq");
        CONFIG_MAP_REVERSE.put("定子","dz");
        CONFIG_MAP_REVERSE.put("转子","zz");
        CONFIG_MAP_REVERSE.put("风罩","fz");
        CONFIG_MAP_REVERSE.put("钢板件","hbj");
        CONFIG_MAP_REVERSE.put("轴","zc");
        CONFIG_MAP_REVERSE.put("盖头","gttt");
        CONFIG_MAP_REVERSE.put("机壳","jk");
        CONFIG_MAP_REVERSE.put("转子总成","zzzc");
        CONFIG_MAP_REVERSE.put("定子总成","dzzc");
        CONFIG_MAP_REVERSE.put("包装","bz");
        CONFIG_MAP_REVERSE.put("标牌","bp");
        CONFIG_MAP_REVERSE.put("开关","sc");
        CONFIG_MAP_REVERSE.put("线缆","lc");
    }
}
