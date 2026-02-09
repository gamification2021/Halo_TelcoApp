package com.example.telcoapp.fragment;


import static com.example.telcoapp.TelcoActivity.rewardArrayList;
import static com.example.telcoapp.TelcoActivity.totalPoints;
import static com.example.telcoapp.utils.DisplayUtils.makeBottomSheetFullScreen;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.app.pushnotification.EventManager;
import com.app.pushnotification.model.CustomEvent;
import com.app.pushnotification.model.Event;
import com.bumptech.glide.Glide;
import com.example.telcoapp.R;
import com.example.telcoapp.model.Reward;
import com.example.telcoapp.model.SpecialForYou;
import com.example.telcoapp.utils.sharedPref.PrefKeys;
import com.example.telcoapp.utils.sharedPref.PrefUtils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class RewardsSingleBottomSheet extends BottomSheetDialogFragment {


    Reward reward;

    CardView back;

    ImageView rewardsSinglePage;

    TextView title;

    TextView points;

    TextView description;

    Button redeem;
    redirectFragment redirectFragment;
    boolean isQrScanner;

    SpecialForYou specialData;

    PrefUtils prefUtils;
    public static TextView count;

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {

        }
    };

    public void setIsQrScanner(boolean isQrScanner) {
        this.isQrScanner = isQrScanner;
    }


    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.rewards_single_bottom_sheet, null);
        back = contentView.findViewById(R.id.back);

        rewardsSinglePage = contentView.findViewById(R.id.rewardsSinglePage);

        title = contentView.findViewById(R.id.title);

        points = contentView.findViewById(R.id.points);

        description = contentView.findViewById(R.id.description);

        redeem = contentView.findViewById(R.id.redeem);

        dialog.setContentView(contentView);
        dialog.setOnKeyListener(new BottomSheetBackDismissListener());
        makeBottomSheetFullScreen(getActivity(), mBottomSheetBehaviorCallback, contentView);
        setCancelable(false);
        prefUtils = PrefUtils.getInstance(getActivity());
        if(specialData == null) {
            title.setText(reward.getProgram());
            points.setText(reward.getPoints() + " Points");
            description.setText(reward.getDescription());
//        if (reward.getType().equalsIgnoreCase("telco")) {
//            Glide.with(getActivity()).load("https://static.dribbble.com/users/1061799/screenshots/4719196/gift.png").into(rewardsSinglePage);
//        } else {
            Glide.with(getActivity()).load(reward.getPicture()).into(rewardsSinglePage);
            if (reward.getType().equalsIgnoreCase("partner")) {
                rewardsSinglePage.setImageDrawable(reward.getImage());
            }

//        }
        } else {
            title.setText(specialData.getTitle());
            points.setText(specialData.getAmount());
            description.setText(specialData.getMainOffer());
            Glide.with(getActivity()).load(specialData.getImage()).into(rewardsSinglePage);
            redeem.setText("Buy");
        }
        back.setOnClickListener(view -> dismiss());
        redeem.setOnClickListener(view -> {
            if(specialData == null) {
                rewardArrayList.add(reward);
                if (count != null)
                    count.setText(String.valueOf(rewardArrayList.size()));
                totalPoints = totalPoints + reward.getPoints();
                if (redirectFragment != null) {
                    redirectFragment.onRewardAdded();
                }

                //TODO:UnComment
                Event event = new Event();
                ArrayList<CustomEvent> customEvents = new ArrayList<>(
                        Arrays.asList(
                                new CustomEvent("reward_name", reward.getProgram()),
                                new CustomEvent("reward_description", reward.getDescription()),
                                new CustomEvent("reward_point", reward.getPoints() + "")
                        )
                );
                event.setCustomEvent(customEvents);
                EventManager.sendEvent("add_to_cart", prefUtils.getStringValue(PrefKeys.MSISDN, "0"), event);

//        int totalPoints = prefUtils.getIntValue(  PrefKeys.TOTAL_POINTS, 0);
//        if(totalPoints > reward.getPoints()) {
//            totalPoints = totalPoints - reward.getPoints();
//            prefUtils.setValue(PrefKeys.TOTAL_POINTS, totalPoints);
//            PointsTransactionActivity.transactions.add(new Transactions(reward.getDescription(),
//                    ContextCompat.getDrawable(getActivity(), R.drawable.transaction_success), "SUCCESS", "-50",
//                    "minus"));
//            NewHomeFragment.totalPointsText.setText(String.valueOf(totalPoints));
//        }
//        else {
//            prefUtils.setValue(PrefKeys.TOTAL_POINTS, 2400);
//            NewHomeFragment.totalPointsText.setText("2400");
//            Toast.makeText(getActivity(), "You do not have sufficent points to play this game", Toast.LENGTH_SHORT).show();
//        }
                dismiss();

                if (isQrScanner) {
                    getActivity().finish();
                }
            } else {
                verificationDialog();
            }
        });
    }

    public void setRewardData(Reward reward) {
        this.reward = reward;
    }

    public void redirectData(redirectFragment redirectFragment) {
        this.redirectFragment = redirectFragment;
    }

    public SpecialForYou getSpecialData() {
        return specialData;
    }

    public void setSpecialData(SpecialForYou specialData) {
        this.specialData = specialData;
    }



    public void verificationDialog() {
        Dialog dialog = new Dialog(Objects.requireNonNull(getActivity()), R.style.Theme_TelcoApp);
        dialog.setContentView(R.layout.dialog_otp_verification);
        Button confirm = dialog.findViewById(R.id.confirm);

        confirm.setOnClickListener(view -> {
            successDialog();
            dialog.dismiss();
        });
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void successDialog() {
        Dialog dialog = new Dialog(Objects.requireNonNull(getActivity()), R.style.Theme_TelcoApp);
        dialog.setContentView(R.layout.recharge_success_dialog);
        Button done = dialog.findViewById(R.id.done);

        done.setOnClickListener(view -> {
            dialog.dismiss();
        });
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public interface redirectFragment{
        void onRewardAdded();
    }
}


