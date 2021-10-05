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
        public int id;
        public String uuid;
        public String hitokoto;
        public String type;
        public String from;
        @SerializedName("from_who")
        public String fromWho;
        public String creator;
        @SerializedName("creator_uid")
        public String creatorUID;
        public int reviewer;
        @SerializedName("commit_from")
        public String commitFrom;
        @SerializedName("created_at")
        public String createdAT;
        public int length;
    }
}
