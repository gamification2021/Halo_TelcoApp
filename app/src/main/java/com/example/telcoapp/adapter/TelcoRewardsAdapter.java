package com.example.telcoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.telcoapp.R;
import com.example.telcoapp.TelcoActivity;
import com.example.telcoapp.fragment.RewardsSingleBottomSheet;
import com.example.telcoapp.model.Reward;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TelcoRewardsAdapter extends RecyclerView.Adapter<TelcoRewardsAdapter.ViewHolder> {

    private TelcoActivity context;
    private LayoutInflater inflater;
    private ArrayList<Reward> rewards;

    public TelcoRewardsAdapter(TelcoActivity context, ArrayList<Reward> rewards) {
        this.context = context;
        this.rewards = rewards;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(R.layout.item_telco_rewards, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reward reward = rewards.get(position);
        holder.title.setText(reward.getProgram());
        holder.descrition.setText(reward.getDescription());
        holder.itemView.setOnClickListener(view -> {
            RewardsSingleBottomSheet rewardsSingleBottomSheet = new RewardsSingleBottomSheet();
            rewardsSingleBottomSheet.setRewardData(rewards.get(position));
            rewardsSingleBottomSheet.show(context.getSupportFragmentManager(), rewardsSingleBottomSheet.getTag());
        });
    }

    @Override
    public int getItemCount() {
        return rewards.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.descrition)
        TextView descrition;
        ViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
