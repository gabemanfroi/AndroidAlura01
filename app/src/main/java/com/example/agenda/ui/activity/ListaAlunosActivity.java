package com.example.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.model.Aluno;
import com.example.agenda.ui.ListaAlunosView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.agenda.ui.activity.ConstanteActivities.CHAVE_ALUNO;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Alunos";
    private final ListaAlunosView listaAlunosView = new ListaAlunosView(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        configuraBotaoAdicionarAluno();
        configuraListaDeAlunos();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull final MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_alunos_menu_remover) {
            listaAlunosView.confirmaRemocao(item);
        }
        return super.onContextItemSelected(item);
    }

    private void configuraBotaoAdicionarAluno() {
        FloatingActionButton botaoAdicionaAluno =
                findViewById(R.id.activity_lista_alunos_fab_novo_aluno);

        botaoAdicionaAluno.setOnClickListener(v -> abreFormularioModoAdicao());
    }

    private void abreFormularioModoAdicao() {
        startActivity(new Intent(ListaAlunosActivity.this
                , FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaAlunosView.atualizaAlunos();
    }

    private void configuraListaDeAlunos() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);
        listaAlunosView.configuraAdapter(listaDeAlunos);
        configuraListenerOnClick(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);
    }

    private void abreFormularioModoEdicao(Aluno aluno) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this,
                FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(vaiParaFormularioActivity);
    }

    private void configuraListenerOnClick(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener((parent, view, position, id) -> {
            Aluno alunoEscolhido = (Aluno) parent.getItemAtPosition(position);
            abreFormularioModoEdicao(alunoEscolhido);
        });
    }

}