package com.example.innove.roomdatabase;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.ref.WeakReference;

/**
 * Created by abhisheksharma on 18-May-2018.
 */

public class AddNoteActivity extends AppCompatActivity {

    private EditText et_title, et_content;
    private NoteDatabse noteDatabase;
    private Note note;
    private boolean update;
    private Boolean showonly = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        noteDatabase = NoteDatabse.getInstance(AddNoteActivity.this);
        et_title = findViewById(R.id.et_title);
        et_content = findViewById(R.id.et_content);

        Button button = findViewById(R.id.but_save);
        showonly = SessionManager.getBooleanFromPreferences(AddNoteActivity.this, "showonly");
        if (showonly == null) {
            if (showonly) {
                button.setVisibility(View.GONE);
            }

        }
        if ((note = (Note) getIntent().getSerializableExtra("note")) != null) {
            getSupportActionBar().setTitle("Update Note");
            update = true;
            button.setText("Update");
            et_title.setText(note.getTitle());
            et_content.setText(note.getContent());
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (update) {
                    note.setContent(et_content.getText().toString());
                    note.setTitle(et_title.getText().toString());
                    noteDatabase.getNoteDao().update(note);
                    setResult(note, 2);
                } else {
                    note = new Note(et_content.getText().toString(), et_title.getText().toString());
                    new InsertTask(AddNoteActivity.this, note).execute();
                }
            }
        });
    }

    private void setResult(Note note, int flag) {
        setResult(flag, new Intent().putExtra("note", note));
        Intent in = new Intent(AddNoteActivity.this, MainActivity.class);
        startActivity(in);
    }

    private class InsertTask extends AsyncTask<Void, Void, Boolean> {

        private WeakReference<AddNoteActivity> activityReference;
        private Note note;

        public InsertTask(AddNoteActivity context, Note note) {
            activityReference = new WeakReference<>(context);
            this.note = note;
        }

        @Override
        protected Boolean doInBackground(Void... objs) {
//            activityReference.get().noteDatabase.getNoteDao().insert(note);
//            return true;
            long j = activityReference.get().noteDatabase.getNoteDao().insert(note);
            note.setNote_id(j);
            Log.e("ID ", "doInBackground: " + j);
            return true;
        }

        // onPostExecute runs on main thread
        @Override
        protected void onPostExecute(Boolean bool) {
            if (bool) {
                activityReference.get().setResult(note, 1);
                Log.e("result", note + "");
                activityReference.get().finish();
            }
        }
    }
}
