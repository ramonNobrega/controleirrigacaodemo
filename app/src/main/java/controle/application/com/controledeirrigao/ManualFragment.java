package controle.application.com.controledeirrigao;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Ramon on 23/08/2015.
 */
public class ManualFragment extends Fragment {

    private Switch valvula1, valvula2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_manual, container, false);

        valvula1 = (Switch) rootView.findViewById(R.id.switch1);
        valvula2 = (Switch) rootView.findViewById(R.id.switch2);

        valvula1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                    alertDialog.setTitle("Ativar Válvula");
                    alertDialog.setMessage("Ativar Válvula 1?");
                    alertDialog.setButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // clock picker

                            Toast.makeText(getActivity(), "Válvula 1 ativada!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    alertDialog.setButton2("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            valvula1.setChecked(false);
                        }
                    });
                    alertDialog.show();
                }
                else
                    Toast.makeText(getActivity(), "Válvula 1 desativada!", Toast.LENGTH_SHORT).show();
            }
        });

        valvula2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                    alertDialog.setTitle("Ativar Válvula");
                    alertDialog.setMessage("Ativar Válvula 2?");
                    alertDialog.setButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getActivity(), "Válvula 2 ativada!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    alertDialog.setButton2("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            valvula2.setChecked(false);
                        }
                    });
                    alertDialog.show();
                }
                else
                    Toast.makeText(getActivity(), "Válvula 2 desativada!", Toast.LENGTH_SHORT).show();
            }
        });
        customizeItems();
        return rootView;
    }

    public void customizeItems(){
        Typeface typeFont = Typeface.createFromAsset(getContext().getAssets(), "Roboto-Regular.ttf");
        valvula1.setTypeface(typeFont);
        valvula2.setTypeface(typeFont);
    }
}

