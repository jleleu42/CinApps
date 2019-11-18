package com.example.cinapps;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class FilmListAdapter extends RecyclerView.Adapter<FilmListAdapter.FilmViewHolder>{
    private Cursor mCursor;
    private Context mContext;


    public FilmListAdapter(Context context, Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;
    }

    @Override
    public FilmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get the RecyclerView item layout
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.film_item, parent, false);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FilmViewHolder holder, int position) {
        // Move the mCursor to the position of the item to be displayed
        if (!mCursor.moveToPosition(position))
            return; // bail if returned null

        // Update valeur
        long id = mCursor.getLong(mCursor.getColumnIndex(FilmDesc.FilmDescEntry._ID));
        String title = mCursor.getString(mCursor.getColumnIndex(FilmDesc.FilmDescEntry.COLUMN_TITLE));
        String date = mCursor.getString(mCursor.getColumnIndex(FilmDesc.FilmDescEntry.COLUMN_DATE));
        int scenario = mCursor.getInt(mCursor.getColumnIndex(FilmDesc.FilmDescEntry.COLUMN_SCENARIO));
        int realisation = mCursor.getInt(mCursor.getColumnIndex(FilmDesc.FilmDescEntry.COLUMN_REALISATION));
        int musique = mCursor.getInt(mCursor.getColumnIndex(FilmDesc.FilmDescEntry.COLUMN_MUSIQUE));
        String description = mCursor.getString(mCursor.getColumnIndex(FilmDesc.FilmDescEntry.COLUMN_DESCRIPTION));


        // affichage
        holder.itemView.setTag(id);
        holder.itemTitle.setText(title);
        holder.itemDate.setText(date);
        holder.itemScenario.setText(""+scenario);
        holder.itemRealisation.setText(""+realisation);
        holder.itemMusique.setText(""+musique);
        holder.itemDesc.setText(description);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }


    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {
            this.notifyDataSetChanged();
        }
    }

    class FilmViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        TextView itemDate;
        TextView itemScenario;
        TextView itemRealisation;
        TextView itemMusique;
        TextView itemDesc;

        public FilmViewHolder(View itemView) {
            super(itemView);
            itemTitle = (TextView) itemView.findViewById(R.id.tv_item_title);
            itemDate = (TextView) itemView.findViewById(R.id.tv_item_date);
            itemScenario = (TextView) itemView.findViewById(R.id.tv_item_scenario);
            itemRealisation = (TextView) itemView.findViewById(R.id.tv_item_realisation);
            itemMusique = (TextView) itemView.findViewById(R.id.tv_item_musique);
            itemDesc = (TextView) itemView.findViewById(R.id.tv_item_desc);
        }
    }
}
