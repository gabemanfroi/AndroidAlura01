package com.example.agenda.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setTitle("Lista de Alunos");

        setContentView(R.layout.activity_lista_alunos);

        FloatingActionButton botaoAdicionaAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);

        botaoAdicionaAluno.setOnClickListener(v -> {
            startActivity(new Intent(ListaAlunosActivity.this
                    , FormularioAlunoActivity.class));
        });

    }

    @Override
    protected void onResume(){
        super.onResume();
        ListView listaDeAlunos = (ListView) findViewById(R.id.activity_lista_alunos_listview);
        AlunoDao alunoDao = new AlunoDao();

        listaDeAlunos.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1,
                alunoDao.todos()
        ));
    }
}