package com.usongon.driverFriend.config;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.sql.Types;
import java.util.Collections;

/**
 * @author usong
 * @date 2023-11-08 00:23
 */
public class MybtaisPlusCodeGeneratorUtil {
    public static String dbUrl = "jdbc:mysql://127.0.0.1:3306/usong_test?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai";
    public static String javaDir = "/src/main/java";
    public static String resourcesDir = "/src/main/resources";
    public static String mapperDir = "/src/main/resources/mapper";
    public static String parentPackage = "com.usongon.usongdemo";
    public static String include = "student";

    public static void main(String[] args) {
        //当前项目目录
        String projectPath = System.getProperty("user.dir");
        FastAutoGenerator.create(dbUrl, "root", "Vw@X!^iM")
                // 1. 全局配置
                .globalConfig(builder -> {
                    // 设置作者
                    builder.author("usong")
                            // 指定输出目录
                            .outputDir(projectPath + javaDir);
                })
                // 2. 数据库字段转换配置
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    if (typeCode == Types.TIMESTAMP) {
                        // 时间戳转换成date
                        return DbColumnType.DATE;
                    }
                    if (typeCode == Types.TINYINT) {
                        // tinyint转换成Integer
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);
                }))
                // 3. 包配置
                .packageConfig(builder -> {
                    // 设置父包名
                    builder.parent(parentPackage)
                            // 设置父包模块名
//              .moduleName("test")
                            .controller("controller")
                            .entity("dao")
                            .mapper("mapper")
                            .service("service")
                            .serviceImpl("service.impl")
                            .xml("mapper")
                            // 单独设置某个包的生成路径，xml文件我习惯放resource中
                            .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + mapperDir));
                })
                // 4. 策略配置
                .strategyConfig(builder -> {
                    builder.enableCapitalMode().enableSkipView()
                            // 设置需要生成的表
                            .addInclude(include.split(","))
                            // 设置过滤表前缀
                            .addTablePrefix("t_")
                            // 实体类
                            .entityBuilder()
                            .enableFileOverride()
                            // 表名驼峰
                            .naming(NamingStrategy.underline_to_camel)
                            // 字段名驼峰
                            .columnNaming(NamingStrategy.underline_to_camel)
                            .enableLombok()
                            .enableActiveRecord()
                            // controller类
                            .controllerBuilder()
                            .enableFileOverride()
                            .enableRestStyle()
                            // service
                            .serviceBuilder()
                            .enableFileOverride()
                            // mapper
                            .mapperBuilder()
                            .enableFileOverride()
                            // 我习惯叫dao
                            .formatMapperFileName("%sMapper")
                            .formatXmlFileName("%sXml")
                    ;
                })
                // 5. 配置模板，使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateConfig(builder -> {
                    builder
                            // 不使用模板也就不生成文件了
                            .disable(TemplateType.CONTROLLER)
                            .controller("/templates/controller.java.vm")
                            .service("/templates/service.java.vm")
                            .serviceImpl("/templates/serviceImpl.java.vm")
                            .entity("/templates/entity.java.vm")
                            .mapper("/templates/mapper.java.vm")
                            .xml("/templates/mapper.xml.vm")
                    ;

                })
                // 6. 注入自定义配置，没用到
                .injectionConfig(builder -> {
//          builder.customFile(null);
                })
                .execute();
    }
}
