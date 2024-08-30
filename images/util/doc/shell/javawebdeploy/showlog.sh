#!/bin/bash

# 查看日志
#
# 参数：
# $1 进程UUID
# $2 项目部署路径
# $3 如果一个git地址上有多个项目，则为目录节点
# $4 如果一个git地址上有多个项目，则为目录节点id
echo "***** start 目录节点: $3 *****"
if [[ "$3" != "null" ]] && [[ "$3" != "" ]]; then
  echo "***** 进入 $3 *****"
  tail -f -n 500 $2/$1/$3/nohup-$4-`date +%Y-%m-%d`.out
else
  echo "***** 进入 $1 *****"
  tail -f -n 500 $2/$1/nohup-$1-`date +%Y-%m-%d`.out
fi