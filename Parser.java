/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

/**
 *
 * @author FRAZ BAKHT
 */
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class parser {

    public static void main(String[] args) throws IOException {
        
        int choice;
         choice = new Scanner(System.in).nextInt();
        switch(choice)
        {
        case 1:
        {  try {
            File inputFile = new File("sample.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            parseHandler Ph1 = new parseHandler();
            saxParser.parse(inputFile, Ph1);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
            break;
        }
        case 2:
        {
            Search s1 = new Search(); 
            s1.thisSearches();
        
        }
        
        
        }
        
       

    }
}

class parseHandler extends DefaultHandler {

    boolean bid = false;
    boolean btitle = false;
    boolean btext = false;
    boolean flag = false; //Since the page has two IDs, and we need one, thats why I made the flag
    String str;
    StringBuilder toAdd = new StringBuilder();
    int IdCounter = 0;
    String Title;
    String ID;
    String stopWords[] = new String[319];
    int swCounter = 0;
    long pageRank = 0;

    parseHandler() {
        try {
            String lines = null;
            FileReader fileReader = new FileReader("stopWords.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((lines = bufferedReader.readLine()) != null) {
                stopWords[swCounter] = "\\b" + lines + "\\b";
                swCounter++;
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        if (qName.equalsIgnoreCase("page")) {
            flag = true;
        } else if (qName.equalsIgnoreCase("id") && IdCounter == 0) {
            bid = true;
            flag = false;
            IdCounter += 1;
        } else if (qName.equalsIgnoreCase("title")) {
            btitle = true;
        } else if (qName.equalsIgnoreCase("text")) {
            btext = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
       
             int counter[]={0,0};
            Integer value=1;
            
            for (int i = 0; i < titleWords.length; i++)
            {
                
                
                 if(!h1.containsKey(titleWords[i]))
                {
                     //counter =h1.get(titleWords[i]);
                    counter[0]=1;
                    h1.put(titleWords[i], counter);
                }
                 else if (h1.containsKey(titleWords[i]))
                {
                   counter =h1.get(titleWords[i]);
                    value++;
                    counter[0]++;
                    
                    h1.replace(titleWords[i],counter );
                }
            
            }
            //System.out.println(counter.get(0));
            Integer values=1;
            for (int i = 0; i < array.length; i++)
            {
                 if(!h1.containsKey(array[i]))
                {
                    
                    counter[1]=1;
                    
                    h1.put(array[i], counter);
                }
                 else if (h1.containsKey(array[i]))
                {
                    
                    
                    counter =h1.get(array[i]);
                    values+=1;
                    counter[1]++;
                    h1.replace(array[i], counter);
                }
                 
            }
            // System.out.println(counter.get(1));
             for (int i = 0; i < titleWords.length; i++) {
                int counter = 0;
                for (int j = 0; j < titleWords.length; j++) {
                    if (titleWords[i].equals(titleWords[j])) {
                        counter++;
                    }

                }
                titlecount[i] = titleWords[i] + "," + Integer.toString(counter);
                //System.out.println(titlecount[i]);
            }

            for (int i = 0; i < array.length; i++) {
                int counter[] = {0, 0};
                for (int j = 0; j < titlecount.length; j++) {
                    String counted[] = titlecount[j].split(",");
                    if (array[i].equals(counted[0])) {
                        counter[0] = Integer.parseInt(counted[1]);
                        //System.out.println(counter[1]);
                    }
                }
                if (h1.containsKey(array[i])) {
                    int count[] = h1.get(array[i]);
                    count[1]++;
                    counter[1] = count[1];
                    h1.put(array[i], counter);
                } else {
                    counter[1] = 1;
                    h1.put(array[i], counter);
                }

            }
            //INVERTED INDEXING
            //Set<String> keys = h1.keySet();
             for (String key:keys)
            {
                
                
                
                String directory ="F:\\DSA search engine\\"+key;
                
                String filename = key+".txt";
                File parentDir = new File(directory);
                parentDir.mkdir();
                File filee = new File(parentDir,filename); 
                try {
                    boolean success = filee.createNewFile();
                    
                if(filee.exists() || success )
                {
                    int one=h1.get(key)[0]; //occurences in title
                int two=h1.get(key)[1]; //occurences in rest of body
                
                //Calculating page rank
                pageRank= (one*2)+two;
                    FileWriter writer = new FileWriter(filee,true);
                    BufferedWriter bufferedWriter = new BufferedWriter(writer);
                    writer.write(Id+"\t");
                    
                    //writer.write(key + "\t");
                    //Set S = h1.entrySet();
                    writer.write(pageRank+ "\t");
                   
                    
                    writer.write(one+"\t");
                    
                    writer.write(two+ "\r\n");
                    writer.close();
                }
                   
                 }   
                 catch (IOException ex) {
                    Logger.getLogger(parseHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //System.out.println(h1.toString());
              try{ 
              System.out.println(ID);
              int Display = h1.get("april")[0];
            int D = h1.get("april")[1];
           
            System.out.println("april" +" "+  Display + " " + D);}
          catch(NullPointerException e)
            {
            
            }
            //System.out.println(" value: " + values );
            //System.out.println(h1);
            // System.out.println("Text: ");
            // for (String stringSet : array)
            //{
            //   System.out.println(stringSet);
            //}
            // System.out.println("TEXT: " + str);
            //System.out.println("End Element" + qName);
        }

    }

    public void invertedIndex() throws ClassNotFoundException {
        String address = "F:\\DSA search engine\\forwardIndex";
        File folder = new File(address);

        for (final File fileEntry : folder.listFiles()) {

            try {
                FileInputStream fileIn = new FileInputStream(fileEntry);
                Page page = (Page) new ObjectInputStream(fileIn).readObject();
                for (String key : page.wordOccurances.keySet()) {
                    
                    //File fileeWord = parentDir.createNewFile();
                    String directory = "F:\\DSA search engine\\invertedIndex";

                    String filename = key + ".txt";
                    File parentDir = new File(directory);
                    parentDir.mkdir();
                    File filee = new File(parentDir, filename);
                    boolean success = filee.createNewFile();

                    if (filee.exists() || success) {
                        FileWriter writer = new FileWriter(filee, true);
                        BufferedWriter bufferedWriter = new BufferedWriter(writer);
                         
                    
                    //writer.write(key + "\t");
                    //Set S = h1.entrySet();
                    
                    int titleOcc=0;
                    String titleArray[] = page.title.toLowerCase().split("\\s+ ");
                    for(String word: titleArray){
                        if(word.equals(key)){
                            titleOcc++;
                        }
                    }
                    pageRank=(titleOcc*2)+page.wordOccurances.get(key).size();
                    writer.write(page.id+"\t");
                    writer.write(titleOcc+ "\t");
                    writer.write(page.wordOccurances.get(key).size()+"\t");
                    writer.write(pageRank+ "\r\n");
                    writer.close();
                        
                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(parseHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void characters(char[] buffer, int start, int length)
            throws SAXException {
        if (btitle) {
            Title = new String(buffer, start, length);
            //System.out.println("Title: "+ Title);
            btitle = false;
        } else if (bid) {
            ID = new String(buffer, start, length);
            //System.out.println("Id: "+ ID);
            bid = false;
        } else if (btext) {
            toAdd.append(new String(buffer, start, length));
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        try {
            this.invertedIndex();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(parseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public String removeStopWords(String wsr, String stopWords[]) //wsr= white space removed 
    {

        wsr = wsr.toLowerCase();
        wsr = wsr.replaceAll("\\W+", " ");
        for (int i = 0; i < stopWords.length; i++) {
            wsr = wsr.replaceAll(stopWords[i], "");
        }
        return wsr;
    }

}
