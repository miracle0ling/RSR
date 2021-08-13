package RSR.Core;


import org.apache.commons.lang3.StringUtils;
import sun.font.ScriptRun;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Respository {

    private String path;

    public Respository(String path) {
        this.path = path;
    }

    protected List<Class<?>> classes = new ArrayList<Class<?>>();

    public void readClass(String path) throws ClassNotFoundException {
        if (StringUtils.isEmpty(path)) {
            path = this.getClass().getResource("/").getPath();
        }

        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                //文件名，不包含路径
                String fileName = tempList[i].getName();
                String filePath = tempList[i].getPath();

                if (Pattern.matches(".*.class", fileName)) {
                    //加载类
                    System.out.println("fileName :" + fileName);
                    System.out.println("filePath :" + filePath);
                    String spilt = "classes\\\\";
                    String classesName = filePath.split(spilt)[1].replace("\\", ".");
                    System.out.println("classesName " + classesName);
                    Class<?> aClass = new RSRClassLoader(filePath).findClass(classesName);
                    if (aClass != null) {
                        this.classes.add(aClass);
                    }
                }
            }
            if (tempList[i].isDirectory()) {
                readClass(tempList[i].getPath());
            }
        }
    }
}
