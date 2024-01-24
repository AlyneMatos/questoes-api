package com.example.vestibular.repository;

import com.example.vestibular.model.Prova;
import com.example.vestibular.model.Questao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvaRepository extends JpaRepository<Prova,Long> {

    @Query("SELECT p FROM Prova p " +
            "JOIN p.banca b " +
            "JOIN p.ano a "
            )
    List<Prova> buscarPorBancaAno(
            @Param("banca") String banca,
            @Param("ano") String ano);
}
