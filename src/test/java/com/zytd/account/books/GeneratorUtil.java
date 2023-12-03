package com.zytd.account.books;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Collections;

public class GeneratorUtil {
    //数据库
    public static final String URL = "jdbc:mysql://60.204.171.249/account_book?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "mtwlc123";

    //输出目录
    public static final String PROJECT_NAME = "account-books-server";
    public static final String OUT_DIR = "/src/main/java/";
    public static final String PARENT_DIR = "com.zytd.account.books";
    //xml生成路径
    public static final String XML_URL = "/src/main/resources/mapper/";

    public static final String AUTHOR = "wl";

    //表前缀
    public static final String TABLE_PREFIX = "";
    //表名
    public static final String[] TABLE_NAMES = new String[]{"member_verification_code"};
    //自定义实体，公共字段
    public static final String[] SUPER_ENTITY_COLUMNS = new String[]{"id", "deleted", "create_time", "create_people", "modify_time", "modify_people"};
    //自定义公共字段实体父类
    public static final String SUPER_ENTITY_CLASS = "com.hn.info.model.BaseEntity";
    //自定义xml模板
    public static final String TEMPLATE_PATH = "/templates/mapper.xml.vm";

    public static void main(String[] args) {
        DataSourceConfig dsc = new DataSourceConfig()
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUrl(URL)
                .setUsername(USERNAME)
                .setPassword(PASSWORD)
                .setDbType(DbType.MYSQL)
                .setTypeConvert(new MySqlTypeConvert() {
                        @Override
                        public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                            //数据库中的datetime 类型默认会转换为java中的LocalDateTime 这里自定转换为Data类型
                            if (fieldType.toLowerCase().contains("datetime")) {
                                return DbColumnType.DATE;
                            }
                            return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
                        }
                    }
                );

        GlobalConfig gc = new GlobalConfig()
                .setOutputDir(PROJECT_NAME + OUT_DIR)   //文件输出目录
                .setAuthor(AUTHOR)
                .setOpen(false)
//                .setSwagger2(true)
                .setServiceName("%sService")
//                .setIdType(IdType.AUTO)
                .setFileOverride(true)  //是否覆盖文件
                .setBaseResultMap(true) // XML ResultMap
                .setBaseColumnList(true);   //XML columList

        PackageConfig pc = new PackageConfig()
                .setParent(PARENT_DIR)
                .setEntity("model") //设置文件所放置的包名的位置
                .setMapper("dao");

        StrategyConfig sc = new StrategyConfig()
                .setNaming(NamingStrategy.underline_to_camel)    //下划线转驼峰命名
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setRestControllerStyle(true)  //是否开启RestController注解
                .setInclude(TABLE_NAMES)  //需要生成的表
//                .setSuperEntityClass(SUPER_ENTITY_CLASS)    // 自定义实体父类
//                .setSuperEntityColumns(SUPER_ENTITY_COLUMNS)    // 自定义实体，公共字段
                .setEntityBooleanColumnRemoveIsPrefix(true)
                .setEntityLombokModel(true)  //是否启用lombok开启注解
//                .setLogicDeleteFieldName("") //设置删除字段
                .setTablePrefix(new String[]{TABLE_PREFIX});    //设置表名前缀

        TemplateConfig tc = new TemplateConfig()
//                .setController(null)    //重新生成文件是否覆盖controller，可以自定义
                .setXml(null);  //关闭默认 xml 生成，调整生成至根目录

        InjectionConfig ic = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        }.setFileOutConfigList(Collections.singletonList(new FileOutConfig(TEMPLATE_PATH) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件目录
                return PROJECT_NAME + XML_URL + tableInfo.getEntityName() + ".xml";
            }
        }));

        AutoGenerator ag = new AutoGenerator()
                .setDataSource(dsc)
                .setGlobalConfig(gc)
                .setPackageInfo(pc)
                .setStrategy(sc)
                .setTemplate(tc)
                .setCfg(ic)
                ;
        ag.execute();
    }
}
