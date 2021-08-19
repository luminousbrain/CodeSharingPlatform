package ru.gontarenko.code.sharing.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gontarenko.code.sharing.platform.enitity.ProgrammingLanguage;

public interface ProgramingLanguageRepository extends JpaRepository<ProgrammingLanguage, Short> {

}
