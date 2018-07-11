package com.xc.rule;

import com.xc.util.PropertyKeys;
import com.xc.util.PropertyUtil;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lifeifan on 2017/11/2.
 */
//@Component
public class TextRule extends BaseRule {
    public final static String DRLDIR = PropertyKeys.DROOLS_DIR_RULE;

    public TextRule(){
        super();
    }

    public TextRule(File path) throws Exception{
        super();
        init(path);
    }

    @Override
    public void init(File path) throws Exception{
        if(!path.exists()) {
            LOGGER.error("Path not exists: {}", path.getAbsolutePath());
        }
        List<File> files = PropertyUtil.unfolder(path);
        for(File f :files){
            LOGGER.info(f.toString());
        }
        List<String> texts = appendFile(files);

        for(String t : texts){
            LOGGER.info("===drl:===");
            LOGGER.info(t);
            LOGGER.info("==========");
        }
        this.kSession = calckSession(texts);
    }

    private List<String> appendFile(List<File> files) throws IOException {
        List<String> texts = new ArrayList<>();
        for(File file:files){
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
            while((line=br.readLine())!=null){
                sb.append(line+'\n');
            }
            br.close();
            texts.add(sb.toString());
        }
        return texts;
    }

    /**
     * 将多个drools规则集合为一个
     * @param paths
     * @throws Exception
     */
    public void init(List<String> paths) throws Exception{
        List<String> texts = new ArrayList<>();
        for (String in : paths) {
            File path = new File(in);
            if(!path.exists()) {
                LOGGER.error("Path not exists: {}", path.getAbsolutePath());
            }
            List<File> files = PropertyUtil.unfolder(path);
            for(File f :files){
                LOGGER.info(f.toString());
            }
            texts = appendFile(files);
            for(String t : texts){
                LOGGER.info("===drl:===");
                LOGGER.info(t);
                LOGGER.info("==========");
            }
        }
        this.kSession = calckSession(texts);
    }

//    protected List<File> unfolder(File path){
//        List<File> files = new ArrayList<>();
//        if(path.exists()){
//            if(path.isFile() && !path.getName().startsWith(".")){
//                files.add(path);
//            }else if(path.isDirectory() && !path.getName().startsWith(".")){
//                File[] fs = path.listFiles();
//                for(File f : fs){
//                    files.addAll(unfolder(f));
//                }
//            }
//
//            else{
//                LOGGER.info("unexpected case:"+path.toString());
//            }
//        }
//        return files;
//    }


    public KieSession calckSession(List<String> rules) throws Exception{
        KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
        for (String rule : rules){
            kb.add(ResourceFactory.newByteArrayResource(rule.getBytes("utf-8")), ResourceType.DRL);
        }
        for(KnowledgeBuilderError error : kb.getErrors()){
                LOGGER.error(error.getMessage());
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kb.getKnowledgePackages());

//        return kbase.newStatefulKnowledgeSession();
        return kbase.newKieSession();
    }

}
