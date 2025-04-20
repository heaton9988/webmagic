//package generator;
//
//
//import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
//import com.baomidou.mybatisplus.core.toolkit.StringPool;
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.config.*;
//import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//
//import java.io.File;
//import java.lang.reflect.Field;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//
///**
// * https://zhuanlan.zhihu.com/p/161871962
// */
//public class MyBatisPlusGenerator {
//
//    /**
//     * 代码生成位置
//     */
//    public static final String PACKAGE_NAME = "com.zzj.crawler.appstore";
//
//    /**
//     * modular 名字
//     */
//    public static final String MODULAR_NAME = "c";
//
//    /**
//     * 基本路径
//     */
//    public static final String SRC_MAIN_JAVA = "/src/main/java/";
//
//    /**
//     * 作者
//     */
//    public static final String AUTHOR = "MyBatisPlusGenerator";
//
//    /**
//     * 是否是 rest 接口
//     */
//    private static final boolean REST_CONTROLLER_STYLE = true;
//
//    public static final String JDBC_MYSQL_URL = "jdbc:mysql://localhost:3306/crawler_appstore?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC&rewriteBatchedStatements=TRUE";
//
//    public static final String JDBC_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
//
//    public static final String JDBC_USERNAME = "root";
//
//    public static final String JDBC_PASSWORD = "12345678";
//
//    /**
//     *
//     * 读取控制台内容
//     *
//     */
//    public static String scanner(String tip) {
//        Scanner scanner = new Scanner(System.in);
//        StringBuilder help = new StringBuilder();
//        help.append("请输入" + tip + "：");
//        System.out.println(help.toString());
//        if (scanner.hasNext()) {
//            String ipt = scanner.next();
//            if (StringUtils.isNotBlank(ipt)) {
//                return ipt;
//            }
//        }
//        throw new MybatisPlusException("请输入正确的" + tip + "！");
//    }
//    public static void main(String[] args) {
//        // 实例化代码生成器
//        AutoGenerator mpg = new AutoGenerator();
//
//        // 全局配置
//        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(projectPath + SRC_MAIN_JAVA);
//        gc.setAuthor(AUTHOR);
//        gc.setOpen(false);
//        gc.setFileOverride(true);
//        gc.setSwagger2(true);
//        mpg.setGlobalConfig(gc);
//
//        // 数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl(JDBC_MYSQL_URL);
//        // dsc.setSchemaName("public");
//        dsc.setDriverName(JDBC_DRIVER_NAME);
//        dsc.setUsername(JDBC_USERNAME);
//        dsc.setPassword(JDBC_PASSWORD);
//        mpg.setDataSource(dsc);
//
//        // 包配置（生成的entity、controller、service等包名）
//        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(MODULAR_NAME);
//        pc.setParent(PACKAGE_NAME);
//        mpg.setPackageInfo(pc);
////        //自定义配置
////        InjectionConfig cfg = new InjectionConfig() {
////            @Override
////            public void initMap() {
////                // to do nothing
////            }
////        };
////        // 如果模板引擎是 freemarker
////        String templatePath = "/templates/mapper.xml.ftl";
////        // 如果模板引擎是 velocity
////        // String templatePath = "/templates/mapper.xml.vm";
////
////        // 自定义输出配置(mapper.xml的文件地址)
////        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
////        focList.add(new FileOutConfig(templatePath) {
////            @Override
////            public String outputFile(TableInfo tableInfo) {
////                return projectPath+"/src/main/resources/mapper/"+tableInfo.getEntityName()+"Mapper"+ StringPool.DOT_XML;
////            }
////        });
////        cfg.setFileOutConfigList(focList);
////        mpg.setCfg(cfg);
////
////        // 配置默认模板
////        TemplateConfig templateConfig = new TemplateConfig();
////        //取消默认mapper的生成地址
////        templateConfig.setXml(null);
////        mpg.setTemplate(templateConfig);
//
//        // 数据库表策略设置
//        StrategyConfig strategy = new StrategyConfig();
//        //数据库表映射到实体的命名
//        strategy.setNaming(NamingStrategy.underline_to_camel);
//        //数据库表字段映射到实体的命名
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        //strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
//        //【实体】是否为lombok模型（默认 false）
//        strategy.setEntityLombokModel(true);
//        //生成 @RestController 控制器
//        strategy.setRestControllerStyle(REST_CONTROLLER_STYLE);
//
//        // strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
//        // 写于父类中的公共字段
//        // strategy.setSuperEntityColumns("id");
//        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
//        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(pc.getModuleName() + "_");
//        mpg.setStrategy(strategy);
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
//
//        //自定义文件生成路径，包路径
//        //这里调用customPackagePath方法，使用可以自己在内部灵活配置路径
//        //如果不调用该方法、就会使用MyBatis-Plus默认的文件生成路径和包路径生成文件、但可以使用上面的PackageConfig做一些简单的配置
//        try {
//            customPackagePath(pc,mpg);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//        mpg.execute();
//    }
//
//
//    /**
//     * https://blog.csdn.net/qq_29550537/article/details/105477585
//     * 自定义包路径，文件生成路径，这边配置更灵活
//     * 虽然也可以使用InjectionConfig设置FileOutConfig的方式设置路径
//     * 这里直接使用Map方式注入ConfigBuilder配置对象更加直观
//     * @param pc
//     * @param mpg
//     * @throws NoSuchFieldException
//     * @throws IllegalAccessException
//     */
//    public static void customPackagePath(PackageConfig pc,AutoGenerator mpg) throws NoSuchFieldException, IllegalAccessException {
//        String moduleName = "crawler-appstore";
//
//        String projectPath = "/Users/xingchuan/Desktop/mybatis-plus-generator";
//        String mavenPath = "/src/main/java/";
//        String dataModelModule = projectPath + "/" + moduleName;
//        String controllerPath = projectPath + "/" + moduleName + mavenPath;
//        String servicePath = projectPath + "/" + moduleName + mavenPath;
//        String modelPath =   dataModelModule + mavenPath;
//
//        //mapper.xml的包路径
//
//
//        /**
//         * packageInfo配置controller、service、serviceImpl、entity、mapper等文件的包路径
//         * 这里包路径可以根据实际情况灵活配置
//         */
//        Map<String,String> packageInfo = new HashMap<>();
//        //asset数据库
////        packageInfo.put(ConstVal.CONTROLLER, PACKAGE_NAME + ".controller");
//        packageInfo.put(ConstVal.SERVICE, PACKAGE_NAME + ".repo");
//        packageInfo.put(ConstVal.SERVICE_IMPL, PACKAGE_NAME + ".repo.impl");
//        packageInfo.put(ConstVal.ENTITY, PACKAGE_NAME + ".data.model.dao");
//        packageInfo.put(ConstVal.MAPPER, PACKAGE_NAME + ".data.mapper");
//
//        /**
//         * pathInfo配置controller、service、serviceImpl、entity、mapper、mapper.xml等文件的生成路径
//         * srcPath也可以更具实际情况灵活配置
//         * 后面部分的路径是和上面packageInfo包路径对应的源码文件夹路径
//         * 这里你可以选择注释其中某些路径，可忽略生成该类型的文件，例如:注释掉下面pathInfo中Controller的路径，就不会生成Controller文件
//         */
//        Map pathInfo = new HashMap<>();
////        pathInfo.put(ConstVal.CONTROLLER_PATH, controllerPath + packageInfo.get(ConstVal.CONTROLLER).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
//        pathInfo.put(ConstVal.SERVICE_PATH, servicePath + packageInfo.get(ConstVal.SERVICE).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
//        pathInfo.put(ConstVal.SERVICE_IMPL_PATH, servicePath + packageInfo.get(ConstVal.SERVICE_IMPL).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
//        pathInfo.put(ConstVal.ENTITY_PATH, modelPath + packageInfo.get(ConstVal.ENTITY).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
//        pathInfo.put(ConstVal.MAPPER_PATH, modelPath + packageInfo.get(ConstVal.MAPPER).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
//        pathInfo.put(ConstVal.XML_PATH, dataModelModule+"/src/main/resources/mapper/" + moduleName);
//        pc.setPathInfo(pathInfo);
//
//        /**
//         * 创建configBuilder对象，传入必要的参数
//         * 将以上的定义的包路径packageInfo配置到赋值到configBuilder对象的packageInfo属性上
//         * 因为packageInfo是私有成员变量，也没有提交提供公共的方法，所以使用反射注入
//         * 为啥要这么干，看源码去吧
//         */
//        ConfigBuilder configBuilder = new ConfigBuilder(mpg.getPackageInfo(), mpg.getDataSource(), mpg.getStrategy(), mpg.getTemplate(), mpg.getGlobalConfig());
//        Field packageInfoField = configBuilder.getClass().getDeclaredField("packageInfo");
//        packageInfoField.setAccessible(true);
//        packageInfoField.set(configBuilder,packageInfo);
//
//        /**
//         * 设置配置对象
//         */
//        mpg.setConfig(configBuilder);
//    }
//}
