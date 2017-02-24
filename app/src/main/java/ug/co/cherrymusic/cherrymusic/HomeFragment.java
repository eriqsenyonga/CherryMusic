package ug.co.cherrymusic.cherrymusic;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    View root;
    RecyclerView rvHome;
    ArrayList<SectionDataModel> allSampleData;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_home, container, false);
        rvHome = (RecyclerView) root.findViewById(R.id.rv_home);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        allSampleData = new ArrayList<SectionDataModel>();

        createDummyData();


        rvHome.setHasFixedSize(true);

        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(getActivity(), allSampleData);

        rvHome.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        rvHome.setAdapter(adapter);


    }

    public void createDummyData() {
        for (int i = 1; i <= 5; i++) {

            SectionDataModel dm = new SectionDataModel();

            if (i == 1) {

                dm.setHeaderTitle("Top Music");

            } else if (i == 2) {

                dm.setHeaderTitle("Top Books");
            } else if (i == 3) {

                dm.setHeaderTitle("Trending News");
            } else if (i == 4) {

                dm.setHeaderTitle("Top Sermons");
            } else if (i == 5) {

                dm.setHeaderTitle("Top Podcasts");
            }else{

                dm.setHeaderTitle("Section " + i);
            }

            // dm.setHeaderTitle("Section " + i);

            ArrayList<SingleItemModel> singleItem = new ArrayList<SingleItemModel>();
            for (int j = 0; j <= 5; j++) {
                singleItem.add(new SingleItemModel("Item " + j, "URL " + j));
            }

            dm.setAllItemsInSection(singleItem);

            allSampleData.add(dm);

        }


    }
}
