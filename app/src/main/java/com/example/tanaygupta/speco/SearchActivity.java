package com.example.tanaygupta.speco;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class SearchActivity extends AppCompatActivity{
    TextView tvresult;
    EditText etword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_word);
        tvresult = (TextView) findViewById(R.id.tvresult);
        etword = (EditText)findViewById(R.id.etword);
    }
    public void search(View view){
        String message = "";
        String word = etword.getText().toString().trim();
        if(word.isEmpty()){
            message = "Please enter Any Word";
            Toast.makeText(this,message, Toast.LENGTH_LONG).show();
        }else{
            new AccessWebServiceTask().execute(word);
        }
    }

    public String wordDefinition(String word) {
        String result = "";
        try {
            String url_string = ConfigURL.DICTIONARY_URL + "?word=" + word;
            Operation op = new Operation();
            InputStream in = op.openGetURLConnection(url_string);
            Document doc = null;
            DocumentBuilderFactory dbf =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder db;
            db = dbf.newDocumentBuilder();
            doc = db.parse(in);
            doc.getDocumentElement().normalize();
            NodeList definitionElements =
                    doc.getElementsByTagName("Definition");
            for (int i = 0; i < definitionElements.getLength(); i++) {
                Node itemNode = definitionElements.item(i);
                if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element definitionElement = (Element) itemNode;
                    NodeList wordDefinitionElements =
                            (definitionElement).getElementsByTagName("WordDefinition");
                    String strDefinition = "";
                    for (int j = 0; j < wordDefinitionElements.getLength(); j++) {
                        Element wordDefinitionElement =
                                (Element) wordDefinitionElements.item(j);
                        NodeList textNodes =
                                ((Node) wordDefinitionElement).getChildNodes();
                        strDefinition +=
                                ((Node) textNodes.item(0)).getNodeValue() + ".\n\n\n";
                    }
                    result += strDefinition;
                }
            }
        } catch (Exception ex) {
            result = ex.toString();
        }
        return result;
    }

    private class AccessWebServiceTask extends AsyncTask<String,Void,String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(SearchActivity.this);
            progressDialog.setMessage("Searching Text...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            return wordDefinition(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();
            tvresult.setText(s);
        }
    }
}
