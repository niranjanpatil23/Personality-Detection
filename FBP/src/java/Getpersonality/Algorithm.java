package Getpersonality;

import com.facebook.connect.FacebookAPI;
import com.facebook.connect.pojo.Data;
import com.facebook.connect.pojo.Feed;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Algorithm {

    static String[] Feat;
    static String[] OCEAN_tuple;
    static MaxentTagger tagger;
    static String[] fin_arr = new String[5];
    /**
     * @param args the command line arguments
     */
    public String[] str()throws Exception{
        // TODO code application logic here

        Feat = new String[23];
    OCEAN_tuple = new String[6];
    tagger = new MaxentTagger("taggers/english-left3words-distsim.tagger");
        
        CSVReader reader = new CSVReader(new FileReader("C:\\Users\\Aakash\\Documents\\NetBeansProjects\\FBP\\fbdata.csv"));
      //  CSVReader reader_next = new CSVReader(new FileReader("F:\\BE\\Project\\fbdata.csv"));
        CSVWriter writer = new CSVWriter(new FileWriter("C:\\Users\\Aakash\\Documents\\NetBeansProjects\\FBP\\FeatCount.csv"));
       
        String[] Col;
        String[] Col_next;
        String[] File_Header = {
            "id", "ap", "cm", "du", "em", "el", "im", "np", "ne", "nb", "pa", "pe",
            "pp", "pr", "qm", "sl", "sr", "sw", "tt", "wc", "we", "yu", "mf"
        };

        writer.writeNext(File_Header);
        reader.readNext();
       // reader_next.readNext();
       // reader_next.readNext();
        int temp_cnt = 1;
        int i = 0;
        while ((Col = reader.readNext()) != null) {
            i++;
        //    System.out.println(i + "     " + Col[0] + "     " + Col[1]);
        //    Col_next = reader_next.readNext();
      //      if (i < 9917 && Col[0].equals(Col_next[0])) {
                 
                temp_cnt++;
                getFeatures(Col[1]);
                Feat[0] = Col[0];
                writer.writeNext(Feat);
            /*} else if (i < 9917 && !Col[0].equals(Col_next[0]) && temp_cnt > 1) {
                getFeatures(Col[1]);
                temp_cnt = 1;
                Feat[0] = Col[0];
                writer.writeNext(Feat);
            } else if (i == 9917 && temp_cnt>1) {
                getFeatures(Col[1]);
                Feat[0] = Col[0];
                writer.writeNext(Feat);
            }
            */

            //getFeatures("is so sleepy it's not even funny that's she can't get to sleep.");
            //getFeatures(",He,loo,!I, -.am' ,Niranjan I, ,am a, Student, hello am@gmail.com @Niranjan");
            //getFeatures("http://stackoverflow.com/questions/6238351/fastest-way-to-detect-external-urls https://stackoverflow.com/questions/8200908/punctuation-regex-in-java");
            //getFeatures("I ME my mine You your yours we us our ours isn't not haven't ");
            //getFeatures(":( :'(  >:-(  :-? :) :D :P <3 :-] ^_^");
            //getFeatures("10 Nir1P Patil20 3Par 100 0 5.4");
            //getFeatures(" [Niranjan]  {Ch} () {} [] (Patil)");
            //getFeatures(" I You We A passenger plane has crashed shortly after take-off from Kyrgyzstan's\n"
            //        + "capital, Bishkek, killing a large number of those on board. The head of\n"
            //        + "Kyrgyzstan's civil aviation authority said that out of about 90\n"
            //        + "passengers and crew, only about 20 people have survived. The Itek Air\n"
            //        + "Boeing 737 took off bound for Mashhad, in north-eastern Iran, but turned \n"
            //        + "round some 10 minutes late?. \n"
            //        + "");
            //getFeatures("I we yours I me you bus I me we bus hello dear you arre tours yours");
        }
        writer.flush();
        writer.close();

        
        
        String[] Col1;
        CSVWriter writer1 = new CSVWriter(new FileWriter("C:\\Users\\Aakash\\Documents\\NetBeansProjects\\FBP\\OceanCount.csv"));
        CSVReader reader1 = new CSVReader(new FileReader("C:\\Users\\Aakash\\Documents\\NetBeansProjects\\FBP\\FeatCount.csv"));
        reader1.readNext();
        String[] FILE_HEADER1 = {"id", "OPN", "CON", "EXT", "AGR", "NEU"};
        writer1.writeNext(FILE_HEADER1);
        i = 0;
        while ((Col1 = reader1.readNext()) != null) {
         //   i++;
        //    System.out.println(i + "   Feature 1st:   " + Col1[1]);

            getOCEAN(Col1);
            OCEAN_tuple[0] = Col1[0];
            writer1.writeNext(OCEAN_tuple);
        }

        writer1.flush();
        writer1.close();

        
        
        
        String[] Col2;
        CSVWriter writer2 = new CSVWriter(new FileWriter("C:\\Users\\Aakash\\Documents\\NetBeansProjects\\FBP\\OceanSc.csv"));
        CSVReader reader2 = new CSVReader(new FileReader("C:\\Users\\Aakash\\Documents\\NetBeansProjects\\FBP\\OceanCount.csv"));
                reader2.readNext();
        int posts_cnt = 0;
        while (reader2.readNext() != null) {
            posts_cnt++;
        }
        String[] FILE_HEADER2 = {"id", "OPN", "CON", "EXT", "AGR", "NEU"};
        writer1.writeNext(FILE_HEADER1);

        CSVReader reader3 = new CSVReader(new FileReader("C:\\Users\\Aakash\\Documents\\NetBeansProjects\\FBP\\OceanCount.csv"));
        reader3.readNext();
        System.out.println("Posts_cnt:\t" + posts_cnt);
        String[][] Ocean_cnt_tuple = new String[posts_cnt][6];
        int j = 0;
        while ((Col2 = reader3.readNext()) != null) {
        //    for(int k=0;k<6;k++)
            //    {Ocean_cnt_tuple[j][k] = Col2[k];}
            for (int k = 0; k < 6; k++) {
                //System.out.print(Col2[k]+"\t");
                Ocean_cnt_tuple[j][k] = Col2[k];
                //System.out.print(Ocean_cnt_tuple[j][k]+"\t");
            }
            //System.out.println();
            j++;
        }
        //System.out.println("j:\t"+j);

        getOceanSc(Ocean_cnt_tuple,posts_cnt);
        System.out.println("OCEAN Value: " + fin_arr[0] + fin_arr[1] + fin_arr[2] + fin_arr[3] + fin_arr[4]+"      ");
        String[] OceanSc = new String[2];
        OceanSc[0] =Arrays.toString(fin_arr);
        System.out.println("OCEAN Value: " + OceanSc[0]+"      ");
        OceanSc[1] = "";
        if(fin_arr[0].equals("y"))
        {
            System.out.println("O:" + fin_arr[0]);
            OceanSc[1] += "You are creative and always open to new ideas. You are an intellectual person, always curious and imaginative. You have a broad range of interests and are willing to take new experiences. \n";
        }
        else if(fin_arr[0].equals("o"))
        {
            System.out.println("O:" + fin_arr[0]);            
            OceanSc[1] += "You are quiet a creative person and down to earth. You are a mediocre person and sometimes you get too curious or you dont care at all about stuff\n";
        }
        else if(fin_arr[0].equals("n"))
        {
            System.out.println("O:" + fin_arr[0]);
            OceanSc[1] += "You are a down to earth person. You are more traditional and may struggle with abstract thinking. You are inartistic in nature and have narrow interests.\n";
        }
        if(fin_arr[1].equals("y"))
        {
            System.out.println("C:" + fin_arr[1]);
            OceanSc[1] += "You are conscious in nature and always well-organized. You have high levels of thoughtfulness and have goal directed behaviour. You are neet, punctual, hard-working, self-disciplined and persevering.\n";
        }
        else if(fin_arr[1].equals("o"))
        {
            System.out.println("C:" + fin_arr[1]);
            OceanSc[1] += "You are hard-working but at times you become lazy. You are punctual and self-disciplined but do not give too much emphasis on it.";
        }
        else if(fin_arr[1].equals("n"))
        {
            System.out.println("C:" + fin_arr[1]);
            OceanSc[1] += "You are a negligent person. You are disorganized and lazy person. You don't care much about situations. You prefer spur of the moment action to planning.\n";
        }
        if(fin_arr[2].equals("y"))
        {
            System.out.println("E:" + fin_arr[2]);
            OceanSc[1] += "You are an outgoing and fun lover person. You believe in being optimistic. You seek adventures and enjoy social situations.\n";
        }
        else if(fin_arr[2].equals("o"))
        {
            System.out.println("E:" + fin_arr[2]);
            OceanSc[1] += "You enjoy being an outgoing person but only with selective people who are very close to you.";
        }
        else if(fin_arr[2].equals("n"))
        {
            System.out.println("E:" + fin_arr[2]);
            OceanSc[1] += "You are an introvert, reserved and more of a loner person. You prefer a quiet evening.\n";
        }
        if(fin_arr[3].equals("y"))
        {
            System.out.println("A:" + fin_arr[3]);
            OceanSc[1] += "You are trustworthy and agree with others. You are good natured, helpful and forgiving person. You are more co-operative, kind and show affection towards others.\n";
        }
        else if(fin_arr[3].equals("o"))
        {
            System.out.println("A:" + fin_arr[3]);
            OceanSc[1] += "You are trustworthy but sometimes you don't co-operate with others. You like to help your near and dear ones. ";
        }
        else if(fin_arr[3].equals("n"))
        {
            System.out.println("A:" + fin_arr[3]);
            OceanSc[1] += "You are uncooperative and hostile in nature. You find it hard to empathize with others. You are rude and manipulative. You quickly and confidently assert own rights. You are ruthless, suspicious and sometimes irritable.\n";
        }
        if(fin_arr[4].equals("y"))
        {
            System.out.println("N:" + fin_arr[4]);
            OceanSc[1] += "You are moody and you tend to experience mood swings. You are always worrying about little things. You have anxiety, anger within you and you are prone to stress. You have negative emotions and may get depressed.\n";
        }
        else if(fin_arr[4].equals("o"))
        {
            System.out.println("N:" + fin_arr[4]);
            OceanSc[1] += "You remain calm but get angry when little things you care about go upside-down. You have a tendency to take moderate risks.";
        }
        else if(fin_arr[4].equals("n"))
        {
            System.out.println("N:" + fin_arr[4]);
            OceanSc[1] += "You are unemotional and calm, self-satisfied person. You are stable and don't get irritated by annoyances. Though you are stable you may take unneccesary risks.\n";
        }
        
        
        return OceanSc;
    }

    public String[] getFin_arr(){
    
    return fin_arr;
    }
             
    static void getOceanSc(String[][] recordData,int numposts) {
        int i,o,n,y,tp=0,tn=0,fp=0,fn=0;
        /*
        for(int p=0;p<numposts;p++){
            for(int q=0;q<6;q++){
                System.out.print(recordData[p][q]+"\t");
            }
        System.out.println();
        }*/
       // System.out.println("Post:\t" + numposts);
        
            
        
        
        for (int j = 1; j < 6; j++) {
            i=0;
                //     i=0;
            //         System.out.println("in j :" + j);
//            while (recordData[0].equals(temp)) {
            //call function to remove comma in data
            //       record = remCommaFmData(records[i]);
            //System.out.println("in i :" + i );
            o = 0;
            y = 0;
            n = 0;
            while (i<numposts) {

                
                

                // System.out.println("RecordData :" + recordData[0]);
                if (Integer.parseInt(recordData[i][j]) > 0) {

                    y++;
                    //System.out.println("in y :" + y);
                } else if (Integer.parseInt(recordData[i][j]) < 0) {
                    n++;
                    //System.out.println("in n :" + n);
                } else {
                    o++;
                    //System.out.println("in o :" + o);
                }
                i++;

                    //System.out.println("in i :" + i);
                //Split over newline to get records
            }
            //i = i-1;
            //numPost = i;
            //System.out.println("y :" + y+"\tn :" + n+"\to :" + o);
            if (y > n && y > o) {
                fin_arr[j - 1] = "y";
                tp = tp + y;
                tn = tn + n;
                fn = fn + o;
            } else if (n > y && n > o) {
                fin_arr[j - 1] = "n";
                tp = tp + n;
                tn = tn + y;
                fn = fn + o;
            } else if (o > n && o > y) {
                fin_arr[j - 1] = "o";
                tp = tp + o;
                fp = fp + n + y;

            } else if (o == n || o == y || n == y) {
                fin_arr[j - 1] = "o";
                tp = tp + o;
                fp = fp + n + y;

            }

        }

    }
    
    
    
    
    
    
    static void getOCEAN(String[] recordData) {
        int[][] ocean_cor = {
            /*1.ap*/{-1, -1, -1, -1, -1},
            /*2.cm*/ {1, -1, -1, -1, 1},
            /*3.du*/ {1, 1, -1, 1, 1},
            /*4.em*/ {-1, 0, 0, 1, -1},
            /*5.el*/ {1, -1, -1, -1, -1},
            /*6.im*/ {-1, 1, 1, 1, -1},
            /*7.np*/ {1, -1, -1, 1, 1},
            /*8.ne*/ {1, -1, -1, -1, -1,},
            /*9.nb*/ {-1, -1, -1, -1, 1},
            /*10.pa*/ {-1, -1, -1, -1, 1},
            /*11.pe*/ {1, 1, 1, 1, 1},
            /*12.pp*/ {-1, 1, 0, 1, 1},
            /*13.pr*/ {-1, 1, 1, 1, 1},
            /*14.qm*/ {1, -1, -1, -1, -1},
            /*15.sl*/ {1, 1, -1, -1, 1},
            /*16.sr*/ {-1, -1, 1, -1, -1},
            /*17.sw*/ {1, -1, -1, -1, 0},
            /*18.tt*/ {1, -1, -1, -1, 1},
            /*19.wc*/ {1, -1, -1, 1, 1},
            /*20.we*/ {1, 1, 1, 1, 1},
            /*21.yu*/ {1, -1, -1, -1, 1},
            /*22.mf*/ {-1, 1, 1, 1, -1}
        };
        double[] AS = {8.909119171,1.543055125,0.095069385,2.86059577,0,1.774730704,0.645980693,0.157864055,1.13159992,0.82487682,0.405207116,3.116876197,3.06202949,1.129729632,5.710375835,1.764805497,9.264169906,0.228985854,27.43058036,0.481227937,0.907561621,1.228985855};
        int k;
        int O = 0;
        int C = 0;
        int E = 0;
        int A = 0;
        int N = 0;

        //String[] recordData = null;
        //recordData = feat_tuple.split(",");
        k = 0;
        for (int j = 1; j < 23; j++) {
            if (Float.parseFloat(recordData[j]) > AS[k]) {
                if (ocean_cor[j - 1][0] > 0) {
                    O += 1;
                } else if (ocean_cor[j - 1][0] < 0) {
                    O -= 1;
                }
                if (ocean_cor[j - 1][1] > 0) {
                    C += 1;
                } else if (ocean_cor[j - 1][1] < 0) {
                    C -= 1;
                }
                if (ocean_cor[j - 1][2] > 0) {
                    E += 1;
                } else if (ocean_cor[j - 1][2] < 0) {
                    E -= 1;
                }
                if (ocean_cor[j - 1][3] > 0) {
                    A += 1;
                } else if (ocean_cor[j - 1][3] < 0) {
                    A -= 1;
                }
                if (ocean_cor[j - 1][4] > 0) {
                    N += 1;
                } else if (ocean_cor[j - 1][4] < 0) {
                    N -= 1;
                }
            }
            k++;

        }
        OCEAN_tuple[1] = O + "";
        OCEAN_tuple[2] = C + "";
        OCEAN_tuple[3] = E + "";
        OCEAN_tuple[4] = A + "";
        OCEAN_tuple[5] = N + "";

    }

    static void getFeatures(String status) {
        int cnt = 0;
        String pattern;
        Pattern r;
        Matcher m;
        int wc = 0;

        String[] swearWords = {
            "f.*?u.*?k",
            "s.*?h.*?t",
            "bi.*?h",
            "bas.*?d",
            "m.*?f.*?",
            "c.*?nt",
            "as.*?s",
            "w.*?nk",
            "co.*?on",
            "wo.*?g",
            "ni.*?g.*?r",
            "vagina",
            "c.*?um",
            "p.*?i.*?s",
            "p.*?orn",
            "ar.*?se",
            "nexon",
            "ho.*?r.*?ny",
            "dil.*?do",
            "doggystyle",
            "cl.*?it",
            "fann.*?y",
            "ho.*?re.*?",
            "kn.*?ob",
            "mastur.*?",
            "hitler",
            "n.*?uts",
            "sob.*?",
            "shag.*?",
            "sl.*?ut.*?",
            "testi.*?",
            "t.*?wa.*?t",
            "viagr.*?a",
            "wil.*?ly",
            "wil.*?lie",
            "jism",
            "dog.*?gy",
            "donkeyri.*?b",
            "breas.*?t",
            "bl.*?wjo.*?b",
            "b.*?b",
            "beastiality",
            "an.*?al",
            "cawk",
            "pus.*?s.*?",
            "rim.*?m",
            "ejaculate",
            "ejakulate",
            "er.*?ct",
            "horni",
            "horna",
            "se.*?x",
            "se.*?ck",
            "ga.*?y",
            "fk",
            "suck",
            "we*?nis"
        };

//1. Punctuation:        
        //pattern = "(?![@',&])\\p{Punct}";
        pattern = "[.|,|;|:|!|'|\"|_|{|}|(|)|?|/|-]"; // not ],[,-
        r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        m = r.matcher(status);
        while (m.find()) {
            cnt++;
        }
        //Feat.add(cnt);
        Feat[1] = cnt + "";
        //System.out.println("Punct:   " + cnt);
        cnt = 0;

//2. Commas:        
        pattern = "[,]";
        r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        m = r.matcher(status);
        while (m.find()) {
            cnt++;
        }
        Feat[2] = cnt + "";
        //System.out.println("Commas:   " + cnt);
        cnt = 0;

//3. Reference to other users:        
        pattern = "@";
        r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        m = r.matcher(status);
        while (m.find()) {
            cnt++;
        }
        Feat[3] = cnt + "";
        //System.out.println("Reference:   " + cnt);
        cnt = 0;

//4.    Exclamation:
        pattern = "!";
        r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        m = r.matcher(status);
        while (m.find()) {
            cnt++;
        }
        Feat[4] = cnt + "";
        //System.out.println("Exclamation:   " + cnt);
        cnt = 0;

//5.    External Links:
        pattern = "https?:\\/\\/((?:[\\w\\d]+\\.)+[\\w\\d]{2,})/";
        r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        m = r.matcher(status);
        while (m.find()) {
            cnt++;
        }
        Feat[5] = cnt + "";
        //System.out.println("Ex Links:   " + cnt);
        cnt = 0;

//6.    FPSP:
        pattern = "((?i)\\bI\\b|\\bme\\b|\\bmy\\b|\\bmine\\b)";
        r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        m = r.matcher(status);
        while (m.find()) {
            cnt++;
        }
        Feat[6] = cnt + "";
        //System.out.println("FPSP:   " + cnt);
        cnt = 0;

//7.    Neg Particle:
        pattern = "not|n't";
        r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        m = r.matcher(status);
        while (m.find()) {
            cnt++;
        }
        Feat[7] = cnt + "";
        //System.out.println("Negative Particle:   " + cnt);
        cnt = 0;

//8.    Neg Emotions:
        String[] words;
        int posEmo = 0;
        int negEmo = 0;
        words = status.split(" ");
        for (int j = 0; j < words.length; j++) {
            if (words[j].contains(":(") || words[j].contains(":'(") || words[j].contains(":[") || words[j].contains(">:-(") || words[j].contains(":-?")) {
                negEmo++;
            }
            if (words[j].contains(":)") || words[j].contains(":D") || words[j].contains(":P") || words[j].contains("<3") || words[j].contains(":-]") || words[j].contains("^_^")) {
                posEmo++;
            }
        }
        Feat[8] = negEmo + "";
        Feat[11] = posEmo + "";
        //System.out.println("Pos Emo:   " + posEmo);
        //System.out.println("Neg Emo:   " + negEmo);

//9.    Numbers:
        pattern = "[0-9]+";
        r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        m = r.matcher(status);
        while (m.find()) {
            cnt++;
        }
        Feat[9] = cnt + "";
        //System.out.println("Numbers:   " + cnt);
        cnt = 0;

//10.   Parentheses:
        pattern = "[(|)|{|}|\\[|\\]]";
        //pattern = "[(|)|{|}]+";
        r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        m = r.matcher(status);
        while (m.find()) {
            cnt++;
        }
        Feat[10] = cnt + "";
        //System.out.println("Parentheses:   " + cnt);
        cnt = 0;

//11.   Pos Emotions:
//12.   Prepositions:
        int numberPrep = 0;
        String tagged = tagger.tagString(status);

        pattern = "\\b\\w+(_PRP\\b)";
        //pattern = "[(|)|{|}]+";
        r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        m = r.matcher(tagged);
        while (m.find()) {
            numberPrep++;
        }
        Feat[12] = numberPrep + "";
        //System.out.println("Prep:   " + numberPrep);

//13.   Pronoun:     
        int numberPronoun = 0;
        pattern = "\\b\\w+(_IN\\b)";
        //pattern = "[(|)|{|}]+";
        r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        m = r.matcher(tagged);
        while (m.find()) {
            numberPronoun++;
        }
        Feat[13] = numberPronoun + "";
        //System.out.println("Pronouns:   " + numberPronoun);

//14.   Question:     
        pattern = "[?]";
        r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        m = r.matcher(status);
        while (m.find()) {
            cnt++;
        }
        Feat[14] = cnt + "";
        //System.out.println("Question:   " + cnt);
        cnt = 0;

//15.   Long Words:     
        for (int j = 0; j < words.length; j++) {
            if (words[j].length() > 6) {
                cnt++;
            }
        }
        Feat[15] = cnt + "";
        //System.out.println("Long Words:   " + cnt);
        cnt = 0;

//16.   Self Reference:
        pattern = "((?i) \\bI\\b|\\bme\\b|\\bmy\\b|\\bmine\\b|\\bWe\\b|\\bour\\b|\\bours\\b|\\bus\\b)";
        r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        m = r.matcher(status);
        while (m.find()) {
            cnt++;
        }
        Feat[16] = cnt + "";
        //System.out.println("Self Reference:   " + cnt);
        cnt = 0;

//17.   Swears:
        for (String swearWord : swearWords) {
            r = Pattern.compile(swearWord, Pattern.CASE_INSENSITIVE);
            m = r.matcher(status);
            while (m.find()) {
                cnt++;
            }
        }
        Feat[17] = cnt + "";
        //System.out.println("Swear Words:   " + cnt);
        cnt = 0;

//18.   Type/Token Ratio:        
//19.   Word Count:
        //m = Pattern.compile("\\w+(-\\w+)+|\\w+").matcher(status);
        //while (m.find()) {
        //    wc++;
        //}
//18, 19, 22.
        String[] Tokens = new String[100];
        Tokens = status.split(" ");
        wc = Tokens.length;
        Feat[19] = wc + "";
        // System.out.println("Total Words:   " + wc);

        String[][] Words = new String[2][wc];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < wc; j++) {
                //    System.out.print(" "+Words[i][j]+" ");
                Words[i][j] = "0";
            }
            //System.out.println();
        }

        int val;
        int TotalWords = 0;
        for (int t = 0; t < wc; t++) {
            for (int p = 0; p <= t; p++) {
                if (Tokens[t].equals(Words[0][p])) {

                    val = Integer.parseInt(Words[1][p]);
                    val++;
                    Words[1][p] = val + "";
                    break;

                } else if (Words[0][p].equals("0")) {

                    Words[0][p] = Tokens[t];
                    val = Integer.parseInt(Words[1][p]);
                    val++;
                    Words[1][p] = val + "";
                    TotalWords++;
                    break;
                }
            }
        }

        float tt = (float) (wc - TotalWords) / TotalWords;
        float mwf = (float) wc / TotalWords;
        Feat[18] = tt + "";
        Feat[22] = mwf + "";
        // System.out.println("tt:  " + tt + "\nmwf:   "+mwf);

        /*for (int i = 0; i < 2; i++) {
         for (int j = 0; j < wc; j++) {
         System.out.print(" " + Words[i][j] + " ");
         }
         System.out.println();
         }
         for (int j = 0; j < wc; j++) {
         System.out.print(" " + Tokens[j] + " ");
         }
         System.out.println();
         */
//20.   FPPP:
        m = Pattern.compile("((?i)\\bwe\\b|\\bus\\b|\\bour\\b|\\bours\\b)").matcher(status);
        while (m.find()) {
            cnt++;
        }
        Feat[20] = cnt + "";
        //System.out.println("FPPP:   " + cnt);
        cnt = 0;

//21.   SPSP:
        m = Pattern.compile("((?i)\\byou\\b|\\byour\\b|\\byours\\b)").matcher(status);
        while (m.find()) {
            cnt++;
        }
        Feat[21] = cnt + "";
        //System.out.println("SPSP:   " + cnt);
        cnt = 0;

//22.   Mean word Frequency:
    }
}
