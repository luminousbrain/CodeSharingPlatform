package ru.gontarenko.codesharingplatform.enitity;

import javax.persistence.*;

@Entity
@Table(name = "languages_table")
public class ProgrammingLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lang")
    private Short id;

    @Column(name = "lang_name")
    private String name;

    public ProgrammingLanguage() {}

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
