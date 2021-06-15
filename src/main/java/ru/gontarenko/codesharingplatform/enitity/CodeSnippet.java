package ru.gontarenko.codesharingplatform.enitity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "code_snippets")
public class CodeSnippet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_snippet")
    private Long id;

    @Column(name = "snippet_title")
    private String title;

    @Column(name = "snippet_text")
    @NotEmpty(message = "Snippet cannot be empty")
    private String text;

    @Column(name = "hidden")
    private boolean hidden;

    @Column(name = "snippet_link")
    private String link;

    @Column(name = "date_create")
    private String dateCreate;

    @Column(name = "expiration_time") // todo sorry snippets was expired (Во view)
    private Integer expirationTime;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_lang")
    private ProgrammingLanguage programmingLanguage;

    public CodeSnippet() {}

    @Override
    public String toString() {
        return "CodeSnippet{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", hidden=" + hidden +
                ", link='" + link + '\'' +
                ", dateCreate='" + dateCreate + '\'' +
                ", expirationTime=" + expirationTime +
                ", user=" + user +
                ", programmingLanguage=" + programmingLanguage +
                '}';
    }


    // getters, setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Integer getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Integer expirationTime) {
        this.expirationTime = expirationTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ProgrammingLanguage getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }
}
