package in.mrasif.dev.mvvmdemo.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.List;

import in.mrasif.dev.mvvmdemo.models.Note;

public class MainViewModel extends ViewModel {
    MutableLiveData<List<Note>> notes;
    MutableLiveData<Boolean> isAdding;

    public LiveData<List<Note>> getNotes(){
        if (notes==null){
            notes=new MutableLiveData<>();
            notes.setValue(new ArrayList<Note>());
            loadData();
        }
        return notes;
    }

    public LiveData<Boolean> isAdding(){
        if (isAdding==null){
            isAdding=new MutableLiveData<>();
            isAdding.setValue(false);
        }
        return isAdding;
    }

    public void addNewValue(Note note){
        isAdding.postValue(true);
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<Note> noteList=notes.getValue();
                noteList.add(note);
                notes.postValue(noteList);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                isAdding.postValue(false);
            }
        }.execute();
    }

    public void updateOldValue(Note note){
        isAdding.postValue(true);
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<Note> noteList=notes.getValue();
                List<Note> noteList1=Stream.of(noteList).map(note1 -> note1.equals(note)?note:note1).collect(Collectors.toList());
                notes.postValue(noteList1);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                isAdding.postValue(false);
            }
        }.execute();
    }

    private void loadData() {
        Note note=new Note();
        note.setId(1);
        note.setTitle("Default title");
        note.setDescription("Default description");
        notes.getValue().add(note);
    }
}
