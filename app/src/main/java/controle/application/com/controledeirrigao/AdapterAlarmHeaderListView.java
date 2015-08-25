package controle.application.com.controledeirrigao;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ramon on 23/08/2015.
 */
public class AdapterAlarmHeaderListView extends BaseAdapter {

    private Context context;
    private List<String> horarios;
    private List<String> duracoes;
    private List<String> statusList;

    public AdapterAlarmHeaderListView(Context context, List<String> horarios, List<String> duracoes, List<String> status) {
        this.context = context;
        this.horarios = horarios;
        this.duracoes = duracoes;
        this.statusList = status;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.header_alarm_list_view, null);
        }

        TextView horario = (TextView) convertView.findViewById(R.id.idHorarioHeader);
        horario.setText(horarios.get(position));

        TextView duracao = (TextView) convertView.findViewById(R.id.idDuracaoHeader);
        duracao.setText(duracoes.get(position));

        TextView status = (TextView) convertView.findViewById(R.id.idStatusHeader);
        status.setText(statusList.get(position));

        Typeface typeFont = Typeface.createFromAsset(context.getAssets(), "Roboto-Regular.ttf");
        horario.setTypeface(typeFont);
        duracao.setTypeface(typeFont);
        status.setTypeface(typeFont);

        return convertView;

    }

}
