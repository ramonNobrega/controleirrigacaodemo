package controle.application.com.controledeirrigao;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Ramon on 23/08/2015.
 */
public class TimerFragment extends Fragment {

    private AdapterAlarmListView adapterAlarmListView;
    private AdapterAlarmHeaderListView adapterAlarmHeaderListView;
    private ListView mAlarmsListView, mAlarmsHeaderListView;
    private List<String> horariosHeader = new ArrayList<String>();
    private List<String> duracoesHeader = new ArrayList<String>();
    private List<String> statusHeader = new ArrayList<String>();
    private List<String> horarios = new ArrayList<String>();
    private List<String> duracoes = new ArrayList<String>();
    private String currentHourText;
    private EditText editText;
    private Button addAlarm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_timer, container, false);

        horariosHeader.add("Horário");
        duracoesHeader.add("Duração");
        statusHeader.add("Status");

        mAlarmsHeaderListView = (ListView) rootView.findViewById(R.id.idHeaderlistView);
        adapterAlarmHeaderListView = new AdapterAlarmHeaderListView(this.getContext(), horariosHeader, duracoesHeader, statusHeader);
        mAlarmsHeaderListView.setAdapter(adapterAlarmHeaderListView);

        mAlarmsListView = (ListView) rootView.findViewById(R.id.idAlarmListView);
        adapterAlarmListView = new AdapterAlarmListView(this.getContext(), horarios, duracoes);
        mAlarmsListView.setAdapter(adapterAlarmListView);

        addAlarm = (Button) rootView.findViewById(R.id.idAddAlarmButton);


        addAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.time_picker, null, false);

                final TimePicker myTimePicker = (TimePicker) view
                        .findViewById(R.id.myTimePicker);

                myTimePicker.setIs24HourView(true);

                new AlertDialog.Builder(getActivity()).setView(view)
                        .setTitle("Defina o Horário")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @TargetApi(11)
                            public void onClick(DialogInterface dialog, int id) {
                                // create a formated hour
                                currentHourText = myTimePicker.getCurrentHour().toString() + myTimePicker.getCurrentMinute().toString();
                                Calendar calendar = Calendar.getInstance();
                                Date d = new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                                        Integer.parseInt(myTimePicker.getCurrentHour().toString()), Integer.parseInt(myTimePicker.getCurrentMinute().toString()));
                                SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm");
                                currentHourText = dateFormatter.format(d);

                                // creating the EditText widget programatically
                                editText = new EditText(getActivity());

                                // create the AlertDialog as final
                                final AlertDialog builder = new AlertDialog.Builder(getActivity())
                                        .setTitle("Defina a duração")
                                        .setView(editText)
                                                // Set the action buttons
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int id) {
                                                horarios.add(currentHourText);
                                                duracoes.add(editText.getText().toString());
                                                adapterAlarmListView.notifyDataSetChanged();
                                            }
                                        })
                                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int id) {
                                                // removes the AlertDialog in the screen
                                            }
                                        })
                                        .show();

                                editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                                    @Override
                                    public void onFocusChange(View v, boolean hasFocus) {
                                        if (hasFocus) {
                                            builder.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                                        }
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).show();
            }
        });

        customizeItems();
        return rootView;
    }

    public void customizeItems(){
        Typeface typeFont = Typeface.createFromAsset(getContext().getAssets(), "Roboto-Regular.ttf");
        addAlarm.setTypeface(typeFont);

    }
}
