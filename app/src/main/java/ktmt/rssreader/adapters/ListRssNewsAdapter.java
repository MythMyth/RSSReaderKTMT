package ktmt.rssreader.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ktmt.rssreader.Data.NewsItem;
import ktmt.rssreader.GlideModule.GlideApp;
import ktmt.rssreader.Helper.Helper;
import ktmt.rssreader.R;

public class ListRssNewsAdapter extends RecyclerView.Adapter<ListRssNewsAdapter.BaseHolder> {

    private List<NewsItem> newsItems = new ArrayList<>();

    public ListRssNewsAdapter() {
    }

    public interface onClickItemListener{
        void onClickItem(int position);
    }

    private onClickItemListener onClickItemListener;

    public void setOnClickItemListener(ListRssNewsAdapter.onClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    public List<NewsItem> getNewsItems() {
        return newsItems;
    }

    public void setNewsItems(List<NewsItem> newsItems) {
        this.newsItems = newsItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChildHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.child_rss_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    class ChildHolder extends BaseHolder{

        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.imv)
        ImageView imageView;
        @BindView(R.id.tvTime)
        TextView tvTime;
        @BindView(R.id.tvDescription)
        TextView tvDescription;

        ChildHolder(View itemView) {
            super(itemView);
        }

        @Override
        void onBindingData(int position) {
            tvTitle.setText(newsItems.get(position).title);
            tvTime.setText(Helper.changeDateToString(newsItems.get(position).time));
            tvDescription.setText(newsItems.get(position).des);
            GlideApp.with(itemView.getContext()).load(newsItems.get(position).getImageLink()).into(imageView);
        }
    }

    abstract class BaseHolder extends RecyclerView.ViewHolder {
        BaseHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickItemListener.onClickItem(getAdapterPosition());
                }
            });
        }

        void bind(int position) {
            onBindingData(position);
        }

        abstract void onBindingData(int position);
    }
}
