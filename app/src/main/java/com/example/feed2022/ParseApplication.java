package com.example.feed2022;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

class ParseApplication {

    //#####################################################
    //1# variável que irá receber o xmlFileContents "arquivo baixado".
    private String xmlData;

    //2#Vamos usar uma Arraylist de "Application1" cada entry estará associada a um application1.
    private ArrayList<Applications> applications = new ArrayList<Applications>() ;

    //3# Precisamos criar um construtor para inicializar o objeto dessa classe com o conteudo baixado e inicializar o Arraylist.
    //Vamos já criar a instância ArrayList de application
    public ParseApplication(String xmlData) {
        this.xmlData = xmlData;
    }

    //4# vamos criar um getter para acessar o ArrayList

    public ArrayList<Applications> getApplications() {
        return applications;
    }

    //5# criando processo para minerar os dados dentro do XML
    public boolean process() {
        boolean status = true;       //sinaliza se a operação foi bem sucedida
        Applications currentRecord = null;    //objeto criado pra cada entry
        boolean inEntry = false;     //sinaliza se um campo a ser analisado é valido. "entry"
        String textValue = "";        //Onde o texto dos campos será armazenado.
        //6# vamos criar uma estrutura try/catch para o processament
        try {
            //7# A classe XmlPullParserFactory que permite a manipulação de conteúdos XML (analisador)
            // 7.1 Vamos criar uma instancia do tipo XmlPullParserFactory capaz de criar um objeto XmlPullParse capaz de tratar o arquivo XML.
            // END_DOCUMENT, TEXT, START_TAG, END_TAG
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

            //7.2configura o suporte do factory para espaços reservados a nomes
            factory.setNamespaceAware(true);

            //7.3  este objeto possui metodos que nos permite extrair o conteudo do campo de dados do XML
            XmlPullParser xpp = factory.newPullParser();

            //7.4 passando o conteudo para o analisador XML.
            xpp.setInput(new StringReader(this.xmlData));

            //7.5  vamos criar um marcador que recebe uma sinalização sempre o  parser encontra um novo campo.
            int evenType = xpp.getEventType();

            //7.6 Famos fazer o laço para ler o documento até o final.
            while (evenType != XmlPullParser.END_DOCUMENT) {

                String tagName = xpp.getName();//guarda o nome do campo ( "entry", "name", "artist"...)
                //7.8vamos verificar se é um dos campos de interesse e fazer o tratamento
                switch (evenType) {
                    // Se é uma tag de inicio de campo...
                    case XmlPullParser.START_TAG:
                        Log.d("ParseApplication", "Starting tag for-> " + tagName);//mostra a tag encontrada(e.g. name, entry, artist..)


                        //7.9. Inicio da Segunda parte, voltando do MainActivity.
                        //Se a tag for de um campo entry... será o início da criação de um objeto application1
                        if (tagName.equalsIgnoreCase("entry")) {
                            inEntry = true; // entrada valida!
                            currentRecord = new Applications();//cria um objeto Application para armazenar dados
                        }
                        break;


                    //8.0 no caso do envento ser um campo de texto capturamos o conteúdo e armazenamos na variável textValue.
                    case XmlPullParser.TEXT:
                        textValue =xpp.getText();
                        Log.d("ParseApplication", "text found");
                        break;

                    //8.1# caso tenha encontrado o final da tag manda pro LogCat
                    case XmlPullParser.END_TAG:
                        Log.d("ParseApplication", "Ending tag for-> " + tagName);
                        //8.2 Ao final de cada TAG, se estiver dentro de um campo "entry" ->inEntry = true
                        //verfica se é um dos campos de interesse e atribui o texto do campo (textValue) no objeto.
                        if(inEntry){

                            if(tagName.equalsIgnoreCase("entry")){
                                applications.add(currentRecord);
                                inEntry =false;}

                            else  if (tagName.equalsIgnoreCase("name")){
                                currentRecord.setName(textValue);
                            }
                            else if(tagName.equalsIgnoreCase("artist")){
                                currentRecord.setArtist(textValue);
                            }
                            else if(tagName.equalsIgnoreCase("releaseDate")){
                                currentRecord.setReleaseDate(textValue);
                            }
                        }
                        break;
                    default:
                }
                //8.3 le o próximo evento através do parse xpp.
                evenType = xpp.next();
            }
        }
        //8.4 Se ocorrer qualquer tipo de exceção
        catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        //8.5. Vamos jogar o resultado no LogCat
        for(Applications app: applications) {
            Log.d("ParseApplications", "*******************");
            Log.d("ParseApplications", "Name: " + app.getName());
            Log.d("ParseApplications", "Artist: " + app.getArtist());
            Log.d("ParseApplications", "Release Date: " + app.getReleaseDate());
        }

        //8.6 retorna o status
        return status;
    }
}

//7.13 vamos retornar para a MainActivity

