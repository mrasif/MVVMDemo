package in.mrasif.dev.mvvmdemo.activities;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import in.mrasif.dev.mvvmdemo.R;
import in.mrasif.dev.mvvmdemo.adapters.NoteAdapter;
import in.mrasif.dev.mvvmdemo.models.Note;
import in.mrasif.dev.mvvmdemo.viewmodels.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvList;
    private FloatingActionButton fabAdd;
    private NoteAdapter adapter;
    private ProgressDialog pdIsAdding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvList=findViewById(R.id.rvNotes);
        fabAdd=findViewById(R.id.fabAdd);
        adapter=new NoteAdapter(this);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(adapter);

        MainViewModel model= ViewModelProviders.of(this).get(MainViewModel.class);
        model.getNotes().observe(this,notes -> {
            adapter.update(notes);
        });


        pdIsAdding=new ProgressDialog(this);
        pdIsAdding.setMessage("Note adding...");

        model.isAdding().observe(this, status->{
            if (status) {
                pdIsAdding.show();
            } else {
                pdIsAdding.dismiss();
            }
        });

        fabAdd.setOnClickListener(view -> {
            Note note=new Note();
            note.setTitle("Any title");
            note.setDescription("Any description");
            model.addNewValue(note);
        });
    }
}
