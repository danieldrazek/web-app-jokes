package pl.pp.spring.jokeswebapp.model;

import java.util.List;

public class Joke extends BaseEntity{
    private String title;
    private String content;
    private List<Joke> categories;

    public Joke() {
    }

    public Joke(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Joke{" +
                "id=" + getId() +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
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

    public List<Joke> getCategories() {
        return categories;
    }

    public void setCategories(List<Joke> categories) {
        this.categories = categories;
    }

}
