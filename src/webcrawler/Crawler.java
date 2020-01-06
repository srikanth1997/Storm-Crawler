/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;

/**
 *
 * @author srikanth
 
 */
import java.net.*;
import java.util.*;
import java.io.*;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Crawler {
    public static final boolean DEBUG = false;
        public static final String DISALLOW = "Disallow:";
     public URL flip1;
        public String page;
        public Crawler(URL flip){
        flip1=flip;
        
    }
        ArrayList<String> list=new ArrayList<>();
        ArrayList<String> flist=new ArrayList<>();
        
        public boolean robotExclusion(URL flip1){
       String strHost=flip1.getHost();
       String strRobot="https://"+strHost+"/robots.txt";
       URL urlRobot;
       try{
           urlRobot=new URL(strRobot);
       }
       catch(MalformedURLException e1){
           return false;
       }
       String strCommands = null;
       try{
           try (InputStream urlRobotStream = urlRobot.openStream()) {
               byte b[] = new byte[1000];
               int numRead = urlRobotStream.read(b);
               strCommands = new String(b, 0, numRead);
               while (numRead != -1) {
                   numRead = urlRobotStream.read(b);
                   if (numRead != -1) {
                       String newCommands = new String(b, 0, numRead);
                       strCommands += newCommands;
                   }
               }
               //System.out.println(strCommands);
               urlRobotStream.close();
           }
        }
       catch(IOException e2){
           return true;
        }
       //if (DEBUG) System.out.println(strCommands);
        String strURL = flip1.getFile();
	int index = 0;
	while ((index = strCommands.indexOf(DISALLOW, index)) != -1) {
	    index += DISALLOW.length();
	    String strPath = strCommands.substring(index);
	    StringTokenizer st = new StringTokenizer(strPath);
            
            if (!st.hasMoreTokens())
		break;
	    String strBadPath = st.nextToken();
	    if (strURL.indexOf(strBadPath) == 0)
		return false;
	}
        return true;
    }
   public String download(URL flip1){
    try{
        
        //URL flip=new URL(flip1);
        BufferedReader in=new BufferedReader(new InputStreamReader(flip1.openStream()));
        String il;
        while((il=in.readLine())!=null){
            //System.out.println(il);
            page=page+il+"\n";
           
        }
        in.close();
        
    }
     catch(Exception e){
            System.out.println(e);
        }
    return page;
    }
   public void crawl(String flip3){
//above fn ret true if there are any disallows 
        list.add (flip3);
        flist.add(flip3);
        URL url=null;
        try{
          url=new URL(list.get(0));
          //list.remove(0);
        }
        catch(MalformedURLException e2){
            System.out.println("xyz");
        }
        for(int i=0;i<list.size();i++){
       if(robotExclusion(url)){
           //System.out.println(flip1htt);
           String page1=download(url);
           //System.out.println(page1);
           //String[] words=page1.split(" ");
           //System.out.println(words[12]);
           //ArrayList<String> list=new ArrayList<>();
           String innerHTML=page1;
           Pattern p = Pattern.compile("a href=\"(.*?)\"", Pattern.DOTALL);
           Matcher m = p.matcher(innerHTML);
           //System.out.println(m.group(1));

           while (m.find()) 
           {
                //System.out.println(m.group(1));
                list.add(m.group(1));
                flist.add(m.group(1));
                
           }
           for(String s:list)
                System.out.println(s);  
            //System.out.println(page2);
            }
       int k=list.size();
       for(int j=0;j<list.size();j++){
           list.remove(j);
                     
       }
                   
           //System.out.println(list.get(2));
           /*for(int i=0;i<words.length;i++){
               //System.out.println(words[i]);
            try{
            URL url=new URL(words[i]);
            // If possible then replace with anchor...
            System.out.println(url);    
            }
            catch(MalformedURLException e2){
                //System.out.println("exception occured");
            }*/
                 
               /*if(words[i]=="<a"){
                   i++;
                   String k=words[i];
                   //System.out.println(k);
                   String k1=k.substring(5,k.length()-1)+" ";
                   //System.out.println(k1);
               }8*/
           }
   }
           

            //for(String word:words)
             //  System.out.println(word);
           //System.out.println("false returned");
           //System.out.println(page1);
       }
       
   
    

