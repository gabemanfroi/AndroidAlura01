package com.example.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDao;
import com.example.agenda.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.agenda.ui.activity.ConstanteActivities.CHAVE_ALUNO;

public class ListaAlunosActivity extends AppCompatActivity {


    public static final String TITULO_APPBAR = "Lista de Alunos";


    private final AlunoDao alunoDao = new AlunoDao();
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(TITULO_APPBAR);
        setContentView(R.layout.activity_lista_alunos);
        configuraBotaoAdicionarAluno();
        configuraListaDeAlunos(alunoDao);
        for (int i = 0; i < 20; i++) {
            alunoDao.salva(new Aluno("Aluno" + i, "123456", "email"));
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_alunos_menu_remover) {
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Aluno aluno = adapter.getItem(menuInfo.position);
            remove(aluno);
        }
        return super.onContextItemSelected(item);
    }

    private void remove(Aluno aluno) {
        alunoDao.remove(aluno);
        adapter.remove(aluno);

    }

    private void configuraBotaoAdicionarAluno() {
        FloatingActionButton botaoAdicionaAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);

        botaoAdicionaAluno.setOnClickListener(v -> {
            abreFormularioModoAdicao();
        });
    }

    private void abreFormularioModoAdicao() {
        startActivity(new Intent(ListaAlunosActivity.this
                , FormularioAlunoActivity.class));
    }

    private void abreFormularioModoEdicao(Aluno aluno) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(vaiParaFormularioActivity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaAlunos();
    }

    private void atualizaAlunos() {
        adapter.clear();
        adapter.addAll(alunoDao.todos());
    }

    private void configuraListaDeAlunos(AlunoDao alunoDao) {

        ListView listaDeAlunos = (ListView) findViewById(R.id.activity_lista_alunos_listview);
        configuraAdapter(listaDeAlunos);
        configuraListenerOnClick(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);
    }


    private void configuraListenerOnClick(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener((parent, view, position, id) -> {
            Aluno alunoEscolhido = (Aluno) parent.getItemAtPosition(position);
            abreFormularioModoEdicao(alunoEscolhido);
        });
    }

    private void configuraAdapter(ListView listaDeAlunos) {
        adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1
        );
        listaDeAlunos.setAdapter(adapter);
    }
}