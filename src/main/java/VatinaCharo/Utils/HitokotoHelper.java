package VatinaCharo.Utils;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class HitokotoHelper {
    public static String parseJSON(String str) {
        StringBuilder msg = new StringBuilder("\n");
        POJO pojo = new Gson().fromJson(str, POJO.class);
        msg.append(pojo.hitokoto).append("\t\t\t\t----").append(pojo.fromWho).append("《").append(pojo.from).append("》");
        return msg.toString();
    }

    public static class POJO {
        public String hitokoto;
        public String from;
        @SerializedName("from_who")
        public String fromWho;
    }
}
