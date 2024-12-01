package vttp.batchb.ssf.day16hw.model;

public class Article {
    private String title;
    private String description;
    private String imgUrl;
    private String url;
    //private String content;

    //getter setter
    public String getTitle() {    return title;}
    public void setTitle(String title) {    this.title = title;}

    public String getDescription() {    return description;}
    public void setDescription(String description) {    this.description = description;}

    public String getImgUrl() {    return imgUrl;}
    public void setImgUrl(String imgUrl) {    this.imgUrl = imgUrl;}

    public String getUrl() {    return url;}
    public void setUrl(String url) {    this.url = url;}

    // public String getContent() {    return content;}
    // public void setContent(String content) {    this.content = content;}

    public String toString() {
        return "Article:\n" +
                "Title: %s\n".formatted(getTitle())+
                "Description: %s\n".formatted(getDescription())+
                "Image url: %s\n".formatted(getImgUrl())+
                "url: %s\n".formatted(getUrl());
                // "Content: %s\n".formatted(getContent());
    }
}
