package com.example.vestibular.repository;

import com.example.vestibular.model.enums.Disciplina;
import com.example.vestibular.model.Questao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestaoRepository extends JpaRepository<Questao,Long> {

//    @Query("FROM Questao q " +
//            "WHERE q.enunciado LIKE %:searchTerm% " +
//            "OR q.alternativas.a LIKE %:searchTerm% " +
//            "OR q.alternativas.b LIKE %:searchTerm% " +
//            "OR q.alternativas.c LIKE %:searchTerm% " +
//            "OR q.alternativas.d LIKE %:searchTerm%")
    @Query("FROM Questao b " +
            "WHERE :searchTerm is null or LOWER(b.enunciado) like %:searchTerm% " +
            "OR cast(b.id as string) = :searchTerm")
    Page<Questao> searchPage(
            @Param("searchTerm") String searchTerm, Pageable pageable);

    @Query("SELECT q FROM Questao q " +
            "JOIN q.conteudoQuestao cq " +
            "JOIN q.prova p " +
            "WHERE cq.disciplina = :disciplina " +
            "AND cq.assunto = :assunto " +
            "AND cq.dificuldade = :dificuldade " +
            "AND p.banca = :banca " +
            "AND p.ano = :ano")
    List<Questao> buscarPorDisciplinaAssuntoDificuldadeBancaAno(
            @Param("disciplina") Disciplina disciplina,
            @Param("assunto") String assunto,
            @Param("dificuldade") String dificuldade,
            @Param("banca") String banca,
            @Param("ano") String ano);
}
