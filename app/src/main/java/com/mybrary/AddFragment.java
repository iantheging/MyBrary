package com.mybrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFragment extends Fragment {

    private View view;

    private EditText title_box;
    private EditText author_box;
    private Button addBook;

    private String title;
    private String author;
    private String isbn;
    private int ownerID;
    private int borrowID;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add, null);

        title_box = (EditText) view.findViewById(R.id.title);
        author_box = (EditText) view.findViewById(R.id.author);
        addBook = (Button) view.findViewById(R.id.addBook);

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = title_box.getText().toString();
                author = author_box.getText().toString();
                Book book = new Book(title, author, "1000000", 22, 54);
                addNewBook(book);
            }
        });

        return view;
    }

    private void addNewBook(Book book) {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("books").child(book.getTitle()).setValue(book);
    }
}
