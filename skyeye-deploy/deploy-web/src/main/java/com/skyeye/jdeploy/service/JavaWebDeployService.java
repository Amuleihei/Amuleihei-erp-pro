package com.skyeye.jdeploy.service;

import com.skyeye.jdeploy.domain.JavaWebDeployInfo;
import com.skyeye.jdeploy.mapper.JavaWebDeployMapper;
import com.skyeye.jdeploy.util.ShellUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class JavaWebDeployService {

	private static Logger LOGGER = LoggerFactory.getLogger(JavaWebDeployService.class);

	@Autowired
	private JavaWebDeployMapper javaWebDeployMapper;

	@Value("${IMAGES_PATH}${shell.javawebdeploy}")
	private String shellFileFolder;

	@Value("${javawebdeploy.basepath}")
	private String basePath;

	public List<JavaWebDeployInfo> getList() {
		return javaWebDeployMapper.getList();
	}

	public JavaWebDeployInfo getDetail(String uuid) {
		return javaWebDeployMapper.getDetail(uuid);
	}

	public void insert(JavaWebDeployInfo javaWebDeployInfo) {
		javaWebDeployMapper.insert(javaWebDeployInfo);
	}

	public String getStatus(String uuid) throws IOException {
		JavaWebDeployInfo info = javaWebDeployMapper.getDetail(uuid);
		if(info != null) {
			String out;
			if(org.apache.commons.lang.StringUtils.isNotBlank(info.getGitFolderName())){
				out = ShellUtil.exec("sh " + shellFileFolder + "/isrunning.sh " + info.getGitFolderId());
			}else{
				out = ShellUtil.exec("sh " + shellFileFolder + "/isrunning.sh " + info.getUuid());
			}
			LOGGER.info(out);
			return String.valueOf(StringUtils.hasText(out) && out.contains("java -jar"));
		} else {
			return "false";
		}
	}

	public String deploy(String uuid) throws IOException {
		JavaWebDeployInfo info = javaWebDeployMapper.getDetail(uuid);
		if(info != null) {
			StringBuilder sb = new StringBuilder();

			// kill进程
			sb.append(killShRun(info));
			// 打包
			sb.append(packageShRun(info));

			String finalName = getFinalName(getOldFilePath(info));
			if(finalName != null) {
				FileUtils.copyFile(
					new File(getOldFileNamePath(info, finalName)),
					new File(getNewFilePath(info)));
				// 启动程序
				sb.append(startShRun(info));
			} else {
				sb.append("打包失败");
			}
			LOGGER.info("result is {}", sb.toString());
			return sb.toString();
		} else {
			return uuid + "对应的项目不存在！";
		}
	}

	private String killShRun(JavaWebDeployInfo info) throws IOException {
		StringBuilder execStr = new StringBuilder();
		execStr.append("sh " + shellFileFolder + "/kill.sh");
		if(org.apache.commons.lang.StringUtils.isNotBlank(info.getGitFolderName())){
			execStr.append(" " + info.getGitFolderId());
		}else{
			execStr.append(" " + info.getUuid());
		}

		String killRunStr = ShellUtil.exec(execStr.toString());
		LOGGER.info("killRunStr is {}", killRunStr);
		return killRunStr;
	}

	private String getOldFilePath(JavaWebDeployInfo info){
		String module = "";
		if(StringUtils.hasText(info.getModule())) {
			module = "/" + info.getModule();
		}
		if(org.apache.commons.lang3.StringUtils.isNotEmpty(info.getGitFolderName())){
			return basePath + "/" + info.getUuid() + "/" + info.getGitFolderName() + "/" + module + "/target";
		}else{
			return basePath + "/" + info.getUuid() + module + "/target";
		}
	}

	private String getOldFileNamePath(JavaWebDeployInfo info, String finalName){
		String module = "";
		if(StringUtils.hasText(info.getModule())) {
			module = "/" + info.getModule();
		}
		if(org.apache.commons.lang3.StringUtils.isNotEmpty(info.getGitFolderName())){
			return basePath + "/" + info.getUuid() + "/" + info.getGitFolderName() + "/" + module + "/target/" + finalName;
		}else{
			return basePath + "/" + info.getUuid() + module + "/target/" + finalName;
		}
	}

	private String getNewFilePath(JavaWebDeployInfo info){
		if(org.apache.commons.lang3.StringUtils.isNotEmpty(info.getGitFolderName())){
			return basePath + "/" + info.getUuid() + "/" + info.getGitFolderName() + "/webapps/" + info.getGitFolderId() + ".jar";
		}else{
			return basePath + "/" + info.getUuid() + "/webapps/" + info.getUuid() + ".jar";
		}
	}

	private String packageShRun(JavaWebDeployInfo info) throws IOException {
		String[] execStr = new String[]{"sh " + shellFileFolder + "/package.sh",
			info.getUuid(),
			info.getUrl(),
			basePath,
			String.valueOf(info.getType()),
			(org.apache.commons.lang.StringUtils.isNotBlank(info.getProfile()) ? info.getProfile() : null),
			info.getBranch(),
			info.getGitFolderName()};
		String packageRunStr = ShellUtil.exec(execStr);
		LOGGER.info("pachageStr is {}", packageRunStr);
		return packageRunStr;
	}

	public String restart(String uuid) throws IOException {
		JavaWebDeployInfo info = javaWebDeployMapper.getDetail(uuid);
		if(info != null) {
			StringBuilder sb = new StringBuilder();
			// kill进程
			sb.append(killShRun(info));
			// 启动程序
			sb.append(startShRun(info));
			return sb.toString();
		} else {
			return uuid + "对应的项目不存在！";
		}
	}

	private String startShRun(JavaWebDeployInfo info) throws IOException {
		String[] execStr = new String[]{"sh " + shellFileFolder + "/start.sh",
			info.getUuid(),
			String.valueOf(info.getPort()),
			basePath,
			info.getStartParams(),
			info.getGitFolderName(),
			info.getGitFolderId()};
		String startRunStr = ShellUtil.exec(execStr);
		LOGGER.info("startRunStr is {}", startRunStr);
		return startRunStr;
	}

	public String stop(String uuid) throws IOException {
		JavaWebDeployInfo info = javaWebDeployMapper.getDetail(uuid);
		if(info != null) {
			return killShRun(info);
		} else {
			return uuid + "对应的项目不存在！";
		}
	}

	public String showLog(String uuid) throws IOException {
		JavaWebDeployInfo info = javaWebDeployMapper.getDetail(uuid);
		if(info != null) {
			String[] execStr = new String[]{"sh " + shellFileFolder + "/showlog.sh",
				info.getUuid(),
				basePath,
				info.getGitFolderName(),
				info.getGitFolderId()};
			return org.apache.commons.lang3.StringUtils.join(execStr, " ");
		} else {
			return "echo \"对应的项目不存在！\"";
		}
	}
	
	private String getFinalName(String folderPath) {
		LOGGER.info("folderPath is {}", folderPath);
		File dir = new File(folderPath);
		File[] files = dir.listFiles();
		
		String fileName = null;
		for(File file : files) {
			String name = file.getName();
			if(file.isFile() && name.endsWith(".jar")) {
				fileName = name;
			}
		}
		return fileName;
	}
}
