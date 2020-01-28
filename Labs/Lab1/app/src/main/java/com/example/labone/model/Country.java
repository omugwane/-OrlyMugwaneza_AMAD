package com.example.labone.model;

public class Country {

        private String name;
        private String info;
        private String anthem;

        public Country(String name, String picture, String info,String anthem) {
            this.name = name;
            this.info = info;
            this.anthem=anthem;
        }

        public Country(String name, String info,String anthem) {
            this.name = name;
            this.info = info;
            this.anthem=anthem;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

    public String getAnthem() {
        return anthem;
    }

    public void setAnthem(String anthem) {
        this.anthem = anthem;
    }
}
