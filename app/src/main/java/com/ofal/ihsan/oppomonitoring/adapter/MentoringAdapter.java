package com.ofal.ihsan.oppomonitoring.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.ofal.ihsan.oppomonitoring.AppMentoring;
import com.ofal.ihsan.oppomonitoring.R;
import com.ofal.ihsan.oppomonitoring.adapter.listener.ItemClickListener;
import com.ofal.ihsan.oppomonitoring.adapter.model.MonitorModel;
import com.ofal.ihsan.oppomonitoring.view.activity.DetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 0878253827096
 * on 12/05/2018
 * ------------------------------
 * This class for
 */
public class MentoringAdapter extends RecyclerView.Adapter<MentoringAdapter.ViewHolder> {

    private List<MonitorModel> monitorModels;
    private Context mContext;

    public MentoringAdapter(List<MonitorModel> monitorModels, Context mContext) {
        this.monitorModels = monitorModels;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MonitorModel monitorModel = monitorModels.get(position);
        holder.bindTo(monitorModel);
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("nik", monitorModel.getNik());
                intent.putExtra("nama", monitorModel.getNama());
                intent.putExtra("alamat", monitorModel.getAlamat());
                intent.putExtra("lat", monitorModel.getLat());
                intent.putExtra("long", monitorModel.getLng());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return monitorModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemClickListener itemClickListener;
        @BindView(R.id.txt_nik)
        TextView txtNik;
        @BindView(R.id.txt_nama)
        TextView txtNama;
        @BindView(R.id.txt_tanggal)
        TextView txtTanggal;
        @BindView(R.id.txt_alamat)
        TextView txtAlamat;
        private MonitorModel monitorModel;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @SuppressLint("SetTextI18n")
        void bindTo(MonitorModel monitorModel) {
            txtNik.setText("NIK :" + monitorModel.getNik());
            txtNama.setText("Nama " + monitorModel.getNama());
            txtTanggal.setText("Hari :" + monitorModel.getHari());
            txtAlamat.setText("Alamat :" + monitorModel.getAlamat());
            this.monitorModel = monitorModel;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v);
        }
    }
}
