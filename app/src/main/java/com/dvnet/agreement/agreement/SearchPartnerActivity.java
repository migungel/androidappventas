package com.dvnet.agreement.agreement;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import com.dvnet.agreement.R;
import com.dvnet.agreement.data.GlobalUser;
import com.dvnet.agreement.data.adapters.PartnerSearchCursorAdapter;
import com.dvnet.agreement.data.partner.PartnerContract;
import com.dvnet.agreement.data.partner.PartnerDbHelper;
import com.dvnet.agreement.partner.AddEditPartnerActivity;
import com.dvnet.agreement.partner.PartnerActivity;
import com.dvnet.agreement.partner.PartnerCursorAdapter;
import com.dvnet.agreement.partner.PartnerFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SearchPartnerActivity extends AppCompatActivity{

    private ListView mListPartner;
    private EditText mSearchable;

    private PartnerSearchCursorAdapter mPartnerAdapter;
    private PartnerDbHelper mPartnerDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_partner);

        mListPartner = (ListView) this.findViewById(R.id.list_search_partner_result);
        mSearchable = (EditText) this.findViewById(R.id.s_partner_name);
        mPartnerAdapter = new PartnerSearchCursorAdapter(this, null);
        mPartnerDbHelper = new PartnerDbHelper(this);

        mListPartner.setAdapter(mPartnerAdapter);
        mListPartner.setTextFilterEnabled(true);
        mListPartner.setFastScrollEnabled(true);

        loadPartner();

        // Prepare your adapter for filtering
        mPartnerAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            @Override
            public Cursor runQuery(CharSequence constraint) {

                String sea = String.valueOf(constraint);

                return mPartnerDbHelper.getPartnerByName(sea);
            }
        });

        mSearchable.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                // This is the filter in action
                mPartnerAdapter.getFilter().filter(s.toString());
                // Don't forget to notify the adapter
                mPartnerAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(final Editable s) {

            }
        });

        mListPartner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor currentItem = (Cursor) mPartnerAdapter.getItem(i);
                String currentPartnerId = currentItem.getString(currentItem.getColumnIndex(PartnerContract.PartnerEntry.ID));

                showDetailScreen(currentPartnerId);
            }
        });

    }

    private void loadPartner() {
        new PartnersLoadTask().execute();
    }

    private class PartnersLoadTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            GlobalUser globalUser = com.dvnet.agreement.data.GlobalUser.getInstance();
            return mPartnerDbHelper.getAllPartnerUnsed(globalUser.getData());
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

    private void showDetailScreen(String partnerId) {
        Intent intent = new Intent(this, AddEditAgreementActivity.class);
        intent.putExtra("partner_id", partnerId);
        setResult(RESULT_OK, intent);
        finish();
    }

}
