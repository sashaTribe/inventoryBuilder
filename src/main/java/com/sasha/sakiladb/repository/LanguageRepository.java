package com.sasha.sakiladb.repository;

import com.sasha.sakiladb.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Byte > {
}
