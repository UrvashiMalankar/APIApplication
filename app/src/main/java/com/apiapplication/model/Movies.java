package com.apiapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Movies {
    @SerializedName("page")
    public int page;

    @SerializedName("results")
    public ArrayList<MovieList> data = new ArrayList();

    @SerializedName("genres")
    public ArrayList<MovieDetail> genres = new ArrayList();

    public class MovieList {

        @SerializedName("original_title")
        public String title;

        @SerializedName("backdrop_path")
        public String image;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

    public class MovieDetail{
        @SerializedName("name")
        public String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
