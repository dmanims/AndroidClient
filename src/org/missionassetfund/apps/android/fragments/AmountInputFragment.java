
package org.missionassetfund.apps.android.fragments;

import org.missionassetfund.apps.android.R;
import org.missionassetfund.apps.android.interfaces.OnInputFormListener;
import org.missionassetfund.apps.android.utils.CurrencyUtils;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class AmountInputFragment extends Fragment {

    private OnInputFormListener listener;

    private EditText etAmount;
    private RadioButton rbExpense;
    private Button btnNext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_amount_input, container, false);

        // Setup view
        etAmount = (EditText) view.findViewById(R.id.etAmount);
        rbExpense = (RadioButton) view.findViewById(R.id.rbExpense);
        btnNext = (Button) view.findViewById(R.id.btnNext);

        // Setup listener
        btnNext.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Double amount = Double.parseDouble(etAmount.getText().toString());
                listener.OnNextSelected(AmountInputFragment.class,
                        CurrencyUtils.getCurrencyValueFormatted(CurrencyUtils.newCurrency(amount)));
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnInputFormListener) {
            listener = (OnInputFormListener) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implement OnNextSelectedListener");
        }
    }

    public String getAmountSelected() {
        return etAmount.getText().toString();
    }
    
    public boolean isExpenseTransaction() {
        return rbExpense.isChecked() ? true : false;
    }

}
