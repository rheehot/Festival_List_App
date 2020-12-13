package org.techtown.festival;

public class Review {

    private String id;
    private String title;
    private String content;
    private String star;

    public Review(){

    }

    public Review(String id, String title, String content, String star){
        this.id = id;
        this.title = title;
        this.content = content;
        this.star = star;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", star='" + star + '\'' +
                '}';
    }
}
