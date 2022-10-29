package com.example.sharecode;

public class Upload {
    //class contains name and url info of the uploads
    private String mName;
    private String mImageUrl;

    public Upload() {
        //empty constructor needed inorder for the class to work with firebase
    }

    public Upload(String name, String imageUrl) {
        //class builder
        if (name.trim().equals("")) {
            name = "No Name";
        }
        mName = name;
        mImageUrl = imageUrl;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
}
