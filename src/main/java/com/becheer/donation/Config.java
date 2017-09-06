package com.becheer.donation;

import com.becheer.donation.utils.TaleUtils;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class Config {
//    private static Properties properties = TaleUtils.getPropFromFile("application.properties");

    public String imageRoot;
    public String projectImage;

    public String getImageRoot() {
        return imageRoot;
    }

    public String getProjectImage() {
        return projectImage;
    }


    public Config(){
        //todo  配置文件读取不到，此处暂时搁置
//        this.imageRoot=properties.getProperty("resource.imageRoot");
//        this.projectImage=properties.getProperty("resource.projectImage");
        this.imageRoot="image/";
        this.projectImage="image/project/";
    }

    public static Config getConfig(){
        return new Config();
    }
}
