package ktmt.rssreader.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ktmt.rssreader.Data.NewsItem;
import ktmt.rssreader.R;

public class ListRssNewsAdapter extends RecyclerView.Adapter<ListRssNewsAdapter.BaseHolder> {

    private List<NewsItem> newsItems = new ArrayList<>();

    public ListRssNewsAdapter(List<NewsItem> newsItems) {
        this.newsItems = newsItems;
    }

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChildHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.child_rss_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    class ChildHolder extends BaseHolder {

        @BindView(R.id.tvTitle)
        TextView tvTitle;
        ChildHolder(View itemView) {
            super(itemView);
        }

        @Override
        void onBindingData(int position) {
            tvTitle.setText(newsItems.get(position).title);
        }
    }

    abstract class BaseHolder extends RecyclerView.ViewHolder{
        BaseHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void bind(int position) {
            onBindingData(position);
        }

        abstract void onBindingData(int position);
    }
}
