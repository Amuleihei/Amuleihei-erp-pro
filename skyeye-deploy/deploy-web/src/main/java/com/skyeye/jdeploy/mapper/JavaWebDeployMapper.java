package com.skyeye.jdeploy.mapper;

import com.skyeye.jdeploy.domain.JavaWebDeployInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface JavaWebDeployMapper {

	@Select("select uuid,name,url,context_path as contextPath,port,type,start_params as startParams," +
			"git_folder_name as gitFolderName, git_folder_id as gitFolderId from java_web_deploy")
	List<JavaWebDeployInfo> getList();

	@Select("select uuid,name,url,context_path as contextPath,port,type,profile,module,branch,start_params as startParams," +
			"git_folder_name as gitFolderName, git_folder_id as gitFolderId from java_web_deploy where uuid=#{uuid}")
	JavaWebDeployInfo getDetail(String uuid);

	@Insert("insert into java_web_deploy " +
			"(uuid,name,url,context_path,port,type,profile,module,branch,start_params, git_folder_name, git_folder_id) " +
			"values " +
			"(#{uuid},#{name},#{url},#{contextPath},#{port},#{type},#{profile},#{module},#{branch}, #{startParams}, #{gitFolderName}, #{gitFolderId})")
	void insert(JavaWebDeployInfo javaWebDeployInfo);

}