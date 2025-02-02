package ru.gontarenko.code.sharing.platform.enitity;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.gontarenko.code.sharing.platform.security.AccountStatus;
import ru.gontarenko.code.sharing.platform.security.AccountType;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user_table")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "You pass incorrect email")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "Password should not be empty")
    @Length(min = 5, message = "Password must be greater than 5 characters")
    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    @Length(max = 25, message = "nickname length must be no more than 25 characters")
    private String nickname;

    @Column(name = "description")
    @Length(max = 500, message = "description length must be no more than 500 characters")
    private String description;

    @Column(name = "location")
    @Length(max = 50, message = "location length must be no more than 50 characters")
    private String location;

    @Column(name = "account_type")
    @Enumerated(EnumType.ORDINAL)
    private AccountType accountType;

    @Column(name = "status")
    @Enumerated(value = EnumType.ORDINAL)
    private AccountStatus accountStatus;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<CodeSnippet> codeSnippets;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "favorite_user_lang",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_lang")
    )
    private List<ProgrammingLanguage> programmingLanguages;

    public User() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.accountType.getAuthorities();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountStatus.equals(AccountStatus.ACTIVE);
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountStatus.equals(AccountStatus.ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.accountStatus.equals(AccountStatus.ACTIVE);
    }

    @Override
    public boolean isEnabled() {
        return this.accountStatus.equals(AccountStatus.ACTIVE);
    }

    public void setDefaultSettingsForNewUser(String encodedPassword) {
        this.setPassword(encodedPassword);
        this.setAccountStatus(AccountStatus.ACTIVE);
        this.setAccountType(AccountType.FREE);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", accountType=" + accountType +
                ", accountStatus=" + accountStatus +
                '}';
    }

    public boolean getPrivacyStatus() {
        String accType = this.getAccountType().toString();
        return accType.equals("PRO") || accType.equals("ADMIN");
    }

    // getters and setters (можно убрать с помощью Lombok)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
       if (nickname == null || nickname.trim().equals("")) {
           return "No nickname";
       } else {
           return nickname;
       }
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public List<CodeSnippet> getCodeSnippets() {
        return codeSnippets;
    }

    public void setCodeSnippets(List<CodeSnippet> codeSnippets) {
        this.codeSnippets = codeSnippets;
    }

    public List<ProgrammingLanguage> getProgrammingLanguages() {
        return programmingLanguages;
    }

    public void setProgrammingLanguages(List<ProgrammingLanguage> programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }
}