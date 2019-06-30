package com.pugerp.androidquiz.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pugerp.androidquiz.R;
import com.pugerp.androidquiz.model.Movie;
import com.pugerp.androidquiz.model.SearchItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> implements Filterable {

    private MovieAdapter.MovieAdapterInterface<SearchItem> listener;
    private List<SearchItem> mData;
    private List<SearchItem> mFilter;
    private Context context;

    public MovieAdapter(Context context, List<SearchItem> data, MovieAdapter.MovieAdapterInterface<SearchItem> listener) {
        this.mData = data;
        this.mFilter = data;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.i_movie, viewGroup, false);
        return new MovieAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapterViewHolder holder, int i) {
        holder.onBind();
    }

    @Override
    public int getItemCount() {
        return mFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String s = constraint.toString();
                if(s.isEmpty()){
                    mFilter = mData;
                }else{
                    List<SearchItem> filteredList = new ArrayList<>();
                    for(SearchItem data : mData){
                        if(data.getTitle().toLowerCase().contains(s) ||
                                data.getType().toLowerCase().contains(s) ||
                                data.getYear().toLowerCase().contains(s)){
                            filteredList.add(data);
                        }
                    }
                    mFilter = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mFilter = (List<SearchItem>) results.values;
                notifyDataSetChanged();
            }
        };
    }


    class MovieAdapterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_poster)
        ImageView ivPoster;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_year)
        TextView tvYear;
        @BindView(R.id.tv_type)
        TextView tvType;

        public MovieAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            Glide.with(itemView)
                    .load(mFilter.get(getAdapterPosition()).getPoster())
                    .error(R.drawable.ic_launcher_background)
                    .placeholder(R.drawable.ic_launcher_background)
                    .fitCenter()
                    .into(ivPoster);
            tvTitle.setText(mFilter.get(getAdapterPosition()).getTitle());
            tvYear.setText(mFilter.get(getAdapterPosition()).getYear());
            tvType.setText(mFilter.get(getAdapterPosition()).getType());
        }

        @OnClick(R.id.wrap_item)
        public void onViewClicked() {
            listener.onClickListener(mFilter.get(getAdapterPosition()));
        }
    }

    public interface MovieAdapterInterface<T>{
        void onClickListener(T movie);
    }


}
