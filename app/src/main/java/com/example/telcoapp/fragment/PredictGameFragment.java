package com.example.telcoapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.telcoapp.R;

import java.util.ArrayList;


public class PredictGameFragment extends Fragment {

//    Unbinder unbinder;
//    @BindView(R.id.predictGameRecycler)
//    RecyclerView predictGameRecycler;
//    PredictGameListAdapter predictGameListAdapter;
//    PredictWinnerGameActivity predictWinnerGameActivity;
//    ArrayList<PredictList> predictLists = new ArrayList<>();
    String type;

    public PredictGameFragment(String type) {
        this.type = type;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        predictWinnerGameActivity = (PredictWinnerGameActivity) getActivity();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_predict_winner, container, false);
//        unbinder = ButterKnife.bind(this, view);
        return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpTopPlayersAdapter();
    }

    public void setUpTopPlayersAdapter() {
//        predictLists.clear();
//
//        if(type.equalsIgnoreCase("all")) {
//            predictLists.add(new PredictList("Machester United", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS6gUkjtsK0R9KV1mGrnWCYgZWYfiTjPxYxOQ&usqp=CAU", "Korea", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSygzAbsc_15zh8P6i67SmCsQFxY1LiIX2VdA&usqp=CAU"));
//            predictLists.add(new PredictList("Bhutan", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQt3N97v5hOoDFsWLHCpcBq4Dvn0WsvaC9taw&usqp=CAU", "NetherLand", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQScvZJbbC3Se1y-iQnQrhVSYANxSRpUFGqJw&usqp=CAU"));
//            predictLists.add(new PredictList("Italia", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcR01JuU-pteQZV67rgwrKXhImocGbGvsElHcg&usqp=CAU", "France", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ9gWv9N6Z889UPMq_Vyd6HTBuduQfZ81XSYQ&usqp=CAU"));
//        }
//        else if(type.equalsIgnoreCase("football")) {
//            predictLists.add(new PredictList("Machester United", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS6gUkjtsK0R9KV1mGrnWCYgZWYfiTjPxYxOQ&usqp=CAU", "Korea", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSygzAbsc_15zh8P6i67SmCsQFxY1LiIX2VdA&usqp=CAU"));
//            predictLists.add(new PredictList("Italia", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcR01JuU-pteQZV67rgwrKXhImocGbGvsElHcg&usqp=CAU", "France", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ9gWv9N6Z889UPMq_Vyd6HTBuduQfZ81XSYQ&usqp=CAU"));
//        }
//        else if(type.equalsIgnoreCase("cricket")) {
//            predictLists.add(new PredictList("Bhutan", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQt3N97v5hOoDFsWLHCpcBq4Dvn0WsvaC9taw&usqp=CAU", "NetherLand", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQScvZJbbC3Se1y-iQnQrhVSYANxSRpUFGqJw&usqp=CAU"));
//        }
//        predictGameListAdapter = new PredictGameListAdapter(predictWinnerGameActivity, predictLists);
//        predictGameRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
//        predictGameRecycler.setAdapter(predictGameListAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
    }
}


