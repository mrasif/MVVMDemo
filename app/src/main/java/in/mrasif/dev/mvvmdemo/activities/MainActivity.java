package in.mrasif.dev.mvvmdemo.activities;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import in.mrasif.dev.mvvmdemo.R;
import in.mrasif.dev.mvvmdemo.adapters.NoteAdapter;
import in.mrasif.dev.mvvmdemo.databinding.ActivityMainBinding;
import in.mrasif.dev.mvvmdemo.dialogs.AddEditNoteDialog;
import in.mrasif.dev.mvvmdemo.viewmodels.MainViewModel;

public class MainActivity extends AppCompatActivity {


    private NoteAdapter adapter;
    private ProgressDialog pdIsAdding;
    private AddEditNoteDialog addEditNoteDialog;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainViewModel model= ViewModelProviders.of(this).get(MainViewModel.class);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        adapter=new NoteAdapter(this, model);
        binding.setAdapter(adapter);

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

        binding.fabAdd.setOnClickListener(view -> {
            addEditNoteDialog =new AddEditNoteDialog(this,model);
            addEditNoteDialog.show();
        });
    }
}
