package VatinaCharo.Utils;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class UpgradeHelper {
    public static String[] parseJSON(String str) {
        StringBuilder msg = new StringBuilder();
        String version = "";
        POJO pojo = new Gson().fromJson(str, POJO.class);
        if (!pojo.draft) {
            msg.append("\n");
            msg.append("Malus\n");
            msg.append("--------------------\n");
            msg.append("Author: ").append(pojo.author.name).append("\n");
            msg.append("Version: ").append(pojo.tagName).append("\n");
            version = pojo.tagName.substring(1);
            msg.append("Time: ").append(pojo.publishedAT).append("\n");
            msg.append("URL: ").append(pojo.htmlURL).append("\n");
            msg.append(pojo.body);
            if (pojo.preRelease) {
                msg.append("\n!NOTICE!  PRE_RELEASE");
            }
        }
        return new String[]{version, msg.toString()};
    }

    public static class POJO {
        @SerializedName("html_url")
        public String htmlURL;
        public Author author;

        public static class Author {
            @SerializedName("login")
            public String name;
        }

        @SerializedName("tag_name")
        public String tagName;
        public Boolean draft = false;
        @SerializedName("prerelease")
        public Boolean preRelease;
        @SerializedName("published_at")
        public String publishedAT;
        public String body;
    }
}
