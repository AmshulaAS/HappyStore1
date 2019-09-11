package com.example.happystore;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public  class DemoFragment2 extends Fragment {
    private ImageView imageView;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<MyGroupHeaderModel> listDataHeader;
    HashMap<String, List<MyGroupHeaderModel>> listDataChild;
    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_demo_fragment2, null);
        imageView = (ImageView) rootView.findViewById(R.id.image0);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        expListView = (ExpandableListView) rootView.findViewById(R.id.expListView);

        prepareListData();


        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);

        Log.i("groups", listDataHeader.toString());
        Log.i("details", listDataChild.toString());

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                String m = listDataHeader.get(groupPosition).getId();
                Toast.makeText(getActivity().getApplicationContext(), m + " Expanded", Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                String m = listDataHeader.get(groupPosition).getId();
                Toast.makeText(getActivity().getApplicationContext(), m + " Collapsed", Toast.LENGTH_SHORT).show();
            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,int groupPosition, int childPosition, long id)
            {

                Toast.makeText(getActivity().getApplicationContext(), listDataHeader.get(groupPosition).getDisplayText() + " -> " + listDataChild.get(listDataHeader.get(groupPosition).getDisplayText()).get(childPosition).getDisplayText(), Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(getActivity(),Shopping_list1.class);

                MyGroupHeaderModel mlistDataHeader = listDataHeader.get(groupPosition);

                MyGroupHeaderModel mlistDataChild = listDataChild.get(mlistDataHeader.displayText).get(childPosition);

                intent1.putExtra("GroupHeader",mlistDataHeader.getId());
                intent1.putExtra("GroupChild",mlistDataChild.getId());
                startActivity(intent1);
                return true;
            }

        });
        return rootView;
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<MyGroupHeaderModel>();
        listDataChild = new HashMap<String, List<MyGroupHeaderModel>>();

        // Adding header data

        MyGroupHeaderModel model1 = getHeader("HOME NEEDS","HomeNeeds");
        MyGroupHeaderModel model2 = getHeader("GROCERY STAPLES ","GroceryStaples");
        MyGroupHeaderModel model3 = getHeader("FRUITS AND VEGETABLES","FruitsVegetables");
        MyGroupHeaderModel model4 = getHeader("BEVERAGES","Beverages");
        MyGroupHeaderModel model5 = getHeader("PERSONAL CARE","PersonalCare");
        MyGroupHeaderModel model6 = getHeader("KIDS UTILITIES","kidsUtilities");
        MyGroupHeaderModel model7 = getHeader("FASHION","Fashion");

        listDataHeader.add(model1);
        listDataHeader.add(model2);
        listDataHeader.add(model3);
        listDataHeader.add(model4);
        listDataHeader.add(model5);
        listDataHeader.add(model6);
        listDataHeader.add(model7);


        // Adding child data
        List<MyGroupHeaderModel> HomeNeeds = new ArrayList<MyGroupHeaderModel>();
        MyGroupHeaderModel HomeNeedsm1 = getHeader("Cleaning accessories","CleaningAccessories");
        MyGroupHeaderModel HomeNeedsm2 = getHeader("Cookware","CookWare");
        MyGroupHeaderModel HomeNeedsm3 = getHeader("Detergents","Detergents");
        MyGroupHeaderModel HomeNeedsm4 = getHeader("Electricals","Electricals");
        MyGroupHeaderModel HomeNeedsm5 = getHeader("Festival Needs","FestivalNeeds");

        HomeNeeds.add(HomeNeedsm1);
        HomeNeeds.add(HomeNeedsm2);
        HomeNeeds.add(HomeNeedsm3);
        HomeNeeds.add(HomeNeedsm4);
        HomeNeeds.add(HomeNeedsm5);

        List<MyGroupHeaderModel> GroceryStaple = new ArrayList<MyGroupHeaderModel>();
        MyGroupHeaderModel GroceryStaplem1 = getHeader("Biscuits and Breads","BiscuitsBreads");
        MyGroupHeaderModel GroceryStaplem2 = getHeader("Cereals and Grains","CerealsGrains");
        MyGroupHeaderModel GroceryStaplem3 = getHeader("Oil and Ghee","OilGhee");
        MyGroupHeaderModel GroceryStaplem4 = getHeader("Instant Food","InstantFood");
        MyGroupHeaderModel GroceryStaplem5 = getHeader("Masala spices","Masalaspices");

        GroceryStaple.add(GroceryStaplem1);
        GroceryStaple.add(GroceryStaplem2);
        GroceryStaple.add(GroceryStaplem3);
        GroceryStaple.add(GroceryStaplem4);
        GroceryStaple.add(GroceryStaplem5);


        List<MyGroupHeaderModel> FruitsVegetables = new ArrayList<MyGroupHeaderModel>();
        MyGroupHeaderModel FruitsVegetablesm1 = getHeader("Exotic Vegetables","ExoticVegetables");
        MyGroupHeaderModel FruitsVegetablesm2 = getHeader("Fruits","Fruits");
        MyGroupHeaderModel FruitsVegetablesm3 = getHeader("Imported Fruits","ImportedFruits");
        MyGroupHeaderModel FruitsVegetablesm4 = getHeader("Vegetables","Vegetables");
        MyGroupHeaderModel FruitsVegetablesm5= getHeader("Dry Fruits","DryFruits");

        FruitsVegetables.add(FruitsVegetablesm1);
        FruitsVegetables.add(FruitsVegetablesm2);
        FruitsVegetables.add(FruitsVegetablesm3);
        FruitsVegetables.add(FruitsVegetablesm4);
        FruitsVegetables.add(FruitsVegetablesm5);

        List<MyGroupHeaderModel> Beverages = new ArrayList<MyGroupHeaderModel>();

        MyGroupHeaderModel Beveragesm1 = getHeader("Health drinks","Healthdrinks");
        MyGroupHeaderModel Beveragesm2 = getHeader("Energy drink","Energydrink");
        MyGroupHeaderModel Beveragesm3 = getHeader("Soft drink","Softdrink");
        MyGroupHeaderModel Beveragesm4 = getHeader("Coffee cans/Tea","Coffeecans/Tea");
        MyGroupHeaderModel Beveragesm5= getHeader("Mineral water","Mineralwater");

        Beverages.add(Beveragesm1);
        Beverages.add(Beveragesm2);
        Beverages.add(Beveragesm3);
        Beverages.add(Beveragesm4);
        Beverages.add(Beveragesm5);

        List<MyGroupHeaderModel> PersonalCare = new ArrayList<MyGroupHeaderModel>();

        MyGroupHeaderModel PersonalCarem1 = getHeader("Fashion accessories","Fashionaccessories");
        MyGroupHeaderModel PersonalCarem2 = getHeader("Hair care cosmetics","Haircarecosmetics");
        MyGroupHeaderModel PersonalCarem3 = getHeader("Sanitary Needs","SanitaryNeeds");
        MyGroupHeaderModel PersonalCarem4 = getHeader("Shaving Needs","ShavingNeeds");
        MyGroupHeaderModel PersonalCarem5 = getHeader("Deos/Perfumes","Deos/Perfumes");

        PersonalCare.add(PersonalCarem1);
        PersonalCare.add(PersonalCarem2);
        PersonalCare.add(PersonalCarem3);
        PersonalCare.add(PersonalCarem4);
        PersonalCare.add(PersonalCarem5);

        List<MyGroupHeaderModel> kidsUtilities = new ArrayList<MyGroupHeaderModel>();

        MyGroupHeaderModel kidsUtilitiesm1 = getHeader("School needs","Schoolneeds");
        MyGroupHeaderModel kidsUtilitiesm2 = getHeader("Toys/Games","Toys/Games");
        MyGroupHeaderModel kidsUtilitiesm3 = getHeader("Kids care","Kidscare");

        kidsUtilities.add(kidsUtilitiesm1);
        kidsUtilities.add(kidsUtilitiesm2);
        kidsUtilities.add(kidsUtilitiesm3);

        List<MyGroupHeaderModel> Fashion = new ArrayList<MyGroupHeaderModel>();

        MyGroupHeaderModel Fashionm1 = getHeader("Men","Men");
        MyGroupHeaderModel Fashionm2 = getHeader("Women","Women");
        MyGroupHeaderModel Fashionm3 = getHeader("Kids","Kids");

        Fashion.add(Fashionm1);
        Fashion.add(Fashionm2);
        Fashion.add(Fashionm3);

        listDataChild.put(listDataHeader.get(0).displayText, HomeNeeds); // Header, Child data
        listDataChild.put(listDataHeader.get(1).displayText, GroceryStaple);
        listDataChild.put(listDataHeader.get(2).displayText, FruitsVegetables);
        listDataChild.put(listDataHeader.get(3).displayText, Beverages);
        listDataChild.put(listDataHeader.get(4).displayText, PersonalCare);
        listDataChild.put(listDataHeader.get(5).displayText, kidsUtilities);
        listDataChild.put(listDataHeader.get(6).displayText, Fashion);
    }

    private MyGroupHeaderModel getHeader(String displayText,String id) {
        MyGroupHeaderModel model = new MyGroupHeaderModel();
        model.setDisplayText(displayText);
        model.setId(id);
        return  model;
    }
}