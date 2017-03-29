package com.example.daniel.feedtest;

/**
 * Created by Daniel Leonett (d@hustling.me) on 13/3/2017.
 * Copyright (c) 2017 Hustling. All rights reserved.
 */
class Hustler {

    private String name;
    private String favoriteSkill;
    private String price;
    private String banner;
    private int skillsCount;


    public Hustler() {

    }

    public Hustler(String banner, String name, String favoriteSkill, String price, int skillsCount) {
        this.banner = banner;
        this.name = name;
        this.favoriteSkill = favoriteSkill;
        this.price = price;
        this.skillsCount = skillsCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFavoriteSkill() {
        return favoriteSkill;
    }

    public void setFavoriteSkill(String favoriteSkill) {
        this.favoriteSkill = favoriteSkill;
    }

    public int getSkillsCount() {
        return skillsCount;
    }

    public void setSkillsCount(int skillsCount) {
        this.skillsCount = skillsCount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }
}
