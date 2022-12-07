package com.dvnet.agreement.agreement;

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
import com.dvnet.agreement.data.agreement.AgreementDbHelper;
import com.dvnet.agreement.data.agreement.AgreementContract;
import com.dvnet.agreement.data.partner.PartnerDbHelper;
import com.dvnet.agreement.partner.AddEditPartnerActivity;
import com.dvnet.agreement.agreement.AgreementActivity;
import com.dvnet.agreement.agreement.AgreementCursorAdapter;
import com.dvnet.agreement.agreement.AgreementFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AgreementFragment extends Fragment {
    public static final int REQUEST_UPDATE_DELETE_AGREEMENT = 2;

    private PartnerDbHelper mAgreementDbHelper;

    private SearchView mSearchView;
    private ListView mAgreementList;
    private AgreementCursorAdapter mAgreementAdapter;
    private FloatingActionButton mAddButton;

    public AgreementFragment() {
        // Required empty public constructor
    }

    public static AgreementFragment newInstance() {
        return new AgreementFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_agreement, container, false);

        // Referencias UI
        mAgreementList = (ListView) root.findViewById(R.id.agreement_list);
        mAgreementAdapter = new AgreementCursorAdapter(getActivity(), null);
        mAddButton = (FloatingActionButton) getActivity().findViewById(R.id.fab_agreement);
        mSearchView = (SearchView) getActivity().findViewById(R.id.t_search_agreement);


        // Instancia de helper
        mAgreementDbHelper = new PartnerDbHelper(getActivity());

        // Setup
        mAgreementList.setAdapter(mAgreementAdapter);
        mAgreementList.setTextFilterEnabled(true);

        // Eventos
        mAgreementList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor currentItem = (Cursor) mAgreementAdapter.getItem(i);
                String currentAgreementId = currentItem.getString(
                        currentItem.getColumnIndex(AgreementContract.AgreementEntry.ID));

                showDetailScreen(currentAgreementId);
            }
        });



        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddScreen();
            }
        });

        // Carga de datos
        loadAgreement();

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (Activity.RESULT_OK == resultCode) {
            switch (requestCode) {
                case AddEditAgreementActivity.REQUEST_ADD_AGREEMENT:
                    showSuccessfullSavedMessage();
                    loadAgreement();
                    break;
                case REQUEST_UPDATE_DELETE_AGREEMENT:
                    loadAgreement();
                    break;
            }
        }

    }

    private void loadAgreement() {
        new AgreementFragment.AgreementsLoadTask().execute();
    }

    private void showSuccessfullSavedMessage() {
        Toast.makeText(getActivity(),
                "Contrato guardado correctamente", Toast.LENGTH_SHORT).show();
    }

    private void showAddScreen() {
        startActivityForResult(new Intent(getActivity(), AddEditAgreementActivity.class), AddEditAgreementActivity.REQUEST_ADD_AGREEMENT);
    }

    private void showDetailScreen(String AgreementId) {

        Intent intent = new Intent(getActivity(), AddEditAgreementActivity.class);
        intent.putExtra(AgreementActivity.EXTRA_AGREEMENT_ID, AgreementId);
        startActivityForResult(intent, AgreementFragment.REQUEST_UPDATE_DELETE_AGREEMENT);

    }

    private class AgreementsLoadTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            GlobalUser globalUser = com.dvnet.agreement.data.GlobalUser.getInstance();
            //return mAgreementDbHelper.getAllAgremeent();
            return mAgreementDbHelper.getAllAgremeentByUserId(globalUser.getData());
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            Log.d(TAG, "onPostExecute() returned: " + cursor.getCount());
            if (cursor != null && cursor.getCount() > 0) {
                mAgreementAdapter.swapCursor(cursor);
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
