package in.mrasif.dev.mvvmdemo.dialogs;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.util.Log;
import android.view.LayoutInflater;

import in.mrasif.dev.mvvmdemo.R;
import in.mrasif.dev.mvvmdemo.databinding.DialogAddEditNoteBinding;
import in.mrasif.dev.mvvmdemo.models.Note;
import in.mrasif.dev.mvvmdemo.viewmodels.MainViewModel;

public class AddEditNoteDialog extends BottomSheetDialog {
    private static final String TAG = "AddEditNoteDialog";
    private DialogAddEditNoteBinding binding;
    private Note note;
    public AddEditNoteDialog(@NonNull Context context, MainViewModel model) {
        super(context);
        binding= DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.dialog_add_edit_note,null,false);
        setContentView(binding.getRoot());
        note=new Note();
        binding.setNote(note);
        binding.setIsNew(true);

        binding.btnSave.setOnClickListener(view -> {
            Log.d(TAG, "AddEditNoteDialog: "+note);
            dismiss();
            model.addNewValue(note);
        });
    }

    public AddEditNoteDialog(@NonNull Context context, MainViewModel model, Note note) {
        super(context);
        binding= DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.dialog_add_edit_note,null,false);
        setContentView(binding.getRoot());
        this.note=note;
        binding.setNote(note);
        binding.setIsNew(false);

        binding.btnSave.setOnClickListener(view -> {
            Log.d(TAG, "AddEditNoteDialog: "+this.note);
            dismiss();
            model.updateOldValue(this.note);
        });
    }

    @Override
    public void show() {
        super.show();
    }
}
