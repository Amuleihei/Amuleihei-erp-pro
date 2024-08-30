#!/bin/bash

# 启动程序
#
# 参数：
# $1 UUID
# $2 端口号
# $3 项目部署路径
# $4 启动参数
# $5 如果一个git地址上有多个项目，则为目录节点
# $6 如果一个git地址上有多个项目，则为目录节点id

cd $3/$1

echo "***** start 目录节点: $5 *****"
if [[ "$5" != "null" ]] && [[ "$5" != "" ]]; then
  cd $5
fi

cd webapps

echo "***** 开始启动项目 *****"
if [[ "$5" != "null" ]] && [[ "$5" != "" ]]; then
  echo "***** $3/$1/$5/nohup.out *****"
  echo "***** jar path is : $6.jar  *****"
  nohup java -jar $4 $6.jar | /usr/local/sbin/cronolog $3/$1/$5/nohup-$6-`date +%Y-%m-%d`.out 2>&1 &
else
  echo "***** $3/$1/nohup.out *****"
  echo "***** jar path is : $1.jar  *****"
  nohup java -jar $4 $1.jar | /usr/local/sbin/cronolog $3/$1/nohup-$1-`date +%Y-%m-%d`.out 2>&1 &
fi

echo "服务已启动"