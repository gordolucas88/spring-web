package br.com.symbiosys.regescweb.repositories;

import br.com.symbiosys.regescweb.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {


}
