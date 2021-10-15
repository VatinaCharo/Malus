package VatinaCharo.Commands;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    private final Map<String, String> param = new HashMap<>();

    public Parser parse(String[] str) throws Exception {
        // TODO: 2021/10/14 实现比较复杂的命令解析
        // -l nju -info "this is a info" -out "这是输出，这是输出。"
        // =>
        // l nju
        // info this is a info
        // out 这是输出，这是输出。
        // 可能的非法输入
        // - l nju
        // -
        // -l
        return this;
    }

    public Map<String, String> getParam() {
        return param;
    }

    private boolean isParam(String s) {
        return s.startsWith("-") && s.length() != 1;
    }
}
