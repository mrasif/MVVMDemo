package in.mrasif.dev.mvvmdemo.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import in.mrasif.dev.mvvmdemo.R;
import in.mrasif.dev.mvvmdemo.databinding.ItemNoteBinding;
import in.mrasif.dev.mvvmdemo.models.Note;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private Context context;
    private List<Note> notes;

    public NoteAdapter(Context context) {
        this.context=context;
        this.notes = new ArrayList<>();
    }

    public void update(List<Note> notes){
        this.notes=notes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.item_note,null,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note=notes.get(position);
        holder.binding.setNote(note);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemNoteBinding binding;
        public ViewHolder(ItemNoteBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
