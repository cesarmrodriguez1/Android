package mx.itesm.navigationdrawerexample;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;


public class FragmentTimePicker1 extends Fragment
{
    TimePickerDialog picker;
    EditText textField;
    Button bTime;
    TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_timepicker1, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        textView =(TextView)view.findViewById(R.id.tpTextView);
        textField =(EditText) view.findViewById(R.id.tpEditText);
        textField.setInputType(InputType.TYPE_NULL);
        textField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minutes = calendar.get(Calendar.MINUTE);
                // time picker dialog
                picker = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                                textField.setText(hourOfDay + ":" + minute);
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });
        bTime=(Button)view.findViewById(R.id.bSetTime);
        bTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Selected Time: "+ textField.getText());
            }
        });
    }
}
