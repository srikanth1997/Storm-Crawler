/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;

import java.util.Scanner;
import java.net.MalformedURLException;
import java.net.URL;
/**
 *
 * @author srikanth
 */
public class WebCrawler {

    /**
     */
    public static String flip2;
    public static void main(String[] args) {
            Scanner kb=new Scanner(System.in);
            System.out.println("Enter URL to be crawled");
            flip2=kb.next();
            URL flip=null;
            try{
                flip=new URL(flip2);
                //Crawler obj=new Crawler(flip);
                //String upage=obj.download();
                //System.out.println(upage);
       
            }
            catch(Exception e){
                System.out.println("xyz");
            }
        Crawler obj=new Crawler(flip);
        obj.crawl(flip2);
    }
        // TODO code application logic here
    }
    

