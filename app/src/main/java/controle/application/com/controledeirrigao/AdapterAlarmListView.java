package controle.application.com.controledeirrigao;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ramon on 23/08/2015.
 */
public class AdapterAlarmListView extends BaseAdapter {

    private Context context;
    private List<String> horarios;
    private List<String> duracoes;

    public AdapterAlarmListView(Context context, List<String> horarios, List<String> duracoes) {
        this.context = context;
        this.horarios = horarios;
        this.duracoes = duracoes;
    }

    @Override
    public int getCount() {
        return horarios.size();
    }

    @Override
    public Object getItem(int position) {
        return horarios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.alarm_list_item, null);
        }

        TextView horario = (TextView) convertView.findViewById(R.id.idItemList1);
        horario.setText(horarios.get(position));

        TextView duracao = (TextView) convertView.findViewById(R.id.idItemList2);
        duracao.setText(duracoes.get(position));

        CheckBox status = (CheckBox) convertView.findViewById(R.id.idItemList3);
        status.setChecked(false);
        Typeface typeFont = Typeface.createFromAsset(context.getAssets(), "Roboto-Regular.ttf");
        horario.setTypeface(typeFont);
        duracao.setTypeface(typeFont);

        ImageButton removeAlarm = (ImageButton) convertView.findViewById(R.id.idRemoveAlarm);
        removeAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context)
                .setTitle("Excluir Alarme").setMessage("Deseja excluir esse alarme?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        horarios.remove(position);
                        duracoes.remove(position);
                        notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //do nothing
                    }
                }).show();
            }
        });

        return convertView;

    }

}
