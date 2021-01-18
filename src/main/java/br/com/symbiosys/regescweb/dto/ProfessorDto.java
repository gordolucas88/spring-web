package br.com.symbiosys.regescweb.dto;

import br.com.symbiosys.regescweb.models.Professor;
import br.com.symbiosys.regescweb.models.StatusProfessor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

public class ProfessorDto {
    private String nome;
    private BigDecimal salario;
    private StatusProfessor status;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public StatusProfessor getStatus() {
        return status;
    }

    public void setStatus(StatusProfessor status) {
        this.status = status;
    }

    public Professor toProfessor(){
        Professor professor = new Professor();
        professor.setNome(this.nome);
        professor.setSalario(this.salario);
        professor.setStatus(this.status);

        return professor;
    }

    @Override
    public String toString() {
        return "ProfessorDto{" +
                "nome='" + nome + '\'' +
                ", salario=" + salario +
                ", status=" + status +
                '}';
    }
}
