package controle.application.com.controledeirrigao;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Ramon on 23/08/2015.
 */
public class SensorFragment extends Fragment {

    private TextView umidadeTitle, temperaturaTitle;
    private TextView umidadeData, temperaturaData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_sensor, container, false);

        umidadeTitle = (TextView) rootView.findViewById(R.id.idUmidadeTitle);
        temperaturaTitle = (TextView) rootView.findViewById(R.id.idTempTitle);

        customizeItems();
        return rootView;
    }

    public void customizeItems(){
        Typeface typeFont = Typeface.createFromAsset(getContext().getAssets(), "Roboto-Regular.ttf");
        umidadeTitle.setTypeface(typeFont);
        temperaturaTitle.setTypeface(typeFont);
    }
}
