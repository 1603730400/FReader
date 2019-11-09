package com.feng.freader.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.feng.freader.R;
import com.feng.freader.entity.data.NovelSourceData;
import com.feng.freader.util.RegexUtil;

import java.util.List;

/**
 * @author Feng Zhaohao
 * Created on 2019/11/9
 */
public class NovelSourceAdapter extends
        RecyclerView.Adapter<NovelSourceAdapter.NovelSourceViewHolder> {

    private static final String TAG = "NovelSourceAdapter";

    private Context mContext;
    private List<NovelSourceData> mNovelSourceDataList;

    public NovelSourceAdapter(Context mContext, List<NovelSourceData> mNovelSourceDataList) {
        this.mContext = mContext;
        this.mNovelSourceDataList = mNovelSourceDataList;
    }

    @NonNull
    @Override
    public NovelSourceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new NovelSourceViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_novel_source, null));
    }

    @Override
    public void onBindViewHolder(@NonNull NovelSourceViewHolder novelSourceViewHolder, int i) {
        Glide.with(mContext)
                .load(mNovelSourceDataList.get(i).getCover())
                .apply(new RequestOptions().placeholder(R.drawable.cover_place_holder))
                .into(novelSourceViewHolder.cover);
        novelSourceViewHolder.name.setText(mNovelSourceDataList.get(i).getName());
        // 作者可能为空
        String str = mNovelSourceDataList.get(i).getAuthor();
        String author = (str == null || str.equals(""))? "未知" : str;
        novelSourceViewHolder.author.setText(author);

        novelSourceViewHolder.introduce.setText(mNovelSourceDataList.get(i).getIntroduce());
        novelSourceViewHolder.webSite.setText(mNovelSourceDataList.get(i).getUrl());
    }

    @Override
    public int getItemCount() {
//        Log.d(TAG, "getItemCount: size = " + mNovelSourceDataList.size());
        return mNovelSourceDataList.size();
    }

    class NovelSourceViewHolder extends RecyclerView.ViewHolder {
        ImageView cover;
        TextView name;
        TextView author;
        TextView introduce;
        TextView webSite;

        public NovelSourceViewHolder(@NonNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.iv_item_novel_source_cover);
            name = itemView.findViewById(R.id.tv_item_novel_source_name);
            author = itemView.findViewById(R.id.tv_item_novel_source_author);
            introduce = itemView.findViewById(R.id.tv_item_novel_source_introduce);
            webSite = itemView.findViewById(R.id.tv_item_novel_source_web_site);
        }
    }
}
