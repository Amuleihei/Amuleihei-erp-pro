#!/bin/bash

# SVN检出并Maven打包
#
# 参数：
# $1 UUID
# $2 SVN地址
# $3 项目部署路径
# $4 版本控制系统(1.SVN;2.GIT)
# $5 Maven profile
# $6 Git分支
# $7 如果一个git地址上有多个项目，则为目录节点

if [ -z "$1" ]||[ -z "$3" ]; then
    echo "参数不能为空"
    exit 0
fi

if [ $4 -eq 1 ]
then
	svn checkout $2 .
else
  if [ ! -d "$3/$1" ];then
    echo "工程文件夹不存在，开始创建"
    mkdir -p $3/$1
    # 进入到工程文件夹目录
    echo "*****cd $3/$1*****"
    cd $3/$1
    echo "开始克隆项目"
	  git clone --depth 1 $2 .
	  if [ "$6" != "null" ]; then
        git checkout $6
    fi
	else
	  echo "工程文件夹已存在"
	  # 进入到工程文件夹目录
    echo "*****cd $3/$1*****"
    cd $3/$1
	  echo "开始拉取项目"
	  git pull $2
	  echo "拉取项目结束."
	fi
fi

echo "***** 目录节点: $7 *****"
if [ ! -z "$7" ]; then
  echo "*****cd $7*****"
  cd $7
fi

# 开始打包
echo "*****开始打包*****"
echo "***** Maven profile: $5 *****"
if [ "$5" != "null" ]; then
    mvn clean package -Dmaven.test.skip=true -P$5
else
    mvn clean package -Dmaven.test.skip=true
fi
