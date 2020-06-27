package com.lonelybanana.lblibrary.log;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lonelybanana.lblibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 将log显示在界面上
 */
public class LBViewPrinter implements LBLogPrinter {
    private RecyclerView recyclerView;
    private LogAdapter adapter;
    private LBViewPrinterProvider viewProvider;

    /**
     * 获取viewProvider，通过viewProvider可以操控log视图的展示和隐藏
     *
     * @return viewProvider
     */
    public LBViewPrinterProvider getViewProvider() {
        return viewProvider;
    }

    public LBViewPrinter(Activity activity) {
        FrameLayout rootView = activity.findViewById(android.R.id.content);
        recyclerView = new RecyclerView(activity);
        adapter = new LogAdapter(LayoutInflater.from(recyclerView.getContext()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        viewProvider = new LBViewPrinterProvider(rootView, recyclerView);

    }

    @Override
    public void print(@NonNull LBLogConfig config, int level, String tag, @NonNull String printString) {
        //将log展示添加到recycleView
        adapter.addItem(new LBLogMo(System.currentTimeMillis(), level, tag, printString));
        //滚动到对应的位置
        recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);

    }

    private static class LogAdapter extends RecyclerView.Adapter<LogViewHolder> {
        private LayoutInflater inflater;
        private List<LBLogMo> logs = new ArrayList<>();

        private LogAdapter(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        void addItem(LBLogMo logItem) {
            logs.add(logItem);
            notifyItemInserted(logs.size() - 1);
        }

        @NonNull
        @Override
        public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View itemView = inflater.inflate(R.layout.lblog_item, parent, false);
            return new LogViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
            LBLogMo logItem = logs.get(position);
            int color = getHighlightColor(logItem.level);
            holder.tagView.setTextColor(color);
            holder.messageView.setTextColor(color);

            holder.tagView.setText(logItem.getFlattened());
            holder.messageView.setText(logItem.log);
        }

        /**
         * 根据log级别获取不同的高亮颜色
         *
         * @param logLevel
         * @return
         */
        private int getHighlightColor(int logLevel) {
            int highLight;
            switch (logLevel) {
                case LBLogType.V:
                    highLight = 0xffbbbbbb;
                    break;
                case LBLogType.D:
                    highLight = 0xffffffff;
                    break;
                case LBLogType.I:
                    highLight = 0xff6a8759;
                    break;
                case LBLogType.W:
                    highLight = 0xffbbb529;
                    break;
                case LBLogType.E:
                    highLight = 0xffff6b68;
                    break;
                default:
                    highLight = 0xffffff00;
                    break;

            }
            return highLight;
        }

        @Override
        public int getItemCount() {
            return logs.size();
        }
    }

    private static class LogViewHolder extends RecyclerView.ViewHolder {
        TextView tagView;
        TextView messageView;

        public LogViewHolder(@NonNull View itemView) {
            super(itemView);
            tagView = itemView.findViewById(R.id.tag);
            messageView = itemView.findViewById(R.id.message);
        }
    }
}
