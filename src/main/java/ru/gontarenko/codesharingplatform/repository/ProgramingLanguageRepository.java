package ru.gontarenko.codesharingplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gontarenko.codesharingplatform.enitity.ProgrammingLanguage;

public interface ProgramingLanguageRepository extends JpaRepository<ProgrammingLanguage, Short> {

}
