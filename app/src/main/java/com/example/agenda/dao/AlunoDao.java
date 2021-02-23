package com.example.agenda.dao;

import com.example.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDao {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeId = 1;

    public void salva(Aluno aluno) {

        aluno.setId(contadorDeId);
        alunos.add(aluno);
        atualizaId();

    }

    private void atualizaId() {
        contadorDeId++;
    }

    public void edita(Aluno aluno) {
        Aluno alunoEncontrado = buscaAlunoPorId(aluno);
        if (alunoEncontrado != null) {
            int posicaoDoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoDoAluno, aluno);
        }
    }

    private Aluno buscaAlunoPorId(Aluno aluno) {
        for (Aluno a : alunos) {
            if (a.getId() == aluno.getId()) {
                return a;
            }
        }
        return null;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    public void remove(Aluno aluno) {

        Aluno alunoDevolvido = buscaAlunoPorId(aluno);
        if (alunoDevolvido != null) {
            alunos.remove(aluno);
        }

    }
}
