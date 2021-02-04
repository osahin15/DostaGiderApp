package com.hawksappstudio.dostagiderapp.view

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hawksappstudio.dostagiderapp.R
import kotlinx.android.synthetic.main.fragment_text.*


class TextFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_text, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var textDeneme =  "<p style=\"text-align: center;\"><font face=\"arial black, sans-serif\" size=\"4\" color=\"#ff0000\">URARTU AUTO</font></p><p style=\"text-align: center;\">RENAULT CLİO 1.5 DCİ JOY&#xa0;</p><p style=\"text-align: center;\">ARACIMIZIN İÇ DIŞ KONDÜSYONU OLDUKÇA DÜZGÜNDÜR</p><p style=\"text-align: center;\">SIFIRDAN FARKSIZDIR.</p><p style=\"text-align: center;\">HERHNAGİ BİR EKSİĞİ YOKTUR</p><p style=\"text-align: center;\">ORJİNAL 7.800 KM DE&#xa0;</p><p style=\"text-align: center;\">KİTAPÇIKLARI VE YEDEK ANAHTARI MEVCUTTUR.</p><p style=\"text-align: center;\"><font size=\"5\" color=\"#ff0000\" face=\"arial black, sans-serif\">HATASIZDIR&#xa0;</font></p><p style=\"text-align: center;\"><font size=\"5\" color=\"#ff0000\" face=\"arial black, sans-serif\">TRAMERSİZ</font></p><p style=\"text-align: center;\"><br></p><p style=\"text-align: center;\"><font face=\"comic sans ms, sans-serif\" size=\"5\">ALICISINA HAYIRLI OLSUN:)</font></p><p style=\"text-align: center;\"><br></p>"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            aciklama_text.setText(Html.fromHtml(textDeneme, Html.FROM_HTML_MODE_COMPACT))
        } else {
            aciklama_text.setText(Html.fromHtml(textDeneme))
        }
    }
}