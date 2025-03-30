#!/bin/bash

######################################
## 用于启动Java Jar包的Shell脚本，
## 包含启动、停止、重启和状态查询功能
## # 启动应用
## ./woo-question.sh start
## # 停止应用
## ./woo-question.sh stop
## # 重启应用
## ./woo-question.sh restart
## # 查看状态
## ./woo-question.sh status
#######################################

# 应用配置
JAR_NAME="woo-question-1.0.0.jar"   # 替换为你的jar包名称
JVM_OPTS="-Xms512m -Xmx1024m"    # JVM参数设置
LOG_FILE="woo-question.log"               # 日志文件名称
PID_FILE="woo-question.pid"               # 进程ID存储文件

# 启动应用
start() {
    if [ -f $PID_FILE ]; then
        PID=$(cat $PID_FILE)
        if ps -p $PID > /dev/null; then
            echo "应用程序已在运行中 (PID: $PID)"
            return 1
        fi
    fi

    if [ ! -f $JAR_NAME ]; then
        echo "错误: 未找到Jar文件 [$JAR_NAME]"
        return 1
    fi

    echo "正在启动 $JAR_NAME ..."
    nohup java $JVM_OPTS -jar $JAR_NAME >> $LOG_FILE 2>&1 &
    echo $! > $PID_FILE
    echo "已启动 (PID: $!) | 日志输出: $LOG_FILE"
}

# 停止应用
stop() {
    if [ ! -f $PID_FILE ]; then
        echo "应用程序未运行"
        return 1
    fi
    
    PID=$(cat $PID_FILE)
    echo "正在停止应用程序 (PID: $PID)..."
    kill $PID
    rm -f $PID_FILE
    echo "已停止"
}

# 查询状态
status() {
    if [ -f $PID_FILE ]; then
        PID=$(cat $PID_FILE)
        if ps -p $PID > /dev/null; then
            echo "应用程序正在运行 (PID: $PID)"
            return 0
        else
            echo "找到PID文件但进程不存在"
            rm -f $PID_FILE
        fi
    fi
    echo "应用程序未运行"
}

# 重启应用
restart() {
    stop
    sleep 2
    start
}

# 参数处理
case "$1" in
    start)
        start
        ;;
    stop)
        stop
        ;;
    restart)
        restart
        ;;
    status)
        status
        ;;
    *)
        echo "使用方法: $0 {start|stop|restart|status}"
        exit 1
esac

exit 0