package com.dvnet.agreement.partner;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.dvnet.agreement.R;
import com.dvnet.agreement.data.GlobalUser;
import com.dvnet.agreement.data.partner.PartnerContract.PartnerEntry;
import com.dvnet.agreement.data.partner.PartnerDbHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ReferFragment extends Fragment {
    public static final int REQUEST_UPDATE_DELETE_PARTNER = 2;

    private PartnerDbHelper mPartnerDbHelper;

    private SearchView mSearchView;
    private ListView mReferList;
    private ReferCursorAdapter mReferAdapter;
    private FloatingActionButton mAddButton;

    public ReferFragment() {
        // Required empty public constructor
    }

    public static ReferFragment newInstance() {
        return new ReferFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_refer, container, false);

        // Referencias UI
        mReferList = (ListView) root.findViewById(R.id.refers_list);
        mReferAdapter = new ReferCursorAdapter(getActivity(), null);
        mAddButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        mSearchView = (SearchView) getActivity().findViewById(R.id.t_search);

        // Instancia de helper
        mPartnerDbHelper = new PartnerDbHelper(getActivity());

        // Setup
        mReferList.setAdapter(mReferAdapter);
        mReferList.setTextFilterEnabled(true);

        // Eventos
        mReferList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor currentItem = (Cursor) mReferAdapter.getItem(i);
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
                case AddEditReferActivity.REQUEST_ADD_PARTNER:
                    showSuccessfullSavedMessage();
                    loadPartner();
                    break;
                case REQUEST_UPDATE_DELETE_PARTNER:
                    loadPartner();
                    break;
            }
        }
    }

    private void loadPartner() { new PartnersLoadTask().execute(); }

    private void showSuccessfullSavedMessage() {
        Toast.makeText(getActivity(),
                "Referido guardado correctamente", Toast.LENGTH_SHORT).show();
    }

    private void showAddScreen() {
        Intent intent = new Intent(getActivity(), AddEditReferActivity.class);
        startActivityForResult(intent, AddEditReferActivity.REQUEST_ADD_PARTNER);
    }

    private void showDetailScreen(String partnerId) {
        Intent intent = new Intent(getActivity(), AddEditReferActivity.class);
        intent.putExtra(ReferActivity.EXTRA_PARTNER_ID, partnerId);
        startActivityForResult(intent, ReferFragment.REQUEST_UPDATE_DELETE_PARTNER);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPartnerDbHelper.close();
    }

    private class PartnersLoadTask extends AsyncTask<Void, Void, Cursor> {
        @Override
        protected Cursor doInBackground(Void... voids) {
            //return mPartnerDbHelper.getAllPartner();
            GlobalUser globalUser = GlobalUser.getInstance();
            return mPartnerDbHelper.getAllReferByUserId(globalUser.getData());
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            Log.d(TAG, "onPostExecute() returned: " + cursor.getCount());
            if (cursor != null && cursor.getCount() > 0) {
                mReferAdapter.swapCursor(cursor);
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
