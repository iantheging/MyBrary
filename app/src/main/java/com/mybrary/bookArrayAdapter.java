package com.mybrary;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class bookArrayAdapter extends BaseAdapter {

    private Context context;
    private List<Book> books;
    private int ID;

    public bookArrayAdapter(Context context, int resource, ArrayList<Book> objects, int ID) {
        this.context = context;
        this.books = objects;
        this.ID = ID;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Book book = books.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.book_list, null);

        TextView title = view.findViewById(R.id.title);
        TextView author = view.findViewById(R.id.author);
        TextView owner = view.findViewById(R.id.ownership_status);

        title.setText(String.valueOf(book.getTitle()));
        author.setText(String.valueOf(book.getAuthor()));

        // Book status signal
//        if (book.getOwnerID() == ID && book.getBorrowID() == ID) {
//            owner.setBackgroundResource(R.drawable.owner_circle);
//        } else if (book.getOwnerID() != ID && book.getBorrowID() == ID) {
//            owner.setBackgroundResource(R.drawable.borrow_circle);
//        } else if (book.getBorrowID() != ID && book.getOwnerID() == ID) {
//            owner.setBackgroundResource(R.drawable.lend_circle);
//        } else {
//            owner.setBackgroundResource(R.drawable.avaliable_circle);
//        }

        return view;
    }
}

