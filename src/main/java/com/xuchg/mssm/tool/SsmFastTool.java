package com.xuchg.mssm.tool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;

import com.xuchg.mssm.module.User;



/**
 * @author mac
 * 快速生成一个vo类的xml／dao／service／serviceImpl
 * 使用注意：
 * 	创建表的时候表中的字段全部小写
 * 	vo类中的属性使用驼峰命名法，并且vo类中的属性顺序要和表中的字段顺序相对应
 * 	表中字段的第一个属性要求是主键
 * 	使用时把vo类的Class传值进方法即可生成dao，xml，service，serviceImpl
 */
public class SsmFastTool {

	static String packageName = "com.xuchg.mssm";
	static String packagePath = "com/xuchg/mssm";
	public static void main(String[] args) throws Exception{
		Class c[] = {User.class};
		for(Class c1:c){
			build(c1);
			
		}
	}
	/**
	 * @param c
	 * @throws Exception
	 */
	public static void build(Class c) throws Exception{
		Field f[] = c.getDeclaredFields();
		File file = new File("SsmFastTool.java");
		String total = file.getAbsolutePath();
		String name = file.getPath();
		String realPath = total.substring(0, total.length()-name.length());
		File f1 = new File(realPath+"/src/main/java/"+packagePath+"/dao/"+c.getSimpleName()+"Dao.java");
		File f2 = new File(realPath+"/src/main/java/"+packagePath+"/module/"+c.getSimpleName()+"Mapping.xml");
		File f3 = new File(realPath+"/src/main/java/"+packagePath+"/service/"+c.getSimpleName()+"Service.java");
		File f4 = new File(realPath+"/src/main/java/"+packagePath+"/service/Impl/"+c.getSimpleName()+"ServiceImpl.java");

				f1.createNewFile();
				f2.createNewFile();
				f3.createNewFile();
				f4.createNewFile();

				writeDao(f1,c);
				writeXml(f2,c);
				writeService(f3,c);
				writeServiceImpl(f4,c);
	}

	public static void writeServiceImpl(File file,Class c) throws IOException{
		PrintWriter bos = new PrintWriter(new FileWriter(file));
		Field f[] = c.getDeclaredFields();
		bos.println("package "+packageName+".service.impl;");
		bos.println("import java.util.ArrayList;");
		bos.println("import java.util.List;");
		bos.println("import org.springframework.beans.factory.annotation.Autowired;");
		bos.println("import org.springframework.stereotype.Service;");
		bos.println("import "+packageName+".dao."+c.getSimpleName()+"Dao;");
		bos.println("import "+packageName+".module."+c.getSimpleName()+";");
		bos.println("import "+packageName+".service."+c.getSimpleName()+"Service;");
		bos.println("@Service");
		bos.println("public class "+c.getSimpleName()+"ServiceImpl implements "+c.getSimpleName()+"Service {");
		bos.println("@Autowired");
		bos.println("	"+c.getSimpleName()+"Dao "+c.getSimpleName().toLowerCase()+"Dao;");
		bos.println("	public List<"+c.getSimpleName()+"> get"+c.getSimpleName()+"ByPage(int page, int sum) {");
		bos.println("		int startIndex = (page-1)*sum;");
		bos.println("		List<"+c.getSimpleName()+"> list="+c.getSimpleName().toLowerCase()+"Dao.get"+c.getSimpleName()+"ByPage(startIndex, sum);");
		bos.println("		return list;");
		bos.println("	}");
		bos.println("	public int getPage(int sum) {");
		bos.println("		List<"+c.getSimpleName()+"> list="+c.getSimpleName().toLowerCase()+"Dao.getPage();");
		bos.println("		int pagenum = (int)list.size()/sum+1;");
		bos.println("		return pagenum;");
		bos.println("	}");
		bos.println("	public void add"+c.getSimpleName()+"("+c.getSimpleName()+" "+c.getSimpleName().toLowerCase()+") {");
		bos.println("		"+c.getSimpleName().toLowerCase()+"Dao.add"+c.getSimpleName()+"("+c.getSimpleName().toLowerCase()+");");
		bos.println("	}");
		bos.println("	public "+c.getSimpleName()+" get"+c.getSimpleName()+"ById(int "+f[0].getName()+") {");
		bos.println("		return "+c.getSimpleName().toLowerCase()+"Dao.get"+c.getSimpleName()+"ById("+f[0].getName()+").get(0);");
		bos.println("	}");
		bos.println("	public void change"+c.getSimpleName()+"Info("+c.getSimpleName()+" "+c.getSimpleName().toLowerCase()+") {");
		bos.println("		"+c.getSimpleName().toLowerCase()+"Dao.change"+c.getSimpleName()+"Info("+c.getSimpleName().toLowerCase()+");");
		bos.println("	}");
		bos.println("	public void delete"+c.getSimpleName()+"ById(int "+f[0].getName()+") {");
		bos.println("		"+c.getSimpleName().toLowerCase()+"Dao.delete"+c.getSimpleName()+"ById("+f[0].getName()+");");
		bos.println("	}");
		bos.println("	@Override");
		bos.println("	public List<"+c.getSimpleName()+"> getAll() {");
		bos.println("	return "+c.getSimpleName().toLowerCase()+"Dao.getPage();");
		bos.println("	}");
		bos.println("}	");
		bos.flush();
		bos.close();
		System.out.println(c.getSimpleName()+"ServiceImpl.java 生成成功！");
	}
	
	public static void writeService(File file,Class c) throws IOException{
		PrintWriter bos = new PrintWriter(new FileWriter(file));
		Field f[] = c.getDeclaredFields();
		bos.println("package "+packageName+".service;");
		bos.println("import java.util.List;");
		bos.println("import "+packageName+".module."+c.getSimpleName()+";");
		bos.println("	public interface "+c.getSimpleName()+"Service {");
		bos.println();
		bos.println("		public List<"+c.getSimpleName()+"> get"+c.getSimpleName()+"ByPage(int page,int sum);");
		bos.println();
		bos.println("		public int getPage(int sum);");
		bos.println();
		bos.println("		public void add"+c.getSimpleName()+"("+c.getSimpleName()+" "+c.getSimpleName().toLowerCase()+");");
		bos.println();
		bos.println("		public "+c.getSimpleName()+" get"+c.getSimpleName()+"ById(int "+f[0].getName()+");");
		bos.println();
		bos.println("		public void change"+c.getSimpleName()+"Info("+c.getSimpleName()+" "+c.getSimpleName().toLowerCase()+");");
		bos.println("		public void delete"+c.getSimpleName()+"ById(int "+f[0].getName()+");");
		bos.println("		public List<"+c.getSimpleName()+"> getAll();");
		bos.println("}");
		bos.println();
		bos.flush();
		bos.close();
		System.out.println(c.getSimpleName()+"Service.java 生成成功！");
	}

	public static void writeXml(File file,Class c) throws IOException{
		PrintWriter bos = new PrintWriter(new FileWriter(file));
		Field f[] = c.getDeclaredFields();
		bos.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		bos.println("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" ");
		bos.println("\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
		bos.println();
		bos.println("	<mapper namespace=\""+packageName+".dao."+c.getSimpleName()+"Dao\">");
		bos.println();
		bos.println("		<select id=\"get"+c.getSimpleName()+"ByPage\" resultType=\""+packageName+".module."+c.getSimpleName()+"\">");
		bos.println("			select * from "+c.getSimpleName().toLowerCase()+" order by "+f[0].getName().toLowerCase()+" limit #{page},#{sum}");
		bos.println("		</select>");
		bos.println();
		bos.println("		<select id=\"getPage\" resultType=\""+packageName+".module."+c.getSimpleName()+"\">");
		bos.println("			select * from "+c.getSimpleName().toLowerCase());
		bos.println("		</select>");
		bos.println();
		bos.println("		<insert id=\"add"+c.getSimpleName()+"\">");
		bos.print("			insert into "+c.getSimpleName().toLowerCase()+" values(#{"+c.getSimpleName().toLowerCase()+"."+f[0].getName()+"}");
		for(int i=1;i<f.length;i++){
			bos.print(",#{"+c.getSimpleName().toLowerCase()+"."+f[i].getName()+"}");
		}
		bos.print(")");
		bos.println();
		bos.println("		</insert>");
		bos.println();
		bos.println("		<select id=\"get"+c.getSimpleName()+"ById\" resultType=\""+packageName+".module."+c.getSimpleName()+"\">");
		bos.println("			select * from "+c.getSimpleName().toLowerCase()+" where "+f[0].getName().toLowerCase()+"=#{"+f[0].getName()+"}");
		bos.println("		</select>");
		bos.println();
		bos.println("		<update id=\"change"+c.getSimpleName()+"Info\">");
		bos.print("			update "+c.getSimpleName().toLowerCase()+" set "+f[1].getName().toLowerCase()+"=#{"+c.getSimpleName().toLowerCase()+"."+f[1].getName()+"}");
		for(int i=2;i<f.length;i++){
			bos.print(","+f[i].getName().toLowerCase()+"=#{"+c.getSimpleName().toLowerCase()+"."+f[i].getName()+"}");
		}
		bos.print(" where "+f[0].getName().toLowerCase()+"=#{"+c.getSimpleName().toLowerCase()+"."+f[0].getName()+"}");
		bos.println();
		bos.println("		</update>");
		bos.println();
		bos.println("		<delete id=\"delete"+c.getSimpleName()+"ById\">");
		bos.println("			delete from "+c.getSimpleName().toLowerCase()+" where "+f[0].getName().toLowerCase()+"=#{"+f[0].getName()+"}");
		bos.println("		</delete>");
		bos.println();
		bos.println("</mapper>");
		bos.flush();
		bos.close();
		System.out.println(c.getSimpleName()+"Mapping.xml 生成成功！");

	}

	public static void writeDao(File file,Class c) throws IOException{
		PrintWriter bos1 = new PrintWriter(new FileWriter(file));
		Field f[] = c.getDeclaredFields();
		bos1.println("package "+packageName+".dao;");
		bos1.println();
		bos1.println("import java.util.List;");
		bos1.println();
		bos1.println("import org.apache.ibatis.annotations.Param;");
		bos1.println();
		bos1.println("import "+packageName+".module."+c.getSimpleName()+";");
		bos1.println();
		bos1.println("public interface "+c.getSimpleName()+"Dao {");
		bos1.println();
		bos1.println("	public List<"+c.getSimpleName()+"> get"+c.getSimpleName()+"ByPage(@Param(\"page\")int page,@Param(\"sum\")int sum);");
		bos1.println();
		bos1.println("	public List<"+c.getSimpleName()+"> getPage();");
		bos1.println();
		bos1.println("	public void add"+c.getSimpleName()+"(@Param(\""+c.getSimpleName().toLowerCase()+"\")"+c.getSimpleName()+" "+c.getSimpleName().toLowerCase()+");");
		bos1.println();
		bos1.println("	public List<"+c.getSimpleName()+"> get"+c.getSimpleName()+"ById(@Param(\""+f[0].getName()+"\")int "+f[0].getName()+");");
		bos1.println();
		bos1.println("	public void change"+c.getSimpleName()+"Info(@Param(\""+c.getSimpleName().toLowerCase()+"\")"+c.getSimpleName()+" "+c.getSimpleName().toLowerCase()+");");
		bos1.println();
		bos1.println("	public void delete"+c.getSimpleName()+"ById(@Param(\""+f[0].getName()+"\")int "+f[0].getName()+");");
		bos1.println();
		bos1.println("}");
		bos1.println();
		bos1.flush();
		System.out.println(c.getSimpleName()+"Dao.java 生成成功！");
		bos1.close();

	}
}

