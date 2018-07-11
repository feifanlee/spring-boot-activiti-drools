package com.xc.util;

import com.xc.config.DroolsMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * Created by lifeifan on 2017/11/3.
 */
public class PropertyUtil {
    static Logger LOGGER = LoggerFactory.getLogger(PropertyUtil.class);

    public static  ThreadLocal<Map<String,Properties>> props = new ThreadLocal<>();
    private static ThreadLocal<Map<String,String>> path = new ThreadLocal<>();

    public static void setPath(String custpath, String type){
        if (path.get()==null){
            path.set(new HashMap<String, String>());
        }
        path.get().put(type, custpath);
    }

    public static String getPath(String type) {
        return path.get().get(type);
    }

    public synchronized static void refresh(String type) throws Exception{
        read(type);
    }

    protected static void read(String type) throws Exception{
        String ppath = path.get().get(type)==null?"config/drools.properties":path.get().get(type);
        File pfile = new File(ppath);
        InputStream is ;
        if(pfile.exists()) {
             is = new FileInputStream(pfile);
             LOGGER.info("Read property file:{}",pfile.getAbsolutePath());
        }else{
            LOGGER.info("outside propeties file not found, check resource file.  --"+ppath);
            is = ClassLoader.getSystemResourceAsStream("config/drools.properties");
        }
        props.get().put(type, new Properties());
        props.get().get(type).load(is);
        is.close();
    }

//    protected static void read() throws Exception{
//        String ppath = path.get()==null?"config":path.get();
//
//        File pfile = new File(ppath);
//        InputStream is = null;
//
//        if(pfile.exists()) {
//            if (pfile.isDirectory()){
//                List<File> files = unfolder(pfile);
//                Vector<InputStream> streams = new Vector<InputStream>();
//                for (File file : files) {
//                    InputStream inputStream = new FileInputStream(file);
//                    streams.add(inputStream);
//                }
//                is = new SequenceInputStream(streams.elements());
//            }else {
//                is = new FileInputStream(pfile);
//                LOGGER.info("Read property file:{}", pfile.getAbsolutePath());
//            }
//        }else{
//            LOGGER.info("outside propeties file not found, check resource file.  --"+ppath);
//            List<File> files =  unfolder(new File(ClassLoader.getSystemResource("config").getFile()));
//            Vector<InputStream> streams = new Vector<InputStream>();
//            for (File file : files) {
//                InputStream inputStream = new FileInputStream(file);
//                streams.add(inputStream);
//            }
//            is = new SequenceInputStream(streams.elements());
////            is = ClassLoader.getSystemResourceAsStream("config/drools.properties");
//        }
//
//        props.set(new Properties());
//        props.get().load(is);
//        is.close();
//    }

    public synchronized static String get(String key,String type) {
        try {
            if (props.get() == null || props.get().get(type)==null) {
                props.set(new HashMap<String, Properties>());
                read(type);
            }
        }catch(Exception e){
            LOGGER.error("Read properties error!");
        }
        try {
            return new String( props.get().get(type).getProperty(key).getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public synchronized static Properties getProps(String type) {
        try {
            if (props.get() == null) {
                props.set(new HashMap<String, Properties>());
                read(type);
            }
        }catch(Exception e){
            LOGGER.error("Read properties error!");
        }
        return props.get().get(type);
    }
//    public synchronized static void set(Properties outsideProps){
//        if(props!=null){
//            LOGGER.info("Properties already exists. Now set new properties: {}",outsideProps);
//        }
//        props.set(outsideProps);
//    }


    public static List<File> unfolder(File path){
        List<File> files = new ArrayList<>();
        if(path.exists()){
            if(path.isFile() && !path.getName().startsWith(".")){
                files.add(path);
            }else if(path.isDirectory() && !path.getName().startsWith(".")){
                File[] fs = path.listFiles();
                for(File f : fs){
                    files.addAll(unfolder(f));
                }
            }

            else{
                LOGGER.info("unexpected case:"+path.toString());
            }
        }
        return files;
    }

    public static void main(String[] args) throws IOException {
//        List<File> files = unfolder(new File(ClassLoader.getSystemResource("config").getFile()));
//        Vector<InputStream> streams = new Vector<InputStream>();
//        for (File file : files) {
//            InputStream inputStream = new FileInputStream(file);
//            streams.add(inputStream);
//        }
//
//        InputStream is = new SequenceInputStream(streams.elements());
//        Properties prop = new Properties();
//        prop.load(is);
//        for (Object o : prop.keySet()) {
//            System.out.println(o);
//        }
////        prop.propertyNames();
////        System.out.println(get("expr1"));
    }
}
