package com.mybrary;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LibraryFragment extends Fragment {

    private View view;

    private ArrayList<Book> books = new ArrayList<>();

    private ListView bookList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_library, null);

        // loads books in the initial time
        loadList();

        final SwipeRefreshLayout pullToRefresh = (SwipeRefreshLayout) view.findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadList();
                pullToRefresh.setRefreshing(false);
            }
        });

        return view;
    }

    private void loadList() {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference("books");

        // fragment uses getActivity() instead of this
        final bookArrayAdapter adapter = new bookArrayAdapter(getActivity(), 0, books, 0);
        bookList = (ListView) view.findViewById(R.id.bookList);
        bookList.setAdapter(adapter);

        // clear ArrayList before adding new books to eliminate duplicates
        //books.clear();
        adapter.notifyDataSetChanged();

        ValueEventListener bookListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    Book book = (Book) dataSnapshot.getValue(Book.class);

//                    String title = (String) dataSnapshot.child("title").getValue();
//                    String author = (String) dataSnapshot.child("author").getValue();
//                    String isbn = (String) dataSnapshot.child("isbn").getValue();
//                    int ownerID = (int) dataSnapshot.child("ownerID").getValue();
//                    int borrowID = (int) dataSnapshot.child("borrowID").getValue();
//                    Book book = new Book(title, author, isbn, 0, 0);
                    books.add(book);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), "dataSnapshot was null", Toast.LENGTH_SHORT);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDatabase.addListenerForSingleValueEvent(bookListener);
    }
}
