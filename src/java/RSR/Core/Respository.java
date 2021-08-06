package RSR.Core;


import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Respository {

    protected List<Class<?>> classes = new ArrayList<Class<?>>();

    public void readClass(String path) throws ClassNotFoundException {
        if (StringUtils.isEmpty(path)) {
            path = this.getClass().getResource("/").getPath();
        }

        System.out.println(path);

        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                //文件名，不包含路径
                String fileName = tempList[i].getName();
                String filePath = tempList[i].getPath();
                if (Pattern.matches(fileName, "+.class")) {
                    //
//                    Class<?> aClass = new RSRClassLoader(filePath).findClass(fileName.replace("/","."));
                    System.out.println(fileName);
                    System.out.println(filePath);
                }
            }
            if (tempList[i].isDirectory()) {
                //这里就不递归了，
                readClass(tempList[i].getPath());
            }
        }
    }
}
