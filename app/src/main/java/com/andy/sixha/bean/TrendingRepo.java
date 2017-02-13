package com.andy.sixha.bean;

import org.json.JSONObject;

/**
 *
 * Created by lancelot on 2016/12/2.
 */

public class TrendingRepo {
    private String avatar;
    private String desc;
    private String link;
    private String repo;
    private String owner;
    private String stars_today;

    public TrendingRepo(JSONObject jsonObject) {
        avatar = jsonObject.optString("avatar");
        desc = jsonObject.optString("desc");
        link = jsonObject.optString("link");
        repo = jsonObject.optString("repo");
        owner = jsonObject.optString("owner");
        stars_today = jsonObject.optString("stars_today");
    }

    public String getAvatar() {
        return avatar;
    }

    public String getDesc() {
        return desc;
    }

    public String getLink() {
        return link;
    }

    public String getRepo() {
        return repo;
    }

    public String getStars_today() {
        return stars_today;
    }

    public String getOwner() {
        return owner;
    }

}
