package VatinaCharo.Commands;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    private String name;
    private final Map<String, String> param = new HashMap<>();

    public Parser parse(String str) {
        // TODO: 2021/10/14 实现比较复杂的命令解析
        return this;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getParam() {
        return param;
    }
}
