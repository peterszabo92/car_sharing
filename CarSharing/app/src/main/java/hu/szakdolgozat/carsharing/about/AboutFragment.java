package hu.szakdolgozat.carsharing.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import hu.szakdolgozat.carsharing.R;
import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FrameLayout root = (FrameLayout) inflater.inflate(R.layout.fragment_about, container, false);

        Element versionElement = new Element();
        Element authorElement = new Element();
        versionElement.setTitle("Verziószám: 1.0");
        authorElement.setTitle("Fejlesztő: Szabó Péter");
        View aboutPage = new AboutPage(getActivity())
                .isRTL(false)
                .setImage(R.drawable.login_header)
                .setDescription(getString(R.string.app_desc))
                .addItem(versionElement)
                .addItem(authorElement)
                .create();
        root.addView(aboutPage);

        return root;
    }
}
