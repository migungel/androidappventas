package com.dvnet.agreement.partner;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import com.dvnet.agreement.R;

import androidx.fragment.app.Fragment;

import com.dvnet.agreement.data.GlobalUser;
import com.dvnet.agreement.data.partner.PartnerContract;
import com.dvnet.agreement.data.partner.PartnerDbHelper;
import com.dvnet.agreement.data.partner.PartnerContract.PartnerEntry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PartnerFragment extends Fragment {
    public static final int REQUEST_UPDATE_DELETE_PARTNER = 2;

    private PartnerDbHelper mPartnerDbHelper;

    private SearchView mSearchView;
    private ListView mPartnerList;
    private PartnerCursorAdapter mPartnerAdapter;
    private FloatingActionButton mAddButton;

    public PartnerFragment() {
        // Required empty public constructor
    }

    public static PartnerFragment newInstance() {
        return new PartnerFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_partner, container, false);

        // Referencias UI
        mPartnerList = (ListView) root.findViewById(R.id.partnes_list);
        mPartnerAdapter = new PartnerCursorAdapter(getActivity(), null);
        mAddButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        mSearchView = (SearchView) getActivity().findViewById(R.id.t_search);

        // Instancia de helper
        mPartnerDbHelper = new PartnerDbHelper(getActivity());

        // Setup
        mPartnerList.setAdapter(mPartnerAdapter);
        mPartnerList.setTextFilterEnabled(true);

        // Eventos
        mPartnerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor currentItem = (Cursor) mPartnerAdapter.getItem(i);
                String currentPartnerId = currentItem.getString(
                        currentItem.getColumnIndex(PartnerEntry.ID));

                showDetailScreen(currentPartnerId);
            }
        });

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddScreen();
            }
        });

        // Carga de datos
        loadPartner();

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Activity.RESULT_OK == resultCode) {
            switch (requestCode) {
                case AddEditPartnerActivity.REQUEST_ADD_PARTNER:
                    showSuccessfullSavedMessage();
                    loadPartner();
                    break;
                case REQUEST_UPDATE_DELETE_PARTNER:
                    loadPartner();
                    break;
            }
        }

    }

    private void loadPartner() {
        new PartnersLoadTask().execute();
    }

    private void showSuccessfullSavedMessage() {
        Toast.makeText(getActivity(),
                "Cliente guardado correctamente", Toast.LENGTH_SHORT).show();
    }

    private void showAddScreen() {
        Intent intent = new Intent(getActivity(), AddEditPartnerActivity.class);
        startActivityForResult(intent, AddEditPartnerActivity.REQUEST_ADD_PARTNER);
    }

    private void showDetailScreen(String partnerId) {

        Intent intent = new Intent(getActivity(), AddEditPartnerActivity.class);
        intent.putExtra(PartnerActivity.EXTRA_PARTNER_ID, partnerId);
        startActivityForResult(intent, PartnerFragment.REQUEST_UPDATE_DELETE_PARTNER);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPartnerDbHelper.close();
    }

    private class PartnersLoadTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            GlobalUser globalUser = com.dvnet.agreement.data.GlobalUser.getInstance();
            return mPartnerDbHelper.getAllPartnerByUserId(globalUser.getData());
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            Log.d(TAG, "onPostExecute() returned: " + cursor.getCount());
            if (cursor != null && cursor.getCount() > 0) {
                mPartnerAdapter.swapCursor(cursor);
            } else {
                // Mostrar empty state
            }
        }
    }

    private void setupSearchView()
    {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener((SearchView.OnQueryTextListener) this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("Search Here");
    }

}
