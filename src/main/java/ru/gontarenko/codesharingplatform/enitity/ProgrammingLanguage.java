package ru.gontarenko.codesharingplatform.enitity;

import javax.persistence.*;

@Entity
@Table(name = "languages_table")
public class ProgrammingLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lang")
    private Long id;

    @Column(name = "lang_name")
    private String name;

    public ProgrammingLanguage() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
