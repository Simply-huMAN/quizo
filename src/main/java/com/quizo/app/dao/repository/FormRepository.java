package com.quizo.app.dao.repository;

import com.quizo.app.dao.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FormRepository extends JpaRepository<Form, UUID> {

    @Query("SELECT f FROM Form f WHERE f.id = :id")
    int saveForm(Form form);

    Form getById(UUID id);
}
