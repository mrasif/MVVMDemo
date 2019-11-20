package in.mrasif.dev.mvvmdemo.dialogs;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.util.Log;
import android.view.LayoutInflater;

import in.mrasif.dev.mvvmdemo.R;
import in.mrasif.dev.mvvmdemo.databinding.DialogAddNoteBinding;
import in.mrasif.dev.mvvmdemo.models.Note;
import in.mrasif.dev.mvvmdemo.viewmodels.MainViewModel;

public class AddNoteDialog extends BottomSheetDialog {
    private static final String TAG = "AddNoteDialog";
    private DialogAddNoteBinding binding;
    private Note note;
    private MainViewModel model;
    public AddNoteDialog(@NonNull Context context, MainViewModel model) {
        super(context);
        binding= DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.dialog_add_note,null,false);
        this.model=model;
        setContentView(binding.getRoot());
        note=new Note();
        binding.setNote(note);

        binding.btnSave.setOnClickListener(view -> {
            Log.d(TAG, "AddNoteDialog: "+note);
            dismiss();
            model.addNewValue(note);
        });
    }

    @Override
    public void show() {
        super.show();
    }
}
