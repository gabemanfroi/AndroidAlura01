package com.example.agenda;

import android.app.Application;

import com.example.agenda.dao.AlunoDao;
import com.example.agenda.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosDeTeste();
    }

    private void criaAlunosDeTeste() {
        AlunoDao alunoDao = new AlunoDao();
        for (int i = 0; i < 20; i++) {
            alunoDao.salva(new Aluno("Aluno" + i, "123456", "email"));
        }
    }
}
