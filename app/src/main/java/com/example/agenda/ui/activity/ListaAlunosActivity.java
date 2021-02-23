package com.example.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaAlunosActivity extends AppCompatActivity {


    public static final String TITULO_APPBAR = "Lista de Alunos";

    private final AlunoDao alunoDao = new AlunoDao();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTitle(TITULO_APPBAR);
        setContentView(R.layout.activity_lista_alunos);
        configuraBotaoAdicionarAluno();
    }

    private void configuraBotaoAdicionarAluno() {
        FloatingActionButton botaoAdicionaAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);

        botaoAdicionaAluno.setOnClickListener(v -> {
            abreFormulario();
        });
    }

    private void abreFormulario() {
        startActivity(new Intent(ListaAlunosActivity.this
                , FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraListaDeAlunos(alunoDao);
    }

    private void configuraListaDeAlunos(AlunoDao alunoDao) {
        ListView listaDeAlunos = (ListView) findViewById(R.id.activity_lista_alunos_listview);

        listaDeAlunos.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1,
                alunoDao.todos()
        ));
    }
}