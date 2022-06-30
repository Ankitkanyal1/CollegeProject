package com.example.chatten.Extras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.chatten.R;

public class PrivacyPolicy extends AppCompatActivity {
    TextView privacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        getSupportActionBar().setTitle("PRIVACY POLICY");
        privacy=findViewById(R.id.privacy);
        privacy.setText("You may be interacting with Chatten as a customer (or potential customer) of Chatten, who purchases Chatten rights to use the Chatten Software our other  Chatten Software on your own websites (a \"Chatten Customer\"). Alternatively, you may be a visitor to one of Chatten's Customer's websites, which uses the Chatten Software on such Customer's website (a \"Chatten Customer Visitor\"). If you are a Chatten Customer Visitor you should review the privacy policy of the Chatten Customer's website, which will govern your use of the Chatten Customer's website and explain how the Chatten Customer treats information collected through such Chatten Customer's use of the Chatten Software implemented on such website. This Privacy Policy merely describes how we may collect, use, store, share, disclose and protect Chatten Customer Visitor information we obtain through our Chatten Software (as implemented on Chatten Customers' websites). We have no control over the privacy practices of Chatten Customers. Whether you are a Chatten Customer or Chatten Customer Visitor, please read this policy carefully to understand our policies and practices regarding collection and use of your information. If you do not agree with this Privacy Policy, do not access or use the Site, Chatten Software or, if you are a Chatten Customer Visitor, the Chatten Customer's website. By accessing or using the Site, Chatten Software or (if you are a Chatten Customer Visitor) the Chatten Customer's website, you agree to this Privacy Policy.\n \n We may update our Privacy Policy from time to time. If we make material changes to how we treat your personal information, we will post the new privacy policy on this page. Your continued use of the Site or Pure Chat Software after we make changes is deemed to be your acceptance of those changes, so please check this policy periodically for updates.The date that this Privacy Policy was last revised is identified at the top of the page. You are responsible for periodically visiting this Privacy Policy to check for any changes.");
        privacy.setMovementMethod(new ScrollingMovementMethod());
    }
}