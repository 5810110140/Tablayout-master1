package com.codingdemos.tablayout;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatusFragment extends Fragment {

    private TextView Datatemp, Datahumid;
    /*DatabaseReference dref;*/
    String statustemp, statushumid;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       /*dref= FirebaseDatabase.getInstance().getReference();*/
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Sensor");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
               /* statustemp = dataSnapshot.child("Temp").getValue().toString();
                Datatemp.setText(statustemp);*/
               for(DataSnapshot userSnap : dataSnapshot.getChildren()){
                   String Temp = userSnap.child("Temp").getValue().toString();
                   String Humid = userSnap.child("Humid").getValue().toString();
                   User user
               }
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });
        View myFragmentView = inflater.inflate(R.layout.fragment_status, container, false);
        TextView Datatemp = (TextView) myFragmentView.findViewById(R.id.datatemperature);
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_control, container, false);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_setting) {
            Toast.makeText(getActivity(), "Clicked on " + item.getTitle(), Toast.LENGTH_SHORT)
                    .show();
        }
        return true;
    }

}
